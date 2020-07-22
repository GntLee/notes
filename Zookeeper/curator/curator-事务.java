
---------------------
 ����				 |
 --------------------
 
	// ��һ���������
	CuratorOp createOp = client.transactionOp().create().forPath("/a/path", "some data".getBytes());
	// �ڶ����������
	CuratorOp setDataOp = client.transactionOp().setData().forPath("/another/path", "other data".getBytes());
	// �������������
	CuratorOp deleteOp = client.transactionOp().delete().forPath("/yet/another/path");

	//�Ѷ����������Ϊһ���������,����ִ��,Ҫôȫ���ɹ�,Ҫôȫ��ʧ��,����ÿ��������ִ�н��
	Collection<CuratorTransactionResult> results = client.transaction().forOperations(createOp, setDataOp,deleteOp);
	
	for (CuratorTransactionResult result : results) {
		System.out.println(result.getForPath() + " - " + result.getType());
	}