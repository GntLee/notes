----------------------------
JobStore					|
----------------------------
	# JobStore�������, �ṩ�����ȳ��������"��������": jobs, triggers, Calendar... ��

	# ����
		AbstractTerracottaJobStore
			|-TerracottaJobStore
		JobStoreSupport
			|-JobStoreCMT
				|-LocalDataSourceJobStore
			|-JobStoreTX
		RAMJobStore
		ClusteredJobStore
			|-DefaultClusteredJobStore
		TerracottaJobStoreExtensions
			|-PlainTerracottaJobStore
	
	# ͨ����������ʵ��
		org.quartz.jobStore.class=org.quartz.simpl.RAMJobStore
	

	