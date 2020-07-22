import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

import javax.websocket.Session;


import ch.qos.logback.core.OutputStreamAppender;
import io.springboot.log.web.socket.channel.LogChannel;

public class SocketOutputStreamAppender<E> extends OutputStreamAppender<E> {
	
	@Override
	public void start() {

		// �ܵ���ȡ��
		PipedInputStream pipedInputStream = new PipedInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pipedInputStream, StandardCharsets.UTF_8));
		
		// �ܵ�д����
		PipedOutputStream pipedOutputStream = new PipedOutputStream();
		
		try {
			 // д���ȡ�ܵ�����
			pipedOutputStream.connect(pipedInputStream); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// �����������Appender
		super.setOutputStream(pipedOutputStream);
		
		// ���߳̿�ʼ�ӹܵ�����ȡ����
		Thread thread = new Thread(() -> {
			String line = null;
			while (true) {
				try {
					line = bufferedReader.readLine();
				} catch (IOException e) {
					// e.printStackTrace();
				}
				if (line != null) {
					for (Session channel : LogChannel.CHANNELS.values()) {
						if (channel.isOpen()) { 
							// �첽�������ݵ��ͻ���
							channel.getAsyncRemote().sendText(line);
						}
					}
				}
			}
		});
		
		thread.setDaemon(Boolean.TRUE);
		thread.setName("socket-log");
		thread.start();
		
		super.start();
	}
}
