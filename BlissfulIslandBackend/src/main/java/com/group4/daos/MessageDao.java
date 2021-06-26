package com.group4.daos;
import com.group4.entities.Message;

import java.util.List;

public interface MessageDao {
    Message getMessageById(int Id);

    List<Message> getAllMessages();

    Message createMessageById(Message message);
}
