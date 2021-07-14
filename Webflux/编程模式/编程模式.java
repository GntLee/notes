--------------------------
���ģʽ
--------------------------
	# ��Ҫ��д�ĺ������
		RouterFunction
			* ·��ӳ��ӿ�
		
		RouterFunctions
			* ·��ӳ�乤����

		HandlerFunction
			* ������ӿ�
		
		HandlerFilterFunction
			* �������������ӿ�
		

		
--------------------------
����
--------------------------
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.reactive.function.server.*;
	import reactor.core.publisher.Mono;

	@Configuration
	public class DemoRouter implements HandlerFunction {

		@Bean
		public RouterFunction<ServerResponse> router (){
			return RouterFunctions.route()
					// ָ�������Լ�������
					.GET("/router", this)
					// ��ӹ�����
					.filter((request, next) -> next.handle(request))
					.build();
		}
		@Override
		public Mono<ServerResponse> handle (ServerRequest request){
			return ServerResponse.ok().bodyValue("Success");
		}
	}