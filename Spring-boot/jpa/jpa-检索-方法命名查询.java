---------------------------------
���ݷ���������					 |
---------------------------------
	# ʹ��find��ͷ
	# �����ѯ��������ж����Ҫʹ�� List<T> ��Ϊ��������ֵ
		* ��Ȼ��������ֶ����ʱ����׳��쳣: IncorrectResultSizeDataAccessException, NonUniqueResultException
	
	


---------------------------------
���ݷ�������������				 |
---------------------------------
	# ����
		�ؼ���				������													JPQL
		And					findByLastnameAndFirstname								�� where x.lastname = ?1 and x.firstname = ?2
		Or					findByLastnameOrFirstname								�� where x.lastname = ?1 or x.firstname = ?2
		Is, Equals			findByFirstname,findByFirstnameIs,findByFirstnameEquals	�� where x.firstname = ?1
		Between				findByStartDateBetween									�� where x.startDate between ?1 and ?2
		LessThan			findByAgeLessThan										�� where x.age < ?1
		LessThanEqual		findByAgeLessThanEqual									�� where x.age <= ?1
		GreaterThan			findByAgeGreaterThan									�� where x.age > ?1
		GreaterThanEqual	findByAgeGreaterThanEqual								�� where x.age >= ?1
		After				findByStartDateAfter									�� where x.startDate > ?1
		Before				findByStartDateBefore									�� where x.startDate < ?1
		IsNull, Null		findByAge(Is)Null										�� where x.age is null
		IsNotNull, NotNull	findByAge(Is)NotNull									�� where x.age not null
		Like				findByFirstnameLike										�� where x.firstname like ?1
		NotLike				findByFirstnameNotLike									�� where x.firstname not like ?1
		StartingWith		findByFirstnameStartingWith								�� where x.firstname like ?1 (parameter bound with appended %)
		EndingWith			findByFirstnameEndingWith								�� where x.firstname like ?1 (parameter bound with prepended %)
		Containing			findByFirstnameContaining								�� where x.firstname like ?1 (parameter bound wrapped in %)
		OrderBy				findByAgeOrderByLastnameDesc							�� where x.age = ?1 order by x.lastname desc
		Not					findByLastnameNot										�� where x.lastname <> ?1
		In					findByAgeIn(Collection<Age> ages)						�� where x.age in ?1
		NotIn				findByAgeNotIn(Collection<Age> ages)					�� where x.age not in ?1
		True				findByActiveTrue()										�� where x.active = true
		False				findByActiveFalse()										�� where x.active = false
		IgnoreCase			findByFirstnameIgnoreCase								�� where UPPER(x.firstame) = UPPER(?1)
	
	# ��֧��������һЩ�﷨(PartTree)
		find			
		read
		get
		query
		stream

		count
		exists
			
		delete
		remove

		* ʹ�õ�ʱ��, Ҫ��ϲ�ͬ�ķ��ؽ���������
	
	# ֧�����Ե���
		class User {
			Address address;
		}
		class Address {
			String name;
		}
		UserRepositroy extends JpaRepository<User, Integer>{
			findByAddressName(String name); // ��address���Ե���������name����
			findByAddress_Name(String name); // ��Ϊ��ѧ��д��, ͨ���»��߱�ʶ�����Ľڵ�
		}

		* jpa����, �»����Ǳ�����ʶ��
		* �����»������ƻ���java���շ����
	
	# ͨ�������������ƽ����
		findFirst10ByName(String name);
		findTop10ByName(String name);

		* ͨ�� First/Top ������
	
	# �ο���:
		PartTreeJpaQuery
		PropertyExpression

---------------------------------
��ҳ������						 |
---------------------------------
	# ֻ��Ҫ�����һ����������: Sort / Pageable 
	# ����ֵ��Ҫ����Ϊ��Ӧ�ķ���ֵ
		

---------------------------------
Demo							 |
---------------------------------
	findByName(String name);

	findByNameLike(String name);
		* LIKE����, �ؼ�����Ҫ�Լ����ͨ���
			findByNameLike("%keywords%");

	findByNameAndId(String name, Integer id);

	findByIdBetween(Integer startId, Integer endId);
	findByIdLessThan(Integer id);
		* ���� id �ֶ�С�� id �ļ�¼

	findByIdIn(List<Integer> ids);

	findByCreateDateAfter(LocalDateTime createDate);
		* ���� create_date �ֶδ��� createDate  �ļ�¼
	