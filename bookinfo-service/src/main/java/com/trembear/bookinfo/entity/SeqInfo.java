package com.trembear.bookinfo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * mongodb自增长序列化类
 *
 * @author MaChuang
 * @since 2018/6/27
 */
@Document(collection = "seqInfo")
public class SeqInfo {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 集合名称
     */
    @Field
    @Indexed(unique = true)
    private String collName;
    /**
     * 序列值
     */
    @Field
    private Long seqId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }
}
