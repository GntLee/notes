----------------------
Stream
----------------------
	# �������ĵĽӿھ���
		ReadStream
		WriteStream
	
	# �첽д��
		* д�������������صģ���д������ʵ�������ڲ��������Ŷ�д��
	
		* ��д�������ٶȱ�ʵ��д��ײ�������Դ�ٶȿ죬��ôд����оͻ��������������յ����ڴ�ľ�
		* ����ͨ���жϣ������Ƿ�װ���ˣ��پ���Ҫ��Ҫ����д�룬writeQueueFull
			if (!sock.writeQueueFull()) {
			  sock.write(buffer);  // �������û������д�롣����������ܶ�ʧ����
			}
		
		* ��ֹ��ʧ���ݣ���ͣһ�£�pause
			sock.write(buffer);				// ��д
			if (sock.writeQueueFull()) {	// ����������ˣ�����ͣһ��
			  sock.pause();					// ���ǣ��ȴ�����
			}
		
		* �����п����ˣ�ȡ����ͣ���ָ�д�룬resume
			ReadStream<String> readStream = null;
			WriteStream<String> writeStream = null;
			// ������Ϣ���¼�
			readStream.handler(message -> {
				writeStream.write(message);				// ��д������
				if (!writeStream.writeQueueFull()) {	// �����Ƿ�����
					readStream.pause();					// �������ˣ���ͣ��ȡ
					writeStream.drainHandler(vod -> {
						readStream.resume();			// �������п����¼����ָ���ȡ
					});
				}
			});
		
		* �Ѿ���װ�õķ�����pipeTo
			sock.pipeTo(sock);
								
