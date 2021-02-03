----------------------
�۲���
----------------------
	# ���� Observable, �۲��� Observer
		* �ܵ���˵���ǣ�����۲���ע�ᵽһ������
		* ����ͨ���㲥���������еĹ۲�����������
	
	# Demo
		import java.util.Observable;
		import java.util.Observer;

		class ObservableImpl extends Observable {
			@Override
			public synchronized void setChanged() {
				super.setChanged();
			}
			@Override
			public synchronized void clearChanged() {
				super.clearChanged();
			}
		}

		public class Main {
			public static void main(String[] args) throws InterruptedException {
				
				ObservableImpl observable = new ObservableImpl();
				
				Observer ob1 = new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						System.out.println("����ob1�����յ�����Ϣ:" + arg);
						o.deleteObserver(this); // ob1 �յ�һ����Ϣ�󣬾�ȡ��ע�ᣬ���ټ���
					}
				};
				Observer ob2 = new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						System.out.println("����ob2�����յ�����Ϣ:" + arg);
					}
				};
				
				// ��ӹ۲���
				observable.addObserver(ob1);
				observable.addObserver(ob2);
				
				// ���á�״̬�Ѿ��ı䡱
				observable.setChanged();
				// �㲥����
				observable.notifyObservers("��һ������");
				
				// ���á�״̬�Ѿ��ı䡱
				observable.setChanged();
				// �㲥����
				observable.notifyObservers("�ڶ�������");
			}
		}