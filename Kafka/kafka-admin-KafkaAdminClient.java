-------------------------
KafkaAdminClient		 |
-------------------------
	# 实例的创建
		KafkaAdminClient KafkaAdminClient.create(Map<String,Object> config);
		KafkaAdminClient KafkaAdminClient.create(Properties properties)
	
	# 各种方法
		

-------------------------
AdminClientConfig		 |
-------------------------
	# 管理客户端的配置项
	bootstrap.servers
		* broker节点的地址
	client.id
	metadata.max.age.ms
	send.buffer.bytes
	receive.buffer.bytes
	reconnect.backoff.ms
	reconnect.backoff.max.ms
	retry.backoff.ms
	request.timeout.ms
	connections.max.idle.ms
	retries
	metrics.sample.window.ms
	metrics.num.samples
	metric.reporters
	metrics.recording.level
	client.dns.lookup
	security.protocol
	ssl.protocol
	ssl.provider
	ssl.cipher.suites
	ssl.enabled.protocols
	ssl.keystore.type
	ssl.keystore.location
	ssl.keystore.password
	ssl.key.password
	ssl.truststore.type
	ssl.truststore.location
	ssl.truststore.password
	ssl.keymanager.algorithm
	ssl.trustmanager.algorithm
	ssl.endpoint.identification.algorithm
	ssl.secure.random.implementation
	sasl.kerberos.service.name
	sasl.kerberos.kinit.cmd
	sasl.kerberos.ticket.renew.window.factor
	sasl.kerberos.ticket.renew.jitter
	sasl.kerberos.min.time.before.relogin
	sasl.login.refresh.window.factor
	sasl.login.refresh.window.jitter
	sasl.login.refresh.min.period.seconds
	sasl.login.refresh.buffer.seconds
	sasl.mechanism
	sasl.jaas.config
	sasl.client.callback.handler.class
	sasl.login.callback.handler.class
	sasl.login.class