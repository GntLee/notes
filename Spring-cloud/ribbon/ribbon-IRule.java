--------------------
IRule				|
--------------------
	# ���ؾ�����㷨����Ҫʵ�� IRule �ӿ�
	# �Զ�����ѯʵ��,һ�㶼ֱ�Ӽ̳�: AbstractLoadBalancerRule
	# Ĭ�ϵ��㷨(ʵ��)
		RoundRobinRule
			* ��ѯ,����ѯ���ķ����ܵ���ʱ,���׳��쳣

		RandomRule
			* ���

		AvailabilityFilteringRule
			* ���ȹ������ڶ�η��ʹ��ϴ��ڶ�·����բ״̬�ķ���
			* ���в�����������������ֵ�ķ���,Ȼ��ʣ������б�����ѯ���Խ��з���

		WeightedResponseTimeRule
			* ����ƽ����Ӧʱ��������з����Ȩ��,��Ӧʱ��Խ�����Ȩ��Խ��
			* ���������,ͳ����Ϣ����,��ʹ�� RoundRobinRule ����,��ͳ����Ϣ�㹻��,���л��ش�Rule

		RetryRule
			* �Ȱ��� RoundRobinRule ���Ի�ȡ����,�����ȡ����ʧ������ָ��ʱ���ڽ�������
			* ��ʵ�͸� RoundRobinRule һ����,�����������ܽ��е��õ�ʱ��,������ѡ��һ������

		BestAvailableRule
			* �����ȹ��˵����ڶ�η��ʹ��϶����ڶ�·����բ״̬�ķ���,Ȼ��ѡ��һ����������С�ķ���

		ZoneAvoidanceRule
			* �����ж�Server������������ܺ�Server�Ŀ�����ѡ�������


--------------------
�޸ĸ����㷨		|
--------------------
	# ע��ȫ��ͨ�õ��㷨ʵ��,ֻ��Ҫ��ioc��ע��ָ�����㷨ʵ���༴��
		@Bean
		public IRule iRule(){
			//����Ĭ�ϵĸ��ؾ����㷨
			return RandomRule();
		}
	
	# ���ָ���ķ���,������ѯ�㷨
		1,����������,����װ���Զ�����㷨ʵ�ֵ�ioc
			//��Ҫ���@Configuration
			public class MyRule(){
				@Bean
				public IRule myRule(){
					return new RandomRule();
				}
			}
		
		2,ʹ�� @RibbonClient ��Ϊָ���ķ������ø����㷢��ʵ��
			@RibbonClient(name = "USER",configuration = MyRule.class)
			@SpringBootApplication
			class Application{
				
			}

		* ע��,�����ڹٷ��ľ���
		* �Զ����������(MyRule),���ܷ��� @CompentScan ��ɨ��İ��Լ��Ӱ���
		* �����Զ�������������,�ͻᱻ���� Ribbon �ͻ���������,Ҳ�ʹﲻ�����ⶨ�ƻ���Ŀ����

		* �ܺ����:'��������MyRule�����ɨ�����ioc,��ô�͸����������һ��,ֱ�Ӿ���Ribbon��Ĭ���㷨ʵ����'
		* �������������ĳ�������ض����㷨ʵ����,���ܱ�ioc����(ֱ��ɾ�� @Configuration ע��,Ȼ������Զ����㷨ʵ�ַ����ﶼ��)

		
		* @RibbonClient Դ��
			public @interface RibbonClient {
				String value() default "";
				String name() default "";
				Class<?>[] configuration() default {};
				//�е��벻ͨ,Ϊɶ�����configuration��ֱ���� IRule ��������,��Ҫ�� Confuguration ��?
			}
	
	# ���һ���ķ���,���岻ͬ����ѯ�㷨
		* ʹ�� @RibbonClients ע��
		* Դ��,һ���Ͷ���ô����
			public @interface RibbonClients {
				//������ @RibbonClient 
				RibbonClient[] value() default {};
				//����Ĭ�ϵ��㷨ʵ��,��� @RibbonClient û���㷨ʵ��,���������ȡ
				Class<?>[] defaultConfiguration() default {};
			}
	




