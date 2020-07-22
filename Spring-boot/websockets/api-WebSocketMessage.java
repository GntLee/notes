-------------------------------
WebSocketMessage
-------------------------------
	# ��ʾ��Ϣ����ӿ� WebSocketMessage<T>
		T getPayload();
			* ��ȡ��Ϣ��

		int getPayloadLength();
			* ��Ϣ���С

		boolean isLast();
			* ������Ϣ, �Ƿ������һ֡
		
	# ����֯
		WebSocketMessage
			|-AbstractWebSocketMessage
			|-BinaryMessage<ByteBuffer> 
			|-PingMessage<ByteBuffer> 
			|-PongMessage<ByteBuffer> 
			|-TextMessage<String>
	
