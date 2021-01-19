----------------------------------
Netty��������					  |
----------------------------------

----------------------------------
Netty Client������ʱ����Ҫ����	  |
----------------------------------
	# Netty������stackoverflow�Ļش�
		https://stackoverflow.com/questions/19739054/whats-the-best-way-to-reconnect-after-connection-closed-in-netty

		private void doConnect() {
			Bootstrap b = ...;
			b.connect().addListener((ChannelFuture f) -> {
				if (!f.isSuccess()) {
					long nextRetryDelay = nextRetryDelay(...);
					// ����ʧ��,��ָ���ӳٺ���������
					f.channel().eventLoop().schedule(nextRetryDelay, ..., () -> {
						doConnect();
					}); // Ҳ���Է�������
				}
			});
		}

----------------------------------
�ڳ������������Ӷϵ���Ҫ����	  |
----------------------------------
	# �ο�
		https://github.com/netty/netty/blob/master/example/src/main/java/io/netty/example/uptime/UptimeClientHandler.java
	


----------------------------------
Client							  |
----------------------------------
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class Client {

    private final EventLoopGroup loop;

    private final InetSocketAddress inetSocketAddress;

    public Client(EventLoopGroup loop,InetSocketAddress inetSocketAddress) {
        this.loop = loop;
        this.inetSocketAddress = inetSocketAddress;
    }

    static class ConnectionListener implements ChannelFutureListener {
        private Client client;
        public ConnectionListener(Client client) {
            this.client = client;
        }
        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (!channelFuture.isSuccess()) {
                // ����ʧ��
                final EventLoop loop = channelFuture.channel().eventLoop();
                loop.schedule(new Runnable() {
                    @Override
                    public void run() {
                        // ��1s������������
                        client.createBootstrap(new Bootstrap(), loop);
                    }
                }, 1L, TimeUnit.SECONDS);
            }
        }
    }

    private Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {
        if (bootstrap != null) {

            bootstrap.group(this.loop);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.remoteAddress(this.inetSocketAddress);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    // ���Handler�����ڳ������������Ӷϵ���Ҫ����
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            final EventLoop eventLoop = ctx.channel().eventLoop();
                            eventLoop.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    // ��������
                                    Client.this.createBootstrap(new Bootstrap(), eventLoop);
                                }
                            }, 1L, TimeUnit.SECONDS);
                            super.channelInactive(ctx);
                        }
                    });
                }
            });

            // �����ӵ�ʱ����Ӽ���,����������ʱ����Ҫ����
            bootstrap.connect().addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        // ����ʧ��
                        final EventLoop loop = future.channel().eventLoop();
                        loop.schedule(new Runnable() {
                            @Override
                            public void run() {
                                // ��1s������������
                                Client.this.createBootstrap(new Bootstrap(), loop);
                            }
                        }, 1L, TimeUnit.SECONDS);
                    }
                }
            });
        }
        return bootstrap;
    }
    // �����ͻ���
    public void run() {
        createBootstrap(new Bootstrap(), loop);
    }
}