-------------------------
hystrix					 |
-------------------------
	# �۶���,��Ҫ��������ṩ��, ������۶�,����Ľ���
	# ���΢����֮�����������,��֮ΪΪ:�ȳ�
		* ����ȳ�����,ĳ��΢������Ӧʱ�����,���߲�����,��ôϵͳ��ִ�й��̾ͻ�����,��������ϵͳѩ��
		
	# Hystrix��ר�����ڴ���ֲ�ʽϵͳ���ӳٺ��ݴ�
		* Hyxstri��֤��һ��������������������,���ᵼ���������ʧ��,���⼶������
	
	
	# ��·��������һ�ֿ��ؿ���
		* ��ĳ������Ԫ��������֮��,ͨ����·���Ĺ��ϼ��(�����ڱ���˿),����÷�����һ������Ԥ��,�ɴ���ı�ѡ��Ӧ
		* �����ǳ�ʱ��ĵȴ������׳����÷��޷�������쳣
		* �Ӷ��������̲߳��ᱻ��ʱ��,����Ҫ��ռ��,�Ӷ������˹����ڷֲ�ʽϵͳ�е�����,����ѩ��


	# �����۶�
		* ��ĳ��΢���񲻿���,������Ӧʱ�����,����з��񽵼�,�����۶ϸ÷���ĵ���,������Ӧ������Ϣ
		* ����⵽�ýڵ�΢������Ӧ������ָ�������·
	
	# Ĭ��5����,20�ε���ʧ��,�ͻ������۶ϻ���

	# Ĭ�ϵĳ�ʱʱ���� 2s

-------------------------
����					 |
-------------------------
	# ����(��������������)
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>

	# ����ע��
		@EnableCircuitBreaker

		* ���������߶˿���
		* Ĭ�ϳ�ʱʱ��:2000 ���� (2��)

	
	# �����۶ϴ���
		//��ӳ�䴦������ͨ��@HystrixCommand��fallbackMethod��ָ���۶Ϸ���
		//��Ӧ�÷����ķ���ֵ��������ö�
		@HystrixCommand(fallbackMethod = "processHystrix_Get")
		@GetMapping("/user/{id}")
		public User get(@PathVariable("id")Integer id){
			
		}

		* ��������ִ�г�ʱ,����÷���ִ�й����׳����쳣,Ҳ�ᴥ���۶Ϸ���
		

		/*-----------------------------------------------------------*/

		//�۶Ϸ���,Ȩ�����η�û�ر��Ҫ��
		public User processHystrix_Get (@PathVariable("id")Integer id){
		
		}

		* �۶Ϸ���������ȶ�,Ҳ���ܻ��׳��쳣�Ļ�,��Ҳ�ǿ������:@HystrixCommand ע���������۶ϼӳֵġ�
		* �������۶Ϸ������β������һ������:Throwable,�Դ�����ȡ��Ŀ�귽�����׳����쳣
		
	# @HystrixCommand
		String groupKey() default "";
		String commandKey() default "";
		String threadPoolKey() default "";

		String fallbackMethod() default "";
			* ָ���۶ϴ���������(�۶Ϸ����ڵ�ǰhandler����)

		HystrixProperty[] commandProperties() default {};
			

		HystrixProperty[] threadPoolProperties() default {};
		Class<? extends Throwable>[] ignoreExceptions() default {};
			* ����Ŀ�귽�����׳����쳣,���ᴥ���۶Ϸ���

		ObservableExecutionMode observableExecutionMode() default ObservableExecutionMode.EAGER;
			* ����ִ�з�ʽ
			* ö��ֵ
				LAZY		ʹ�� observe() ģʽ
				EAGER		ʹ�� toObservable() ģʽ

		HystrixException[] raiseHystrixExceptions() default {};
		String defaultFallback() default "";

-------------------------
ͨ�õ��۶ϴ���			 |
-------------------------
	# �Զ�����ʵ�ֽӿ�:FallbackFactory

		public interface FallbackFactory<T> {
			T create(Throwable cause);
		}
		
		* T����,������Ҫ�۶ϵĵ�
		* T�������Ϊ��Service,��ô������᷵��һ��T,���ص����T�����з���,�����۶ϵ�T���۶Ϸ���
		* ͨ�����:����Ҹ�T,�ҷ���һ��T����,������ҵ�T�����۶��¼�,�򴥷��ҷ��ظ����T�Ķ�Ӧ�ķ���
		
		* ��ʵ���಻Ҫ�������: @Component ע��,��������Ҫ��Spring��ioc����
	
		