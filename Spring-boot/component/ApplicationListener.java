--------------------------------
Application��Event�¼�			|
--------------------------------
	# Spring �ĺ����� ApplicationContext,��������� beans ��������������

	# ������ beans ʱ,ApplicationContext ����ĳЩ���͵��¼�
		* ������������ʱ,ContextStartedEvent ����
		* ��������ֹͣʱ,ContextStoppedEvent ����

	# ͨ�� ApplicationEvent ��� ApplicationListener �ӿ����ṩ�� ApplicationContext �д����¼�
	# ���һ�� bean ʵ�� ApplicationListener,��ôÿ�� ApplicationEvent �������� ApplicationContext ��,�Ǹ� bean �ᱻ֪ͨ
	# Spring �ṩ�����µı�׼�¼�
		1,ContextRefreshedEvent
			* ApplicationContext ����ʼ����ˢ��ʱ,���¼�������
			* ��Ҳ������ ConfigurableApplicationContext �ӿ���ʹ�� refresh() ����������

		2,ContextStartedEvent
			* ��ʹ�� ConfigurableApplicationContext �ӿ��е� start() �������� ApplicationContext ʱ,���¼�������
			* ����Ե���������ݿ�,����������ڽ��ܵ�����¼��������κ�ֹͣ��Ӧ�ó���

		3,ContextStoppedEvent
			* ��ʹ�� ConfigurableApplicationContext �ӿ��е� stop() ����ֹͣ ApplicationContext ʱ,��������¼�
			* ������ڽ��ܵ�����¼�������Ҫ������Ĺ���

		4,ContextClosedEvent
			* ��ʹ�� ConfigurableApplicationContext �ӿ��е� close() �����ر� ApplicationContext ʱ,���¼�������
			* һ���ѹرյ������ĵ�����������ĩ�ˣ������ܱ�ˢ�»�����

		5,RequestHandledEvent
			* ����һ�� web-specific �¼�,�������� bean HTTP �����Ѿ�������
	# ����
		1,�Զ�����ʵ�� ApplicationListener<E extends ApplicationEvent>
		2,���ݷ���:ApplicationEvent������Ҫ�������¼�
		3,�Ѹ������IOC
	
	# Demo
		public class CStartEventHandler implements ApplicationListener<ContextStartedEvent>{
			public void onApplicationEvent(ContextStartedEvent event) {
				System.out.println("ContextStartedEvent Received");
			}
		}
	
	# ����ϵͳԤ������¼�
		ContextRefreshedEvent
		ServletWebServerInitializedEvent
		ApplicationStartedEvent
		ApplicationReadyEvent
		PayloadApplicationEvent


--------------------------------
�Զ����¼�,�뷢��				|
--------------------------------
	# �¼�����ʵ�ֽӿ�:ApplicationEvent
		import org.springframework.context.ApplicationEvent;
		public class MyEvent extends ApplicationEvent {
			private static final long serialVersionUID = -2362115341823186646L;
			
			// ���캯����������һ���¼�����,�����ڼ������л�ȡ������¼�����
			public MyEvent(Object source) {
				super(source);
			}
		}
	

	# ʵ�ּ������ӿ�:
		import org.springframework.context.ApplicationListener;
		import org.springframework.stereotype.Component;

		@Component
		public class SpringApplcationListenner implements ApplicationListener<MyEvent>{

			@Override
			public void onApplicationEvent(MyEvent event) {
				System.err.println(event.getSource() + ":" + event.getClass().getName());
			}
		}

		* ���͵Ķ���,���������Զ�����¼�����
	
	# ʹ�� ApplicationContext ���������ķ����¼�
		ApplicationContext applicationContext = SpringApplication.run(PasteApplication.class,args);
		// ͨ�� publishEvent(); �����¼�
    	applicationContext.publishEvent(new MyEvent("��ð��������¼�"));
	
	
	# �¼�������,ֻҪ�Ƿ��Ϸ��Ͷ���ļ�����(��������),���Ჶ���¼�
		import org.springframework.boot.context.event.ApplicationReadyEvent;
		import org.springframework.context.ApplicationEvent;
		import org.springframework.stereotype.Component;
		

		// ������û�з���,�����ͻᲶ������ʵ���� ApplicationEvent �ӿڵ��¼�����
		@Component
		public class ApplicationListener implements org.springframework.context.ApplicationListener.ApplicationListener{

			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.err.println(event.getSource() + ":" + event.getClass().getName());
			}
		}

	
