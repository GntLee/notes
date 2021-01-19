
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 
 * ��keystore����֤�飬˽Կ����Կ
 * 
 */
public class ExportCert {

	// ����֤�� base64��ʽ
	public static void exportCert(KeyStore keystore, String alias, String exportFile) throws Exception {
		Certificate cert = keystore.getCertificate(alias);
		Encoder encoder = Base64.getEncoder();
		String encoded = new String(encoder.encode(cert.getEncoded()));
		FileWriter fw = new FileWriter(exportFile);
		fw.write("-----BEGIN CERTIFICATE-----\r\n"); // �Ǳ���
		fw.write(encoded);
		fw.write("\r\n-----END CERTIFICATE-----"); // �Ǳ���
		fw.close();
	}

	// �õ�KeyPair
	public static KeyPair getKeyPair(KeyStore keystore, String alias, char[] password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		Key key = keystore.getKey(alias, password);
		if (key instanceof PrivateKey) {
			Certificate cert = keystore.getCertificate(alias);
			PublicKey publicKey = cert.getPublicKey();
			return new KeyPair(publicKey, (PrivateKey) key);
		}
		return null;
	}

	// ����˽Կ
	public static void exportPrivateKey(PrivateKey privateKey, String exportFile) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encoded = new String(encoder.encode(privateKey.getEncoded()));
		FileWriter fw = new FileWriter(exportFile);
		fw.write("���CBEGIN PRIVATE KEY���C\r\n"); // �Ǳ���
		fw.write(encoded);
		fw.write("\r\n���CEND PRIVATE KEY���C"); // �Ǳ���
		fw.close();
	}

	// ������Կ
	public static void exportPublicKey(PublicKey publicKey, String exportFile) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encoded = new String(encoder.encode(publicKey.getEncoded()));
		FileWriter fw = new FileWriter(exportFile);
		fw.write("���CBEGIN PUBLIC KEY���C\r\n"); // �Ǳ���
		fw.write(encoded);
		fw.write("\r\n���CEND PUBLIC KEY���C"); // �Ǳ���
		fw.close();
	}

	public static void main(String args[]) throws Exception {

		// keysotre�ļ�������
		String keysotreFile = "C:\\Users\\Administrator\\Desktop\\ssl\\server\\server.keystore";
		String password = "123456";
		
		// ����keystore�ļ�
		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(new FileInputStream(new File(keysotreFile)), password.toCharArray());

		// ֤��ı���
		String alias = "server";
		String exportCertFile = "C:\\Users\\Administrator\\Desktop\\ssl\\server.cer";
		String exportPrivateFile = "C:\\Users\\Administrator\\Desktop\\ssl\\serverPrivateKey.txt";
		String exportPublicFile = "C:\\Users\\Administrator\\Desktop\\ssl\\serverPublicKey.txt";

		// ����֤�鵽ָ��Ŀ¼
		ExportCert.exportCert(keystore, alias, exportCertFile);

		// ������Կ��˽Կ��ָ��Ŀ¼
		KeyPair keyPair = ExportCert.getKeyPair(keystore, alias, password.toCharArray());
		ExportCert.exportPrivateKey(keyPair.getPrivate(), exportPrivateFile);
		ExportCert.exportPublicKey(keyPair.getPublic(), exportPublicFile);
	}
}