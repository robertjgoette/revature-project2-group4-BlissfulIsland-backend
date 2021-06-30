package com.group4.services;

import com.group4.daos.AccountDAO;
import com.group4.daos.MessageDao;
import com.group4.entities.Account;
import com.group4.entities.Message;
import com.group4.exceptions.DaoFailureException;
import com.group4.exceptions.ResourceNotFound;
import com.group4.exceptions.InvalidInputException;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{
    private MessageDao messageDao;
    private AccountDAO accountDAO;

    public MessageServiceImpl(MessageDao messageDao, AccountDAO accountDAO){
        this.messageDao = messageDao;
        this.accountDAO = accountDAO;
    }

    @Override
    public Message addMessage(Message message) {
        try {
            //1) first check if users exist to be able to send message
            Account sender = this.accountDAO.getAccountById(message.getSenderID());
            Account receiver = this.accountDAO.getAccountById(message.getReceiverID());

            //2) check that message body is not empty
            if(message.getMessageBody() == ""){
                throw new InvalidInputException("Message body may not be empty", 400);
            }

            //3 retrieve and return message, this should work
            return messageDao.createMessage(message);
        }catch(ResourceNotFound resourceNotFound){
            throw resourceNotFound;
        }catch (NullPointerException nullPointerException){
            throw new InvalidInputException("Invalid input for message", 404);
        }
    }

    @Override
    public List<Message> retrieveAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    public Message retrieveMessageById(int Id) {
        try {
            return messageDao.getMessageById(Id);
        }catch(DaoFailureException daoFailureException){
            throw new ResourceNotFound(daoFailureException.getMessage());
        }
    }
}
