package com.eshop.gateway.gb32960.codec;

import java.util.List;

import com.eshop.common.ProtocolParseUtil;
import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.gateway.gb32960.pojo.req.PlatformLoginMsg;
import com.eshop.gateway.gb32960.pojo.req.PlatformLogoutMsg;
import com.eshop.gateway.gb32960.pojo.req.RealInfoUpMsg;
import com.eshop.gateway.gb32960.pojo.req.VehicleLoginMsg;
import com.eshop.gateway.gb32960.pojo.req.VehicleLogoutMsg;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.ReferenceCountUtil;

public class gb32960Decoder extends ReplayingDecoder<Void>{
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("decode<<<<< ip:"+ctx.channel().remoteAddress()+"hex:"+ByteBufUtil.hexDump(in));
        if (in.readableBytes() < 25) { //包头最小长度
            return;
        }
        if (in.readUnsignedShort()!= gb32960Const.START_SYMBOL) {
        	ctx.close();
        	return;
        }
        
        GB32960DataPacket msg = decode(in);
        if (msg != null) {
            out.add(msg);
        }		
    }	
	
	private GB32960DataPacket decode(ByteBuf in) {
		
        in.markReaderIndex();//标记缓存的读索引
        in.readerIndex(in.readerIndex()+20);
        Integer length = in.readUnsignedShort();
        in.resetReaderIndex();//复位缓存的读索引
        
        ByteBuf escape = ByteBufAllocator.DEFAULT.heapBuffer(length+23);//DataPacket parse方法回收
		in.readBytes(escape);
		
		//校验
        byte pkgCheckSum = escape.getByte(escape.writerIndex() - 1);
        escape.writerIndex(escape.writerIndex() - 1);//排除校验码
        byte calCheckSum = ProtocolParseUtil.XorSumBytes(escape);
        if (pkgCheckSum != calCheckSum) {
            //log.warn("校验码错误,pkgCheckSum:{},calCheckSum:{}", pkgCheckSum, calCheckSum);
            System.out.println("校验码错误,pkgCheckSum:"+pkgCheckSum+",calCheckSum:"+ calCheckSum);
            ReferenceCountUtil.safeRelease(escape);
            return null;
        }
        //解码
        return parse(escape);	
		
	}
	
    public GB32960DataPacket parse(ByteBuf bb) {
    	GB32960DataPacket packet = null;
        Short requestId = bb.getUnsignedByte(bb.readerIndex());
        switch (requestId) {
            case gb32960Const.VEHICLE_LOGIN:  //车辆登入
                packet = new VehicleLoginMsg(bb);
                break;
            case gb32960Const.REAL_INFO_UP: //实时信息上报
            case gb32960Const.REISSUE_INFO_UP: //补发信息上报
                packet = new RealInfoUpMsg(bb);
                break;
            case gb32960Const.VEHICLE_LOGOUT:  //车辆登出
                packet = new VehicleLogoutMsg(bb);
                break;
            case gb32960Const.PLATFORM_LOGIN: //平台登入
                packet = new PlatformLoginMsg(bb);
                break;
            case gb32960Const.PLATFORM_LOGOUT: //平台登出
                packet = new PlatformLogoutMsg(bb);
                break;
            default:
                packet = new GB32960DataPacket(bb);
                break;
        }
        packet.parse();
        return packet;
    }
}
