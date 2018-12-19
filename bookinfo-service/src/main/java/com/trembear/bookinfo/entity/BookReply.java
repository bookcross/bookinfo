package com.trembear.bookinfo.entity;

import com.trembear.bookinfo.annotation.AutoInc;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * description 书评
 *
 * @author Junwei.Xiong
 * since 2018-12-10 11:37
 */
@Document(collection = "ReplyDoc")
public class BookReply {
    @Id
    @AutoInc
    private Long id;
    private Long parentId;
    private Long bookId;
    private Integer senderId;
    private String senderName;
    private String senderLogo;
    private Integer acceptId;
    private String acceptName;
    private String content;
    private Date createTime;
    private String isDelete;

    public String getSenderLogo() {
        return senderLogo;
    }

    public void setSenderLogo(String senderLogo) {
        this.senderLogo = senderLogo;
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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
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
