
------------------------
·��
------------------------
	# һ��·�����ñ����Ͼ��� RouteDefinitionLocator �ӿڵ�ʵ��
		public interface RouteDefinitionLocator {
			Flux<RouteDefinition> getRouteDefinitions();
		}
	
	# Ϊ·�����Ԫ����
		spring:
		  cloud:
			gateway:
			  routes:
			    - id: route_with_metadata
				  uri: https://example.org
				  metadata: # Ԫ������Ϣ
				    optionName: "OptionValue"
				    compositeObject:
					  name: "value"
				    iAmNumber: 1