-----------------------------------
BeanPostProcessor				   |
-----------------------------------
	# Bean ���������е�һ���ӿ�

	# ������������,Spring��Bean��ʼ��֮ǰ,��ʼ��֮������ø�ʵ��,����Bean����'����Processor'
	
		import org.springframework.beans.BeansException;
		import org.springframework.beans.factory.config.BeanPostProcessor;

		@Component
		public class ApplicationBeanPostProcessor implements BeanPostProcessor {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				// �ڳ�ʼ��֮���Bean���д���
				return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
			}
			
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				// �ڳ�ʼ��֮ǰ��Bean���д���
				return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
			}
		}
