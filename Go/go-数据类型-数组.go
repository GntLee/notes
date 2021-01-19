-------------------------
����
-------------------------
	# Go�е����飬����ָ�����鳤���Լ�������
		* ����һ����ʼ����Ϻ󣬲����޸�

	# �������������ʼ��
		* �������ȣ����ʼ�����ڲ���ʼ����ʱ��Ĭ��ֵ�����������͵�Ĭ��ֵ��
			var arr [3]bool
			arr[0] = true
			arr[1] = false
			arr[2] = true
			fmt.Println(arr)
			
			
			var arr [3]bool
			arr = [3]bool{true, true, true}

		
		* ���������ȣ�ֱ�ӳ�ʼ��
			arr := [...]int {1,2,3,4,5,6}

			* [...] ��ʼ�����ٸ�Ԫ�أ�����Ͷ೤


		* ͬʱ�������Ⱥ�ֱ�ӳ�ʼ��
			arr := [100]int {1, 2}
			var arr [3]int = [3]int{1, 2, 3}
			
			* Ԫ�ظ������ܴ��ڳ��ȣ����ұ������������ã������쳣
				arr := [5]int {1, 2, ,4,5} // syntax error: unexpected comma, expecting expression

			* ���Ԫ�ظ���С�ڳ��ȣ�ʣ�µ�Ԫ��ΪĬ��ֵ

		
		* ����������ʼ��������Ϊ��ͬ�±����ò�ͬ��ֵ
			arr := [3]int {0:1, 2, 2:3}
			arr := [5]int {0:1, 3:3, 1:2}

			* ��{}�У�ͨ�� [�±�]:[ֵ]����Ϊָ�����±긳ֵ������δָ����λ�ã�����Ĭ��ֵ
			* �����ظ������±��ֵ
				arr := [3]int {0: 1, 1: 2, 1:3} // duplicate index in array literal: 1

			* ָ���±�����ֵ��Ĭ�����±���ƶ�һλ
				arr := [5]int {0:1, 3:3, 1:2, 9}	// [1 2 9 3 0]  ��9ǰ����±���1������9���±�2��
		
		* ͨ��new��ʼ��һ�����飬���ص���һ�������ָ��
			a := new([5]int)
			fmt.Println(a)	// &[0 0 0 0 0]

		
	# ���Ⱥ����ͣ��������һ����
		* ���Ȳ�ͬ���������Ͳ�ͬ�������໥�Ƚϸ�ֵ����֮����ԡ�
			var arr0 [4]bool
			var arr1 [3]bool
			fmt.Println(arr0 == arr1)	// invalid operation: arr0 == arr1 (mismatched types [4]bool and [3]bool)
			arr0 = arr1					// cannot use arr1 (type [3]bool) as type [4]bool in assignment

		
	# ͨ���±� []������Ԫ��
		* ʹ�� len() ȫ�ֺ�������ȡ���鳤��
		* �±��0��ʼ
		* ���Խ����׳��쳣 // invalid array index 8 (out of bounds for 3-element array)
	
	
	# ���� 
		* range ����������ֵ���±�
			arr := [5]int {1, 2, 3, 4, 5}
			for i, v := range arr {
				fmt.Printf("index=%d val=%d \n", i, v)
			}

		* range ֻдһ������������ֻ�����±�
			var arr [3]int
			for i := range arr {
				fmt.Println(i)
			}
		
		* �±����
			arr := [5]int {1, 2, 3, 4, 5}
			for i := 0; i < len(arr); i++ {
				fmt.Printf("index=%d val=%d \n", i, arr[i])
			}
	
	# ��ά����
		arr := [2][2]int {{1, 2}, {1, 2}}		// [[1 2] [1 2]]
		var arr [3][2]int						// [[0 0] [0 0] [0 0]]
		var arr [2][0]int						// [[] []]
		var arr [0][0]int						// []
		
		* ������һά����һ����ֻ�ǲ�����Ԫ�ر��������
		* �м�ά������Ӽ���[]
	
		* ά������ĳ�ʼ��������Ҫ��������������һ��
				var arr = [3][2]int {
					0: [2]int{1, 2},
					1: [...]int{3, 4}
				}
		
			var  arr = [3][2]int {
				[2]int {1, 2},
				1: [2]int {3, 4},
				[2]int {5, 6},
			}
	
	# �����ʼ����ʱ�򣬴����Ż����ˣ����һ�б������, ��β
		var  arr = [3]int {
			1, 
			2, 
			3,		// �������, ��Ȼ�쳣��syntax error: unexpected newline, expecting comma or }
		}
		fmt.Println(arr)
	
	# ������һ��ֵ����
		arr1 := [3]int{1, 2, 3}
		arr2 := arr1
		arr2[0] = 9
		fmt.Println(arr1, arr2) // [1 2 3] [9 2 3]

		* ����������arr1������ֵ���ͣ���ֵ��ʵ�Ǹ��Ƹ���һ������arr2����arr2�����仯��arr1�����ᷢ���κα仯

	# ���������
		* ֧�� == �� != ����
