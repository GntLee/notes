----------------------
�ڵ�����				|
----------------------
	# ���ԶԽڵ����÷�HTML�ı�׼����

	attributes
		* �����˴���һ��Ԫ�ص����Ե�Attr����,������Element�ڵ�
		* ���� NamedNodeMap ����

	dataset
		* ���ص���һ�� map,��������˽ڵ��ϵ����� data- ����
			let p = document.querySelector('p');
			console.log(p.dataset); //DOMStringMap {a: "1", b: "2"}
			console.log(p.dataset.a);   //1


	createAttribute();
		* ����һ��ָ�����Ƶ����Խڵ����
		* ���ص���һ�����Խڵ����

	setAttributeNode();
		* ����һ�����Խڵ����

	removeAttributeNode()	
		* �Ƴ�ָ�������Խڵ㣬�����ر��Ƴ��Ľڵ㡣


	getAttribute();
		* ��ȡָ�����Ƶ�����ֵ
	
	setAttribute(������,����ֵ);
		* ����������������ֵ
	
	removeAttribute();
		* ɾ��ָ�����Ƶ�����ֵ
	
	hasAttribute();
		* �жϽڵ��Ƿ�߱�ָ��������

----------------------
�ڵ�����-����			|
----------------------
	isId	
		* ��������� id ���ͣ��򷵻� true�����򷵻� false��
	name	
		* �������Ե����ơ�
	value	
		* ���û򷵻����Ե�ֵ��
	specified	
		* �����ָ�����ԣ��򷵻� true�����򷵻� false��

----------------------
�ڵ�����-����			|
----------------------
	# NamedNodeMap ����

	nodemap.getNamedItem()
		* �� NamedNodeMap ����ָ�������Խڵ㡣
	nodemap.item()	
		* ���� NamedNodeMap ��λ��ָ���±�Ľڵ㡣
	nodemap.length
		* ���� NamedNodeMap �еĽڵ�����
	nodemap.removeNamedItem()	
		* �Ƴ�ָ�������Խڵ㡣
	nodemap.setNamedItem()	
		* ����ָ�������Խڵ㣨ͨ�����ƣ���