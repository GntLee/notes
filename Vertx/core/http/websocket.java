------------------
websocket
------------------
	# ͨ�� HttpServer ����ws����
		.webSocketHandler(webSocket -> {
			webSocket.closeHandler(vod -> { // �����ر�
				// ����Ϊnull������ִ���� reject();
				Short code = webSocket.closeStatusCode();
				String reason = webSocket.closeReason();
				System.out.println(String.format("code=%d, reason=%s", code, reason));
			});
			
			if (webSocket.path().equals("/channel")) { // ֻ�������URI������

			} else {
				// �ܾ����֣��Զ��ر�����
				webSocket.reject(404);
			}
		})

	# �첽����WebSocket����
		server.webSocketHandler(webSocket -> {
		  Promise<Integer> promise = Promise.promise();
		  webSocket.setHandshake(promise.future());
		  authenticate(webSocket.headers(), ar -> {
			if (ar.succeeded()) {
			  // ��101״̬�루Э���л�����������
			  // ����401״̬�루δ��Ȩ���ܾ�����
			  promise.complete(ar.succeeded() ? 101 : 401);
			} else {
			  // ����500����״̬��
			  promise.fail(ar.cause());
			}
		  });
		});

		* �����ֶ�������WebSocket���ִ�������������ã�webSocketHandler����ģ��������󣬽��Զ�����WebSocket���֡�
	
	# Э���л�Ϊ WebSocket
		httpServer.requestHandler(req -> {
			if (req.path().equals("/channel")) {
				// ����ΪwebSocket
				req.toWebSocket().onSuccess(socket -> {
					socket.closeHandler(vod -> { // �����ر�
						Short code = socket.closeStatusCode();
						String reason = socket.closeReason();
						System.out.println(String.format("code=%d, reason=%s", code, reason));
					});
					socket.textMessageHandler(message -> {
						System.out.println("�յ��ͻ�����Ϣ��" + message);
						socket.writeTextMessage(message).onComplete(result -> {
							System.out.println("��Ϣ���ͽ����success=" + result.succeeded());
						});
					});
				})
				// �����쳣
				.onFailure(err -> {
					System.err.println("ws�����쳣��" + err.getMessage());
				});
			} else {
				req.response().setStatusCode(401).end("fail");
			}
		})
			