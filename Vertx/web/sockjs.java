---------------------
Sockjs
---------------------
	# Demo
		// sockjs ����
		SockJSHandlerOptions options = new SockJSHandlerOptions().setHeartbeatInterval(2000);
		
		// ����handler
		SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);
		
		// ��� mainRouter
		mainRouter.mountSubRouter("/channel", sockJSHandler.socketHandler(socket -> {
			LOGGER.info("�µ����ӣ�remote={}", socket.remoteAddress());
			socket.handler(buffer -> {
				
				String message = buffer.toString();
				
				LOGGER.info("�µ���Ϣ��{}", message);
				if ("bye".equalsIgnoreCase(message)) {
					socket.close(WebSocketCloseStatus.NORMAL_CLOSURE.code(), "be");
					return;
				}
				
				// ��д���ͻ���
				socket.write(message)
					.onSuccess(vod -> {
					}).onFailure(exception -> {
						LOGGER.info("��Ӧ�ͻ����쳣��{}", exception.getMessage());
					});
			});
			socket.endHandler(vod -> {
				LOGGER.info("���ӶϿ���{}", socket.remoteAddress());
			});
			socket.exceptionHandler(exception -> {
				LOGGER.info("�����쳣��{}", exception.getMessage());
			});
		}));