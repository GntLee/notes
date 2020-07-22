


/**
��Ҫ����
<dependency>
	<groupId>com.google.zxing</groupId>
	<artifactId>core</artifactId>
	<version>3.4.0</version>
</dependency>
<dependency>
	<groupId>com.google.zxing</groupId>
	<artifactId>javase</artifactId>
	<version>3.4.0</version>
</dependency>
**/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.lang.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * ��ά�빤��
 * 
 * @author Administrator
 *
 */
public class QrCodeUtils {

	/**
	 * ���ɶ�ά��
	 * @param width		���
	 * @param height	�߶�
	 * @param content	����
	 * @param out		���
	 * @param logo		��ά���м��logo
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void qrCode(int width, int height, String content, OutputStream out, @Nullable InputStream logo) throws WriterException, IOException {
		
		String format = "png";// ͼ������
		
		Map<EncodeHintType, Object> hints = new HashMap<>(3);
		
		// ���ݱ����ʽ
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// ָ������ȼ�
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// ���ö�ά��ߵĿնȣ��Ǹ���
		hints.put(EncodeHintType.MARGIN, 1);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

		if (logo == null) { // ����Ҫlogo
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
		} else {
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
			BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig), logo);
			ImageIO.write(bufferedImage, format, out);
		}
	}


	/**
	 * ʶ���ά��
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String QRReader(InputStream qrCode) throws IOException, NotFoundException {
		MultiFormatReader formatReader = new MultiFormatReader();
		// ��ȡָ���Ķ�ά���ļ�
		BufferedImage bufferedImage = ImageIO.read(qrCode);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
		Map hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		com.google.zxing.Result result = formatReader.decode(binaryBitmap, hints);
//		System.out.println("���������" + result.toString());
//		System.out.println("��ά���ʽ���ͣ�" + result.getBarcodeFormat());
//		System.out.println("��ά���ı����ݣ�" + result.getText());
		bufferedImage.flush();
		return result.getText();
	}

	/**
	 * ��ά�����logo
	 * 
	 * @param matrixImage Դ��ά��ͼƬ
	 * @param logo    		logoͼƬ
	 * @return ���ش���logo�Ķ�ά��ͼƬ
	 */
	public static BufferedImage LogoMatrix(BufferedImage matrixImage, InputStream logo) throws IOException {
		/**
		 * ��ȡ��ά��ͼƬ����������ͼ����
		 */
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();

		/**
		 * ��ȡLogoͼƬ
		 */
		BufferedImage logoImage = ImageIO.read(logo);

		// ��ʼ����ͼƬ
		g2.drawImage(logoImage, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);// ����
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);// ���ñʻ�����
		
		// ָ�����ȵ�Բ�Ǿ���
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
		g2.setColor(Color.white);
		g2.draw(round);// ����Բ������

		// ����logo ��һ����ɫ�߿�
		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);// ���ñʻ�����
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
		
		g2.setColor(new Color(128, 128, 128));
		g2.draw(round2);// ����Բ������

		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}
}
