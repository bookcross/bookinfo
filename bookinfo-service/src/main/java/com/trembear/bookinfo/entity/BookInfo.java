package com.trembear.bookinfo.entity;

import com.trembear.bookinfo.annotation.AutoInc;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Junwei.Xiong
 * description BookInfo
 * since 2018-12-06 14:38
 */
@Document(collection = "bookDoc")
public class BookInfo {
    @Id
    private Long id;
    private String bookName;
    //标题图
    private String bookHeadImg;
    //多图
    private String bookPic;
    //简介
    private String bookConent;
    //书主
    private int bookOwner;
    //书主
    private String bookOwnerName;
    //作者
    private String author;
    //类型
    private String type;
    //适合年龄
    private String suitbleAge;
    //收藏数
    private int collectionNum;
    //评论数
    private int replayNum;
    //位置
    private String locaion;
    //可以借书的时间
    private Date canCrossDate;
    private Date createdDate;
    //（如果时间大于CanCrossDate这个时间，那么可借）
    private String canLend ;
    private String isDelete;
    //评分
    private float star;
    //评分人数
    private float starNum;

    public String getBookOwnerName() {
        return bookOwnerName;
    }

    public void setBookOwnerName(String bookOwnerName) {
        this.bookOwnerName = bookOwnerName;
    }

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

    public int getBookOwner() {
        return bookOwner;
    }

    public void setBookOwner(int bookOwner) {
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

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getReplayNum() {
        return replayNum;
    }

    public void setReplayNum(int replayNum) {
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

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public float getStarNum() {
        return starNum;
    }

    public void setStarNum(float starNum) {
        this.starNum = starNum;
    }

    public String getCanLend() {
        return canLend;
    }

    public void setCanLend(String canLend) {
        this.canLend = canLend;
    }
}
