#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include <windows.h>
//��������Ϸ
int main(void) {

    //��������Ϊʱ���
    srand((unsigned int )time(NULL));

    //����ĸ���
    int random_numbers[4];

    //���������
    int input_number;

    //�����ĸ���
    int intput_numbers[4];

    for(int x = 0 ; x < 4 ; x++){
        random_numbers[x] = rand() % 10;
    }

    printf("�Ѿ��������ĸ������\n");

    while(true){
        printf("�����������λ��\n");

        scanf("%d",&input_number);

        intput_numbers[0] = (input_number / 1000) % 10;
        intput_numbers[1] = (input_number / 100) % 10;
        intput_numbers[2] = (input_number / 10) % 10;
        intput_numbers[3] = input_number % 10;

        int flag = 0;

        for(int x = 0 ;x < 4 ; x++){
            if(intput_numbers[x] > random_numbers[x]){
                printf("%d λ����\n", x + 1);
            }else if(intput_numbers[x] < random_numbers[x]){
                printf("%d λС��\n", x + 1);
            }else{
                printf("%d λ�¶���\n", x + 1);
                flag ++;
            }
        }

        if(flag == 4){
            printf("��ϲ��,ȫ���¶��ˡ�");
            Sleep(2000);
            break;
        }
    }
    
    return EXIT_SUCCESS;
}