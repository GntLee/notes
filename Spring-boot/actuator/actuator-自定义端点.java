------------------------------------
�Զ���˵�							
------------------------------------
	# �� Bean �ϱ�ʶ @Endpoint ע��
		* �ڸ������У�ʹ�� @ReadOperation, @WriteOperation �� @DeleteOperation ע����κη��������Զ�ͨ��JMX��¶����webӦ�ó����У�Ҳ����ͨ��HTTP��¶��


------------------------------------
ע��
------------------------------------
	@Endpoint
		String id() default "";
		boolean enableByDefault() default true;

	@ReadOperation
	@WriteOperation
	@DeleteOperation

	@WebEndpoint
	@JmxEndpoint

	@EndpointWebExtension
	@EndpointJmxExtension

	@ServletEndpoint
	@ControllerEndpoint
	@RestControllerEndpoint

	@Selector

------------------------------------
 �ӿ�	
------------------------------------
	EndpointFilter