


--------------------------------
ssl								|
--------------------------------
	# app.run(),����,ͨ�� ssl_context �ؼ��ֲ���ָ��֤���ļ��ĵ�ַ
	from flask import Flask, request, render_template
	app = Flask('flask-application')

	app.config.DEBUG = True


	@app.route('/')
	def hello_world():
		return render_template('index.html')


	if __name__ == '__main__':
		app.run(debug=True, ssl_context=('ssl/fullchain.cer','ssl/javaweb.key'))
