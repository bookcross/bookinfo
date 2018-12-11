package com.trembear.bookinfo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * description 图书漂流记录
 *
 * @author Junwei.Xiong
 * since 2018-12-11 11:42
 */
@Document(collection = "recordDoc")
public class BookCrossRecoder {
    @Id
    private long id;
    private long bookId;
    private String name;
    private long senderId;
    private String  senderJ;
    private String  senderW;
    private String senderAddress;
    private long accepterId;
    private String accepterJ;
    private String accepterW;
    private String accepterAddress;
    private Date sendTime;
    private Date acceptTime;
    private boolean isSend;//是否发出
    private boolean isAccept;//是否确认收到

    public String getSenderJ() {
        return senderJ;
    }

    public void setSenderJ(String senderJ) {
        this.senderJ = senderJ;
    }

    public String getSenderW() {
        return senderW;
    }

    public void setSenderW(String senderW) {
        this.senderW = senderW;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getAccepterJ() {
        return accepterJ;
    }

    public void setAccepterJ(String accepterJ) {
        this.accepterJ = accepterJ;
    }

    public String getAccepterW() {
        return accepterW;
    }

    public void setAccepterW(String accepterW) {
        this.accepterW = accepterW;
    }

    public String getAccepterAddress() {
        return accepterAddress;
    }

    public void setAccepterAddress(String accepterAddress) {
        this.accepterAddress = accepterAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(long accepterId) {
        this.accepterId = accepterId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }
}
