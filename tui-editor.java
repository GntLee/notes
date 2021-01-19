<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/static/tui-editor/lib/codemirror/lib/codemirror.css">
		<link rel="stylesheet" href="/static/tui-editor/lib/highlightjs/styles/github.css">
		<link rel="stylesheet" href="/static/tui-editor/dist/tui-editor.css">
	    <link rel="stylesheet" href="/static/tui-editor/dist/tui-editor-contents.css">
	    <link rel="stylesheet" href="/static/tui-editor/lib/tui-color-picker/dist/tui-color-picker.css">
	    <link rel="stylesheet" href="/static/tui-editor/lib/tui-chart/dist/tui-chart.css">
		
	</head>
	<body>
		<div id="editor"></div>
		<button onclick="post();" style="width: 200px;height: 50px">
			�ύ
		</button>
	</body>
	<script src="/static/tui-editor/lib/jquery/dist/jquery.js"></script>
    <script src="/static/tui-editor/lib/markdown-it/dist/markdown-it.js"></script>
    <script src="/static/tui-editor/lib/to-mark/dist/to-mark.js"></script>
    <script src="/static/tui-editor/lib/tui-code-snippet/dist/tui-code-snippet.js"></script>
    <script src="/static/tui-editor/lib/tui-color-picker/dist/tui-color-picker.js"></script>
    <script src="/static/tui-editor/lib/codemirror/lib/codemirror.js"></script>
    <script src="/static/tui-editor/lib/highlightjs/highlight.pack.js"></script>
    <script src="/static/tui-editor/lib/squire-rte/build/squire-raw.js"></script>
    <script src="/static/tui-editor/lib/plantuml-encoder/dist/plantuml-encoder.js"></script>
    <script src="/static/tui-editor/lib/raphael/raphael.js"></script>
    <script src="/static/tui-editor/lib/tui-chart/dist/tui-chart.js"></script>
    <script src="/static/tui-editor/dist/tui-editor-Editor-all.js"></script>
    <script type="text/javascript">

	   let editor = new tui.Editor({
		   	el:document.querySelector('#editor'),
		    viewer: true,
	    	//�༭��������,markdown,wysiwyg
	        initialEditType: 'markdown',
	        //��ʼְ
	        //initialValue:content,
	        //markdown��Ԥ����ʽ,tab, vertical
	        previewStyle: 'vertical',
			//��ʼ���߶�,ֵ��������:auto	        
	        height: '500px',
			//��͸߶�
			minHeight: '100px',
	        //����
	        language:'zh_CN',
	        //�Ƿ����ü��̿�ݼ�
	        useCommandShortcut:true,
	        //�Ƿ�ʹ��Ĭ�ϵ�html������(xss��ȫ)
	        useDefaultHTMLSanitizer:false,
	        //�����֧�ֵ����ԣ�Ĭ����highlight.js֧�ֵ�ȫ��
	        //codeBlockLanguages:[]
	        //�����������͸��ȸ����������
	        usageStatistics:true,
	        //���������壬Ĭ�ϼ�������
	        //toolbarItems: [],
	        //�������½�ģʽ�л���
	        hideModeSwitch:false,
	        //��չ����
	        exts: [
	        	'scrollSync',			//Ԥ������ͬ��
	        	'colorSyntax',			//������ɫ
	        	'table',				//���
	        	'mark',					//δ֪
	        	'uml',					//uml����
	        	'chart'					//ͳ��ͼ
	        	/** ͳ��ͼ ������������С/�󣬿�/�ߵ���Ϣ
				{						
					name: 'chart',
					minWidth: 100,
					maxWidth: 600,
					minHeight: 100,
					maxHeight: 300
				}
	        	**/
			],
	        //�¼�
	        events:{
	        	load(editor){
	        		console.log('�������');
	        	},
	        	change(editor){
	        		//console.log('���ݷ����仯');
	        	},
	        	stateChange(editor){
	        		//console.log('���λ�øı�');
	        	},
	        	focus(editor){
	        		//console.log('�༭����ý���');
	        	},
	        	blur(editor){
	        		//console.log('�༭��ʧȥ����');
	        	}
	        },
	        //���Ӻ���
	        hooks:{
	        	//��Ԥ���¼�����֮ǰ,�ò���ΪԤ����html
	        	previewBeforeHook(p){
	        		//console.log(p);
	        	},
	        	//���ͼƬ
	        	addImageBlobHook(file,call,source){
	        		console.log(file,call,source);
	        		call('https://springcloud-community.oss-cn-beijing.aliyuncs.com/2018/0730/d782731b3e144c57889709497a7c8766.png');
	        		/*
	        			file	�ļ�����
	        			call	���ڻ�д��ַ�Ļص�����
	        			source	��ʾ�¼���Դ,'paste'(ճ��), 'drop'(��ק), 'ui'(ui���)
	        		*/
	        	}
	        }
	    });
	   /*
	   		## API
	   		
	   		//��ȡhtml
			getHtml();
	   		//��ȡmarkdown����
			getMarkdown();
			//δ֪���о��Ǻ�getMarkdown() һ����
			getValue()
	   		//�������ݣ��Ḳ�ǵ�����
			setValue();
	   		//�ڹ��λ�ò�������
			insertText();
	   		//��ʾ�༭��
			show();
	   		//���ر༭��
	   		hide();
	   		
	   		## �ĵ���ַ
	   		//�ĵ���ҳ
			https://nhnent.github.io/tui.editor/api/latest/index.html
	   		//���fontawesome��ť
	   		https://nhnent.github.io/tui.editor/api/latest/tutorial-example16-toolbar-add-button-fontawesome.html
	   */
	   
		editor._getHtml = function(){
			return $('.te-preview')[0].innerHTML;
		}
	   
	   function post(){

				let formData = new FormData();
				
				let htmlContent = editor._getHtml();
				let markdownContent = editor.getMarkdown();
				
				formData.set('htmlContent',htmlContent);
				formData.set('markdownContent',markdownContent);
				
				fetch('/temp/tui-editor/post',{
					method:'POST',
					body:formData
				}).then(function(response){
					if(response.ok){
						response.json().then(function(rep){
							console.log(rep);
							window.location.href = '/temp/tui-editor/' + rep.data;
						});
					}
				}).catch(function(e){
					throw e;
				});
	   		
	   }
    </script>
</html>

1,ȫ���༭û
2,���ָ��ͳ��ͼ���ж�̬����ʾ
	* ���ɶ�̬ͼ��ʱ��,������һ��id,js���ݸ�id����������¼�
3,Ԥ�����ڵĿ���û
4,Ԥ��html�������ӳ�̫��
5,��ʼ��ʱ,<div></div>�ڵ��ǩ���治֧�ֳ�ʼ��markdown����
6,���ɵ�umlͼ����https��
