-----------------------------
Javafx						 |
-----------------------------
	# �ο���ַ
		https://openjfx.io/
		www.javafxchina.net/main/
		https://www.javafxdeveloper.com/
	
	# JavaFX�ܹ�����̬ϵͳ�ĸ߽���ͼ
		* ����ͼ(Scene Graph)
		* JavaFX���ܵĹ���API(Java Public APIs for JavaFX Features)
		* ͼ��ϵͳ(Graphics System)
		* Glass���幤�߰�(Glass Windowing Toolkit)
		* ��ý���ͼ��(Media and Images)
		* Web���(Web Component)
		* CSS
		* UI�ؼ�(UI Controls)
		* ����(Layout)
		* 2-D��3-Dת��(2-D and 3-D Transformations)
		* �Ӿ���Ч(Visual Effects)
	
	# JavaFXӦ�ó�������ṹ
		* JavaFXӦ�ó����������Ҫ�̳��� application.Application ��,��д��start()����������JavaFXӦ�ó�������
		* JavaFXӦ�ó���UI��������Ϊ��̨(Stage)�볡��(Scene),Stage����JavaFX��������,Scene�����������ݵ�����

		* ��JavaFX��,Scene�е����ݻ�����ͼ�νڵ�(Node)���ɵķֲ㳡��ͼ(Scene Graph)��չ��
		* �ṹͼ
			stage
				scene
					node


		* ��JavaFXӦ�ó�����ͨ��JavaFX Packager���ߴ��ʱ,main()�����Ͳ��Ǳ���ĵ���,��ΪJavaFX Package���߻ὫJavaFX LauncherǶ�뵽JAR�ļ���
		* ���Ǳ���main()�������Ǻ����õ�,�����������в�����JavaFX Launcher��JAR�ļ�,������ʹ��ĳЩû�н�JavaFX������ȫ���ɽ�ȥ��IDEʱ,����Ƕ����JavaFX�����SwingӦ�ó�������Ҫmain()����
	
	# Hello World
		import javafx.application.Application;
		import javafx.stage.Stage;

		public class Main extends Application {
			public static void main(String[] args) {
				Applicationl.launch(args);
			}
			@Override
			public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Hello World!");
				primaryStage.show();
			}
		}
	
		