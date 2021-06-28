package com.group4.serviceTests;

import com.group4.entities.Account;
import com.group4.entities.Message;
import com.group4.exceptions.InvalidInputException;
import com.group4.exceptions.ResourceNotFound;
import com.group4.services.MessageService;
import com.group4.daos.AccountDAO;
import com.group4.daos.MessageDao;
import com.group4.services.MessageServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MessageServiceImplTests {

    AccountDAO accountDao = Mockito.mock(AccountDAO.class);
    MessageDao messageDao = Mockito.mock(MessageDao.class);
    MessageService messageService = new MessageServiceImpl(messageDao, accountDao);

    /*test data*/
    Message testMsg1 = new Message(0, 1, 2, "test", 0,1);
    Account testAcct = new Account(0, "email@abc.com", "password", "can", "dy", 123, 2);
    Message invalidSender = new Message(0, 1000, 2, "test", 0,1);
    Message invalidReceiver = new Message(0, 1, 1001, "test", 0,1);
    Message emptyBody = new Message(0, 1, 2, "", 0,1);

    @BeforeTest
    void init(){
        /*results for successfully add*/
        Mockito.when(accountDao.getAccountById(1)).thenReturn(testAcct);
        Mockito.when(accountDao.getAccountById(2)).thenReturn(testAcct);
        Mockito.when(messageDao.createMessage(testMsg1)).thenReturn(testMsg1);

        /*mock recieving a ResourceNotFoundError*/
        Mockito.when(accountDao.getAccountById(1000)).thenThrow(new ResourceNotFound("sender not found."));
        /*for fail to add new because invalid receiver*/
        Mockito.when(accountDao.getAccountById(1001)).thenThrow(new ResourceNotFound("receiver not found."));

        /*fail to get message by id ResourceNotFoundError*/
        Mockito.when(messageDao.getMessageById(10000)).thenThrow(new ResourceNotFound("message not found"));
    }

    /*Successfully created a message*/
    @Test
    void create_message_success(){
        Message result = messageService.addMessage(testMsg1);
        Assert.assertEquals(result.getMessageID(), testMsg1.getMessageID());
    }

    /*Fail to create a message because invalid sender*/
    @Test
    void create_message_fail_sender_not_exist(){
        try{
            Message result = messageService.addMessage(invalidSender);
            Assert.assertFalse(true);
        }catch(ResourceNotFound resourceNotFound){
            Assert.assertEquals(resourceNotFound.getMessage() , "sender not found.");
        }
    }

    /*Fail to create a message because invalid receiver*/
    @Test
    void create_message_fail_receiver_not_exist(){
        try{
            Message result = messageService.addMessage(invalidReceiver);
            Assert.assertFalse(true);
        }catch(ResourceNotFound resourceNotFound){
            Assert.assertEquals(resourceNotFound.getMessage(), "receiver not found.");
        }
    }


    /*Fail to create a message because body is empty*/
    @Test
    void create_message_fail_empty_body(){
        try{
            Message result = messageService.addMessage(emptyBody);
            Assert.assertFalse(true);
        }catch(InvalidInputException invalidInputException){
            Assert.assertEquals(invalidInputException.getStatusCode(), 400);
        }
    }

    /*Fail to retrieve message because invalid id*/
    @Test
    void failToRetrieve_ResourceNotFoundException(){
        try{
            Message result = messageService.retrieveMessageById(10000);
            Assert.assertFalse(true);
        }catch(ResourceNotFound resourceNotFound){
            Assert.assertEquals(resourceNotFound.getMessage(), "message not found");
        }
    }
}
