-------------------------
��̬��չ����
-------------------------
	# ��ʹ��spring boot������ʱ����ʱ������Ҫ�Զ��廷���������߱�д��������չ�㣬
	# ����ʹ�� EnvironmentPostProcessor (ֻ�ǻ�����ʹ�û������Ͳ���Ҫ)����spring�����Ĺ���֮ǰ��������һЩϵͳ����

	# ���ҽ���ʵ�������  org.springframework.core.Ordered ע�⣬���ʵ��ʵ��Ҳ�ᰴ��@Orderע���˳��ȥ������
	
	# ��Ҫʵ�ֽӿ� EnvironmentPostProcessor
		@FunctionalInterface
		public interface EnvironmentPostProcessor {
			void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application);
		}
	
	# �� META-INF/spring.factories �ļ�������
		org.springframework.boot.env.EnvironmentPostProcessor=[ʵ����ȫ·��]