----------------------------
װ�������ģʽ				|
----------------------------
	# ûɶ��˵��,��
		abstract class Beverage {		
			// ����
			private static final String defaultName = "unknow";
			public String getName() {
				return defaultName;
			}
			abstract int cost();
		}
		abstract class CondimentDecorator extends Beverage {	// ����
			public abstract String getName();	
		}

		class Coffee extends Beverage {		// ����
			@Override
			public String getName() {
				return "����";
			}
			@Override
			public int cost() {
				return 15;
			}
		}
		class Cola extends Beverage {		// ����
			@Override
			public String getName() {
				return "����";
			}
			@Override
			public int cost() {
				return 10;
			}
		}
		class Coriander extends CondimentDecorator {	// ���
			private Beverage beverage; 
			public Coriander(Beverage beverage) {
				this.beverage = beverage;
			}
			@Override
			public String getName() {
				return "���";
			}
			@Override
			public int cost() {
				return 2 + this.beverage.cost();
			}
		}
		class Mustard extends CondimentDecorator {		// ��ĩ
			private Beverage beverage; 
			public Mustard(Beverage beverage) {
				this.beverage = beverage;
			}
			@Override
			public String getName() {
				return "��ĩ";
			}
			@Override
			public int cost() {
				return 1 + this.beverage.cost();
			}
		}
		public class Main {
			public static void main(String[] args) throws InterruptedException {
				Beverage beverage = new Coffee();	//	����
				beverage = new Coriander(beverage);	// 	�� ���
				beverage = new Mustard(beverage);	// 	�� ��ĩ
				System.out.println(beverage.cost()); // 18��Ǯ
			}
		}

