---------------------
�̳�֧��
---------------------
	# �̳�֧��
		* ͨ�������ǣ������ṩ���ߺ������߶�ʹ��ͬһ�� @FeignClient �ӿ�
		* �ṩ���ṩʵ�֣������߲���Ҫʵ��
		
		* Spring �ٷ�Ҳ��������ô���������

	# Demo
		
		* �����ӿڣ�ֻ�����ӿں�URI��˫��������
			public interface UserService {
				@RequestMapping(method = RequestMethod.GET, value ="/users/{id}")
				User getUser(@PathVariable("id") long id);
			}
		
		* �����ṩ��ʵ��
			@RestController
			public class UserResource implements UserService {
				// TODO ʵ��
			}
		

		* ��������ߣ�ֱ�Ӽ̳й����ӿڣ���������
			package project.user;

			@FeignClient("users")
			public interface UserClient extends UserService {

			}
					
	
