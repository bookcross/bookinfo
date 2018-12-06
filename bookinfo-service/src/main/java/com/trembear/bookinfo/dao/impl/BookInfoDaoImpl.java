package com.trembear.bookinfo.dao.impl;

import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.entity.BookInfo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-12-06 15:11
 */
@Component
public class BookInfoDaoImpl extends MongoDaoImpl<BookInfo> implements BookInfoDao {

}
