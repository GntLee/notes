--------------------------------
DataFieldMaxValueIncrementer	|
--------------------------------
	# ���ڻ�ȡDB����������
	# ��ͬ��DB,�в�ͬ��ʵ��
		mysqlʵ��
			MySQLMaxValueIncrementer
	# DataFieldMaxValueIncrementer �ӿڶ�����3����ȡ��һ������ֵ�ķ���
	����int nextIntValue()
			* ��ȡ��һ������ֵ,������������Ϊint

	����long nextLongValue()
			* ��ȡ��һ������ֵ��������������Ϊlong

	����String nextStringValue()
			* ��ȡ��һ������ֵ,������������ΪString
	

	# SpringBoot ����
		@Autowired
		private DataSource dataSource;
		
		@Bean
		public DataFieldMaxValueIncrementer userIdGenarater(){
			MySQLMaxValueIncrementer m = new MySQLMaxValueIncrementer();
			//����
			m.setIncrementerName("user_id_sequence");
			//��
			m.setColumnName("id");
			//�����С
			m.setCacheSize(1);
			//��������Դ
			m.setDataSource(dataSource);
			return m;
		}