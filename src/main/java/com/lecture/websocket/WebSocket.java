package com.lecture.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler implements InitializingBean {
	public static Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();

	public WebSocket() {
		super();
		System.out.println("create SocketHandler instance!");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		sessionSet.remove(session);
		System.out.println("remove session!");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		sessionSet.add(session);
		System.out.println("add session!");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);

		System.out.println("receive message:" + message.toString());
		sendMessage(message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("web socket error!");
	}

	@Override
	public boolean supportsPartialMessages() {
		System.out.println("call method!");

		return super.supportsPartialMessages();
	}

	public void sendMessage(String message) {
		for (WebSocketSession session : this.sessionSet) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (Exception ignored) {
					System.out.println("fail to send message!");
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
}
