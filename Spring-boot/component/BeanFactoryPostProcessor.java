------------------------------------
BeanFactoryPostProcessor			|
------------------------------------
	# bean������bean���Դ�������,���Թ���bean���������е�beandefinition(δʵ����)���ݿ��������������޸�����

		import org.springframework.beans.BeansException;
		import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
		import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
		import org.springframework.stereotype.Component;

		@Component
		public class ApplicationBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

			@Override
			public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
			}
		}
