package com.group4.daos;
import com.group4.entities.Message;
import com.group4.exceptions.DaoFailureException;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {
    /**
     * Retrieve a message by the message's ID.
     * @param message the object representation of message to be added to database
     * @return the message that was successfully added
     * @throws SQLException if the connection fails
     * @throws DaoFailureException if message cannot be added
     */
    Message createMessage(Message message);

    /**
     * Retrieve a message by the message's ID.
     * @param Id the id of the message
     * @return the retrieved message
     * @throws SQLException if the connection fails
     * @throws DaoFailureException if id not found in database
     */
    Message getMessageById(int Id);

    /**
     * Retrieve a message by the message's ID.
     * @return List<Message>- List of all messages in database
     * @throws SQLException if the connection fails
     */
    List<Message> getAllMessages();
}
