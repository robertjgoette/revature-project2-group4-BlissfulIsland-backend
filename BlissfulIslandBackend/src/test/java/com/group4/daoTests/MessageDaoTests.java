package com.group4.daoTests;
import com.group4.daos.MessageDao;
import com.group4.daos.MessageDaoPostgres;
import com.group4.entities.Message;
import com.group4.exceptions.DaoFailureException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class MessageDaoTests {
    static MessageDao messageDao = new MessageDaoPostgres();

    /*Successfully add a new message*/
    @Test(priority = 2)
    void createNewMessageSuccess(){
        Message testMessage = new Message(0, 58, 59, "Test Message", 0, 1);
        Message result = messageDao.createMessage(testMessage);
        Assert.assertNotEquals(result.getMessageID(), 0);
    }

    /*Fail to add a new message because no sender with that ID*/
    @Test(priority = 3)
    void createNewMessageFailSenderNotExist(){
        try {
            Message testMessage = new Message(0, 888888, 1, "Test Message", 0, 1);
            Message result = messageDao.createMessage(testMessage);
            Assert.assertFalse(true);
        }catch (DaoFailureException messageNotFoundException) {
            Assert.assertTrue(true);
        }
    }

    /*Fail to add a new message because no receiver with that ID*/
    @Test(priority = 4)
    void createNewMessageFailReceiverNotExist(){
        try {
            Message testMessage = new Message(0, 888888, 1, "Test Message", 0, 1);
            Message result = messageDao.createMessage(testMessage);
            Assert.assertFalse(true);
        }catch (DaoFailureException messageNotFoundException) {
            Assert.assertTrue(true);
        }
    }
    /*Fail to add a new message because message body is empty */
    @Test(priority = 5)
    void createNewMessageFailEmptyBody(){
        try {
            Message testMessage = new Message(0, 888888, 1, "", 0, 1);
            Message result = messageDao.createMessage(testMessage);
            Assert.assertFalse(true);
        }catch (DaoFailureException messageNotFoundException) {
            Assert.assertTrue(true);
        }
    }

    /*Successfully get message by Id*/
    @Test(priority = 6)
    void getMessageById(){
        Message result = messageDao.getMessageById(44);
        Assert.assertEquals(result.getMessageID(), 44);
    }

    /*fail to get message because bad value*/
    @Test(priority = 7)
    void getMessageByIdFailBadValue(){
        try {
            Message result = messageDao.getMessageById(-123);
            Assert.assertFalse(true);
        }catch(DaoFailureException daoFailureException){
            Assert.assertTrue(true);
            Assert.assertEquals(daoFailureException.getStatusCode(), 404);
        }
    }

    /*Fail to get message by Id because doesn't exist*/
    @Test(priority = 8)
    void getMessageByIdFailNotExist(){
        try {
            Message result = messageDao.getMessageById(12312);
            Assert.assertFalse(true);
        }catch(DaoFailureException daoFailureException){
            Assert.assertEquals(daoFailureException.getStatusCode(), 404);
        }
    }

    /*Success get all messages*/
    @Test(priority = 9)
    void getAllMessagesExist(){
        List<Message> result = messageDao.getAllMessages();
        Assert.assertTrue(result.size() > 0);
    }

    /*Success get all messages no messages exist*/
    @Test(priority = 1)
    void getAllMessagesExistNoneExist(){
        List<Message> result = messageDao.getAllMessages();
        Assert.assertTrue(result.size() == 0);
    }
}
