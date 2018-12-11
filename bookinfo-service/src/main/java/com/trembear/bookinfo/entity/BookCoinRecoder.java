package com.trembear.bookinfo.entity;

import java.util.Date;

/**
 * description 书币记录
 *
 * @author Junwei.Xiong
 * since 2018-12-11 13:48
 */
public class BookCoinRecoder {
    private long id;
    private long userId;
    private long crossRecoderId;
    private String type;//0 系统赠送，1 交易活动
    private Integer coin;
    private Integer total;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCrossRecoderId() {
        return crossRecoderId;
    }

    public void setCrossRecoderId(long crossRecoderId) {
        this.crossRecoderId = crossRecoderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
