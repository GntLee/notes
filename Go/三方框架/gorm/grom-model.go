---------------
model
---------------
	# ģ���Լ��ֶ�
		* ģ���Ǳ�׼�� struct
		* �ܶ��� Go �Ļ����������͡�ʵ���� Scanner �� Valuer �ӿڵ��Զ������ͼ���ָ���������
	
	# Ĭ��Լ��
		*  ʹ�� ID ��Ϊ����
		* ʹ�ýṹ������ ���θ���(�»���+����) ��Ϊ����
		* �ֶ����� ���� ��Ϊ����
		* ��ʹ�� CreatedAt��UpdatedAt �ֶ�׷�ٴ���������ʱ��
	
	# Ԥ�����һ���ṹ��
		// gorm.Model �Ķ���
		type Model struct {
		  ID        uint           `gorm:"primaryKey"`
		  CreatedAt time.Time
		  UpdatedAt time.Time
		  DeletedAt gorm.DeletedAt `gorm:"index"`
		}
	
	# �ֶ�Ȩ�޿���
		* �ɵ������ֶ���ʹ�� GORM ���� CRUD ʱӵ��ȫ����Ȩ��
		* GORM �����ñ�ǩ�����ֶμ����Ȩ�ޡ������Ϳ�����һ���ֶε�Ȩ����ֻ����ֻд��ֻ������ֻ���»��߱�����
		type User struct {
		  Name string `gorm:"<-:create"` // ֻ�ܶ�������
		  Name string `gorm:"<-:update"` // ֻ�ܶ�������
		  Name string `gorm:"<-"`        // ֻ�ܶ������£�����
		  Name string `gorm:"<-:false"`  // ֻ�ܣ���ֹд
		  Name string `gorm:"->"`        // readonly (disable write permission unless it configured )
		  Name string `gorm:"->;<-:create"` // allow read and create
		  Name string `gorm:"->:false;<-:create"` // createonly (disabled read from db)
		  Name string `gorm:"-"`  // ignore this field when write and read with struct
		}
	
	# ����/����ʱ��׷�٣����롢���롢�롢Time��
		* Լ��ʹ�� CreatedAt��UpdatedAt ׷�ٴ���/����ʱ��
		* ��������������ֶΣ�GORM �ڴ���������ʱ���Զ���� ��ǰʱ��
		* ���Ҫʹ�ò�ͬ���Ƶ��ֶΣ��������� autoCreateTime��autoUpdateTime ��ǩ
		* �����Ҫ���� UNIX����/�ɣ���ʱ����������� time��ֻ��򵥵ؽ� time.Time �޸�Ϊ int ����
			type User struct {
			  CreatedAt time.Time // �ڴ���ʱ��������ֶ�ֵΪ��ֵ����ʹ�õ�ǰʱ�����
			  UpdatedAt int       // �ڴ���ʱ���ֶ�ֵΪ��ֵ�����ڸ���ʱ��ʹ�õ�ǰʱ����������

			  Updated   int64 `gorm:"autoUpdateTime:nano"` // ʹ��ʱ����������������ʱ��
			  Updated   int64 `gorm:"autoUpdateTime:milli"` // ʹ��ʱ���������������ʱ��
			  Created   int64 `gorm:"autoCreateTime"`      // ʹ��ʱ���������䴴��ʱ��
			}
		
	# Ƕ��ṹ��
		* �����ֶΣ�GORM �Ὣ���ֶΰ����ڸ��ṹ����
		* ���������Ľṹ���ֶΣ�����ͨ����ǩ embedded ����Ƕ��
			type Author struct {
				Name  string
				Email string
			}

			type Blog struct {
			  ID      int
			  Author  Author `gorm:"embedded"`		// embeddedǶ��
			  Upvotes int32
			}

			// ��Ч��
			type Blog struct {
			  ID    int64
				Name  string
				Email string
			  Upvotes  int32
			}
		
		* ����ʹ�ñ�ǩ embeddedPrefix ��Ϊ db �е��ֶ������ǰ׺
			type Blog struct {
			  ID      int
			  Author  Author `gorm:"embedded;embeddedPrefix:author_"`
			  Upvotes int32
			}
			// ��Ч��
			type Blog struct {
			  ID          int64
				AuthorName  string
				AuthorEmail string
			  Upvotes     int32
			}
	
	# ����
		* ��ͨ����/Ψһ����
			type User struct {
				ID uint
				NickName string `gorm:"type:varchar(10); index:name"`			// `nick_name` varchar(10) DEFAULT NULL / KEY `name` (`nick_name`)
				Email string `gorm:"type:varchar(50); uniqueIndex:mail"`		// `email` varchar(50) DEFAULT NULL	/ UNIQUE KEY `mail` (`email`)
				Number uint `gorm:"type:int(50) unsigned; index:,unique"`		//  `number` int(50) unsigned DEFAULT NULL / UNIQUE KEY `idx_users_number` (`number`)
			}

		* ����������2��index������ͬ������������
			type UserEmail struct {
				ID uint
				UserId uint		`gorm:"uniqueIndex:user_id_email; type:INT(11) UNSIGNED NOT NULL COMMENT '�û�ID'"`
				Email string	`gorm:"uniqueIndex:user_id_email; type:VARCHAR(50) NOT NULL COMMENT '�����˻�'"`
			}

			* ֻҪ���κ�һ������Ϊ uniqueIndex�������յ�������������unique��
			* ����ͨ�� priority ָ��������˳��ֵԽС��Խ��ǰ����Ĭ�����ȼ�ֵ�� 10��������ȼ�ֵ��ͬ����˳��ȡ����ģ�ͽṹ���ֶε�˳��
				Email string	`gorm:"index:user_id_email,priority:5; type:VARCHAR(50) NOT NULL COMMENT '�����˻�'; "`
				
			
		* ͬһ���ֶΣ�����������ظ������� index ��ǩ����
			type UserEmail struct {
				ID uint
				UserId uint		`gorm:"index:user_id_email; type:INT(11) UNSIGNED NOT NULL COMMENT '�û�ID'"`
				Email string	`gorm:"index:user_id_email; index:email; type:VARCHAR(50) NOT NULL COMMENT '�����˻�'; "`
			}
			KEY `user_id_email` (`user_id`,`email`),
			KEY `email` (`email`)
	
	# ����ID�����ö��primaryKey����
		type UserEmail struct {
			UserId uint		`gorm:"primaryKey; type:INT(11) UNSIGNED NOT NULL COMMENT '�û�ID'"`
			EmailId uint	`gorm:"primaryKey; type:VARCHAR(50) NOT NULL COMMENT '����ID'; "`
		}
		
	
	# �Զ������������ͨ��ʵ�� Tabler �ӿ�������Ĭ�ϱ���
		type Tabler interface {
			TableName() string
		}
	
		* TableName ��֧�ֶ�̬�仯�����ᱻ���������Ա����ʹ��
	
	

---------------
���б�ǩ
---------------	
	column					ָ�� db ����
	type					���������ͣ��Ƽ�ʹ�ü����Ժõ�ͨ�����ͣ�
		* ���磺�������ݿⶼ֧�� bool��int��uint��float��string��time��bytes 
		* ���ҿ��Ժ�������ǩһ��ʹ�ã����磺not null��size, autoIncrement�� �� varbinary(8) ����ָ�����ݿ���������Ҳ��֧�ֵġ�
		* ��ʹ��ָ�����ݿ���������ʱ������Ҫ�����������ݿ��������ͣ��磺MEDIUMINT UNSIGNED not NULL AUTO_INSTREMENT
	size					ָ���д�С�����磺size:256
	primaryKey				ָ����Ϊ����
	unique					ָ����ΪΨһ
	default					ָ���е�Ĭ��ֵ�������¼�����ݿ�ʱ��Ĭ��ֵ �ᱻ���� ���ֵΪ ��ֵ ���ֶ�
	precision				ָ���еľ���
	scale					ָ���д�С
	not null				ָ����Ϊ NOT NULL
	autoIncrement			ָ����Ϊ�Զ�����
	autoIncrementIncrement	�Զ�����������������¼֮��ļ��
	embedded				Ƕ���ֶ�
	embeddedPrefix			Ƕ���ֶε�����ǰ׺
	autoCreateTime			����ʱ׷�ٵ�ǰʱ�䣬���� int �ֶΣ�����׷���뼶ʱ�����������ʹ�� nano/milli ��׷�����롢����ʱ��������磺autoCreateTime:nano
	autoUpdateTime			����/����ʱ׷�ٵ�ǰʱ�䣬���� int �ֶΣ�����׷���뼶ʱ�����������ʹ�� nano/milli ��׷�����롢����ʱ��������磺autoUpdateTime:milli
	index					���ݲ�����������������ֶ�ʹ����ͬ�������򴴽������������鿴 ���� ��ȡ����
	uniqueIndex				�� index ��ͬ������������Ψһ����
	check					�������Լ�������� check:age > 13���鿴 Լ�� ��ȡ����
	<-						�����ֶ�д���Ȩ�ޣ� <-:create ֻ������<-:update ֻ���¡�<-:false ��д��Ȩ�ޡ�<- �����͸���Ȩ��
	->						�����ֶζ���Ȩ�ޣ�->:false �޶�Ȩ��
	-						���Ը��ֶΣ�- �޶�дȨ��
	comment					Ǩ��ʱΪ�ֶ����ע��

------------------------
����ģ��Demo
---------------	---------
	// �û�
	type User struct {
		Id 				uint		`gorm:"type:int(11) unsigned not null auto_increment comment 'ID';"`
		NickName 		string		`gorm:"type:varchar(20) default null comment '�ǳ�'"`
		Email 			string		`gorm:"type:varchar(50) not null comment '����'; index:email,unique"`
		CreateAt 		*time.Time 	`gorm:"type:timestamp not null default current_timestamp comment '����ʱ��'"`
		UpdateAt 		*time.Time	`gorm:"type:timestamp not null default current_timestamp comment '�޸�ʱ��'"`
		Version 		uint		`gorm:"type:int(11) unsigned not null comment '�汾��'"`
		Introduction	string		`gorm:"type:varchar(200) default null comment '����˵��'"`
	}

	// �û����� ��һ��һ��
	type UserConfig struct {
		UserId 		uint	`gorm:"primaryKey; type:int(11) unsigned not null comment '�û�ID'"`
		AllowFollow bool	`gorm:"type:tinyint(1) unsigned not null comment '�Ƿ�������˹�ע��'"`
	}

	// ��ɫ
	type Role struct {
		Id		uint	`gorm:"type:int(11) unsigned not null auto_increment comment 'ID';"`
		Name	string	`gorm:"type:varchar(20) not null comment '��ɫ����'"`
	}

	// �û���ɫ���� ����Զࣩ
	type UserRole struct {
		UserId	uint	`gorm:"primaryKey; type:int(11) unsigned not null comment '�û�ID'"`
		RoleId	uint	`gorm:"primaryKey; type:int(11) unsigned not null comment '��ɫID'; index:role_id"`
	}

	# ͨ�õ����
		type Menu struct {
			Id int				`gorm:"type:int(32) unsigned auto_increment; primaryKey; comment:ID" json:"id"`
			ParentId int		`gorm:"type:int(32) unsigned; not null; size:32; comment:�����˵�ID"'"`
			Type MenuType		`gorm:"not null; size:10; comment:�˵�����"`
			Icon string			`gorm:"size:50; comment:Сͼ��"`
			Title	string		`gorm:"not null; size:20; uniqueIndex:title; comment:����"`
			Mapping	string		`gorm:"not null; size:30; uniqueIndex:mapping_method; comment:����·��"`
			Method string		`gorm:"not null; size:10; uniqueIndex:mapping_method; comment:���󷽷�"`
			Enabled bool		`gorm:"type:tinyint(1) unsigned; not null; comment:�Ƿ���"`
			Comment string		`gorm:"size:200; comment:��ע"`
			CreateAt *time.Time	`gorm:"type:timestamp not null default current_timestamp; comment:����ʱ��"`
			UpdateAt *time.Time	`gorm:"type:timestamp null default null; comment:�޸�ʱ��"`
		}
