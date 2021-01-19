--------------------------------
feign������					|
--------------------------------
	# ����Demo
		@FeignClient("USER-SERVICE")
		public interface UserFegin {
			
			@GetMapping("/user/{id}")
			UserEntity m1(@PathVariable("id")Integer userId);
				* rest����
			
			@GetMapping("/user")
			UserEntity m2(@RequestParam("id")Integer userId);
				* form ������
			
			@GetMapping("/user")
			UserEntity m3(@RequestHeader("h")String header);
				* ������ͷ����
			
			@PostMapping("/user")
			UserEntity m4(UserEntity userEntity);
				* �������ݷ�װΪ����
		}
		
	
	 # feign�ӿڱ���Ҫͨ��:@RequestHeader,@RequestParam,@RequestBody �����������
		* ���ұ���ͨ�� value ���������ò���������
		