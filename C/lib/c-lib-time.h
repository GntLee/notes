------------------------
time					|
------------------------

CLK_TCK
	* ����ʱ��ÿ�����ߵ�ʱ�Ӵ����(һ���ж��ٸ�clock_t)
	* printf("%d",CLK_TCK);  //1000

time()
	* ���ص�ǰϵͳʱ��(����),�ĸ��ֽ�

clock_t  clock (void);
	* ���س���ӿ�ʼִ�е�ִ�иú����ĺķ�ʱ��
	* ���ʱ�䵥λ��: clock tick �� "ʱ�Ӵ��"
	* �������Ϊ���ص��Ǻ���
	* Demo
		//��¼��ʼ�ͽ���
		clock_t start,stop;
		//����ʱ��,��λΪ��
		double duration;

		int main(int argc, char **argv) {
			start = clock();
			//TODO ִ��Ŀ�꺯������
			stop = clock();
			duration = ((double)(stop - start)) / CLK_TCK;
			printf("�ķѣ�%f��\n",duration);
			return EXIT_SUCCESS;
		}
