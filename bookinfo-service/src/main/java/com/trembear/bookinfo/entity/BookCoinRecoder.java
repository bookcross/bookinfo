package com.trembear.bookinfo.entity;

import com.trembear.bookinfo.annotation.AutoInc;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * description 书币记录
 *
 * @author Junwei.Xiong
 * since 2018-12-11 13:48
 */
public class BookCoinRecoder {
    @Id
    @AutoInc
    private Long id;
    private Integer userId;
    private Long crossRecoderId;
    private String type;//0 系统赠送，1 交易活动
    private int coin;
    private int total;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCrossRecoderId() {
        return crossRecoderId;
    }

    public void setCrossRecoderId(Long crossRecoderId) {
        this.crossRecoderId = crossRecoderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
