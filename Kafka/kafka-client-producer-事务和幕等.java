----------------------------
�����Ļ��					|
----------------------------
	# ��Ϣ�м������Ϣ���䱣���� 3 ���㼶
		* at most once,����һ��,��Ϣ���ܻᶪʧ,�����Բ����ظ�����

		* at least once,����һ��,��Ϣ�����ᶪʧ,�����ܻ��ظ�����(KafkaĬ��)

		* exactly once,ǡ��һ��,ÿ����Ϣ�϶��ᱻ����һ���ҽ�����һ��
	
----------------------------
Ļ��						|
----------------------------
	# �������ڽ������Ե�ʱ���п��ܻ��ظ�д����Ϣ
		* ��Ϣ���ͺ�������������������ͨ���ж�,��ô�����߾��޷��жϸ���Ϣ�Ƿ񼺾��ύ
		* �����߽��ж��������ȷ����Ϣ�Ѿ�д��Kafka,������ԵĹ������п��ܻ������Ϣ���ظ�д��

	# ʹ�� Kafka ��Ļ���Թ���֮��Ϳ��Ա����������
	# ����Ļ��ʹ��������:enable.idempotence
		properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

	# ȷ��Ļ�ȹ�������,����Ҫ�������õ���ȷ��
		retries
		acks=
		max.in.flight.requests.per.connection

		* ʵ������ʹ�� Ļ���Թ��ܵ�ʱ��,��ȫ���Բ�������(Ҳ����������)�⼸������
		* ���ǿ��������,���ܻ���Ϊ���ò�������Ļ����Ϣ�����쳣

	# ԭ��
		* ÿ���µ�������ʵ���ڳ�ʼ����ʱ�򶼻ᱻ����һ�� PID , ��� PID ���û���������ȫ͸���� 
		* ����ÿ�� PID,��Ϣ���͵���ÿһ���������ж�Ӧ�����к�
		* ��Щ���кŴ� 0 ��ʼ��������,������ÿ����һ����Ϣ�ͻὫ <PID ����> ��Ӧ�����кŵ�ֵ�� l

		* broker�����е� <PID ������> ��ά����һ�����к�
		* ���յ���Ϣʱ��,ֻ���������к�ֵ == (<PID ������> +  1),broker�Ż����
		
		* �����Ϣ���к� < (<PID ������> +  1),˵����Ϣ���ظ���д��,broker�ᶪ��������Ϣ
		* ����������к� > (<PID ������> +  1),˵����;����Ϣ��ʧδд�뵽broker,����������,��ʱ�ͻ��׳��ǳ����ص��쳣:OutOfOrderSequenceException
	
	# Kafka��Ļ��ֻ�ܱ�֤���������߻Ự(session )�е�������Ļ�ȵ�

----------------------------
Ļ��						|
----------------------------
	# Ļ���Բ����ܿ�����������,����������ֲ����ȱ��,������Ա�֤�Զ������д�������ԭ����
	# ��������:transactional.id
		properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "1");
		
		* ����Ҫ����뿪��Ļ��:enable.idempotence=true
		* transactionalldֵ�ɳ����ṩ
		* transactionalld�� PIDһһ��Ӧ,��ͬ���� transactionalld ���û���ʽ���� ,��PID ���� Kafka �� ������� 
	
	# ���������Ϣ�����߶�����ͬ�� transactionalld ,���������Ǹ����׳��쳣:ProducerFencedExcept
		* ÿ��������ͨ�� transactionalld ��ȡ PID �� ͬʱ,�����ȡһ������������producer epoch
		* ���������Ǹ�ӵ����ͬ transactionalld ��������ʵ�������ٹ���

	# ������صķ���

		void initTransactions()
			* ��ʼ������,ǰ���Ǳ���������transactionalld,�����׳��쳣
		void beginTransaction()
			* ��������
		void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> offsets,String consumerGroupId)
			* Ϊ�������ṩ�������ڵ�λ���ύ�Ĳ���
		void abortTransaction() 
			* �ع�����
		void commitTransaction()
			* �ύ����
	
	# һ������Ĳ���
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// ����Ļ��
		properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		// ��������id
		properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "1");
		
		try(KafkaProducer<Void, String> kafkaProducer = new KafkaProducer<>(properties)){
			// ��ʼ������
			kafkaProducer.initTransactions();
			// ��ʼ����
			kafkaProducer.beginTransaction();
			try {
				ProducerRecord<Void, String> producerRecord1 = new ProducerRecord<>("topic_1", "Message-1");
				ProducerRecord<Void, String> producerRecord2 = new ProducerRecord<>("topic_1", "Message-2");
				kafkaProducer.send(producerRecord1);
				kafkaProducer.send(producerRecord2);
				
				//TODO ����ҵ�����
				
				// �ύ����
				kafkaProducer.commitTransaction();
			}catch (Exception e) {
				// �ع�����
				kafkaProducer.abortTransaction();
			}
		}
	
	# sendOffsetsToTransaction ???
		public class Transactional {
			public static Properties getConsumerConfig() {
				Properties properties = new Properties();
				properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
				properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
				properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
				properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_1");
				properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
				return properties;
			}

			public static Properties getProducerConfig() {
				Properties properties = new Properties();
				properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
				properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
				properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
				properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "1");
				return properties;
			}

			public static void main(String[] args) {
				KafkaConsumer<Void, String> kafkaConsumer = new KafkaConsumer<>(getConsumerConfig());
				kafkaConsumer.subscribe(Collections.singletonList("topic_1"));

				KafkaProducer<Void, String> kafkaProducer = new KafkaProducer<>(getProducerConfig());
				kafkaProducer.initTransactions();
				while (true) {
					ConsumerRecords<Void, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
					if (!consumerRecords.isEmpty()) {
						Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
						kafkaProducer.beginTransaction();
						try {
							for(TopicPartition topicPartition : consumerRecords.partitions()) {
								List<ConsumerRecord<Void,String>> consumerRecordList = consumerRecords.records(topicPartition);
								for(ConsumerRecord<Void,String> consumerRecord : consumerRecordList) {
									// ������Ϣ
									System.out.println(consumerRecord.value());
									
									// ������Ϣ
									ProducerRecord<Void, String> producerRecord = new ProducerRecord<Void, String>("topic_1",consumerRecord.value());
									kafkaProducer.send(producerRecord);
								}
								long lastConsumedOffset = consumerRecordList.get(consumerRecordList.size() - 1).offset();
								offsets.put(topicPartition,new OffsetAndMetadata(lastConsumedOffset + 1));
							}
							kafkaProducer.sendOffsetsToTransaction(offsets, "group_1");
							kafkaProducer.commitTransaction();
						}catch (Exception e) {
							kafkaProducer.abortTransaction();
						}
					}
				}
			}
		}
