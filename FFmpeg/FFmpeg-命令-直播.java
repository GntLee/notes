----------------------
ֱ��
----------------------
	# ����
		ffmpeg -re -i out.mp4 -c copy -f flv rtmp://host/live/streamName
	
	# ����
		ffmpeg -i rtmp://host/live/streamName -c copy dump.flv
	