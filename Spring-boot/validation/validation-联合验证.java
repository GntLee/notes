-------------------------------
������֤
-------------------------------
	# ����У�飬����������֤�߼��Ƚϸ��ӣ���ǰ���ݵ���֤��������������
	# �����Լ�����һ�� ��֤����, ʹ�� @AssertTrue/@AssertFalse �����У��
	
-------------------------------	
GroupSequenceProvider
-------------------------------
	# Hibernate �ṩ��ע��
		@Retention(RUNTIME)
		@Target({ TYPE })
		public @interface GroupSequenceProvider {

			/**
			 * @return The default group sequence provider implementation.
			 */
			Class<? extends DefaultGroupSequenceProvider<?>> value();
		}
