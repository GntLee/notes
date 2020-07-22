--------------------------------
DataFieldMaxValueIncrementer	|
--------------------------------
	# 用于获取DB自增的主键
	# 不同的DB,有不同的实现
		mysql实现
			MySQLMaxValueIncrementer
	# DataFieldMaxValueIncrementer 接口定义了3个获取下一个主键值的方法
	　　int nextIntValue()
			* 获取下一个主键值,主键数据类型为int

	　　long nextLongValue()
			* 获取下一个主键值，主键数据类型为long

	　　String nextStringValue()
			* 获取下一个主键值,主键数据类型为String
	

	# SpringBoot 配置
		@Autowired
		private DataSource dataSource;
		
		@Bean
		public DataFieldMaxValueIncrementer userIdGenarater(){
			MySQLMaxValueIncrementer m = new MySQLMaxValueIncrementer();
			//名称
			m.setIncrementerName("user_id_sequence");
			//列
			m.setColumnName("id");
			//缓存大小
			m.setCacheSize(1);
			//设置数据源
			m.setDataSource(dataSource);
			return m;
		}