----------------------------
JsonPointerIterator
----------------------------
	# �Զ���ʵ�ֵĽӿ�	interface JsonPointerIterator 

----------------------------
����
----------------------------
	boolean isObject(@Nullable Object currentValue);
	boolean isArray(@Nullable Object currentValue);
	boolean isNull(@Nullable Object currentValue);
	boolean objectContainsKey(@Nullable Object currentValue, String key);
	Object getObjectParameter(@Nullable Object currentValue, String key, boolean createOnMissing);
	Object getArrayElement(@Nullable Object currentValue, int i);
	boolean writeObjectParameter(@Nullable Object currentValue, String key, @Nullable Object value);
	boolean writeArrayElement(@Nullable Object currentValue, int i, @Nullable Object value);
	boolean appendArrayElement(@Nullable Object currentValue, @Nullable Object value);


----------------------------
��̬
----------------------------
	JsonPointerIterator JSON_ITERATOR = new JsonPointerIteratorImpl();
		* Ԥ�����ʵ��