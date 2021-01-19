----------------------
querydsl
----------------------
	# Maven
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-sql</artifactId>
			<version>${querydsl.version}</version>
		</dependency>

		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-sql-codegen</artifactId>
			<version>${querydsl.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>1.6.1</version>
		</dependency>
	
		<build>
			<finalName>springboot-querydsl</finalName>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<configuration>
						<executable>true</executable>
						<includeSystemScope>true</includeSystemScope>
					</configuration>
				</plugin>
				<plugin>
					<groupId>com.querydsl</groupId>
					<artifactId>querydsl-maven-plugin</artifactId>
					<version>${querydsl.version}</version>
					<executions>
						<execution>
							<goals>
								<goal>export</goal>
							</goals>
						</execution>
					</executions>
					<configuration>
						<!-- ��ѯ������������ -->
						<jdbcDriver>com.mysql.cj.jdbc.Driver</jdbcDriver>
						<jdbcUrl>jdbc:mysql://127.0.0.1:3306/querydsl?serverTimezone=GMT%2b8</jdbcUrl>
						<jdbcUser>root</jdbcUser>
						<jdbcPassword>root</jdbcPassword>
						<packageName>io.springboot.querydsl.domain</packageName>
						<targetFolder>${project.basedir}/target/generated-sources/java</targetFolder>
					</configuration>
					<dependencies>
						<dependency>
							<groupId>mysql</groupId>
							<artifactId>mysql-connector-java</artifactId>
							<version>8.0.18</version>
						</dependency>
					</dependencies>
				</plugin>
			</plugins>
		</build>

-------------------------------------
���ݱ����ͺ��ֶ����͵�ӳ��ת��
-------------------------------------
	#  ͨ�� configuration ����ת��
		<typeMappings>
		  <typeMapping>
			<table>IMAGE</table>
			<column>CONTENTS</column>
			<type>java.io.InputStream</type>
		  </typeMapping>
		</typeMappings>

	# ͨ�� configuration �����Զ����ת����
		<customTypes>
		  <customType>com.querydsl.sql.types.InputStreamType</customType>
		</customTypes>
	
	# �Զ���ת����, ʵ�ֽӿ�: Type
		* ���;���java����
		* ����һ���԰Ѷ��jdbc����ӳ��Ϊͬһ��java����
			interface Type {
				int[] getSQLTypes();			// sql���ͣ����Դ� Types ��ȡ��
				Class<T> getReturnedClass();	// java����
				String getLiteral(T value);
				@Nullable
				T getValue(ResultSet rs, int startIndex) throws SQLException;
				void setValue(PreparedStatement st, int startIndex, T value) throws SQLException;
			}


		* Ҳ����ֱ�Ӽ̳г�����
			public abstract class AbstractType<T> implements Type<T> {
				private final int type;
				public AbstractType(int type) {
					this.type = type;
				}
				@Override
				public final int[] getSQLTypes() {
					return new int[]{type};
				}
				@Override
				public String getLiteral(T value) {
					return value.toString();
				}
			}
	
		
		* demo
			import java.sql.PreparedStatement;
			import java.sql.ResultSet;
			import java.sql.SQLException;
			import java.sql.Types;
			import com.querydsl.sql.types.AbstractType;
			import io.springboot.querydsl.domain.enums.Gender;
			public class GenderType extends AbstractType<Gender>{
				public GenderType() {
					super(Types.TINYINT);
				}
				@Override
				public Class<Gender> getReturnedClass() {
					return Gender.class;
				}
				@Override
				public Gender getValue(ResultSet rs, int startIndex) throws SQLException {
					int value = rs.getInt(startIndex);
					for (Gender gender : Gender.values()) {
						if (value == gender.ordinal()) {
							return gender;
						}
					}
					throw new IllegalArgumentException(String.format("ö�٣�%s ��û��ordinalֵ=%d��ʵ��", Gender.class.getName(), value));
				}
				@Override
				public void setValue(PreparedStatement st, int startIndex, Gender value) throws SQLException {
					st.setInt(startIndex, value.ordinal());
				}
			}

-------------------------------------
���ݱ����ͺ��ֶ����͵�ӳ��ת��
-------------------------------------