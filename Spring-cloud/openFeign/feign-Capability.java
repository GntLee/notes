-------------------------
Capability
-------------------------
	# ����֧��
		* Feign ���ܹ����˺��� Feign ������Ա�����޸���Щ���
			���磬���ܿ��Ի�ȡClient��װ����������װ�κ��ʵ�����ظ� Feign
		
			@Configuration
			public class FooConfiguration {
				@Bean
				Capability customCapability() {
					return new CustomCapability();
				}
			}
		
		* ����һ������Capability bean �������Ƿ����� @FeignClient �����У������޸Ŀͻ��˵���Ϊ
	
	

