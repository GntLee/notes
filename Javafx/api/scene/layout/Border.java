----------------------------------
Border
----------------------------------
	# public final class Border
	
	# ���췽��
		public Border(@NamedArg("images") BorderImage... images)
		public Border(@NamedArg("strokes") BorderStroke... strokes)
		public Border(@NamedArg("strokes") BorderStroke[] strokes, @NamedArg("images") BorderImage[] images)
		public Border(@NamedArg("strokes") List<BorderStroke> strokes, @NamedArg("images") List<BorderImage> images)


	# ��̬����
		public static final Border EMPTY = new Border((BorderStroke[])null, null);
	