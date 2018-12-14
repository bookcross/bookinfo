package com.trembear.bookinfoapi.dto;

import java.util.Date;
import java.util.List;

/**
 * description 图书DTO
 *
 * @author Junwei.Xiong
 * since 2018-12-07 11:18
 */
public class BookDto {
    /**
     *     bookName: "",
     *     address: "",
     *     addressJ: "",
     *     addressW: "",
     *     author: "",
     *     picList:[],
     *     innerPic: "",
     *     describe: ""
     */
    private Long id;
    private String bookName;
    //标题图
    private String bookHeadImg;
    //多图
    private List<PicFileDto> picList;
    //简介
    private String bookConent;
    //书主
    private String bookOwner;
    //作者
    private String author;
    //类型
    private String type;
    //适合年龄
    private String suitbleAge;
    //精度
    private String addressJ;
    //维度
    private String addressW;
    //收藏数
    private Integer collectionNum;
    //评论数
    private Integer replayNum;
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

    public List<PicFileDto> getPicList() {
        return picList;
    }

    public void setPicList( List<PicFileDto> picList) {
        this.picList = picList;
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

    public String getAddressJ() {
        return addressJ;
    }

    public void setAddressJ(String addressJ) {
        this.addressJ = addressJ;
    }

    public String getAddressW() {
        return addressW;
    }

    public void setAddressW(String addressW) {
        this.addressW = addressW;
    }
}
