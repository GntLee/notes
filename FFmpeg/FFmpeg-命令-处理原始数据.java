-------------------
����ԭʼ����
-------------------
	# ��ȡyuv����
		ffmpeg -i demo.mp4 -an -c:v rawvideo -pix_fmt yuv420p out.yuv
	
	# ��ȡpcm����
		ffmepg -i demo.mp4 -ar 44100 -ac2 -f s16le -vn out.pcm
	


