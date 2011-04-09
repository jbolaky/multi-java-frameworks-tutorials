package com.mytutorials.spring.ws.cxf.common.service.api;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.mytutorials.spring.ws.cxf.common.entity.Message;

@WebService
public interface ContactUsService {
    
    List<Message> getMessages();
    
    String getMessagesAsString();
    
    void postMessage(@WebParam(name = "message") Message message);
}