

------------------------
indexedDB				|
------------------------
	# ����db
		window.indexedDB.open(dbName,version);
	
	# ����index
		let objectStore = db.createObjectStore(objectStoreName,options);
	
	# ��������
		let transaction = db.transaction(objectStoreName,enum);
		let objectStore = transaction.objectStore(objectStoreName);
	
	#  CRUD
		objectStore.add(item);
		objectStore.get(id);
		objectStore.put(row);
		objectStore.delete(id);
	
	# ����
		objectStore.openCursor();
	
	# range
		...

------------------------
indexedDB-demo			|
------------------------
	//db����
	let db = null;
	//db����
	let dbName = 'database';
	//index����
	let objectStoreName = 'users';

	//������
	let opener = window.indexedDB.open(dbName,1);
	opener.onsuccess = function(event){
		db = event.target.result;
	}

	//�ڵ�һ�δ���db�����ǰ汾�����޸ĵ�ʱ���ִ��
	opener.onupgradeneeded = function(event){
		let db = event.target.result;
		
		//�������ݿ�洢����
		let objectStore = db.createObjectStore(objectStoreName,{
			keyPath:'id',
			autoIncrement:true
		});
		
		//����洢�����������
		objectStore.createIndex('id','id',{
			//ΨһԼ��
			unique:true
		});
		objectStore.createIndex('name','name');
		objectStore.createIndex('age','age');
	}

	function create(db,item){
		//��������
		let transaction = db.transaction(objectStoreName,'readwrite');//readonly readwrite versionchange
		//ͨ������,�򿪴洢����
		let objectStore = transaction.objectStore(objectStoreName);
		//�����¼
		objectStore.add(item);
	}

	function update(db,id){
		let transaction = db.transaction(objectStoreName,'readwrite');
		let objectStore = transaction.objectStore(objectStoreName);
		//����id������¼,�����α�
		let cursor = objectStore.get(id);
		cursor.onsuccess = function(event){
			//�ɹ����������,�������ʧ��,���� undefined
			let row = event.target.result;
			//�޸ļ�¼ֵ
			row.name = 'new name';
			//ʹput���¼�¼
			objectStore.put(row);
		}
	}

	function remove(db,id){
		let transaction = db.transaction(objectStoreName,'readwrite');
		let objectStore = transaction.objectStore(objectStoreName);
		//ͨ��idɾ����¼
		objectStore.delete(id);
	}

	function foreach(db){
		let transaction = db.transaction(objectStoreName,'readwrite');
		let objectStore = transaction.objectStore(objectStoreName);
		let cursor = objectStore.openCursor();
		cursor.onsuccess = function(event){
			let cursor = event.target.result;
			if(cursor){
				let value = cursor.value;
				//��������ֵ
				console.log(value);
				cursor.continue();//��������
			}else{
				//�������
			}
		}
	}	


	function rangeEach(db,start,end){
		let transaction = db.transaction(objectStoreName,'readwrite');
		let objectStore = transaction.objectStore(objectStoreName);
		/*
			bound(start,end,lowerOpen,upperOpen);
				id��Χ��,������bool�������������Ƿ������ʼ�ͽ���(Ĭ�϶�Ϊtrue)
			only(id);
				������ָ����id
			lowerBound(id);			
				С�������id
			upperBound(id);			
				���ڲ�����id
		*/
		//������api,��ʾ��������id start - end ֮�������
		let range = IDBKeyRange.bound(start,end);
		//�ж�range�����ķ�Χ,�Ƿ������id = 5�ļ�¼,���� bool
		let include = range.includes(5);
		//ͨ����Χapi�����α�
		let cursor = objectStore.openCursor(range);
		cursor.onsuccess = function(event){
			let cursor = event.target.result;
			if(cursor){
				let value = cursor.value;
				//��������ֵ
				console.log(value);
				cursor.continue();//��������
			}else{
				//�������
			}
		}
	}