package com.learning.chat.application.websocket;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	// Store WebSocket sessions in a thread-safe set to broadcast messages
	private static final Set<WebSocketSession> clients = new CopyOnWriteArraySet<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// Add the session to the set of clients after a connection is established
		clients.add(session);
		System.out.println("New connection established, client added: " + session.getId());

		// Send a welcome message back to the newly connected client
		session.sendMessage(new TextMessage("Welcome to the chat!"));
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		// Log the message received from the client
		System.out.println("Message received from client: " + message.getPayload());

		// Send a response back to the client
		String responseMessage = "Server: " + message.getPayload();
		session.sendMessage(new TextMessage(responseMessage)); // Respond back to the sender
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// Remove the session when it's closed
		clients.remove(session);
		System.out.println("Connection closed, client removed: " + session.getId());
	}
}
