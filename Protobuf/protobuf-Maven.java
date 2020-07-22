--------------------
Maven ���
--------------------
	# ���
		<!-- https://mvnrepository.com/artifact/org.xolstice.maven.plugins/protobuf-maven-plugin -->
		<dependency>
			<groupId>org.xolstice.maven.plugins</groupId>
			<artifactId>protobuf-maven-plugin</artifactId>
			<version>0.6.1</version>
		</dependency>
		
		* ����������ĵ�
			https://www.xolstice.org/protobuf-maven-plugin/compile-mojo.html
	
	# ��������
			<plugin>
				<groupId>org.xolstice.maven.plugins</groupId>
				<artifactId>protobuf-maven-plugin</artifactId>
				<version>0.6.1</version>
				<configuration>
					<!-- proto���������ŵ���Ŀ��Ŀ¼�� -->
					<protocExecutable>${project.basedir}/protoc-3.11.2-win64/bin/protoc.exe</protocExecutable>
					<!-- proto �ļ����ڵ��ļ��� ���ŵ���Ŀ��Ŀ¼��-->
					<protoSourceRoot>${project.basedir}/proto</protoSourceRoot>
					<!-- ��������ļ��� -->
					<outputDirectory>${project.build.sourceDirectory}</outputDirectory>
					<!-- �Ƿ�ÿ�α���ǰ����������ļ��� -->
					<clearOutputDirectory>false</clearOutputDirectory>
					<!-- ��ʱ�ļ��� -->
					<temporaryProtoFileDirectory>${project.build.directory}/protoc-dependencies</temporaryProtoFileDirectory>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
							<goal>test-compile</goal>
						</goals>
					</execution>
				</executions>
			</plugin>


