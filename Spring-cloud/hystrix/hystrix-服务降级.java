--------------------
���񽵼�			|
--------------------
	# ����Ľ�������,���ڿͻ�����ɵ�

	# �Զ�����ʵ�ֽӿ�:FallbackFactory

		public interface FallbackFactory<T> {
			T create(Throwable cause);
		}
		
		* T����,������Ҫ�۶ϵĵ�
		* T�������Ϊ��Service,��ô������᷵��һ��T,���ص����T�����з���,�����۶ϵ�T���۶Ϸ���
		* ͨ�����:����Ҹ�T,�ҷ���һ��T����,������ҵ�T�����۶��¼�,�򴥷��ҷ��ظ����T�Ķ�Ӧ�ķ���
		
		* ��ʵ���಻Ҫ�������: @Component ע��,��������Ҫ��Spring��ioc����
	
	# �� @FeignClient ���fallbackFactory������ֵ
		@FeignClient(value = "USER",fallbackFactory = UserFallbackFactoryImpl.class)

		*  fallbackFactory������ֵΪ,FallbackFactory��ʵ����
	
	# ��application�����ļ��������
		feign.hystrix.enabled=true
	
	
	# ��ʵ���ַ�ʽ,���ڿͻ�����ɵ��۶�,���ַ���˶�û����Ӧ��,���Խ��������Ӧ
	# ����ͻ���ִ�����۶�,��ô�÷���ᱻ����
	
		
	
