-----------------------------
ö��						 |
-----------------------------
	# ö�������﷨�����ʽΪ
		enum DAY
		{
			  MON=1, TUE, WED, THU, FRI, SAT, SUN
		};

		* ��һ��ö�ٳ�Ա��'Ĭ��ֵΪ���͵� 0',����ö�ٳ�Ա��ֵ��ǰһ����Ա�ϼ� 1
		* �����ʵ���аѵ�һ��ö�ٳ�Ա��ֵ����Ϊ 1,�ڶ�����Ϊ 2,�Դ�����

	# �����ڶ���ö������ʱ�ı�ö��Ԫ�ص�ֵ
		enum season {spring, summer=3, autumn, winter};

		* ��ָ��ֵ��ö��Ԫ��,��ֵΪǰһԪ�ؼ� 1,Ҳ��˵ spring ��ֵΪ 0,summer ��ֵΪ 3,autumn ��ֵΪ 4,winter ��ֵΪ 5
	
	# ö�ٱ����Ķ���
		* ǰ��ֻ��������ö������,ͨ���������ַ�ʽ������ö�ٱ���
		
		1,�ȶ���ö�����ͣ��ٶ���ö�ٱ���
			enum DAY
			{
				  MON=1, TUE, WED, THU, FRI, SAT, SUN
			};
			enum DAY day;
		
		2,����ö�����͵�ͬʱ����ö�ٱ���
			enum DAY
			{
				  MON=1, TUE, WED, THU, FRI, SAT, SUN
			} day;
		
		3,ʡ��ö������,ֱ�Ӷ���ö�ٱ���
			enum
			{
				  MON=1, TUE, WED, THU, FRI, SAT, SUN
			} day;
	
	# ö�ٵı���
		* ��C ������,ö�������Ǳ����� int ���� unsigned int �����������,���԰��� C ���Թ淶��û�а취����ö�����͵�
		* ������һЩ����������,ö�����ͱ��������ǿ���ʵ���������ı���
			#include<stdio.h>
			enum DAY
			{
				  MON=1, TUE, WED, THU, FRI, SAT, SUN
			} day;
			int main()
			{
				// ����ö��Ԫ��
				for (day = MON; day <= SUN; day++) {
					printf("ö��Ԫ�أ�%d \n", day);
				}
			}
		*  ö�����Ͳ�����,����ö���޷�����
	
	# ö���� switch �е�ʹ��
		enum color { red=1, green, blue };

		enum  color favorite_color;
	 
		printf("��������ϲ������ɫ: (1. red, 2. green, 3. blue): ");
		scanf("%d", &favorite_color);
	 
		switch (favorite_color){
			case red:
				printf("��ϲ������ɫ�Ǻ�ɫ");
				break;
			case green:
				printf("��ϲ������ɫ����ɫ");
				break;
			case blue:
				printf("��ϲ������ɫ����ɫ");
				break;
			default:
				printf("��û��ѡ����ϲ������ɫ");
		}
	
	# ������ת��Ϊö��
		enum Color {
			RED,
			BLUE,
			YELLOW,
			BLACK
		};
		int a = 0;
		enum Color color = (enum Color)a;