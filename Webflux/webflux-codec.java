--------------------------
Codec
--------------------------
	# Http��Ϣ�ı����
		HttpMessageReader
			DecoderHttpMessageReader
		
		HttpMessageWriter
			EncoderHttpMessageWriter
	
	# ϵͳ�ı���������ýӿ�
		ServerCodecConfigurer
			
			DefaultCodecs defaultCodecs();
			CustomCodecs customCodecs();
			void registerDefaults(boolean registerDefaults);
			List<HttpMessageReader<?>> getReaders();
			List<HttpMessageWriter<?>> getWriters();
			CodecConfigurer clone();

			
			ServerDefaultCodecs defaultCodecs();
			ServerCodecConfigurer clone();

