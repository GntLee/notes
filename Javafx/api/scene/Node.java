----------------------
Node			
----------------------
	# abstract class Node implements EventTarget, Styleable
	

	# ʵ������
		void setLayoutX(double value)
		void setLayoutY(double value)
			* ����x/y���λ��
		
		void setOpacity(double value)
		double getOpacity()
			* ���������͸����, 0 - 1, ֵԽ��Խ��͸��
			
			
		boolean contains(double localX, double localY)
		boolean contains(Point2D localPoint)
			* �ж�ָ���������Ƿ����������
			* ����Ҫ��x�������

		
	