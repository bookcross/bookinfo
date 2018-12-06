package com.trembear.bookinfo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Junwei.Xiong
 * @description BookInfo
 * @since 2018-12-06 14:38
 */
@Document(collection = "bookDoc")
public class BookInfo {
    @Id
    private Long id;
    private String bookName;
    private String bookHeadImg;
    private String bookPic;
    private String bookConent;
    private String bookOwner;
    private String author;
    private String type;
    private String suitbleAge;
    private Integer collectionNum;
    private Integer replayNum;
    private String locaion;
    private Date canCrossDate;
    private Date createdDate;
    private String isDelete;
    private Integer star;

    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookHeadImg() {
        return bookHeadImg;
    }

    public void setBookHeadImg(String bookHeadImg) {
        this.bookHeadImg = bookHeadImg;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public String getBookConent() {
        return bookConent;
    }

    public void setBookConent(String bookConent) {
        this.bookConent = bookConent;
    }

    public String getBookOwner() {
        return bookOwner;
    }

    public void setBookOwner(String bookOwner) {
        this.bookOwner = bookOwner;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuitbleAge() {
        return suitbleAge;
    }

    public void setSuitbleAge(String suitbleAge) {
        this.suitbleAge = suitbleAge;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getReplayNum() {
        return replayNum;
    }

    public void setReplayNum(Integer replayNum) {
        this.replayNum = replayNum;
    }

    public String getLocaion() {
        return locaion;
    }

    public void setLocaion(String locaion) {
        this.locaion = locaion;
    }

    public Date getCanCrossDate() {
        return canCrossDate;
    }

    public void setCanCrossDate(Date canCrossDate) {
        this.canCrossDate = canCrossDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
