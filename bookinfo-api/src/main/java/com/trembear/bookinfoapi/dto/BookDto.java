package com.trembear.bookinfoapi.dto;

import com.trembear.bookinfoapi.vo.PageDetail;

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
    private int bookOwner;
    //作者
    private String author;
    //类型
    private String bookType;
    //适合年龄
    private String suitableAge;
    //精度
    private String addressJ;
    //维度
    private String addressW;
    //收藏数
    private int collectionNum;
    //评论数
    private int replayNum;
    //位置
    private String address;
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
    private String bookOwnerName;
    //是否可以收藏
    private String isCollent;
    //漂流列表
    private List<BookCrossRecoderDto> bookCrossRecoderDtos;
    //漂流
    private PageDetail<BookReplyDto> bookReplyDtos;

    public String getBookOwnerName() {
        return bookOwnerName;
    }

    public void setBookOwnerName(String bookOwnerName) {
        this.bookOwnerName = bookOwnerName;
    }

    public String getIsCollent() {
        return isCollent;
    }

    public void setIsCollent(String isCollent) {
        this.isCollent = isCollent;
    }

    public List<BookCrossRecoderDto> getBookCrossRecoderDtos() {
        return bookCrossRecoderDtos;
    }

    public void setBookCrossRecoderDtos(List<BookCrossRecoderDto> bookCrossRecoderDtos) {
        this.bookCrossRecoderDtos = bookCrossRecoderDtos;
    }

    public PageDetail<BookReplyDto> getBookReplyDtos() {
        return bookReplyDtos;
    }

    public void setBookReplyDtos(PageDetail<BookReplyDto> bookReplyDtos) {
        this.bookReplyDtos = bookReplyDtos;
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

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getSuitableAge() {
        return suitableAge;
    }

    public void setSuitableAge(String suitableAge) {
        this.suitableAge = suitableAge;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
