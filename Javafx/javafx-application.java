-------------------------
application������������ʽ|
-------------------------
	# �̳� Application, ��main��������
		public class Main extends Application {
			public static void main(String[] args) {
				launch(args);  // ���� Application �ľ�̬����������
			}
			@Override  
			public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Hello World!");
				primaryStage.show();
			}
		}
	
	# ͨ��main�������� Application ʵ����
		// �̳� Application
		public class ApplicationImpl extends Application {
			@Override
				primaryStage.setTitle("Hello World!");
				primaryStage.show();
			}
		}

		public class Main  {
			public static void main(String[] args) {
				// ��main������ָ��ʵ�����class
				Application.launch(ApplicationImpl.class, args);
			}
		}
		

-------------------------
application����������	 |
-------------------------
	# ������ǰִ��
		void init()
	
	# �ڹر�ǰִ��
		void stop()
	

-------------------------
ui �߳�					 |
-------------------------
	# �����߳�: JavaFX-Launcher
		* ���߳����������߳�, ����ִ�� init() �������߳�


	# UI�߳�: JavaFX Application Thread
		* Ϊ�˲�����, JavaFX-Launcher�̻߳�����һ��UI�߳�������javafx
	

	

