//��ȡ�����ڶ��������

int max_second(int *p,size_t len){

	int max = 0;
	int second = 0;

	for(int x = 0 ;x < len; x++){
		int v = *(p + x);
		if(v > max){
			second = max;
			//�ڶ����ֵ,Ϊ��һ�ε����ֵ
			max = v;
		}else{
			if(v > second){
				second = v;
			}
		}
	}
	return second;
}