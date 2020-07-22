------------------------
ProducerConfig			|
------------------------
	# ��Ϣ����������
	# �ṩ��N���������(���Ƕ���̬���������,��д,.ת��Ϊ�»���,������ CONFIG ��β)
		bootstrap.servers (BOOTSTRAP_SERVERS_CONFIG)
			* Kafka��Ⱥ���б�,���ʹ�ö��ŷָ�
			* ip:port

		client.dns.lookup
		buffer.memory
			* ��Ϣ�ۼ���(RecordAccumulator)�Ļ����С
			* Ĭ��ֵΪ:33554432kb = 32MB

	
		
		acks
			* ָ�������б���Ҫ�ж��ٸ�������������Ϣ,�ŷ��سɹ�
			* ���漰��Ϣ�Ŀɿ��Ժ�������֮���ƽ��
			* ö��ֵ
				0
					* ��Ϣ��������Ϊ�ǳɹ���,���ܷ����Ƿ�д��ɹ�(���������,��Ϣ���ܻᶪʧ)
				1
					* ֻҪ��Ϣд���˷�����leader��������ɹ�(����,һ�������)
				-1
					* ֵҲ������:all
					* �����Ƿ��������и���(ISR)��д����,����ɹ�
					* ���Ⲣ����ζ����Ϣ��һ���ɿ�,��Ϊ ISR �п���ֻ�� leader ����,�������˻����� acks= l �����
					* Ҫ��ø��ߵ���Ϣ�ɿ�����Ҫ��� min.insync.replicas �Ȳ���������

		compression.type
			* ������Ϣ��ѹ����ʽ,Ĭ��Ϊ:none Ҳ���ǲ�ѹ��
			* ֧�ֵ��㷨(ֵ)
				gzip
				snappy
				lz4

		batch.size
			* �� RecordAccumulator �� BufferPool �Ỻ��� ByteBuffer ��С
			* BufferPool ֻ����ض���С�� ByteBuffer ���й���,��������С�� ByteBuffer ���Ỻ��� BufferPool ��
			* Ĭ��:16384B,�� 16KB

		linger.ms
			* �趨�����߷��� ProducerBatch ֮ǰ�ȴ�������Ϣ(ProducerRecord)����ProducerBatch ��ʱ��
			* Ĭ��ֵΪ 0(ֻ������Ϣ������ProducerBatch�ͷ���,���ȴ�)
			* �����߿ͻ��˻��� ProducerBatch ��������ȴ�ʱ�䳬�� linger.ms ֵʱ������ȥ
			* �������������ֵ��������Ϣ���ӳ�,����ͬʱ������һ����������
			* ��������� TCP Э���е� Nagle �㷨���� ��ͬ��֮��

		delivery.timeout.ms
		client.id
			* ���ÿͻ��˵�id

		send.buffer.bytes
			* �������� Socket ������Ϣ������(SO_SNDBUF)�Ĵ�С,Ĭ��ֵΪ 131072B,Ҳ����128KB
			* �������Ϊ -l,��ʹ�ò���ϵͳ��Ĭ��ֵ

		receive.buffer.bytes
			* �������� Socket ������Ϣ������(SO_RECBUF )�Ĵ�С
			* Ĭ��ֵΪ 32768B,Ҳ����32 kb
			* �������Ϊ -l,��ʹ�ò���ϵͳ��Ĭ��ֵ
			* ��� Producer �� Kafka ���ڲ�ͬ�Ļ��� ,������ʵص����������ֵ 

		max.request.size
			* ������Ϣ�������ܹ����͵���Ϣ������Ĭ��Ϊ:1048576kb = 1MB
			* һ�㲻����ȥ�޸�
			* ҲҪע��,brokerҲ�ǿ������ý��ܵ���Ϣ������

		reconnect.backoff.ms
		reconnect.backoff.max.ms
		retries
			* ���ڿ����Ե��쳣,��������� retries ����,��ôֻҪ�ڹ涨�����Դ��������лָ���,�Ͳ����׳��쳣
			* Ĭ��ֵΪ 0,Ҳ����˵��������,�����׳��쳣

		retry.backoff.ms
			* ���ڿ������쳣������,�����ظ����õ�ʱ����
			* Ĭ��Ϊ:100(����)
			
		max.block.ms
			* ��send()api��������ʱ��(����������,û�пռ�����Ϣ,һ�㷢������Ϣ�����ٶ�,������Ϣ�ķ����ٶ�)
			* �����������ʱ��(����),������ʱ��ͻ��׳��쳣
			* Ĭ��Ϊ:60000(60s)

		request.timeout.ms
			* Producer �ȴ�������Ӧ���ʱ��,Ĭ��ֵΪ 30000 (ms)
			* ����ʱ֮�����ѡ���������
			* ע�����������Ҫ �� broker �˲��� replica.lag.time.max.ms ��ֵҪ��
			* �������Լ�����ͻ������Զ��������Ϣ�ظ��ĸ���

		metadata.max.age.ms
			* ������ʱ��δ����Ԫ����,�ͻ�ѡ������С��broker,����MetadataRequest������Ԫ������Ϣ
			* Ĭ��300000(ms) = 5����

		metrics.sample.window.ms
		metrics.num.samples
		metrics.recording.level
		metric.reporters
		max.in.flight.requests.per.connection
			* ��Ϣ��RecordAccumulator���͵�broker������,�ᱻ���浽 InFlightRequests ��,ֱ����Ӧ
			* ����������ÿ������(Ҳ���ǿͻ����� Node ֮�������)��໺���������
			* ���������,����Ϣ�Ѿ��ӻ����з���ȥ��,���ǻ�û�յ���Ӧ������
			* Ĭ��ֵΪ5,Ҳ����˵,��໺��5��δ��Ӧ������,һ��������ֵ,�Ͳ�����������ӷ��͸����������
			* ���ǻ����е�����,�յ�����Ӧ

		key.serializer
		value.serializer
			* ����key/value�ı�����
			* ֵ�Ǳ���������·��

		connections.max.idle.ms
			* ָ���ڶ��֮��ر����Ƶ�����,Ĭ��ֵ�� 540000(ms)�� 9 ����

		partitioner.class
			* ���÷�������ʵ����

		interceptor.classes
			* ������������ʵ����

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
		enable.idempotence
			*  �Ƿ���Ļ���Թ���

		transaction.timeout.ms
		transactional.id
			* ���������id,����Ψһ

	
	# ���캯��
		ProducerConfig(Properties props)
		ProducerConfig(Map<String, Object> props)
	
	# ��̬����
		Map<String, Object> addSerializerToConfig(Map<String, Object> configs,Serializer<?> keySerializer, Serializer<?> valueSerializer)
		Properties addSerializerToConfig(Properties properties,Serializer<?> keySerializer,Serializer<?> valueSerializer)
		Set<String> configNames()
			* ���ؿ����õ�key

		void main(String[] args)
			* main����,��html��ʽ��ӡ���ú�˵��