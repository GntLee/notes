-------------------
�¼��ͼ�����	   |
-------------------
	# ������¼�����
		Event

-------------------
�۲���			   |
-------------------
	# ����ļ������۲���
		Observable
			void addListener(InvalidationListener listener);
			void removeListener(InvalidationListener listener);
	
	# ϵͳԤ���������
		InvalidationListener
			void invalidated(Observable observable);
		ChangeListener
			void changed<T>(ObservableValue<? extends T> observable, T oldValue, T newValue);
		