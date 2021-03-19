package com.eshop.gateway.gb32960.codec;

import java.util.List;

import com.eshop.common.ProtocolParseUtil;
import com.eshop.gateway.gb32960.config.MsgStatusEnum;
import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.gateway.gb32960.pojo.req.CRRCLoginMsg;
import com.eshop.gateway.gb32960.pojo.req.ClockCorrectMsg;
import com.eshop.gateway.gb32960.pojo.req.GB32960HeartBeatMsg;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class gb32960Decoder extends ReplayingDecoder<MsgStatusEnum> {

	//该段参考https://blog.csdn.net/nimasike/article/details/95483252，Netty源码分析-ReplayingDecoder

	public gb32960Decoder() {
		super(MsgStatusEnum.MSG_HEADER);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		
		System.out.println("decode<<<<< ip:" + ctx.channel().remoteAddress() + "hex:"
				+ ByteBufUtil.hexDump(this.internalBuffer()));
		switch (state()) {
		case MSG_HEADER:
			if (in.readableBytes() < 25) { // 包头最小长度
				return;
			}
			int startSymbol = in.readUnsignedShort();
            checkpoint(MsgStatusEnum.MSG_BODY);
            if (startSymbol != gb32960Const.START_SYMBOL) {
            	log.info("实际可读长度=" + this.actualReadableBytes());
    			System.out.println("实际可读长度=" + this.actualReadableBytes());
    			in.skipBytes(this.actualReadableBytes());// 丢弃
    			ctx.close();
    			return;
    		}
		case MSG_BODY:
			in.markReaderIndex();
			in.readerIndex(in.readerIndex() + 20);
			Integer length = in.readUnsignedShort();
			in.resetReaderIndex();
			ByteBuf escape = ByteBufAllocator.DEFAULT.heapBuffer(length+23);//DataPacket parse方法回收
			escape = in.readBytes(length+23);
			checkpoint(MsgStatusEnum.MSG_HEADER);//读取成功设置还原点，更新状态
			GB32960DataPacket msg =decode(escape);
			if (msg != null) {
				out.add(msg);
			}
			break;
			default:
				throw new Error(" gb32960Decoder illage error");
		}

		/*
		 * in.markReaderIndex();// 标记缓存的读索引 int startSymbol = in.readUnsignedShort();
		 * in.readerIndex(in.readerIndex() + 20); Integer length =
		 * in.readUnsignedShort();
		 * 
		 * if (startSymbol != gb32960Const.START_SYMBOL) { System.out.println("实际可读长度="
		 * + this.actualReadableBytes()); in.skipBytes(this.actualReadableBytes());// 丢弃
		 * ctx.close(); return; } System.out.println("length=" + length);
		 * System.out.println("actualReadableBytes=" + this.actualReadableBytes());
		 * System.out.println("readableBytes()=" + in.readableBytes()); if
		 * (this.actualReadableBytes() < length + 1) { System.out.println("可读数据太短");
		 * in.resetReaderIndex();// 复位缓存的读索引 return; } in.resetReaderIndex();// 复位缓存的读索引
		 * GB32960DataPacket msg = decode(in); if (msg != null) { out.add(msg); }
		 */
	}
	
private GB32960DataPacket decode(ByteBuf escape) {
		
		/*
		 * in.markReaderIndex();//标记缓存的读索引 in.readerIndex(in.readerIndex()+22); Integer
		 * length = in.readUnsignedShort(); in.resetReaderIndex();//复位缓存的读索引
		 */		/*
		 * if(this.actualReadableBytes()<length+23) { System.out.println("数据可能发生拆包");
		 * return null; }
		 */
		/*
		 * ByteBuf escape = ByteBufAllocator.DEFAULT.heapBuffer(length+23);//DataPacket
		 * parse方法回收
		 * System.out.println("in的readindex="+in.readerIndex()+"in的writerIndex="+in.
		 * writerIndex());
		 * System.out.println("in可读大小="+in.readableBytes()+"escape可写大小="+escape.
		 * writableBytes()); in.readUnsignedShort();//将readIndex后移两位
		 * in.readBytes(escape);
		 */
		/*
		 * System.out.println("复制后in的readindex="+in.readerIndex()+"in的writerIndex="+in.
		 * writerIndex());
		 * System.out.println("复制后in可读大小="+in.readableBytes()+"escape可写大小="+escape.
		 * writableBytes());
		 */
		
		//校验
        byte pkgCheckSum = escape.getByte(escape.writerIndex() - 1);
        escape.writerIndex(escape.writerIndex() - 1);//排除校验码
        byte calCheckSum = ProtocolParseUtil.XorSumBytes(escape);
        if (pkgCheckSum != calCheckSum) {
        	
            //log.warn("校验码错误,pkgCheckSum:{},calCheckSum:{}", pkgCheckSum, calCheckSum);
			
			/*
			 * System.out.println("校验码错误,pkgCheckSum:"+pkgCheckSum+",calCheckSum:"+
			 * calCheckSum+"length="+length); in.skipBytes(length+23); //丢弃
			 * //in.skipBytes(this.actualReadableBytes());//丢弃
			 * System.out.println("丢弃后in的readindex="+in.readerIndex()+"in的writerIndex="+in.
			 * writerIndex());
			 * System.out.println("丢弃后in可读大小="+in.readableBytes()+"escape可写大小="+escape.
			 * writableBytes());
			 */
			System.out.println("校验码错误,pkgCheckSum:"+pkgCheckSum+",calCheckSum:"+
					  calCheckSum); 
            ReferenceCountUtil.safeRelease(escape);
            return null;
        }
        //解码
        return parse(escape);	
		
	}

	/*
	 * private GB32960DataPacket decode(ByteBuf in) {
	 * 
	 * in.markReaderIndex();//标记缓存的读索引 in.readerIndex(in.readerIndex()+22); Integer
	 * length = in.readUnsignedShort(); in.resetReaderIndex();//复位缓存的读索引
	 * 
	 * if(this.actualReadableBytes()<length+23) { System.out.println("数据可能发生拆包");
	 * return null; }
	 * 
	 * ByteBuf escape = ByteBufAllocator.DEFAULT.heapBuffer(length+23);//DataPacket
	 * parse方法回收
	 * System.out.println("in的readindex="+in.readerIndex()+"in的writerIndex="+in.
	 * writerIndex());
	 * System.out.println("in可读大小="+in.readableBytes()+"escape可写大小="+escape.
	 * writableBytes()); in.readUnsignedShort();//将readIndex后移两位
	 * in.readBytes(escape);
	 * System.out.println("复制后in的readindex="+in.readerIndex()+"in的writerIndex="+in.
	 * writerIndex());
	 * System.out.println("复制后in可读大小="+in.readableBytes()+"escape可写大小="+escape.
	 * writableBytes());
	 * 
	 * //校验 byte pkgCheckSum = escape.getByte(escape.writerIndex() - 1);
	 * escape.writerIndex(escape.writerIndex() - 1);//排除校验码 byte calCheckSum =
	 * ProtocolParseUtil.XorSumBytes(escape); if (pkgCheckSum != calCheckSum) {
	 * //log.warn("校验码错误,pkgCheckSum:{},calCheckSum:{}", pkgCheckSum, calCheckSum);
	 * System.out.println("校验码错误,pkgCheckSum:"+pkgCheckSum+",calCheckSum:"+
	 * calCheckSum+"length="+length); in.skipBytes(length+23); //丢弃
	 * //in.skipBytes(this.actualReadableBytes());//丢弃
	 * System.out.println("丢弃后in的readindex="+in.readerIndex()+"in的writerIndex="+in.
	 * writerIndex());
	 * System.out.println("丢弃后in可读大小="+in.readableBytes()+"escape可写大小="+escape.
	 * writableBytes()); ReferenceCountUtil.safeRelease(escape); return null; } //解码
	 * return parse(escape);
	 * 
	 * }
	 */

	public GB32960DataPacket parse(ByteBuf bb) {
		GB32960DataPacket packet = null;
		Short requestId = bb.getUnsignedByte(bb.readerIndex());
		switch (requestId) {
		case gb32960Const.VEHICLE_LOGIN: // 车辆登入
			
			packet = new VehicleLoginMsg(bb);
			System.out.println("VehicleLoginMsg初始化完成");
			break;
		case gb32960Const.REAL_INFO_UP: // 实时信息上报
		case gb32960Const.REISSUE_INFO_UP: // 补发信息上报
			packet = new RealInfoUpMsg(bb);
			break;
		case gb32960Const.VEHICLE_LOGOUT: // 车辆登出
			packet = new VehicleLogoutMsg(bb);
			break;
		case gb32960Const.PLATFORM_LOGIN: // 平台登入
			packet = new PlatformLoginMsg(bb);
			break;
		case gb32960Const.PLATFORM_LOGOUT: // 平台登出
			packet = new PlatformLogoutMsg(bb);
			break;
		case gb32960Const.VEHICLE_HEART_BEAT: // 车辆心跳
			packet = new GB32960HeartBeatMsg(bb);
			break;
		case gb32960Const.CLOCK_CORRECT: // 终端校时
			packet = new ClockCorrectMsg(bb);
			break;
		case gb32960Const.CRRC_LOGIN: //中车企标-终端登入请求
			packet = new CRRCLoginMsg(bb);
			break;
		default:
			packet = new GB32960DataPacket(bb);
			break;
		}
		packet.parse();
		System.out.println("解析完成");
		return packet;
	}
}
