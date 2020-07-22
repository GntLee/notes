
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class RedPacketHelper {
	
	/**
	 * ����������������һ�������ֱ�ӷ���ʣ����
	 * @param amount	�ܽ��
	 * @param number	����ܸ���
	 * @return
	 */
	protected static Long randomAmount(Long amount, Integer number) {
		/** ------------ ����У�鿪ʼ ------------- **/
		if (amount < 1) {
			throw new IllegalArgumentException("�������С��1��Ǯ��");
		}
		if (number < 1) {
			throw new IllegalArgumentException("�����������С��1��");
		}
		if (amount < number) {
			if (number != null) {
				throw new IllegalArgumentException(String.format("�Ƿ��ĺ��������%s Ԫ���ָܷ� %s ����", format(fenToYuan(amount)), number));
			}
		}
		/** ------------ ����У����� ------------- **/
		
		// ���1������������ܽ��
		if (number == 1) {
			return amount;
		}
		
        // ���������������
        // ����� = (�ܽ�� / �ܸ���)(ƽ��ֵ���С��) * 2
        BigDecimal maxValue = new BigDecimal(amount).divide(new BigDecimal(number), 0, RoundingMode.DOWN).multiply(new BigDecimal(2));
        
        // ��ȡ 0% - 99% �������ֵ
        // 0.00 - 0.99
        BigDecimal random = new BigDecimal(ThreadLocalRandom.current().nextInt(100)).divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
        
        // �����ȡ���(���������С��λ)
        long randomValue = maxValue.multiply(random, MathContext.UNLIMITED).longValue();

        return randomValue == 0 ? 1: randomValue;
	}
	
	/**
	 * ���������Ľ��ֲ�
	 * @param amount	�����1Ԫ����λ�Ƿ�
	 * @param number	������������1��
	 * @param mines		�׵�š����Ϊnull����ʾ����׵�
	 */
	public static LinkedList<Long> scatter(Long amount, Integer number, Integer[] mines) {

		/** ------------ ����У�鿪ʼ ------------- **/
		if (amount < 100) {
			throw new IllegalArgumentException("�������С��1��Ǯ");
		}
		if (number < 1) {
			throw new IllegalArgumentException("�����������С��1��");
		}
		if (mines != null ) {
			if (mines.length >= number) {
				throw new IllegalArgumentException("�׵���������С�ڷ�������");
			}
			for (Integer mine : mines) {
				if (mine < 0 || mine > 9) {
					throw new IllegalArgumentException("�׵��ֻ���ǣ�0 - 9"); 
				}
			}
		}
		/** ------------ ����У����� ------------- **/
		
		LinkedList<Long> scatter = new LinkedList<>();
		
		
		// ��Ҫ�ֶ��Ŀ����׵�
		if (mines != null) {
			
			for (Integer mine : mines) {
				
				// �����������
				Long randomAmount = randomAmount(amount, number);
				
				// β��
				Integer last = (int) (randomAmount % 10);
				
				// �׵㲹��
				if (mine !=- last) {
					randomAmount += mine - last;
					/**
					 *  ����׺���0�����Һ�����С��1��(0.01 - 0.09)������ļ���ᵼ�º�����ֱ��Ϊ0��
					 *  ����ʽ�� ֱ�Ӵ���Ϊ1ëǮ
					 */
					if (randomAmount == 0) {
						randomAmount = 10L;
					}
				}
				
				// �׺���ӵ�����ͷ
				scatter.addFirst(randomAmount);
				
				// �ܽ�����
				amount -= randomAmount;
				// �������������
				number --;
			}
		}
		
		// ���������ͨ�Ľ��
		while (number > 0) {
			Long randomAmount = randomAmount(amount, number);
			scatter.addLast(randomAmount);
			amount -= randomAmount;
			number --;
		}
		
		return scatter;
	}
	
    public static Double fenToYuan(Long fen) {
    	try {
    		return new BigDecimal(fen).divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY).doubleValue();
    	}catch (ArithmeticException e) {
    		throw e;
		}
    }
    
    public static String format(Double value) {
    	return new DecimalFormat("#.##").format(value);
    }
	
	public static void main(String[] args) {
		for (int x = 0 ;x < 1000000 ;x ++) {
			
			// Long amount = ThreadLocalRandom.current().nextLong(100, 100 * 1000);
			
			// ��� 1 - 999��Ǯ���������
			Long amount = ThreadLocalRandom.current().nextLong(1, 1000) * 100;
			// ���2 - 10����
			Integer number = ThreadLocalRandom.current().nextInt(2, 10);
			
			// ����׵㣨0 - (���� - 1)��
			Integer[] mines = new Integer[ThreadLocalRandom.current().nextInt(number - 1)];
			
			for (int i = 0; i < mines.length ; i ++) {
				mines[i] = ThreadLocalRandom.current().nextInt(10);
			}
			
			LinkedList<Long> result = scatter(amount, number, mines);
			System.out.print("�ܽ�" + fenToYuan(amount).longValue() + " Ԫ �ܸ�����" + number);
			
			System.out.print(" �׵�ֲ���[");
			for (Integer mine : mines) {
				System.out.print(mine + ", ");
			}
		
			System.out.print("]");
			System.out.print(" ����ֲ���[");
			for (Long val : result) {
				System.out.print(format(fenToYuan(val)) + ", ");
				if (val <= 0) {
					throw new RuntimeException("�쳣�����������0���߸�С�Ľ��");
				}
			}
			
			Long total = result.stream().reduce((t, u) -> {
				return t + u;
			}).get();
			
			boolean success = total.equals(amount);
			
			if (!success) {
				throw new RuntimeException("�쳣����ƥ��");
			}
			
			System.out.println("] �ܽ���Ƿ�ƥ�䣺" + success);
		}
	}
}

