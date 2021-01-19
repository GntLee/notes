------------------------
producer				|
------------------------
	# Maven
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>2.1.1</version>
		</dependency>
	
	# ��Ϣ�����ߵ��߼�
		1. ���ÿͻ��˲���,�Լ����ݲ���������Ӧ����Ϣ������ʵ��
		2. ������Ϣ
		4. ������Ϣ
		5. �رտͻ���(������ʵ��)

	# �����ķ�����ʾ
		Properties properties = new Properties();
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("client.id", "producer.client.id.demo");
		
		try(KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)){
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", "��ð�");
			kafkaProducer.send(producerRecord);
		}
	
	# �����߿ͻ������̰߳�ȫ��

	# �쳣�Ŀ����Ի���
		* KafkaProducer �� һ��ᷢ���������͵��쳣 : �����Ե��쳣�Ͳ������Ե��쳣 
		* �����Ŀ������쳣
			NetworkException(�����쳣,����п�������������˲ʱ���϶����µ��쳣,����ͨ�����Խ��)
			LeaderNotAvailableException(������ leader ����������,����쳣ͨ�������� leader �������߶��µ� leader ����ѡ�����֮ǰ,����֮��������»ָ�)
			UnknownTopicOrPartitionException
			NotEnoughReplicasException
			NotCoordinatorException

		* �����Ĳ��������쳣
			RecordTooLargeException(���͵���Ϣ̫��,����ִ������,ֱ���׳�)

		* ��������� retries ����,��ôֻҪ�ڹ涨�����Դ��������лָ���,�Ͳ����׳��쳣(retries ������Ĭ��ֵΪ 0)
			props.put(ProducerConfig.RETRIES_CONFIG, "10");
		
		* ���ڿ������쳣������,�������������ظ����õ�ʱ����,retry.backoff.ms(Ĭ��100����)
			props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "100");

		* ������ retries �� retry.backoff.ms ֮ǰ,����ȹ���һ�¿��ܵ��쳣�ָ�ʱ��
		* ���������趨�ܵ�����ʱ���������쳣�ָ�ʱ��,�Դ������������߹���ط������� 
	
	# ��Ϣ�����л�(������)
		* ��Ҫ�õ����л�,����Ϣ(key��value)���л�Ϊbyte[]
		* �������л��ӿ�:Serializer<T>
			byte[] serialize(String topic, T data);
		* ������Ϣ������(�������õ�����)
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

	
	# ������
		* ��Ϣsend()��brokerʱ���ܻᾭ��������,���л���(������),������֮��Żᱻ���͵�broker
		* �����Ϣ����(ProducerRecord)ָ����partition����ֵ,��ô�Ͳ���Ҫ��������,��Ϊ�Ѿ�ָ������
		* ���δָ��partition����ֵ,��ô��Ҫ�����ڷ�����,����key�ֶ��������partitionֵ
		* �������ӿ�:Partitioner
			void configure(Map<String, ?> configs)
			int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster);
			void close();
		
		* Kafka�ṩĬ�ϵķ�����ʵ��
			DefaultPartitioner
				* ���key��Ϊnull,��ô���key����hash(����MurmurHash2�㷨,�߱����������ܺ͵���ײ��)����
				* �������յ�hashֵ�����������
				* Ҳ����˵��ͬ��key�ᱻ���͵�ͬһ������(�����չ��partition��������ô�Ͳ��ܱ�֤��)
				* ����õ��ķ����Ż������з����е�����һ��,���ܻ�ѡ�񵽲����õķ���
			
				* ���KeyΪnull,��ô��Ϣ�ᱻ����ѯ�ķ�ʽ���͵�ÿ�����õķ���
				* ����õ��ķ����Ž�Ϊ���÷����е�����һ��,����ѡ�񵽲����õķ���
				
		* ʹ���Զ���ķ�����
			properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
			
	
	# ������
		* ��������������Ϣ������֮ǰ��һЩ�����Ĺ���,����:����,���ݵ��޸�
		* Ҳ���������ڷ��ͻص��߼�ǰ��һЩ���ƻ�������,����:ͳ��
		* �������ӿ�:ProducerInterceptor<K,V>
			void configure(Map<String, ?> configs);
			ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
			void onAcknowledgement(RecordMetadata metadata, Exception exception);
			void close();
		
		* Kafka������Ϣ����,��������֮ǰ������������onSend����

		* һ����˵��ò�Ҫ�޸���Ϣ��:topic,partition,key����Ϣ
		* ����ȷ����׼ȷ���ж�,������ܻ������Ԥ��������ƫ��Ŀ���
		* �������ܻ�Ӱ������ļ���,������ӡ��broker����־��ѹ������
		
		* Kafka������Ϣ��Ӧ��(send api����)֮ǰ������Ϣ����ʧ��ʱ������������onAcknowledgement����
		* һ����,��Ҫ�ж� exception �Ƿ�Ϊnull,�Ӷ�ȷ����Ϣ�Ƿ��ͳɹ�
		* ���������� Callback ִ��
		* �������������Producer��I/O�߳���,����Խ��Խ��,�����Ӱ����Ϣ�ķ����ٶ�

		* close()������Ҫ���ڹر�������ʱִ��һЩ��Դ��������

		* �������м�������,��ִ�����׳����쳣,���ᱻ���񲢼�¼����־
		* ���ǲ������ϴ���(�����׳�)
		
		* �������������ö�,�γ�һ��������(�������������)
		* �����������ĳһ���������׳����쳣,���ᱻ����
		* ��һ���������������һ��ִ�гɹ�����������ʼִ��(�Թ�ִ���쳣��������)
		* ���������ʹ�ö��ŷָ�

		* ����������
			properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "io.javaweb.kafka.interceptor.SimpleInterceptor");
		
		* ������������
			properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "SimpleInterceptor1,SimpleInterceptor1");
	
	# ִ�й���
		* �����߿ͻ����������߳����:���߳�,�����߳�
		* ���̴߳�����Ϣ -> ������ ��> ������ -> ���������� -> ��Ϣ�ۼ���(RecordAccumulator)
		* �����̸߳������Ϣ�ۼ����л�ȡ��Ϣ,���͵�broker

	# ��Ϣ���ۼ���(����)
		* ���̵߳����� send() api����������ִ����Ϣ�ķ���,���Ȱ���Ϣ��ӵ��ۼ��� (RecordAccumulator)
		* RecordAccumulator �����þ��ǻ�����Ϣ
		* �Ա�sender�߳������Ľ��з���,���Ч��(�������紫��)
		* Ĭ�ϻ���Ĵ�СΪ:33554432kb = 32MB
		* ����Ĵ�С��������
			properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, String.valueOf(1024 * 1024 * 50));
		
		* ��������ߵ���Ϣ�����ٶȳ����˷�����Ϣ�����������ٶ�
		* �Ӷ�ռ���˻�����,�����������,send() api Ҫô����ֱ�����������µĿռ�,Ҫô�׳��쳣
		* ����ͨ������������send()����,�����ʱ��(ms),����ʱ��ͻ��׳��쳣
			properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, String.valueOf(60000));
		
		* ���̷߳��͵���Ϣ���ᱻ׷���� RecordAccumulator ��ĳ��˫�˶���(Deque)��
		* RecordAccumulator Ϊÿ��������ά����һ��˫�˶���,���е����ݾ���:ProducerBatch(Deque<ProducerBatch>)
			ConcurrentMap<TopicPartition, Deque<ProducerBatch>> batches;
		* һ��ProducerBatch���������һ����Ϣ��¼(ProducerRecord)
		* ��Ϣд�뻺���ʱ��,׷�ӵ�˫�˶��е�β��,sender�̶߳�ȡ��Ϣ��ʱ��,��˫�˶��е�ͷ����ȡ

		* �� RecordAccumulator ���ڲ�����һ�� BufferPool
		* ����Ҫ����ʵ�� ByteBuffer �ĸ���,��ʵ�ֻ���ĸ�Ч���� 
		* ���� BufferPool ֻ����ض���С�� ByteBuffer ���й���,��������С�� ByteBuffer ���Ỻ��� BufferPool ��
		* ����ض��Ĵ�С�� batch.size ������ָ��,Ĭ��ֵΪ 16384B,�� 16KB
			properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, String.valueOf(16384));
		
		* ��һ����Ϣsend()��RecordAccumulator��ʱ��,�����ҵ����Ӧ�ķ�������(Deque),���������,���ȴ���
		* �ٴӶ���β����ȡһ��ProducerBatch,������������½�
		* �ж�ProducerBatch�Ƿ����㹻�Ŀռ�д�뵱ǰ����Ϣ,���������ֱ��д��
		* ���������,�򴴽�һ���µ�ProducerBatch,���½� ProducerBatch ʱ����������Ϣ�Ĵ�С�Ƿ񳬹� batch.size �����Ĵ�С
		* ���������,��ô���� batch.size �����Ĵ�С������ ProducerBatch,������ʹ��������ڴ�����֮��,����ͨ�� BufferPool �Ĺ��������и���
		* �������,��ô���������Ĵ�С������ ProducerBatch,����ڴ����򲻻ᱻ����
	
	# ������Ӧ�Ļ���
		* ��Ϣ��RecordAccumulator���͵�broker������,�ᱻ���浽 InFlightRequests ��,ֱ����Ӧ
		* ���Ļ����ʽΪ -> broker�ڵ�id:Deque<Request>
			Map<String, Deque<NetworkClient.InFlightRequest>> requests = new HashMap<>(); 
		
		* ����ͨ����������ÿ������(Ҳ���ǿͻ����� Node ֮�������)��໺���������
		* max.in.flight.requests.per.connection: Ĭ��ֵΪ5,Ҳ����˵,��໺��5��δ��Ӧ������,һ��������ֵ,�Ͳ�����������ӷ��͸����������
		* ���ǻ����е�����,�յ�����Ӧ
			properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, String.valueOf(5));

		* ����ͨ�� Deque<ProducerBatch>.size() �� max.in.flight.requests.per.connection ���бȽ�
		* �Ӷ��ж��Ƿ�ѻ���N���Ѿ����͵���δ��Ӧ����Ϣ,�����Ĵ��ںܶ�,��ô��broker�����縺�رȽϴ�,��������������

	
	# Ԫ���ݵĸ���
		* Ԫ����,��ʵ���ǳ�������ҵ�����������һЩ��������
		* �������Ƕ�����͸����,�������ǲ���ȱ�ٵ�,����:kafka��Ⱥ�е���������,ÿ������ķ�������,�����ĸ�������ȵ���Ϣ
		* ���ͻ�����û����Ҫʹ�õ�Ԫ������Ϣʱ,����û��ָ����������Ϣ
		* ���߳��� metadata.max.age.ms ʱ��û�и���Ԫ���ݶ�������Ԫ���ݵĸ��²���,metadata.max.age.ms ��Ĭ��ֵΪ 300000,�� 5 ����
			properties.setProperty(ProducerConfig.METADATA_MAX_AGE_CONFIG, String.valueOf(300000));
		
		* ���µĲ������ڿͻ�����˵��͸����

		* ����Ҫ����Ԫ���ݵ�ʱ��,������ѡ��:leastLoadedNode
		* leastLoadedNode��ʵ��������Node�и�����С��Node(�ж�InFlightRequest�е�δ��Ӧ����������ֵ,����Խ��,�ڵ�ĸ���ԽС)
		* Ȼ������� Node ���� MetadataRequest ��������ȡ�����Ԫ������Ϣ,������²������� Sender �̷߳��� ��
		* �ڴ����� MetadataRequest ֮�� ͬ������� InFlightRequest ֮��Ĳ���ͺͷ�����Ϣʱ������
		* Ԫ������Ȼ�� Sender �̸߳������,�������߳�Ҳ��Ҫ��ȡ��Щ��Ϣ,���������ͬ��ͨ�� synchronized �� final �ؼ���������
	
	# ��Ϣ������������
		* Kafkaֻ�ܱ�֤һ�������е���Ϣ�������Ե�

		* ����� acks ��������Ϊ����ֵ,���� max.in.flight.requests.per.connection ��������Ϊ���� l ��ֵ,��ô�ͻ���ִ��������
		* �����һ������Ϣд��ʧ��,���ڶ�������Ϣд��ɹ�,��ô�����߻����Է��͵�һ���ε���Ϣ
		* ��ʱ�����һ���ε���Ϣд��ɹ�,��ô���������ε���Ϣ�ͳ����˴���
		
		* һ�����,����Ҫ��֤��Ϣ˳��ĳ��Ͻ���Ѳ��� max.in.flight.requests.per.connection ����Ϊ 1,�����ǰ� acks ����Ϊ 0
		* ������Ӱ�����������