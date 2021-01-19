----------------------------------------
ssl˫����֤								|
----------------------------------------
	# һ��WebӦ�ö��ǲ���SSL������֤��
		* ԭ��ܼ�,�û���Ŀ�㷺,��������ͨѶ����û���ݽ�����֤,һ�㶼��Ӧ���߼�������֤�û��ĺϷ�����

	# ��ҵӦ�öԽ�,���ܻ�Ҫ��Կͻ���(��Զ���)�������֤
		* ��ʱ����Ҫ��SSL˫����֤
		* SSL˫����֤,Ҫ��ͻ��˺ͷ�������Ҫ���Լ���֤��,���һ��ർ���˹�Կ

----------------------------------------
֤������								|
----------------------------------------
	# ����֤��
		 keytool -genkey -alias [��Կ����] -validity [��Ч��(��)] -keystore [keystore�ļ�] -keyalg [�㷨(RSA)]

	# ������Կ
		keytool -export -alias [��Կ����] -file [��Կ�ļ�] -keystore [keystore�ļ�] -storepass [keystore����]

	# ���빫Կ
		keytool -import -alias [��Կ����] -keystore [keystore�ļ�] -file [��Կ�ļ�] -keypass [��Կ����] -storepass [keystore����]
	
	# ɾ������Ĺ�Կ
		keytool -delete -alias [��Կ����] -keystore [keystore�ļ�]

	# �鿴�Ѿ�����Ĺ�Կ�б�
		keytool -list -v -keystore [keystore�ļ�] -storepass [keystore����]

	# ��ʾ
		1 ���������֤��,���ҵ�����Կ
			keytool -genkey -alias server -validity 100 -keystore server.keystore -keyalg RSA
			
			keytool -export -alias server -file server.cer -keystore server.keystore
		
		2 �ͻ�������֤��
			keytool -genkey -alias client -validity 100 -keystore client.keystore -keyalg RSA
		
		3 �ͻ��˵������˹�Կ��֤��
			keytool -import -trustcacerts -alias server -file server.cer -keystore client.keystore
		
		4 �ͻ��˵�����Կ
			keytool -export -alias client -file client.cer -keystore client.keystore
		
		5 ����˵���ͻ��˹�Կ��֤��
			keytool -import -trustcacerts -alias client -file client.cer -keystore server.keystore
		
		* �ͻ��˴�֤��ɾ���Ѿ�����Ĺ�Կ
			keytool -delete -alias server -keystore client.keystore
			
		* ����˴�֤��ɾ���Ѿ�����Ĺ�Կ
			keytool -delete -alias client -keystore server.keystore
	

----------------------------------------
SSLContextFactory						|
----------------------------------------

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLContextFactory {

	// Э��
	private static final String PROTOCOL = "TLS";		//SSLv3
	
	// keystore ����
	private static final String KEY_KEYSTORE_TYPE = "JKS";
	
	// �㷨
	private static final String ALGORITHM = "SunX509";

	public static SSLContext getSslContext(String keystore, String password) throws NoSuchAlgorithmException,UnrecoverableKeyException, KeyStoreException, CertificateException, IOException, KeyManagementException {
		
		SSLContext sslContext = SSLContext.getInstance(PROTOCOL);

		KeyManager[] keyManagers = getKeyManagers(Files.newInputStream(Paths.get(keystore)), password);
		
		TrustManager[] trustManagers = getTrustManagers(Files.newInputStream(Paths.get(keystore)), password);

		sslContext.init(keyManagers, trustManagers, null);
		
		return sslContext;
	}

	private static KeyManager[] getKeyManagers(InputStream keystore, String password)throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException,IOException {
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(ALGORITHM);
		KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
		keyStore.load(keystore, password.toCharArray());
		keyManagerFactory.init(keyStore, password.toCharArray());
		KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
		return keyManagers;
	}

	private static TrustManager[] getTrustManagers(InputStream keystore, String password)throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(ALGORITHM);
		KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
		keyStore.load(keystore, password.toCharArray());
		trustManagerFactory.init(keyStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		return trustManagers;
	}
}



----------------------------------------
�����									|
----------------------------------------
	SSLEngine engine = SSLContextFactory.getSslContext("server.keystore","123456").createSSLEngine();
	engine.setUseClientMode(false);
	engine.setNeedClientAuth(true);  // ��Ҫ��֤�ͻ���
	socketChannel.pipeline().addLast(new SslHandler(engine));

----------------------------------------
�ͻ���									|
----------------------------------------
	SSLEngine engine = SSLContextFactory.getSslContext("client.keystore","123456").createSSLEngine();
	engine.setUseClientMode(true);
	socketChannel.pipeline().addLast(new SslHandler(engine));

----------------------------------------
���������Ƿ����						|
----------------------------------------
	# ����ͨ�������¼�,�������ȡ��SslHandler��handshakeFuture,�������ü���

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(new GenericFutureListener<Future<? super Channel>>() {
			@Override
			public void operationComplete(Future<? super Channel> future) throws Exception {
				if(!future.isSuccess()) {
					// ����ʧ��
					future.cause().printStackTrace();
				}
			}
		});
	}