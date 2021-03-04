
	package com.eshop.gateway.gb32960.server;

	import java.util.concurrent.TimeUnit;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Qualifier;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Component;
	import com.eshop.gateway.gb32960.codec.gb32960Decoder;
	import com.eshop.gateway.gb32960.codec.gb32960Encoder;
import com.eshop.gateway.gb32960.handler.CRRCLoginMsgHandler;
import com.eshop.gateway.gb32960.handler.ClockCorrectMsgHandler;
import com.eshop.gateway.gb32960.handler.HeartBeatMsgHandler;
	import com.eshop.gateway.gb32960.handler.PlatformLoginMsgHandler;
	import com.eshop.gateway.gb32960.handler.PlatformLogoutMsgHandler;
	import com.eshop.gateway.gb32960.handler.RealInfoUpMsgHandler;
	import com.eshop.gateway.gb32960.handler.VehicleLoginMsgHandler;
	import com.eshop.gateway.gb32960.handler.VehicleLogoutMsgHandler;

	import io.netty.channel.ChannelInitializer;
	import io.netty.channel.ChannelPipeline;
	import io.netty.channel.socket.SocketChannel;
	import io.netty.handler.timeout.IdleStateHandler;
	import io.netty.util.concurrent.EventExecutorGroup;

	@Component
	public class GB32960ChannelInitializer extends ChannelInitializer<SocketChannel>{
		
		@Value("${netty.gb32960-read-timeout}")
	    private int readTimeOut;

	    @Autowired
	    @Qualifier("businessGroup")
	    private EventExecutorGroup businessGroup;
	    
	    @Autowired 
	    private PlatformLoginMsgHandler platformLoginMsgHandler;

	    @Autowired 
	    private PlatformLogoutMsgHandler platformLogoutMsgHandler;

	    @Autowired
	    @Qualifier("gb32960Handler")
	    private HeartBeatMsgHandler heartBeatMsgHandler;

	    @Autowired
	    private RealInfoUpMsgHandler realInfoUpMsgHandler;

	    @Autowired
	    private VehicleLoginMsgHandler vehicleLoginMsgHandler;
	    
	    @Autowired
	    private VehicleLogoutMsgHandler vehicleLogoutMsgHandler;
	    
	    @Autowired
	    private ClockCorrectMsgHandler clockCorrectMsgHandler;
	    
	    @Autowired
	    private CRRCLoginMsgHandler crrcLoginMsgHandler;
	    
	    @Override
	    protected void initChannel(SocketChannel ch) throws Exception {
	        ChannelPipeline pipeline = ch.pipeline();
	        pipeline.addLast(
	                new IdleStateHandler(readTimeOut, 0, 0, TimeUnit.MINUTES));   //心跳与重连
	        // gb32960协议
	        pipeline.addLast(new gb32960Decoder()); //解码器
	        pipeline.addLast(new gb32960Encoder()); //编码器
	        pipeline.addLast(platformLoginMsgHandler); //平台登入处理器
	        pipeline.addLast(platformLogoutMsgHandler); //平台登出处理器
	        pipeline.addLast(vehicleLoginMsgHandler); //车辆登入处理器
	        pipeline.addLast(crrcLoginMsgHandler); //中车企标-终端登入处理器
	        pipeline.addLast(heartBeatMsgHandler); //心跳处理器
	        pipeline.addLast(businessGroup, realInfoUpMsgHandler);//因为locationMsgHandler中涉及到数据库操作，所以放入businessGroup        
	        pipeline.addLast(clockCorrectMsgHandler); //终端校时处理器
	        pipeline.addLast(vehicleLogoutMsgHandler); //车辆登出处理器

	  }
	}
