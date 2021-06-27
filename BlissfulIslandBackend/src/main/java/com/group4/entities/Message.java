package com.group4.entities;

import com.group4.exceptions.DaoFailureException;

import java.sql.SQLException;

public class Message {
    private int messageID;
    private int senderID;
    private int receiverID;
    private String messageBody;
    private long timeSent;
    private int messageType;

    /**
     * Construct a Message
     * @param messageID
     * @param senderID
     * @param receiverID
     * @param messageBody
     * @param timeSent
     * @param messageType
     */
    public Message(int messageID, int senderID, int receiverID, String messageBody, long timeSent, int messageType) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.messageBody = messageBody;
        this.timeSent = timeSent;
        this.messageType = messageType;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", senderID=" + senderID +
                ", receiverID=" + receiverID +
                ", messageBody='" + messageBody + '\'' +
                ", timeSent=" + timeSent +
                ", messageType=" + messageType +
                '}';
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public long getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(long timeSent) {
        this.timeSent = timeSent;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
