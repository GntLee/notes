------------------------------
��־����ĺ�����
------------------------------

	# ���ĵĽӿ�
		* ��־�ĸ�ʽ��������
			type LogFormatter func(params LogFormatterParams) string
		
		* ��ʽ������
			type LogFormatterParams struct {
				Request *http.Request
					* ����
				TimeStamp time.Time
					* ִ��ʱ���
				StatusCode int
					* ״̬��
				Latency time.Duration
					* ִ��ʱ��
				ClientIP string
					* �ͻ���IP
				Method string
					* ���󷽷�
				Path string
					* ����·��
				ErrorMessage string
					* �쳣��Ϣ
				BodySize int
					* �������С
				Keys map[string]interface{} // contains filtered or unexported fields
			}
			func (p *LogFormatterParams) IsOutputColor() bool
			func (p *LogFormatterParams) MethodColor() string
			func (p *LogFormatterParams) ResetColor() string
			func (p *LogFormatterParams) StatusCodeColor() string
		
		* ��־������
			type LoggerConfig struct {
				Formatter LogFormatter
					* ��ʽ����������Ĭ�� gin.defaultLogFormatter
				Output io.Writer
					* ���Ŀ�ĵأ�Ĭ��	gin.DefaultWriter.
				SkipPaths []string
					* Ҫ������·��
			}

	# �ṩ��һЩ��־����
		func Logger() HandlerFunc 
			* ����Ĭ�����õ���־������
				return LoggerWithConfig(LoggerConfig{})
		func LoggerWithConfig(conf LoggerConfig) HandlerFunc
			* �������ã�������־������
		func LoggerWithFormatter(f LogFormatter) HandlerFunc
			* ���ݸ�ʽ��Formater�������ڴ�����
		func LoggerWithWriter(out io.Writer, notlogged ...string) HandlerFunc
			* �������λ�ã��ͺ���·�����ش�����
		
	# ȫ��Ĭ�ϵ�debug��־�������
		var DebugPrintRouteFunc func(httpMethod, absolutePath, handlerName string, nuHandlers int)
			* Ĭ��
				debugPrint("%-6s %-25s --> %s (%d handlers)\n", httpMethod, absolutePath, handlerName, nuHandlers)
	

	# ���м������ʽ��ӵ�
		
		