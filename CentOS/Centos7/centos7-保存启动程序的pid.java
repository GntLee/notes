--------------------
�������������pid	|
--------------------
	# nohup java [command] & echo $! > [file].pid

		command ִ������������
		file �����pid�ļ�

	# demo
		nohup java -jar socket.jar > socket.log 2 >&1 & echo $! > socket.pid