---------------
EntityGraph
---------------
	@NamedEntityGraph
		String name() default "";
		NamedAttributeNode[] attributeNodes() default {};	

			@NamedAttributeNode
				|-String value();	
					* ��������
				|-String subgraph() default "";
				|-String keySubgraph() default "";

			* ָ��Ҫjoin����������

		boolean includeAllAttributes() default false;

		NamedSubgraph[] subgraphs() default {};
			@NamedSubgraph
				|-String name();
				|-Class type() default void.class;
				|-NamedAttributeNode[] attributeNodes();
		
		NamedSubgraph[] subclassSubgraphs() default {};
			@NamedSubgraph
					|-String name();
					|-Class type() default void.class;
					|-NamedAttributeNode[] attributeNodes();
		

		* ��ʶ��ʵ������
		* �ڹ���������ʱ��, FetchType��ô����LAZY����EAGER��SQL����ִ�е�ʱ������һ�������ѯ��N���ӱ��ѯ��ɵ�
		* ���ֲ�ѯЧ��һ��Ƚϵ���, �����Ӷ�����N���ͻ�ִ��N+1��SQL

		* ����ͨ�� @NamedEntityGraphs ���ö��
			NamedEntityGraph[] value();
		
	@EntityGraph
		String value() default "";
			* ָ�� Entity ʵ����ָ��name�� @NamedEntityGraph
		
		EntityGraphType type() default EntityGraphType.FETCH;
			* ���ط�ʽ, ö��

			LOAD	
			FETCH	

		String[] attributePaths() default {};
			* ������ʱʵ���ṩ������ attributePaths ��ת��Ϊ, EntityGraph
			* ������ʽ��� @NamedEntityGraph ��ʵ��, Ҳ����Ҫ��ʶ value ����


		* ��� @NamedEntityGraph ���join����, �����ڹ�����ϵ�н���n+1�μ���������
		* ��ʵ�����ϱ�ʶ @NamedEntityGraph ָ��Ҫjoin�������ֶ�
	

---------------
demo
---------------
	# ͬ��ʵ���ʶ @NamedEntityGraph
		// ʵ���ʶ
		@NamedEntityGraph(name = "User.loadAll", attributeNodes = {
			@NamedAttributeNode("addresses"),			// ָ��Ҫjoin������ User ʵ���еĹ�������
			@NamedAttributeNode("roles")
		})
		@Entity
		@Table(name = "user")
		public class User


		// Repository  ����������ʶ
		@EntityGraph(value = "User.loadAll",type = EntityGraph.EntityGraphType.FETCH)
		@Override
		List<User> findAll();

		// ���ɵ�SQL
		select
			user0_.id as id1_2_0_,
			addresses1_.id as id1_0_1_,
			role3_.id as id1_1_2_,
			user0_.name as name2_2_0_,
			addresses1_.name as name2_0_1_,
			addresses1_.user_id as user_id3_0_1_,
			addresses1_.user_id as user_id3_0_0__,
			addresses1_.id as id1_0_0__,
			role3_.name as name2_1_2_,
			roles2_.user_id as user_id1_3_1__,
			roles2_.role_id as role_id2_3_1__ 
		from
			user user0_ 
		left outer join
			address addresses1_ 
				on user0_.id=addresses1_.user_id 
		left outer join
			user_role roles2_ 
				on user0_.id=roles2_.user_id 
		left outer join
			role role3_ 
				on roles2_.role_id=role3_.id


	# ֱ�ӱ�ʶ @EntityGraph
		@EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"roles"})
		@Query("FROM User")
		List<User> findTest();

	   select
			user0_.id as id1_2_0_,
			role2_.id as id1_1_1_,
			user0_.gender as gender2_2_0_,
			user0_.name as name3_2_0_,
			user0_.version as version4_2_0_,
			role2_.name as name2_1_1_,
			roles1_.user_id as user_id1_3_0__,
			roles1_.role_id as role_id2_3_0__ 
		from
			user user0_ 
		left outer join
			user_role roles1_ 
				on user0_.id=roles1_.user_id 
		left outer join
			role role2_ 
				on roles1_.role_id=role2_.id