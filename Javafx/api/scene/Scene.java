--------------------
Scene				|
--------------------
	# Scene implements EventTarget

	# ���캯��
		public Scene(@NamedArg("root") Parent root)
		public Scene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height)
		public Scene(@NamedArg("root") Parent root, @NamedArg(value="width", defaultValue="-1") double width, @NamedArg(value="height", defaultValue="-1") double height, @NamedArg("depthBuffer") boolean depthBuffer)
		public Scene(@NamedArg("root") Parent root, @NamedArg(value="width", defaultValue="-1") double width, @NamedArg(value="height", defaultValue="-1") double height, @NamedArg("depthBuffer") boolean depthBuffer, @NamedArg(value="antiAliasing", defaultValue="DISABLED") SceneAntialiasing antiAliasing)
		public Scene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height, @NamedArg(value="fill", defaultValue="WHITE") Paint fill)
		public Scene(@NamedArg("root") Parent root, @NamedArg(value="fill", defaultValue="WHITE") Paint fill)\
	
	# ��̬����

	# ʵ������
		void setCursor(Cursor value)
			* �������ָ���ʱ�����ʽ
			* Cursor ��һ��������, �Ѿ�Ԥ������N����ʽ
				DEFAULT
				CROSSHAIR
				TEXT
				WAIT
				SW_RESIZE
				SE_RESIZE
				NW_RESIZE
				NE_RESIZE
				N_RESIZE
				S_RESIZE
				W_RESIZE
				E_RESIZE
				OPEN_HAND
				CLOSED_HAND
				HAND
				MOVE
				DISAPPEAR
				H_RESIZE
				V_RESIZE
				NONE
		* Cursor ���߱�һ����̬����: Cursor cursor(final String identifier), ���Լ���ͼƬ��Ϊ������ʽ
			URL url = getClass().getClassLoader().getResource("favicon.ico");
			String path = url.toExternalForm();
			scene.setCursor(Cursor.cursor(path));

			
