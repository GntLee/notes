----------------------
Platform
----------------------
	# Platform һ����̬������, ƽ̨!

	# ��̬����
		void runLater(Runnable runnable)
			* ���һ�����񵽶���
			* ע��, ��������������һ���߳���ִ��, ����ʹ��UI�߳�: JavaFX Application Thread ��ִ�е�
			* �������ִ�д���������, ���ܵ���UI��������
			* һ������ִ�и����������֮�������

		boolean isFxApplicationThread()

		void exit()
			* �˳�Ӧ��

		ReadOnlyBooleanProperty accessibilityActiveProperty()

		boolean isAccessibilityActive()

		boolean isSupported(ConditionalFeature feature)
			* �жϵ�ǰ�����Ƿ�֧��һЩ����, ö��
				GRAPHICS
				CONTROLS
				MEDIA
				WEB
				SWT
				SWING
				FXML
				SCENE3D
				EFFECT
				SHAPE_CLIP
				INPUT_METHOD
				TRANSPARENT_WINDOW
				UNIFIED_WINDOW
				TWO_LEVEL_FOCUS
				VIRTUAL_KEYBOARD
				INPUT_TOUCH
				INPUT_MULTITOUCH
				INPUT_POINTER
	
		boolean isImplicitExit()
		void setImplicitExit(boolean implicitExit)
			* �����ֵΪ true, ��ô�ڹص���������һ�����ڵ�ʱ���, ����ͻ��˳�
			* �����ֵΪ false, �����˳�����, ����Ҫ�ֶ��ĵ��� Platform.exit() �������˳�����
		


	
