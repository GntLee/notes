--------------------------------------------
AbstractGatewayFilterFactory
--------------------------------------------
	# GatewayFilterFactory�ĳ���ʵ��
		public abstract class AbstractGatewayFilterFactory<C> extends AbstractConfigurable<C>
			implements GatewayFilterFactory<C>, ApplicationEventPublisherAware

--------------------------------------------
static
--------------------------------------------

--------------------------------------------
this
--------------------------------------------
	public AbstractGatewayFilterFactory()
	public AbstractGatewayFilterFactory(Class<C> configClass)

	protected ApplicationEventPublisher getPublisher()
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher)