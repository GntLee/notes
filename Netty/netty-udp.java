-------------------------------
udp								|
-------------------------------
	# ���
		DatagramPacket
		DatagramPacketEncoder
		DatagramPacketDecoder
	
	# �ʴɵ�����
		// �����㲥
		bootstrap.option(ChannelOption.SO_BROADCAST, true);
		// ����UDP��������Ϊ2M
		bootstrap.option(ChannelOption.SO_RCVBUF, 2048 * 1024);
		// ����UDPд������Ϊ1M
		bootstrap.option(ChannelOption.SO_SNDBUF, 1024 * 1024);

-------------------------------
DatagramPacket					|
-------------------------------
	# �̳�:DefaultAddressedEnvelope ʵ�� ByteBufHolder
	# Netty ��udp��Ϣ�ķ�װ
		DatagramPacket(ByteBuf data, InetSocketAddress recipient)
		DatagramPacket(ByteBuf data, InetSocketAddress recipient, InetSocketAddress sender)

		* data ����
		* recipient ���շ���Ϣ
		* sender ���ͷ���Ϣ
	
	# ����
		DatagramPacket copy()
		DatagramPacket duplicate()
		DatagramPacket retainedDuplicate()
		DatagramPacket replace(ByteBuf content)
		DatagramPacket retain()
		DatagramPacket retain(int increment)
		DatagramPacket touch()
		DatagramPacket touch(Object hint)

-------------------------------
DatagramPacketDecoder			|
-------------------------------
	# UDP��Ϣ�Ľ�����
		DatagramPacketDecoder(MessageToMessageDecoder<ByteBuf> decoder)
		

-------------------------------
DatagramPacketEncoder			|
-------------------------------
	# UDP��Ϣ�ı�����
		DatagramPacketEncoder(MessageToMessageEncoder<? super M> encoder)
	

-------------------------------
UDP������						|
-------------------------------
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioDatagramChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					
					ch.pipeline().addLast(new SimpleChannelInboundHandler<DatagramPacket>() {
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
							
							// �յ�����Ϣ
							ByteBuf byteBuf = msg.content();
							
							// ���ͷ�����Ϣ
							InetSocketAddress inetSocketAddress = msg.sender();
							
							System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
							System.out.println(inetSocketAddress);
							
							// ��Ӧ�ͻ���
							DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer("���Ѿ��յ���",StandardCharsets.UTF_8), inetSocketAddress);
							ctx.writeAndFlush(datagramPacket);
						}
					});
				}
			});
			
			// �󶨼��� 
			ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(1024)).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}

-------------------------------
UDP�ͻ���						|
-------------------------------
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class Clien {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioDatagramChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new SimpleChannelInboundHandler<DatagramPacket>() {
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
							// �յ�����Ϣ
							ByteBuf byteBuf = msg.content();
							
							// ���ͷ�����Ϣ
							InetSocketAddress inetSocketAddress = msg.sender();
							
							System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
							System.out.println(inetSocketAddress);
						}
					});
				}
			});
			
			// �󶨱��ض˿�(�������Ϊ0��������˿�)
			ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(0)).sync();
			
			// ��������
			DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer("Hello World!", StandardCharsets.UTF_8), new InetSocketAddress("127.0.0.1", 1024));
			channelFuture.channel().writeAndFlush(datagramPacket);
			
			channelFuture.channel().closeFuture().sync();
		}finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
