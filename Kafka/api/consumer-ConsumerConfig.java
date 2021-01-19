-----------------------------
ConsumerConfig				 |
-----------------------------
	# ��Ϣ����������
	# ������
		bootstrap.servers
			* kafka��Ⱥ�Ľڵ�
			* ip:port
			* ���Ӷ��ʹ�ö��ŷָ�

		client.dns.lookup
		group.id
			* ����������,Ĭ��ֵΪ���ַ���

		session.timeout.ms
			* �����Э������������������Ƿ�ʧЧ��ʱ��
			* Ĭ��:1000

		heartbeat.interval.ms
			* ʹ��Kafka��������ܵ�ʱ��
			* ��������Э����֮����������ʱ��,Ĭ��ֵΪ:3000ms
			* ͨ��������֪�µ������߼���/�뿪��,�Ӷ����½��з����ķ���(���ؾ���)

			* ��ֵ�����: session.timeout.ms С(ͨ������������ 1/3)
		
		partition.assignment.strategy
			* ��������topic֮��ķ����������ʵ����
			* �ӿ�:PartitionAssignor
			* Ĭ��:org.apache.kafka.clients.consumer.RangeAssignor

		metadata.max.age.ms
			* ����Ԫ���ݵļ��,Ĭ��Ϊ:300000ms,5����
			* ����������ʱ��Ԫ����û�б�����,��ô�ͻ������Ľ��и���

		enable.auto.commit
			* �Ƿ����Զ��ύ����λ��,Ĭ��ֵΪ:true

		auto.commit.interval.ms
			* * ��������λ�Ƽ�¼�ύ�ļ��,Ĭ��: 50000(ms) Ҳ����5��

		client.id
			* ��ǰ�����ߵ�id
			* ���������,���Զ�������һ���ǿ��ַ���(consumer-[���])
				consumer-1....consumer-x

		max.partition.fetch.bytes
			* ���ô�ÿ���������ظ������ߵ����������
			* Ĭ��Ϊ:1MB,�ò�����:fetch.max.bytes ����
			* �����ò������Ƶ���һ����ȡ��������Ϣ�Ĵ�С

			* ͬ����,��Ҳ����һ��ǿӲ������
			* ��������������Ϣ��СҪС,��Ϣ���ǿ��Ա����ѵ�

		send.buffer.bytes
			* �������� Socket ������Ϣ������(SO_SNDBUF)�Ĵ�С,Ĭ��ֵΪ 131072B,Ҳ����128KB
			* �������Ϊ -l,��ʹ�ò���ϵͳ��Ĭ��ֵ

		receive.buffer.bytes
			* �������� Socket ������Ϣ������(SO_RECBUF )�Ĵ�С
			* Ĭ��ֵΪ 32768B,Ҳ����32 kb
			* �������Ϊ -l,��ʹ�ò���ϵͳ��Ĭ��ֵ
			* ��� Producer �� Kafka ���ڲ�ͬ�Ļ��� ,������ʵص����������ֵ 
		
		fetch.min.bytes
			* ������һ��poll()�ܴ�һ������������ȡ����С����ֵ,Ĭ��Ϊ 1Byte
			* �����Ϣ�����ֵ,��ô�ͻ�����,ֱ�����ڵ��ڸ�ֵ,�Ż᷵��
			* �ʵ��ĵ����������,���Դ���������,����Ҳ����ɶ�����ӳ�
			* �����ӳ����е�Ӧ�þͲ��ʺ�

		fetch.max.bytes
			* ������һ���ܴ�һ����������ȡ���������ֵ,Ĭ��Ϊ 50MB

			* �ò������õ�ֵ,���Ǿ��Ե����ֵ
			* ����ڵ�һ���ǿյķ�������ȡ�ĵ�һ����Ϣ���ڸ�ֵ,��ô��Ϣ��Ȼ�᷵��
			* ��ȷ�������߼�������
			
			* ������һ��ǿӲ������
			* ���������������Ϣ��СҪС,��Ϣ���ǿ��Ա����ѵ�
			* Kafak�����ܽ��ܵ���Ϣ���ֵ,��ͨ������˵Ĳ���:message.max.bytes ������

		fetch.max.wait.ms
			* �����ݲ��� fetch.min.bytes ��ʱ��,kafka������
			* �ò���ָ��kafka�����ȴ�ʱ��,Ĭ��Ϊ 500ms
			* ��ʱ����Զ��ķ���
			* ���Ӧ�ö��ӳ�����,�ǿ����ʵ��ĵ�С�ò���

		reconnect.backoff.ms
			* ���ó�����������ָ������֮ǰ�ĵȴ�ʱ��
			* ����Ƶ������������,Ĭ��ֵΪ: 50ms
			* ���ֻ�����������������broker���͵���������

		reconnect.backoff.max.ms
		retry.backoff.ms
			* ���ó������·���ʧ�ܵ�����ָ�����������֮ǰ�ĵȴ�ʱ��
			* ������ĳЩ���������Ƶ���ط���,Ĭ��ֵΪ:100 ms

		auto.offset.reset
			* ���������Ҳ�������ƫ������¼��ʱ��,�����￪ʼ��������
			* ö��ֵ:
				earliest	����Ϊ�����ƫ����,��ͷ��ʼ����
				latest		��ƫ������Ϊ����ƫ����,ͨ�׵�˵���ǲ�������ǰ����Ϣ��,��������Ϣ��ʼ����(Ĭ��)
				none		���û���ҵ�ƫ������¼,�׳��쳣

			* �������ƫ����Խ����,Ҳ��ͨ�������õĲ�������������ƫ����


		check.crcs
		metrics.sample.window.ms
		metrics.num.samples
		metrics.recording.level
		metric.reporters
		key.deserializer
		value.deserializer
			* ����value��key�Ľ�����

		request.timeout.ms
			* Ĭ�ϵ���������ʱʱ��
			* �����ߵȴ�broker����Ӧʱ��

		default.api.timeout.ms
		connections.max.idle.ms
			* �����ڶ��֮��ر����Ƶ�����,Ĭ��ֵ��:540000 ms = 9����
			
		interceptor.classes
			* ����һ�����߶��������������
			* ����Ƕ��ʹ�ö��ŷָ�

		max.poll.records
			* һ����������ȡ��������Ϣ
			* Ĭ��ֵΪ 500 ��,�����Ϣ��������Ƚ�С�Ļ�,���Ե����ֵ

		max.poll.interval.ms
			* ͨ�������߹��������ߵ�ʱ��,������ָ����ȡ��Ϣ�̵߳�������ʱ��
			* ����������ʱ�仹û����poll()����,����������Ϊ���������Ѿ��뿪��������
			* �����ٴδ������ؾ���(��������)
			* Ĭ��ֵ:300000

		exclude.internal.topics
			* Kafka���������ڲ�����:_consumer_offsets �� __transaction_state
			* �ò���ָ�������ڲ������Ƿ�������߹���,Ĭ��ֵΪ:true

			* �������Ϊtrue,��ô������ֻ��ʹ��:subsribe(Collection<> ) �����ĵ��ڲ�Topic
			
		internal.leave.group.on.close
		isolation.level
			* ��������ĸ��뼶��
			* ö��ֵ:
				READ_UNCOMMITTED(Ĭ��)
					* ���Զ�ȡ��HW(High Watermark)����λ��
					* ���Զ�ȡ��������δcommit��������Ϣ
				
				READ_COMMITTED
					* �������Ѿ��ύ����Ϣ
					* ֻ�����ѵ� LSO(Last Stable Offset)��λ��

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
		