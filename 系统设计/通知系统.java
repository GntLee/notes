----------------------------
֪ͨϵͳ					|
----------------------------
	# ֪ͨ���
		* ����
		* ����
			- ��Դ�������ѡ��ҹ�ע����Դ�и��¡����۵��¼�ʱ֪ͨ�ҡ�
			- ��Դ�������ѡ��ҷ�������Դ�����ۡ��ղص��¼�ʱ֪ͨ�ҡ�
			- ϵͳ���ѡ�ƽ̨�����һЩ�㷨������ȿ��ܻ�������Դ��һЩ���飬��ʱ����յ�ϵͳ֪ͨ��
		* ˽��
	
----------------------------
����						|
----------------------------
	# ����� notify
		id			//������
		userId		//�����߱�ţ�ͨ��Ϊϵͳ����Ա
		titletype	//�������
		content		// ����
		createdAt	//����ʱ��
	
	# �û������ user_notify
		id					 //�û������ţ�
		notifyId			//����id
		userId				//�û�id
		createdAt			//��ȡ����ʱ�䣻
		state				//״̬���Ѷ�|δ����
		readDate			//�Ķ�ʱ��
	
	# ��ȡδ��֪ͨ
		* ƽ̨����һ�򹫸�֮��, ���û���¼��ʱ��ȥ��ȡվ�ڹ���(notify)������ user_notify ��, ������Щ�ܾö�û��½���û���û��Ҫ������
		*���״���ȡ�������û���ע��ʱ��: ������� user_notify.createdAt ����һ����ȡ��ʱ��ڵ��ȡ���桹
	
	# ���û��������ʱ��, ����Hash�ֱ�
		user_notify_{hash(user_id)}

	
----------------------------
����						|
----------------------------
	# ���ѵ����ݹ�ʽ
		��someone do something in someone��s something��
		��˭��һ������˭����������ʲô������

		someone			�¼�������(�û�)
		do something	�¼�(��Ϊ)
		someone��s		��Դ������(�û�)
		something		��Դ
	

	notify_event ֪ͨ�¼���
		id: {type: 'integer', primaryKey: true, autoIncrement:true} 
		userID: {type: 'string', required: true} //�û�ID
		action: {type: 'string', required: true} //�������磺���/����/����/�ղ�
		objectID: {type: 'string', required: true}, //����ID���磺����ID��
		objectType: {type: 'string', required: true} //�����������ͣ��磺�ˡ����¡������Ƶ�ȣ�
		createdAt��{type: 'timestamp', required: true} //����ʱ�䣻
	
	notify_setting_config	֪ͨ�������ñ�
		id: {type: 'integer', primaryKey: true, autoIncrement:true} //֪ͨ���ñ�ţ�
		objectType: {type: 'string', required: true} //��Դ�������ͣ��磺��Ŀ�����¡����ۡ���Ʒ����Ƶ��ͼƬ���û���
		action: {type: 'string', required: true} //������Ҳ��֪ͨ���ͣ��磺�����¡����ۡ��ղ�
		objectRelationship: {type: 'string', required: true} //�û�����Դ�Ĺ�ϵ���磺�û�������published���û���ע��followed��
		messageTemplate: {type: 'string', required: true} //Ϊĳ��֪ͨ�������ö�Ӧ����Ϣģ��
		notifyChannel: {type: 'string', required: true} //Ϊĳ��֪ͨ��������һ��������������
		description: {type: 'string', required: true}  //����ѡ�����������
		settingType: {type: 'string', required: true} //remind��privateLetters
	
	notify_setting	֪ͨ���ñ�
		id: {type: 'integer', primaryKey: true, autoIncrement:true} //�û�֪ͨ����ID��
		userId: {type: 'string', required: true}��//�û�ID����Ӧ notify_remind �е� recipientId��
		settingId: {type: 'string', required: true}��//֪ͨ���ñ�ID��
		createdAt��{type: 'timestamp', required: true} //����ʱ�䣻
	
	notify_remind ֪ͨ���ѱ�
		id: {type: 'integer', primaryKey: true, autoIncrement:true} //������
		remindID: {type: 'string', required: true} //֪ͨ���ѱ�ţ�
		senderID: {type: 'string', required: true} //�����ߵ�ID������0������ϵͳ���͵ģ�
		senderName: {type: 'string��, required: true} //�������û�����
		senderAction: {type: 'string', required: true} //�����ߵĶ������磺�����¡����ۡ��ղأ�
		objectID: {type: 'string', required: true}, //Ŀ�����ID��
		object: {type: 'string', required: false}, //Ŀ��������ݻ��飬���磺���±��⣻
		objectType: {type: 'string', required: true} //�������������ͣ��磺�ˡ����¡������Ƶ�ȣ�
		recipientID: {type: 'string��} //��Ϣ�����ߣ������Ƕ���������߻����ߣ�
		message: {type: 'text', required: true} //��Ϣ���ݣ�������ģ�����ɣ���Ҫ��ǰ���壻
		createdAt��{type: 'timestamp', required: true} //����ʱ�䣻
		status��{type: 'integer', required: false} //�Ƿ��Ķ���Ĭ��δ����
		readAt:{type: 'timestamp', required: false} //�Ķ�ʱ�䣻


	-----------------------------
		Notify
		id          : {type: 'integer', primaryKey: true},      // ����
		content     : {type: 'text'},   // ��Ϣ������
		type        : {type: 'integer', required: true, enum: [1, 2, 3]},  // ��Ϣ�����ͣ�1: ���� Announce��2: ���� Remind��3����Ϣ Message
		target      : {type: 'integer'},    // Ŀ���ID
		targetType  : {type: 'string'},    // Ŀ�������
		action      : {type: 'string'},    // ������Ϣ�Ķ�������
		sender      : {type: 'integer'},    // �����ߵ�ID
		createdAt   : {type: 'datetime', required: true}

		UserNotify
		id          : {type: 'integer', primaryKey: true},      // ����
		isRead      : {type: 'boolean', required: true},   
		user        : {type: 'integer', required: true},  // �û���Ϣ������
		notify      : {type: 'integer', required: true}   // ������Notify
		createdAt   : {type: 'datetime', required: true}

		Subscription
		target      : {type: 'integer', required: true},    // Ŀ���ID
		targetType  : {type: 'string', required: true},    // Ŀ�������
		action      : {type: 'string'},						// ���Ķ���,��: comment/like/post/update etc.
		user        : {type: 'integer'}��
		createdAt   : {type: 'datetime', required: true}

----------------------------
˽��						|
----------------------------
