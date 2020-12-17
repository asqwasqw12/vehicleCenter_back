package com.eshop.gateway.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshop.jt808.server.JT808ChannelInitializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.concurrent.EventExecutorGroup;

@Component
public class NettyTcpServer {
	
	@Value("${netty.port}")
	private int port;
	
	@Autowired
	@Qualifier("bossGroup")
	private NioEventLoopGroup bossGroup;
	
	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup workerGroup;
	
	@Autowired
    @Qualifier("businessGroup")
    private EventExecutorGroup businessGroup;
	
	 @Autowired
	private JT808ChannelInitializer jt808ChannelInitializer;
	 
	 @PostConstruct
	 public void start() throws InterruptedException {
		 ServerBootstrap serverBootstrap = new ServerBootstrap();
		 serverBootstrap.group(bossGroup,workerGroup)
		 .channel(NioServerSocketChannel.class)
		 .childHandler(jt808ChannelInitializer)  //使用jt808协议handler
		 .option(ChannelOption.SO_BACKLOG,1024)   //服务端可连接队列数，对应TCP/IP协议listen函数中backlog参数
		 .childOption(ChannelOption.TCP_NODELAY,true)	//立即写出
		 .childOption(ChannelOption.SO_KEEPALIVE,true);	//长连接
		 ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.SIMPLE);	////内存泄漏检测 开发推荐PARANOID 线上SIMPLE
		 ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
		 if(channelFuture.isSuccess()) {
			 System.out.println("TCP服务启动完毕,port="+this.port);
		 }
	 }
	 
	 @PreDestroy
	    public void destroy() {
	        bossGroup.shutdownGracefully().syncUninterruptibly();
	        workerGroup.shutdownGracefully().syncUninterruptibly();
	        businessGroup.shutdownGracefully().syncUninterruptibly();
	        System.out.println("关闭成功");
	    }

}
