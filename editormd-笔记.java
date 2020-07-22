
# ��ʼ��
	//��ʼ���༭��
			editor = editormd({
				id:id,
				path:'/static/editormd/lib/',
				height:500,
				width:'100%',
				placeholder:'',
				saveHTMLToTextarea:true,
				//Ԥ���ӳ�,����
				delay : 300,
				
				//֧��
			    tex:true,
			    flowChart:true,
			    sequenceDiagram:true,
		        emoji:true,
		        taskList:false,
		        tocm:false,
		        
		        autoFocus:false,
		        atLink:false,
		        watch:true,
				htmlDecode:'style,script,iframe|on*',
		        imageUpload:true,
		        imageFormats: ['jpg,png,gif'],
		        imageUploadURL: '/upload/editor',
		        
		        //�¼�
		        onchange:function(){
		        }
			});
			
			let editorNode = document.querySelector('#' + id);

			/**
			 * ͼƬ����ϴ�
			 */
		    editorNode.addEventListener('paste',function(event){
				let clipboardData = event.clipboardData;
		        if(clipboardData){
		            let items = clipboardData.items;
		            if(items && items.length > 0){
		                for(let item of items){
		                	if(item.type.startsWith("image/")){
		                		let file = item.getAsFile();
			                	if(!file){
			                		//TODO���ļ���ȡʧ�ܣ�����֧�ֲ��ֽ�ͼ���ƺ��ͼƬճ���ϴ�
			                		return;
			                	}
			                	//TODO���ϴ�
		                	}
		                }
		            }
		        }
		    });

			/**
			 * ͼƬ��ק�ϴ�
			 */
			editorNode.addEventListener('dragenter',function(event){
				event.preventDefault();
			});
			editorNode.addEventListener('dragover',function(event){
				event.preventDefault();
			});
			editorNode.addEventListener('drop',function(event){
				event.preventDefault();
				let files = event.dataTransfer.files;
				if(files){
					if(files.length > 5){
						//TODO����ק��������5
						return;
					}
					if(Array.from(files).some(file => !file.type.startsWith('image/'))){
						//TODO��һ�����߶���ļ���ͼƬ����
						return;
					}
					//TODO���ϴ�
				}
			});
			editorNode.addEventListener('dragend',function(event){
				event.preventDefault();
			});
			
			return editor;
		}
			
		function post(){
			
			let formData = new FormData();
			
			//let htmlContent = editor.getHTML(); 
			let htmlContent = $('.editormd-preview')[0].innerHTML;
			let markdwonContent = editor.getMarkdown();
			
			formData.set('htmlContent',htmlContent);
			formData.set('markdwonContent',markdwonContent);
			
			fetch('/temp/post/create',{
				method:'POST',
				body:formData
			}).then(function(response){
				if(response.ok){
					response.json().then(function(rep){
						console.log(rep);
						window.location.href = '/temp/post/' + rep.data;
					});
				}
			}).catch(function(e){
				throw e;
			});
		}