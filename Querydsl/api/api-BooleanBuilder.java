---------------------
BooleanBuilder
---------------------
	# boolean �����Ĺ�����
		Expression
			|-BooleanBuilder

	
	# ���췽��
		public BooleanBuilder()
		public BooleanBuilder(Predicate initial)
	
	# ʵ������
		public <R,C> R accept(Visitor<R,C> v, C context)
		public BooleanBuilder and(@Nullable Predicate right)
		public BooleanBuilder andAnyOf(Predicate... args)
		public BooleanBuilder andNot(Predicate right)
		public BooleanBuilder clone()
		public BooleanBuilder not()
		public BooleanBuilder or(@Nullable Predicate right)
		public BooleanBuilder orAllOf(Predicate... args)
		public BooleanBuilder orNot(Predicate right)
		public Class<? extends Boolean> getType()
		public Predicate getValue()
