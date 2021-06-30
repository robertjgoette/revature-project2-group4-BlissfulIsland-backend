package com.group4.daos;

import java.sql.SQLException;

import com.group4.entities.Message;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.group4.exceptions.InvalidInputException;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;
import com.group4.exceptions.DaoFailureException;


public class MessageDaoPostgres implements MessageDao{
    @Override
    public List<Message> getAllMessages() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from message";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Message> messages = new ArrayList<>();

            while(rs.next()){
                Message message = new Message();
                message.setMessageID(rs.getInt("message_id"));
                message.setSenderID(rs.getInt("sender_id"));
                message.setReceiverID(rs.getInt("receiver_id"));
                message.setMessageBody(rs.getString("message_body"));
                message.setTimeSent(rs.getLong("message_time_sent"));
                message.setMessageType(rs.getInt("message_type"));
                messages.add(message);
            }
            return messages;
        }catch(SQLException sqlException){
            throw new DaoFailureException(sqlException.getMessage(), 404, "Could not get messages.");
        }
    }

    @Override
    public Message createMessage(Message message) {
        try(Connection connection = ConnectionUtil.createConnection()){
        String sql = "insert into message values(default, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        long time = Instant.now().toEpochMilli();
        try {
            ps.setInt(1, message.getSenderID());
            ps.setInt(2, message.getReceiverID());
            ps.setString(3, message.getMessageBody());
            ps.setLong(4, time);
            ps.setInt(5, message.getMessageType());
        }
        catch (NullPointerException e){
            throw new InvalidInputException("Invalid input for message", 404);
        }
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        int key = rs.getInt("message_id");
        message.setMessageID(key);
        message.setTimeSent(time);
        return message;
        }catch(SQLException sqlException){
            throw new DaoFailureException(sqlException.getMessage(), 404, "Cannot create new message");
        }
    }

    @Override
    public Message getMessageById(int id) {
         try(Connection connection = ConnectionUtil.createConnection()){
             String sql = "select * from Message where message_id = ?";
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             rs.next();
             Message message = new Message();
             message.setMessageID(rs.getInt("message_id"));
             message.setSenderID(rs.getInt("sender_id"));
             message.setReceiverID(rs.getInt("receiver_id"));
             message.setMessageBody(rs.getString("message_body"));
             message.setTimeSent(rs.getLong("message_time_sent"));
             message.setMessageType(rs.getInt("message_type"));

             return message;
         }catch(SQLException sqlException){
             throw new DaoFailureException(
                     "The message with the id of " + id +" could not be found",
                     404,
                     "Message not found"
             );
         }
    }
}
