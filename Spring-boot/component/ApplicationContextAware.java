------------------------------
ApplicationContextAware		  |
------------------------------
	# Spring����øýӿڵ�ʵ��,��ApplicatioContextע�����
		
		public interface ApplicationContextAware extends Aware {
			void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
		}
	
