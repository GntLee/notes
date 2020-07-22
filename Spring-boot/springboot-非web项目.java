------------------------------
springboot �� web��Ŀ		  |
------------------------------
	# �ڷ� web ��Ŀ��ʹ��springboot
	# MAVEN
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.1.3.RELEASE</version>
		</parent>

		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter</artifactId>
			</dependency>
		</dependencies>

		<build>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<configuration>
						<executable>true</executable>
						<includeSystemScope>true</includeSystemScope>
					</configuration>
				</plugin>
			</plugins>
		</build>

		* ���� pom ���ǽ���ʹ�� parent
		* ������Ҫ����һ�� spring-boot-starter �Ϳ�����
	
	# Main ����
		import java.io.IOException;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.ConfigurableApplicationContext;

		@SpringBootApplication
		public class Main {

			public static void main(String[] args) throws IOException {

				SpringApplication springApplication = new SpringApplication(Main.class);
				
				ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
				
				System.in.read();
			}
		}

	