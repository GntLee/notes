-------------------------
KafkaAdminClient		 |
-------------------------
	# �̳���:AdminClient
	# ʵ���Ĵ���
		KafkaAdminClient KafkaAdminClient.create(Map<String,Object> config);
		KafkaAdminClient KafkaAdminClient.create(Properties properties)
	
	# ����
		AlterConfigsResult alterConfigs(Map<ConfigResource, Config> configs, final AlterConfigsOptions options)
			* �޺����������

		AlterReplicaLogDirsResult alterReplicaLogDirs(Map<TopicPartitionReplica, String> replicaAssignment, final AlterReplicaLogDirsOptions options)

		void close(long duration, TimeUnit unit);

		CreateAclsResult createAcls(Collection<AclBinding> acls, CreateAclsOptions options)
		CreateDelegationTokenResult createDelegationToken(final CreateDelegationTokenOptions options)
		CreatePartitionsResult createPartitions(Map<String, NewPartitions> newPartitions,final CreatePartitionsOptions options)
			* �޸�parition�����͸���������

		CreateTopicsResult createTopics(final Collection<NewTopic> newTopics,final CreateTopicsOptions options)
			* ����topic

		DeleteAclsResult deleteAcls(Collection<AclBindingFilter> filters, DeleteAclsOptions options)
		DeleteConsumerGroupsResult deleteConsumerGroups(Collection<String> groupIds, DeleteConsumerGroupsOptions options)
		DeleteRecordsResult deleteRecords(final Map<TopicPartition, RecordsToDelete> recordsToDelete, final DeleteRecordsOptions options)
		DeleteTopicsResult deleteTopics(Collection<String> topicNames, DeleteTopicsOptions options)
			* ɾ��topic

		DescribeAclsResult describeAcls(final AclBindingFilter filter, DescribeAclsOptions options)
		DescribeClusterResult describeCluster(DescribeClusterOptions options) 
		DescribeConfigsResult describeConfigs(Collection<ConfigResource> configResources, final DescribeConfigsOptions options)
		DescribeConsumerGroupsResult describeConsumerGroups(final Collection<String> groupIds, final DescribeConsumerGroupsOptions options)
		DescribeDelegationTokenResult describeDelegationToken(final DescribeDelegationTokenOptions options)
		DescribeLogDirsResult describeLogDirs(Collection<Integer> brokers, DescribeLogDirsOptions options)
		DescribeReplicaLogDirsResult describeReplicaLogDirs(Collection<TopicPartitionReplica> replicas, DescribeReplicaLogDirsOptions options)
		DescribeTopicsResult describeTopics(final Collection<String> topicNames, DescribeTopicsOptions options)
			* ��ȡָ��topic��������Ϣ
			* ��������,isr,leader����Ϣ

		ExpireDelegationTokenResult expireDelegationToken(final byte[] hmac, final ExpireDelegationTokenOptions options)
		ListConsumerGroupOffsetsResult listConsumerGroupOffsets(final String groupId, final ListConsumerGroupOffsetsOptions options)
		ListConsumerGroupsResult listConsumerGroups(ListConsumerGroupsOptions options)
		ListTopicsResult listTopics(final ListTopicsOptions options)
			* ��ȡ���е�topic����

		Map<MetricName, ? extends Metric> metrics()
		RenewDelegationTokenResult renewDelegationToken(final byte[] hmac, final RenewDelegationTokenOptions options)
	
	# ������һЩ����
		public static void addPartition() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			
			/**
			 * ��ָ����partition�������ӵ�5��
			 */
			// NewPartitions newPartitions = NewPartitions.increaseTo(5); // ������ʾ�޸ĺ������
			
			/**
			 * ��ָ����parition�������ӵ�6��
			 * ��������ָ���丱���ķ��䷽��
			 */
			List<List<Integer>> newAssignments = new ArrayList<>();
			newAssignments.add(Arrays.asList(1,2));		// ��1������,����������,��broker 1 �� 2��	leader�ڵ���broker2��
			newAssignments.add(Arrays.asList(2,3));		// ��2������,����������,��broker 2 �� 3��	leader�ڵ���broker3��
			newAssignments.add(Arrays.asList(3,1));		// ��3������,����������,��broker 3 �� 1��	leader�ڵ���broker1��
			NewPartitions newPartitions = NewPartitions.increaseTo(6,newAssignments);
			
			// ִ���޸Ĳ���
			CreatePartitionsResult createPartitionsResult = kafkaAdminClient.createPartitions(Collections.singletonMap("topic_1", newPartitions));
			createPartitionsResult.all().get();
		}

		public static void alterConfig() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			// ConfigEntry ��ʾһ��������
			ConfigEntry configEntry = new ConfigEntry("cleanup.policy","compact");
			// ����������� Config
			Config config = new Config(Arrays.asList(configEntry));
			// ���� topic/broker ��Config ����map
			Map<ConfigResource, Config> configMap = Collections.singletonMap(new ConfigResource(ConfigResource.Type.TOPIC, "topic_1"), config);
			// ִ���޸�
			 AlterConfigsResult alterConfigsResult = kafkaAdminClient.alterConfigs(configMap);
			 alterConfigsResult.all().get(); // ������ֱ�����е��޸Ķ����
		}
		public static void describe() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			DescribeConfigsResult describeConfigsResult = kafkaAdminClient.describeConfigs(Arrays.asList(new ConfigResource(ConfigResource.Type.TOPIC,"topic_1")));
			Map<ConfigResource, Config> configMap = describeConfigsResult.all().get();
			for(Map.Entry<ConfigResource, Config> entry : configMap.entrySet()) {
				// ���ͺ�����
				System.out.println("type=" + entry.getKey().type() + " name=" + entry.getKey().name());
				// ��������
				System.out.println(entry.getValue());
			}
		}

		public static void createTopic() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			NewTopic newTopic = new NewTopic("topic_1", 1, (short)1);
			CreateTopicsResult createTopicsResult = kafkaAdminClient.createTopics(Arrays.asList(newTopic));
			createTopicsResult.all().get();// �����̣߳�ֱ���������ⴴ���ɹ�
		}

		public static void deleteTopic() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			DeleteTopicsResult deleteTopicsResult = kafkaAdminClient.deleteTopics(Arrays.asList("demo1","demo2","demo3","test"));
			deleteTopicsResult.all().get(); // �����̣߳�ֱ����������ɾ���ɹ�
		}

		public static void listTopics() throws InterruptedException, ExecutionException {
			Properties properties = new Properties();
			properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties);
			ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
			Collection<TopicListing>  topicListings = listTopicsResult.listings().get();
			for(TopicListing topicListing : topicListings) {
				System.out.println("��������:" + topicListing.name() + " �Ƿ����ڲ���:" + topicListing.isInternal());
			}
		}
		
-------------------------
AdminClientConfig		 |
-------------------------
	# ����ͻ��˵�������
	bootstrap.servers
		* broker�ڵ�ĵ�ַ
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
