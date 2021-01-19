
public static void word2Pdf(InputStream src, OutputStream dsc) throws IOException {
		
	//����Openoffice������
	OpenOfficeConnection connection = new SocketOpenOfficeConnection("192.168.265", 8100);
	connection.connect();

	//Դ�ļ�document docx ����
	DocumentFormat docxDocumentFormat = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT,"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
	
	//Դ�ļ�document doc ����
	DocumentFormat docDocumentFormat = new DocumentFormat("Microsoft Word",
	DocumentFamily.TEXT,"application/msword", "doc");
	docDocumentFormat.setExportFilter(DocumentFamily.TEXT, "MS Word 97");


	//Ŀ���ļ�document pdf����
	DocumentFormat pdfDocumentFormat = new DocumentFormat("Portable Document Format", "application/pdf", "pdf");
	pdfDocumentFormat.setExportFilter(DocumentFamily.DRAWING, "draw_pdf_Export");
	pdfDocumentFormat.setExportFilter(DocumentFamily.PRESENTATION, "impress_pdf_Export");
	pdfDocumentFormat.setExportFilter(DocumentFamily.SPREADSHEET, "calc_pdf_Export");
	pdfDocumentFormat.setExportFilter(DocumentFamily.TEXT, "writer_pdf_Export");

	
	//���ط�����ת��
	//DocumentConverter documentConverter = new OpenOfficeDocumentConverter(connection);	
	
	//Զ�̷�����ת��
	StreamOpenOfficeDocumentConverter streamOpenOfficeDocumentConverter = new StreamOpenOfficeDocumentConverter(connection);
	//convert�м�������,���Խ���File�Ȳ���
	streamOpenOfficeDocumentConverter.convert(src, docDocumentFormat, dsc, pdfDocumentFormat);

	// �ر�����
	connection.disconnect();
}