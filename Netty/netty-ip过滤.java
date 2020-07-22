--------------------------------
ip����							|
--------------------------------
	# ���
		IpFilterRuleType
			* ���صĽ��ö��
				ACCEPT
			    REJECT

		IpFilterRule
			*��������
			|-IpSubnetFilterRule

		AbstractRemoteAddressFilter
			* ��ͬ��������
			|-UniqueIpFilter
			|-RuleBasedIpFilter

	# AbstractRemoteAddressFilter
		* ������,�ṩ��һ�����󷽷�,���ڹ���ip
			boolean accept(ChannelHandlerContext ctx, T remoteAddress) throws Exception;
		
		* ���ip���ں�����,��ᴥ���쳣
			 throw new IllegalStateException("cannot determine to accept or reject a channel: " + ctx.channel());

		* �ṩ�ɸ�д���¼�����
			void channelAccepted(ChannelHandlerContext ctx, T remoteAddress);
				* �ͻ�����֤ͨ��ʱ����

			ChannelFuture channelRejected(ChannelHandlerContext ctx, T remoteAddress);
				* �ͻ�����֤ʧ��ʱ����
				* ip���ش���(����������ʵ��Ҫ���ͻ�����Ӧ������)
				* �������Ӧ�κ�����,���� null

	# IpFilterRule
		* ip���������,��һ���ӿ�,ʵ����:IpSubnetFilterRule
			IpSubnetFilterRule(String ipAddress, int cidrPrefix, IpFilterRuleType ruleType)
			IpSubnetFilterRule(InetAddress ipAddress, int cidrPrefix, IpFilterRuleType ruleType)


--------------------------------
UniqueIpFilter					|
--------------------------------
	# Ψһip���ӵĹ�����
	# һ��ipֻ�ܱ���һ������
	# accept() ʵ��
		@Override
		protected boolean accept(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) throws Exception {
			final InetAddress remoteIp = remoteAddress.getAddress();
			if (!connected.add(remoteIp)) {
				return false; // ��ip�Ѿ�������Set����,��ֹ����
			} else {
				ctx.channel().closeFuture().addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						// �Ͽ�����ʱ��Set�����Ƴ�
						connected.remove(remoteIp);
					}
				});
				return true;
			}
		}

--------------------------------
RuleBasedIpFilter				|
--------------------------------
	# ���ݹ���������ip
	# ���췽��
		RuleBasedIpFilter(IpFilterRule... rules)
	# accept() ʵ��
	    @Override
		protected boolean accept(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) throws Exception {
			for (IpFilterRule rule : rules) {
				if (rule == null) {
					break;
				}
				if (rule.matches(remoteAddress)) {
					return rule.ruleType() == IpFilterRuleType.ACCEPT;
				}
			}
			return true;
		}
