------------------------------------
BeanPostProcessor					|
------------------------------------
	# Bean ���������е�һ���ӿ�
	# ���Beanʵ���˸ýӿ�,��ApplicationContext�رյ�ʱ��,����ó��󷽷�
		public interface DisposableBean {
			void destroy() throws Exception;
		}
