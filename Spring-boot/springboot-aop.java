-----------------------
Spring boot-AOP			|
-----------------------
	1,��������
		<dependency>  
			<groupId>org.springframework.boot</groupId>  
			<artifactId>spring-boot-starter-aop</artifactId>  
		</dependency>  
	
	2. ������̬����
		@EnableAspectJAutoProxy
			boolean proxyTargetClass() default false;
				* ����Aop��ʵ�ַ�ʽ
				* Ϊtrue �Ļ�ʹ��cglib,Ϊfalse�Ļ�ʹ��java��Proxy

			boolean exposeProxy() default false;
				* �Ƿ�¶�����࣬���Ϊtrue�����Ѵ�����洢�� ThreadLocal ��
				* ���һ���࣬�ڲ�����������ò��ߴ���ĳ�����Ĭ��Ϊ false.
					AopContext.currentProxy()
						* ��ȡ����ǰ��������ʵ��
				
				* ʹ��
					public void foo (){
						// ǿ��ת��Ϊ��ǰ�࣬�������Ǵ����Ķ���Ȼ��ִ�б��෽��
						((Demo)AopContext.currentProxy()).bar();
					}
	

	3,����Ҫִ��AOP�ĵط���Springһ���Ĳ���
		@Aspect  
		@Component  
		@Befor
		...
	