package com.trembear.bookinfoapi.dto;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-10 13:33
 */
public class BookReplyDto {
    private Long id;
    private Long parentId;
    private Long bookId;
    private Long senderId;
    private String senderName;
    private String acceptId;
    private String acceptName;
    private String content;
    private Date createTime;
    private String isDelete;
    private List<BookReplyDto> bookReplyDtos;

    public List<BookReplyDto> getBookReplyDtos() {
        return bookReplyDtos;
    }

    public void setBookReplyDtos(List<BookReplyDto> bookReplyDtos) {
        this.bookReplyDtos = bookReplyDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}

