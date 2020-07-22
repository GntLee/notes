----------------------
Window				  |
----------------------
	# ʵ���˽ӿ�: EventTarget

	# ���캯��
		protected Window()
	
	# protected ����
		void setScene(Scene value)
		void show()
		<T extends Event> void setEventHandler(final EventType<T> eventType, final EventHandler<? super T> eventHandler)

	# ʵ������
		<T extends Event> void addEventFilter(final EventType<T> eventType, final EventHandler<? super T> eventFilter)
		<T extends Event> void addEventHandler(final EventType<T> eventType, final EventHandler<? super T> eventHandler)
		EventDispatchChain buildEventDispatchChain(EventDispatchChain tail)
		void centerOnScreen()
		ObjectProperty<EventDispatcher> eventDispatcherProperty()
		void fireEvent(Event event)
		ReadOnlyBooleanProperty focusedProperty()
		EventDispatcher getEventDispatcher()
		EventHandler<WindowEvent> getOnCloseRequest()
		EventHandler<WindowEvent> getOnHidden()
		EventHandler<WindowEvent> getOnHiding()
		EventHandler<WindowEvent> getOnShowing()
		EventHandler<WindowEvent> getOnShown()
		
		ObservableMap<Object, Object> getProperties()
		Scene getScene()
		Object getUserData()
		boolean hasProperties()
		ReadOnlyDoubleProperty heightProperty()
			
		void hide()
		boolean isFocused()
		boolean isShowing()
		ObjectProperty<EventHandler<WindowEvent>> onCloseRequestProperty()
		ObjectProperty<EventHandler<WindowEvent>> onHiddenProperty()
		ObjectProperty<EventHandler<WindowEvent>> onHidingProperty()
		ObjectProperty<EventHandler<WindowEvent>> onShowingProperty()
		ObjectProperty<EventHandler<WindowEvent>> onShownProperty()
		DoubleProperty opacityProperty()
		<T extends Event> void removeEventFilter(final EventType<T> eventType, final EventHandler<? super T> eventFilter)
		<T extends Event> void removeEventHandler(final EventType<T> eventType, final EventHandler<? super T> eventHandler)
		void requestFocus()
		ReadOnlyObjectProperty<Scene> sceneProperty() 
		void setEventDispatcher(EventDispatcher value)
		void setOnCloseRequest(EventHandler<WindowEvent> value)
		void setOnHidden(EventHandler<WindowEvent> value)
		void setOnHiding(EventHandler<WindowEvent> value)
		void setOnShowing(EventHandler<WindowEvent> value)
		void setOnShown(EventHandler<WindowEvent> value)

		void setOpacity(double value)
		double getOpacity()
			* ͸����, ȡֵ��Χ: 0f..1f ,0 ����ȫ͸��

		void setUserData(Object value)
		void setWidth(double value)
		ReadOnlyBooleanProperty showingProperty() 
		void sizeToScene()
		ReadOnlyDoubleProperty widthProperty()
		
		
		ReadOnlyDoubleProperty xProperty()
		ReadOnlyDoubleProperty yProperty()
			* xy,�����property
		void setX(double value)
		void setY(double value)
		double getX()
		double getY()
			* ����/��ȡx,y����
			* x ��Ļ�ĺ���, ���Ͻ���0
			* y ��Ļ������, ���Ͻ���0
	
		void setHeight(double value)
		double getHeight()
		void setWidth(double value)
		double getWidth()
			* ��ȡ/���ÿ�߲���
			* ֻ���ڴ��� show() ��֮��, ���ܶ�ȡ�� ��߲���