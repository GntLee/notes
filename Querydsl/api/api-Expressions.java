--------------------------
Expressions
--------------------------
	# ���ڷ���ʵ��·��

	# ��̬����
		public static final NumberExpression<Integer> ONE = numberTemplate(Integer.class, "1");

		public static final NumberExpression<Integer> TWO = numberTemplate(Integer.class, "2");

		public static final NumberExpression<Integer> THREE = numberTemplate(Integer.class, "3");

		public static final NumberExpression<Integer> FOUR = numberTemplate(Integer.class, "4");

		public static final NumberExpression<Integer> ZERO = numberTemplate(Integer.class, "0");

		public static final BooleanExpression TRUE = booleanTemplate("true");

		public static final BooleanExpression FALSE = booleanTemplate("false");
	
	# ��̬����
		<T> SimplePath<T> path(Class<? extends T> type, String variable) 
		<T> SimplePath<T> path(Class<? extends T> type, Path<?> parent, String property)
	