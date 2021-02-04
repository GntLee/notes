-------------------------------
����ģʽ
-------------------------------
	# ����ģʽ
		// ����ӿ�
		interface Command {
			void execute();
		}
		class Car implements Command{		// ��
			public void run() {
				System.out.println("car is runing...");
			}
			@Override
			public void execute() {
				this.run();
			}
		}

		class Control {		// ������
			private Command command;
			public Control(Command command) {
				this.command = command;
			}
			public void control() {
				this.command.execute();
			}
		}

		public class Main {
			public static void main(String[] args) throws InterruptedException {
				Control control = new Control(new Car());
				control.control();  // car is runing...
			}
		}