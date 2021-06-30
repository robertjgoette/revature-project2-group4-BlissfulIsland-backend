package com.group4.controllers;

import com.google.gson.Gson;
import com.group4.entities.Message;
import com.group4.exceptions.InvalidInputException;
import com.group4.exceptions.ResourceNotFound;
import com.group4.services.MessageService;
import io.javalin.http.Handler;

import java.awt.print.Book;
import java.util.List;

public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    public Handler createMessage = ctx -> {
        try{
            // 1) gson object and config it to our Message bean object
            Gson gson = new Gson();
            Message message = gson.fromJson(ctx.body(), Message.class);

            // 2) get the data
            message = this.messageService.addMessage(message);

            // 3) make json string
            String messageJSON = gson.toJson(message);

            // 4) complete response
            ctx.result(messageJSON);
            ctx.status(201);
            ctx.contentType("application/json");

        //exceptions
        }catch(InvalidInputException e) {
            ctx.result(e.getMessage());
            ctx.status(e.getStatusCode());
            ctx.contentType("application/json");
        }catch(ResourceNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
            ctx.contentType("application/json");
        }catch(Exception e){
            e.printStackTrace();
            ctx.result("Server error");
            ctx.status(500);
            ctx.contentType("application/json");
        }
    };

    public Handler getAllMessages = ctx -> {
        try{
            List<Message> messages = this.messageService.retrieveAllMessages();
            Gson gson = new Gson();
            String messagesJSON = gson.toJson(messages);
            ctx.result(messagesJSON);
            ctx.status(200);
            ctx.contentType("application/json");
        }catch(Exception e){
            ctx.result(e.getMessage());
            ctx.status(500);
            ctx.contentType("application/json");
        }
    };

    public Handler getMessageById = ctx -> {
        try {
            Gson gson = new Gson();
            int id = Integer.parseInt(ctx.pathParam("id"));
            Message message = messageService.retrieveMessageById(id);
            String messageJSON = gson.toJson(message);
            ctx.result(messageJSON);
            ctx.status(200);
            ctx.contentType("application/json");
        }catch(ResourceNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
            ctx.contentType("application/json");
        }catch(Exception e){
            ctx.result("Server error");
            ctx.status(500);
            ctx.contentType("application/json");
        }
    };
}
