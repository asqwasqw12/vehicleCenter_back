package com.eshop.modules.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@ServerEndpoint("/webSocket/vehicleStatus/{userName}")
@Component
public class WebsocketServer {
	
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebsocketServer> websocketSet = new CopyOnWriteArraySet<WebsocketServer>();
	
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	 private static Map<String,Session> sessionPool = new HashMap<String,Session>();
	
	
	//连接成功调用的方法
	@OnOpen
	public void onOpen(Session session,@PathParam(value="userName")String userName ) {
		System.out.println("成功建立连接！！！");
		this.session = session;
		sessionPool.put(userName, session);
		websocketSet.add(this);
		System.out.println("【websocket消息】有新的连接，总数为:"+websocketSet.size());
	}
	
	//连接关闭调用的方法
	@OnClose
	public void onClose() {
		websocketSet.remove(this);
		System.out.println("【websocket消息】连接断开，总数为:"+websocketSet.size());
	}
	
	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息*/	
	@OnMessage
	public void onMessage(String message, Session session,@PathParam(value="userName")String userName) {
		System.out.println("【websocket消息】收到客户端"+userName+"消息:"+message);
		//群发消息
        sendAllMessage(message);	
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	// 此为广播消息
    public void sendAllMessage(String message) {
        for(WebsocketServer webSocket : websocketSet) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送文本)
    public void sendTextMessage(String userName, String message) {
        Session session = sessionPool.get(userName);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送对象)
    public void sendObjMessage(String userName, Object message) {
        Session session = sessionPool.get(userName);
        if (session != null) {
            try {
                session.getAsyncRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
