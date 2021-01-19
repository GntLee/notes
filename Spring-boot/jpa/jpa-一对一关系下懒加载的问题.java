------------------------------------
һ��һ��ϵ�������ص�����
------------------------------------
	# �û�
		@Entity
		@Table(name = "user")
		public class User implements Serializable {
			
			private static final long serialVersionUID = 8175166175439387541L;
			
			@Id
			@Column(columnDefinition = "INT(11) UNSIGNED COMMENT '�û�id'")
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Integer id;
			
			@Column(columnDefinition = "varchar(20) COMMENT '�ǳ�'")
			private String name;
			
			// ����
			@OneToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "id", referencedColumnName = "user_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
			private UserSeting userSeting;
		}

	# �û�����
		@Entity
		@Table(name = "user_seting")
		public class UserSeting implements Serializable {
			
			private static final long serialVersionUID = -1007318207008996614L;

			@Id
			@Column(columnDefinition = "INT(11) unsigned COMMENT '����id'")
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Integer id;
			
			@Column(columnDefinition = "TINYINT(1) unsigned COMMENT '�Ƿ����֪ͨ'")
			private Boolean notify;
			
			@OneToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false, 
					foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
			private User user;
		}
	
	# ���ϵ
		CREATE TABLE `user` (
		  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '�û�id',
		  `gender` tinyint(1) unsigned NOT NULL COMMENT '�Ա�0��Ů��1����',
		  `name` varchar(20) DEFAULT NULL COMMENT '�ǳ�',
		  `version` int(11) unsigned NOT NULL COMMENT '�汾��',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

		CREATE TABLE `user_seting` (
		  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
		  `notify` tinyint(1) unsigned DEFAULT NULL COMMENT '�Ƿ����֪ͨ',
		  `user_id` int(11) unsigned NOT NULL COMMENT '�û�id',
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `UK_rdchrax5rp1m1y4kpax285krw` (`user_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
	
	# ������ʧЧ
		* ���� UserSeting , user ����������

		* ���� User, UserSeting ������ʧЧ
		* ��Ȼ����������һ���� UserSeting �ļ���, �����Ǹ���id����, ���Ǹ��ݹ���id
			select
				userseting0_.id as id1_4_0_,
				userseting0_.notify as notify2_4_0_,
				userseting0_.user_id as user_id3_4_0_ 
			from
				user_seting userseting0_ 
			where
				userseting0_.id=?
	
	# ԭ��
		* ���� UserSeting ��ʱ��, ��Ϊ UserSeting �и� user_id ���, �����֪�� UserSeting ��һ�� User, ����������
		* ���� User ��ʱ��, ��Ϊû���������, ��֪��ӵ�и� User �� UserSeting ��˭, ��ȷ�� UserSeting ���Ƿ��м�¼


------------------------------------
�������
------------------------------------
	# �� User, ��� UserSeting �Ĺ������
		* ȱ��������������˹�ϵ
	
	# �� UserSeting ��, ʹ�� User ��id, ��Ϊ�Լ���id, ��ʹ�ö���� userId �ֶ�

