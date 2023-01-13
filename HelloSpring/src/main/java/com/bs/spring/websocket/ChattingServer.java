package com.bs.spring.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//websocket������ Ȱ��Ǵ� pojo
//TextWebSocketHandlerŬ������ ��� �޾Ƽ� ����.
@Slf4j
public class ChattingServer extends TextWebSocketHandler {

	private Map<String,WebSocketSession> clients=new HashMap();
	
	private ObjectMapper mapper;
	
	@Autowired
	public void setMapper(ObjectMapper mapper) {
		this.mapper=mapper;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		log.debug("Ŭ���̾�Ʈ ����");
		
	}

	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		//Ŭ���̾�Ʈ�� ���� �޼���Ȯ���ϱ�
		//Ŭ���̾�Ʈ�� ���� �޼����� payload���� ����ȴ�.
		log.debug("{}",message.getPayload());
		//Ŭ���̾�Ʈ�� ���� json������ jackson�̿��ؼ� �Ľ��ϱ�
//		ObjectMapper mapper=new ObjectMapper();
		SendMessage msg=mapper.readValue(message.getPayload(), SendMessage.class);
		log.debug("{}",msg);
		
		switch(msg.getType()) {
			case "open" : addClient(session,msg);break;//client������ �߰�
			case "msg" : sendMessage(msg);break;//�޼����� Ŭ���Ʈ���� ����
			case "system" : sendAdminMessage();break;// �ý��������� Ŭ���̾�Ʈ���� ����
		}
		
		
	}
	
	private void addClient(WebSocketSession session, SendMessage msg) throws IOException{
		session.getAttributes().put("info", msg);
		clients.put(msg.getSender(), session);
		SendMessage adminmsg=new SendMessage("system","","",msg.getSender()+"�� �����߽��ϴ�.","");
//		ObjectMapper mapper=new ObjectMapper();
		for(String id:clients.keySet()) {
			WebSocketSession client=clients.get(id);
			client.sendMessage(new TextMessage(mapper.writeValueAsString(adminmsg)));
		}
	}
	
	private void sendMessage(SendMessage msg) throws IOException{
		
		for(String id:clients.keySet()) {
			WebSocketSession client=clients.get(id);
			client.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
		}	
		
	}
	private void sendAdminMessage() {
		
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	
	
	

}
