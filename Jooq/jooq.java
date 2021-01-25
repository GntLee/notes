----------------------------
Jooq
----------------------------
	# JOOQ
		* ȫ��Java Object Oriented Querying, ������Java�����ѯ
		* ����Data Geekery��˾�з���DA����(Data Access Layer), ��Ҫ�����������

	# �ٷ���ַ
		https://www.jooq.org/
		https://www.jooq.org/learn/
		https://github.com/jOOQ/jOOQ
		https://www.jooq.org/doc/current/manual-single-page/

	# �ο�ѧϰ
		https://amao12580.github.io/post/2016/04/JOOQ-from-entry-to-improve/

	# Maven ����
		<dependency>
			<groupId>org.jooq</groupId>
			<artifactId>jooq</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jooq</groupId>
			<artifactId>jooq-meta</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jooq</groupId>
			<artifactId>jooq-codegen</artifactId>
		</dependency>
	
	
	# ����
		// ��ʼ��Connection
		try(Connection connection = DriverManager.getConnection("jdbc....", "root", "root")){
			// ��ʼ��dslContext��ָ��jdbc���Ӻ����ݿⷽ��
			try(DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL)){
				// ִ�����󣬻�ȡ��ִ�н��
				Result<Record> result = dslContext.select().from(Tables.AUTHOR).fetch();
				System.out.println(result);
			}
		} 

----------------------------
Jooq �Զ�����
----------------------------
	# ͨ����������
		* windows
			java -classpath jooq-3.13.2.jar;^
			jooq-meta-3.13.2.jar;^
			jooq-codegen-3.13.2.jar;^
			reactive-streams-1.0.2.jar;^
			mysql-connector-java-5.1.18-bin.jar;. ^
			org.jooq.codegen.GenerationTool jooq.xml
		
		* Linux/Mac
			java -classpath jooq-3.13.2.jar:\
			jooq-meta-3.13.2.jar:\
			jooq-codegen-3.13.2.jar:\
			reactive-streams-1.0.2.jar:\
			mysql-connector-java-5.1.18-bin.jar:. \
			org.jooq.codegen.GenerationTool jooq.xml

	# ͨ����������
		import org.jooq.codegen.GenerationTool;
		import org.springframework.core.io.ClassPathResource;

		public class JooqGeneration {
			public static void main(String[] args) throws Exception {
				// ��classpath��ȡ���ļ���Դ
				ClassPathResource classPathResource = new ClassPathResource("/jooq/jooq.xml");
				// ��ȡ���ļ���Դ�ľ���·��
				String path = classPathResource.getFile().getAbsolutePath();
				// ���� GenerationTool �������
				GenerationTool.main(new String[] {path});
			}
		}
	
	# maven�Զ����ɲ�������飩
		jooq-plugin.xml