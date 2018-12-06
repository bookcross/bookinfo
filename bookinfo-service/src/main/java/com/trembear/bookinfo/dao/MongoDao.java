package com.trembear.bookinfo.dao;

import com.trembear.bookinfo.common.exception.BookException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Mongo持久层操作
 * @author MaChuang
 * @since 2018/6/27
 * @param <T> .
 */
public interface MongoDao<T> {

     void save(T t) throws BookException;
     void saveBatch(List<T> entities) throws BookException;
     void delete(Serializable id) throws BookException;
     void update(T t) throws BookException;
     T findById(Serializable id, List<String> excludeFields) throws BookException;
     T findById(Serializable id) throws BookException;
     List<T> search(int pageNum, int pageSize, Map<String, Object> condition, String field, String keyword, Sort sort, List<String> excludeFields) throws BookException;
     List<T> search(int pageNum, int pageSize, String field, String keyword, Sort sort) throws BookException;
     List<T> search(int pageNum, int pageSize, String field, String keyword) throws BookException;
     Long searchTotal(Map<String, Object> condition, String field, String keyword) throws BookException;
     List<T> findByCondition(Map<String, Object> condition) throws BookException;
     List<T> findByCondition(Map<String, Object> condition, List<String> excludeFields, Sort sort) throws BookException;
     List<T> findByCondition(Map<String, Object> condition, Sort sort) throws BookException;
     List<T> pageList(int pageNum, int pageSize, Map<String, Object> condition, Sort sort, List<String> excludeFields, Criteria special) throws BookException;
     List<T> pageList(int pageNum, int pageSize, Map<String, Object> condition, Sort sort, List<String> excludeFields) throws BookException;
     List<T> pageList(int pageNum, int pageSize, Map<String, Object> condition, Sort sort) throws BookException;
     List<T> pageList(int pageNum, int pageSize, Map<String, Object> condition) throws BookException;
     Long recordTotal(Map<String, Object> condition) throws BookException;
     Long recordTotal(Map<String, Object> condition, Criteria special) throws BookException;
}
