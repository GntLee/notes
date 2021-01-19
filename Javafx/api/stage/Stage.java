---------------------
stage				 |
---------------------
	# �̳��� Window ���

	# ���캯��
		Stage()
		Stage(@Default("javafx.stage.StageStyle.DECORATED") StageStyle style)
	
	# ʵ������
		boolean isAlwaysOnTop()
		void setAlwaysOnTop(boolean value)
			* �����Ƿ�һֱ������ǰ��

		ReadOnlyBooleanProperty alwaysOnTopProperty()
		void close()
			* �رմ���

		ObjectProperty<String> fullScreenExitHintProperty()
		ObjectProperty<KeyCombination> fullScreenExitKeyProperty()
		ReadOnlyBooleanProperty fullScreenProperty()
		String getFullScreenExitHint()
		KeyCombination getFullScreenExitKeyCombination()
		double getMaxHeight()
		double getMaxWidth()
		double getMinHeight()
		double getMinWidth()
		Modality getModality()
		Window getOwner()
		StageStyle getStyle()
		
		void initModality(Modality modality)
			* ����ģ̬, ö��
				NONE
					* Ĭ��, ʲôҲû

				WINDOW_MODAL
					* �ô��ڻ�һֱ�ڸ�����������ǰ��, ������ر�, ���޷�������������
					* �ֲ������

				APPLICATION_MODAL  
					* ��ǰӦ��, �ô��ڻ�һֱ����ǰ��, ������ر�, �޷�����Ӧ�õ���������
					* ȫ�־����
			
			* primaryStage �������� modalitymodality (Cannot set modality for the primary stage)
				


		void initOwner(Window owner)
			* ���õ�ǰ���, Ϊ owner �������

		void initStyle(StageStyle style)
			* ���ô��������, ö��
				DECORATED(Ĭ��)
				UNDECORATED(��͸���Ĵ���)
				TRANSPARENT(��͸���Ĵ���)
				UTILITY(�����, ��С��ť, ֻ�йر�)
				UNIFIED(�ޱ߿�)

		
		boolean isMaximized()
		
		DoubleProperty maxHeightProperty()
		ReadOnlyBooleanProperty maximizedProperty()
		DoubleProperty maxWidthProperty()
		DoubleProperty minHeightProperty()
		DoubleProperty minWidthProperty()
		BooleanProperty resizableProperty()
		
		void setFullScreenExitHint(String value)
		void setFullScreenExitKeyCombination(KeyCombination keyCombination)
		
		void setFullScreen(boolean value)
		boolean isFullScreen()
			* ����/��ȡ�Ƿ�ȫ��,(��ESC�Ż��˳�����)
			* ��Ҫ�������� Scene �Ż���Ч
		
		void setMaximized(boolean value)
			* �����Ƿ������(ȫ��)
		
		void setMaxHeight(double value)
		void setMaxWidth(double value)
		void setMinHeight(double value)
		void setMinWidth(double value)
			* ����/��ȡ�����С�Ŀ��

		void setResizable(boolean value)
		boolean isResizable()
			* ����/��ȡ�Ƿ���������

		void setScene(Scene value)

		String getTitle()
		void setTitle(String value)
			* ����/��ȡ����
		
		ObservableList<Image> getIcons()
		ReadOnlyBooleanProperty iconifiedProperty()
		void setIconified(boolean value)
		boolean isIconified()
			* icon������


		void show()
		void showAndWait()
		StringProperty titleProperty()
		void toBack()
		void toFront() 