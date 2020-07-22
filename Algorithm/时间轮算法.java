------------------------
ʱ�����㷨				|
------------------------
	# ��ʱ�����������,һ���ǿ������߳�,��һ���ǿ�һ���������Ȼ����ɨ��
		* �Զ��׼������ַ�ʽ�ı׶˺�����,ǰ�߶��߳����Ĺ���,���߶�ʱ�����Ĺ���(�ܶ�δ��ʱ�������ᱻ����ظ�ɨ����������)



------------------------
�̶�ʱ���ʱ�����㷨	|
------------------------
	# ����ʱ���ֶ����ʱ��ָ��ʱ���ֵĹ���ʱ�������
	# ������񣬵������ʱ��󣬴������ڼ���

package io.javaweb.example.algorithm.timewheel;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TimingWheel<E> {

	// ���ڼ���
	public static interface ExpirationListener <E> {
		void expired(E expiredObject);
	}

	
	// һ��tick�ĳ���ʱ��
	private final long tickDuration;
	
	// tick����
	private final int ticksPerWheel;
	
	// ��ǰtick��ָ��
	private volatile int currentTickIndex = 0;

	// ���ڵ�����
	private final CopyOnWriteArrayList<ExpirationListener<E>> expirationListeners = new CopyOnWriteArrayList<ExpirationListener<E>>();
	
	// �̶Ȳ��
	private final ArrayList<Slot<E>> wheel;
	
	// ���� �� �������ڲ�۵�ӳ�䡣ȫ��ָ��
	private final Map<E, Slot<E>> indicator = new ConcurrentHashMap<E, Slot<E>>();

	// �Ƿ�shutdown
	private final AtomicBoolean shutdown = new AtomicBoolean(false);
	
	// ��д��
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	// �����߳�
	private Thread workerThread;

	/**
	 * @param tickDuration ÿһ�̶�ʱ��
	 * @param ticksPerWheel �ж��ٸ��̶�
	 * @param timeUnit tickDuration ��ʱ�䵥λ
	 */
	public TimingWheel(int tickDuration, int ticksPerWheel, TimeUnit timeUnit) {
		if (timeUnit == null) {
			throw new NullPointerException("unit");
		}
		if (tickDuration <= 0) {
			throw new IllegalArgumentException("tickDuration must be greater than 0: " + tickDuration);
		}
		if (ticksPerWheel <= 0) {
			throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + ticksPerWheel);
		}
		
		// ʵ�������
		this.wheel = new ArrayList<Slot<E>>();
		
		// ת��ʱ��Ϊ����
		this.tickDuration = TimeUnit.MILLISECONDS.convert(tickDuration, timeUnit);

		// �̶����� + 1
		this.ticksPerWheel = ticksPerWheel + 1;

		for (int i = 0; i < this.ticksPerWheel; i++) {
			
			// ʵ�������
			wheel.add(new Slot<E>(i));
		}
		
		// �Ƴ��հ׵ĵ�λ
		wheel.trimToSize();

		// ʵ���������߳�
		workerThread = new Thread(new TickWorker(), "Timing-Wheel");
	}

	public void start() {
		if (shutdown.get()) {
			throw new IllegalStateException("Cannot be started once stopped");
		}

		if (!workerThread.isAlive()) {
			// ���������߳�
			workerThread.start();
		}
	}

	public boolean stop() {
		if (!shutdown.compareAndSet(false, true)) {
			// ֹͣʧ��
			return false;
		}

		// ��ǰִ���߳��жϱ�ʶ
		boolean interrupted = false;
		
		while (workerThread.isAlive()) {
			// �����ж������߳�
			workerThread.interrupt();
			try {
				// ��ǰ�߳�����100���룬ֱ�������̴߳���������ж��쳣
				workerThread.join(100);
			} catch (InterruptedException e) {
				interrupted = true;
			}
		}
		if (interrupted) {
			Thread.currentThread().interrupt();
		}

		return true;
	}

	public void addExpirationListener(ExpirationListener<E> listener) {
		expirationListeners.add(listener);
	}

	public void removeExpirationListener(ExpirationListener<E> listener) {
		expirationListeners.remove(listener);
	}

	public long add(E e) {
		synchronized (e) {
			
			// �ж��Ƿ��Ѿ���ӹ�,����Ѿ���ӹ��������Ƴ�
			checkAdd(e);
			
			// ��һ��ָ���λ�ã���Ϊ�����������������ӵ�����棩
			int previousTickIndex = getPreviousTickIndex();
			
			// ��ȡ��һ��ָ��λ�õĶ���
			Slot<E> slot = wheel.get(previousTickIndex);
			
			// ���Ԫ�ص�����
			slot.add(e);
			
			// �������Ͳ�۵�ӳ���ϵ
			indicator.put(e, slot);
			
			// ������� * ÿ����۵�ִ��ʱ�� = ִ����Ҫ�ȴ���ʱ��
			return (this.ticksPerWheel - 1) * this.tickDuration;
		}
	}

	private void checkAdd(E e) {
		Slot<E> slot = indicator.get(e);
		if (slot != null) {
			slot.remove(e);
		}
	}

	/**
	 * ��ȡ��һ�ο̶�ָ���λ�ã�Ҳ���ǵ�ǰʱ���֣����ִ�е�һ����ۣ�
	 * @return
	 */
	private int getPreviousTickIndex() {
		// ������
		lock.readLock().lock();
		try {
			int cti = this.currentTickIndex;
			if (cti == 0) {
				// �����ǰָ���λ����0����ָ�����һ���̶�
				return this.ticksPerWheel - 1;
			}
			return cti - 1;
		} finally {
			lock.readLock().unlock();
		}
	}

	public boolean remove(E e) {
		synchronized (e) {
			Slot<E> slot = indicator.get(e);
			if (slot == null) {
				return false;
			}

			indicator.remove(e);
			return slot.remove(e) != null;
		}
	}

	private void notifyExpired(int idx) {
		
		// ��ȡ��ǰ��۵�Slot
		Slot<E> slot = wheel.get(idx);
		
		// ��ȡ����Slot�е�����Set
		Set<E> elements = slot.elements();
		
		for (E e : elements) {
			// ��Slot���Ƴ�����
			slot.remove(e);
			synchronized (e) {
				// ��ָʾ����ȡ���������
				Slot<E> latestSlot = indicator.get(e);
				if (latestSlot.equals(slot)) {
					// �Ƴ�������ϵ
					indicator.remove(e);
				}
			}
			
			for (ExpirationListener<E> listener : expirationListeners) {
				// ѭ�����������¼�
				listener.expired(e);
			}
		}
	}

	/**
	 * ʱ�����߳�
	 * @author Administrator
	 */
	private class TickWorker implements Runnable {

		// ����ʱ��
		private long startTime;

		// ʱ�����ƶ�����
		private long tick;

		@Override
		public void run() {
			// ʱ���ֿ�ʼʱ��
			this.startTime = System.currentTimeMillis();
			
			this.tick = 1;

			// ��ʼת��ʱ����
			for (int i = 0; !shutdown.get(); i++) {
				if (i == wheel.size()) {
					// ָ�뵽��ĩβ������Ϊ0
					i = 0;
				}
				lock.writeLock().lock();
				try {
					// ͬ������ָ��λ��
					currentTickIndex = i;
				} finally {
					lock.writeLock().unlock();
				}
				
				// ���ѵ�ǰָ��ĵ�������
				notifyExpired(currentTickIndex);
				
				// �ȴ���һ��ѭ��
				waitForNextTick();
			}
		}
		
		// �ƶ�����һ���̶�
		private void waitForNextTick() {
			for (;;) {
				// ��ǰʱ��
				long currentTime = System.currentTimeMillis();
				
				/*
				 	��Ҫ��ȥ�̵߳�ִ�к�ʱ
					ÿ���̶Ⱥ�ʱ * ʱ�����ƶ����� = �ӵ�һ��ִ�У�����������Ҫ����ͣʱ�� 
					��ǰʱ�� - ��ʼ��ʱ��		= �ӵ�һ��ִ�У����������ĵ�ʱ��
				*/
				// �߳���ͣʱ�� = ÿ���̶Ⱥ�ʱ * ʱ�����ƶ����� - (��ǰʱ�� - ��ʼ��ʱ��)
				long sleepTime = tickDuration * this.tick - (currentTime - this.startTime);

				if (sleepTime <= 0) {
					break;
				}

				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					return;
				}
			}

			// ת������ + 1
			this.tick++;
		}
	}

	private static class Slot<E> {

		private int id;
		//����ȫ��HashMap����Set����
		private Map<E, E> elements = new ConcurrentHashMap<E, E>();

		public Slot(int id) {
			this.id = id;
		}

		public void add(E e) {
			elements.put(e, e);
		}

		public E remove(E e) {
			return elements.remove(e);
		}

		public Set<E> elements() {
			return elements.keySet();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("rawtypes")
			Slot other = (Slot) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Slot [id=" + id + ", elements=" + elements + "]";
		}
	}
}
