-----------------------------
Vertx
-----------------------------
	# �ٷ���ַ
		https://vertx.io/
		https://github.com/eclipse-vertx/vert.x
		https://vertx-china.github.io/
		https://how-to.vertx.io/

		https://vertx.io/docs/apidocs/
	
	# ѧϰ�ο�
		https://segmentfault.com/a/1190000021036621
		http://vertxchina.github.io/vertx-translation-chinese/

		https://www.bilibili.com/video/BV1Ep4y1Y7Do
	
	# Maven
		<dependency>
			<groupId>io.vertx</groupId>
			<artifactId>vertx-core</artifactId>
			<version>3.9.2</version>
		</dependency>
		
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.8.1</version>
			<configuration>
				<compilerArgs>
					<arg>-parameters</arg>
				</compilerArgs>
				<source>1.8</source>
				<target>1.8</target>
				<encoding>UTF-8</encoding>
			</configuration>
		</plugin>

		* ����JDK8
	

-----------------------------
����
-----------------------------
	io.vertx.core.Launcher run [verticle]