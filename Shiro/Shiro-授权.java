------------------------
��Ȩ					|
------------------------
	# ��ɫ��У�鷽ʽ
		1,����
			Subject subject = SecurityUtils.getSubject();
			if(subject.hasRole(��admin��)) {
				//��Ȩ��
			} else {
				//��Ȩ��
			}
		
		2,ע��ʽ��
			@RequiresRoles("admin")
			public void hello() {
				//��Ȩ��
			}
		
		3,JSP��ǩ
			<shiro:hasRole name="admin">
				<!�� ��Ȩ�� ��>
			</shiro:hasRole>
		
	
	# ��Ȩ
		* ��ini�������û�����Ϣ����Ȩ��ɫ
			[user]
			kevin=123456,admin,manager
		
		* �ж��û��Ƿ���ָ���Ľ�ɫ
			boolean subject().hasRole("admin")
				* �Ƿ���ָ���Ľ�ɫ
			boolean subject().hasAllRoles(Arrays.asList("admin", "manager"))
				* �Ƿ���ָ�������н�ɫ
			boolean[] subject().hasRoles(Arrays.asList("role1", "role2", "role3"))
				* �����н�ɫ����У��,���ص���һ��У������
			
			subject().checkRole("admin");
				* ������ָ���Ľ�ɫ,���û���׳��쳣
			subject().checkRoles("admin", "manager");
				* ������ָ�������н�ɫ,�������һ��û��,�׳��쳣
			
	
	# ������Դ�ķ��ʿ���(��ʾ��ɫ)
		* ini����
			[users]
			Kevin=123,role1,role2
			Litch=123,role1

			[roles]
			role1=user:create,user:update
			role2=user:create,user:delete

			* �û�:����,��ɫ1,��ɫ2
			* ��ɫ����:Ȩ��1,Ȩ��2			(��Դ��ʶ��:����)

		* ����
			subject().isPermitted("user:create")
				* �ж��Ƿ�߱�ָ����Ȩ��
			subject().isPermittedAll("user:update", "user:delete")
				* �ж��Ƿ�߱�ָ��������Ȩ��
			subject().isPermitted("user:view")
				* �ж��Ƿ񲻾߱�ָ����Ȩ��
			
			subject().checkPermission("user:create")
				* ������Ȩ��
			subject().checkPermissions("user:delete", "user:update")
				* ������ָ��������Ȩ��
			

		* �ַ���ͨ���Ȩ��
			* "��Դ��ʶ��:����:����ʵ��ID" �����ĸ���Դ���ĸ�ʵ�����Խ���ʲô����
			* ������Դ,����Ȩ��
				subject().checkPermissions("system:user:update");	//�û�ӵ����Դ"system:user"��"update"Ȩ��
			
			* ������Դ���Ȩ��
				role=system:user:update,system:user:delete
				subject().checkPermissions("system:user:update", "system:user:delete");	//�û�ӵ����Դ��system:user���ġ�update���͡�delete��Ȩ��
			
				role2="system:user:update,delete"
				subject().checkPermissions("system:user:update,delete");

				* ͨ��"system:user:update,delete" ��֤ "system:user:update, system:user:delete"��û�����
				* ���Ƿ������ǹ��򲻳���