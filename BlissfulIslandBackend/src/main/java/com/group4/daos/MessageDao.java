package com.group4.daos;
import com.group4.entities.Message;

import java.util.List;

public interface MessageDao {
    public Message getMessageById(int Id);

    public List<Message> getAllMessages();

    public Message createMessageById(Message message);
}
