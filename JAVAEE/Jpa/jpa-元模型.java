--------------------------
Ԫģ��
--------------------------
	# ��������ʵ���������
		* ����ҲҪ�� EntityManager ����, �� Entity ����ͬһ������
		* �� Entity ��ͬ��, ������_
		* �淶Ԫģ���������ȫ���� static �� public ��
	
	# Demo
		* ʵ����
			@Entity
			@Table
			public class Employee {  
				private int id;   
				private String name;
				private int age;
				@OneToMany
				private List<Address> addresses;
			}
	
		* ʵ�����Ԫģ��
			import javax.annotation.Generated;
			import javax.persistence.metamodel.SingularAttribute;
			import javax.persistence.metamodel.ListAttribute;
			import javax.persistence.metamodel.StaticMetamodel;

			@StaticMetamodel(Employee.class)
			public abstract class Employee_ {     
				public static volatile SingularAttribute<Employee, Integer> id;   
				public static volatile SingularAttribute<Employee, Integer> age;   
				public static volatile SingularAttribute<Employee, String> name;    
				public static volatile ListAttribute<Employee, Address> addresses;
			}
	
		* ���ܶ� @MappedSuperclass �̳е��������þ�̬����������


	# ���������������ӿ�
		SingularAttribute
			* ��ͨ������
		CollectionAttribute
		MapAttribute
		SetAttribute
		ListAttribute
			* һ�Զ�/��Զ�����

--------------------------
�Զ�����Ԫģ��
--------------------------
	# �Զ�����Ԫģ�͵�maven���
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-jpamodelgen</artifactId>
			<version>5.4.10.Final</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.1.0.Final</version>
		</dependency>
		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
				<compilerArguments>
					<processor>org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor</processor>
				</compilerArguments>
			</configuration>
		</plugin>

