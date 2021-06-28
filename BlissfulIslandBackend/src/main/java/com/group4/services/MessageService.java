package com.group4.services;

import com.group4.entities.Message;
import com.group4.daos.MessageDao;
import com.group4.daos.MessageDaoPostgres;
import com.group4.exceptions.ResourceNotFound;

import java.sql.SQLException;
import java.util.List;

public interface MessageService{
    /**
     * Add a new message
     * @param message the object representation of message to be added to database
     * @return the message that was successfully added
     * @throws ResourceNotFound if the sender or receiver id in the message object are invalid
     * @throws SQLException if the connection in the dao fails
     */
    Message addMessage(Message message);

    /**
     * Retrieve all the messages in the database
     * @return a list of messages retrieved
     * @throws SQLException if the connection in the dao fails
     */
    List<Message> retrieveAllMessages();

    /**
     * Retrieve a message in the database by id
     * @param Id the id of the message to look for.
     * @return  the messages retrieved
     * @throws ResourceNotFound if the message with
     * @throws SQLException if the connection in the dao fails
     */
    Message retrieveMessageById(int Id);
}
