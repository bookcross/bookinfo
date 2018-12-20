package com.trembear.bookinfoapi.dto;

import java.util.Date;

/**
 * description 图书借阅ＤＴＯ
 *
 * @author Junwei.Xiong
 * since 2018-12-07 11:18
 */
public class BookCrossRecoderDto {
    private Long id;
    private Long bookId;
    private String name;
    private Integer senderId;
    private String senderName;
    private String  senderJ;
    private String  senderW;
    private String senderAddress;
    private Integer accepterId;
    private String accepterName;
    private String accepterJ;
    private String accepterW;
    private String accepterAddress;
    private Date sendTime;
    private Date acceptTime;
    private String type;//0:新书发布，1，正常漂流
    private boolean isSend;//是否发出
    private boolean isAccept;//是否确认收到

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAccepterName() {
        return accepterName;
    }

    public void setAccepterName(String accepterName) {
        this.accepterName = accepterName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(Integer accepterId) {
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
