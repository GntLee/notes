-------------------------
document - ����
-------------------------
	# ֧�����Ե���, ������key ��Ҫ���˫����
		db.user.find({"account.email": "10000@qq.com"});
	
	# ������, �����˵����������, �Ϳ���Ӧ��һ��
	
		db.user.insert({name: "vi", account: {email: '10000@qq.com', phone: '10086'}, skill:['java', 'python', 'javascript']});
		db.user.insert({name: "kk", account: {email: '99999@qq.com', phone: '10000'}, skill:['Ruby', 'C++', {lang: 'javascirpt', proficiency: 80}]});
		db.user.insert({name: "kk", account: {email: '99999@qq.com', phone: '10000'}, skill:[{lang: 'javascirpt', proficiency: 80},{lang: 'ruby', proficiency: 72},{lang: 'c', proficiency: 55}]});
		
		// �޸�
		db.user.update({"account.email": "10000@qq.com"}, {$set: {"account.phone": 10010}});
		
		// ��ѯ
		db.user.find({"account.phone": /\d+/});

-------------------------
document - ����
-------------------------
	# �ڲ�ѯ��
		* ��׼��ѯ, �����е�Ԫ�غ�λ�ñ���һ��һ��, ���ܱ�ƥ��
			db.user.find({"skill": ['java', 'python', 'javascript']});
		
		* ƥ�䵥��Ԫ��, ֻҪ������ָ��Ԫ��, �Ϳ���ƥ��
			db.user.find({"skill": 'java'}); // ����Ԫ�ز���д []
		
		* ƥ����Ԫ�أ�or��ϵ
			db.user.find({$or: [{"skill": "java"}, {"skill": "python"}]}); // and ��ϵҲͬ��
		
		* ƥ��������ĳ��ָ����Ԫ�أ�ĳ��ָ��λ�õ�Ԫ�أ�
			db.user.find({"skill.2": 'javascript'}); // ͨ�� arrayKey.[index] ָ��Ԫ�ص��±ꡣ�±��Ǵ�0��ʼ
	
	# ����Ԫ���Ƕ���
		* ����ƥ�䵥������
			db.user.find({"skill.lang": "java"}); // {skill: [{lang: "java"}]}
		
		* ƥ��������, ʹ�� $elemMatch
			db.user.find({"skill.lang": {$elemMatch: {lang: "Java", proficiency: 80}}});
			


	# ��������
	# ��������
	# ���޸���