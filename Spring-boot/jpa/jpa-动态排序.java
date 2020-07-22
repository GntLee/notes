-----------------------
jpa��̬���򹤾���
-----------------------
	public static final String DEFAULT_ORDER = "ASC";
	
	/**
	 * JPA���������װ
	 * @param properties  �����ʵ�����ԣ����Ǳ��ֶΣ�
	 * @param orders
	 * @return
	 */
	public static Sort jpaSort(String[] properties, String[] orders) {
		if (properties == null || properties.length == 0) {
			return null;
		}
		
		Order[] orderArray = new Order[properties.length];

		for (int x = 0; x < properties.length; x++) {

			// �����ֶ�
			String property = properties[x];
			
			// �ж������ƵĺϷ��ԣ���ֹSQLע�롣ֻ���ǡ���ĸ�����֣��»��ߡ�
			/**
			 * JPA �ϸ������������򡣲���ҪУ��
			 */
	//		if (!property.matches("[A-Za-z0-9_]+")) {
	//			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�Ƿ��������������ƣ�" + property));
	//		}
			
			// �������
			Sort.Direction direction = null;
			
			if (orders != null && orders.length > x) {
				try {
					direction = Sort.Direction.valueOf(orders[x].toUpperCase().trim());	
				} catch (IllegalArgumentException e) {
					throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�Ƿ���������ԣ�" + orders[x]));	
				}
			}else {
				direction = Sort.Direction.ASC;;
			}
			
			orderArray[x] = new Order(direction, property, NullHandling.NULLS_LAST);
		}
		
		return Sort.by(orderArray);
	}