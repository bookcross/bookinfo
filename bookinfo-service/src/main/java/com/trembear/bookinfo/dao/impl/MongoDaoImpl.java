package com.trembear.bookinfo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.trembear.bookinfo.common.enums.SystemRest;
import com.trembear.bookinfo.common.exception.BookException;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.dao.MongoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mongo持久层操作
 * @author MaChuang
 * @since 2018/6/27
 * @param <T> .
 */
public class MongoDaoImpl<T> implements MongoDao<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoDaoImpl.class);

    @Autowired
    protected MongoTemplate mongoTemplate;

    private Class<T> clazz;

    public MongoDaoImpl() {
        try {
            ParameterizedType type  = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazz = (Class<T>) type.getActualTypeArguments()[0];
            LOGGER.info("reflect Class:{}",clazz);
        } catch (Exception e) {
            LOGGER.error("MongoDaoImpl reflection happen error:{}",e);
        }
    }
    /**
     * 创建对象
     * @param t
     */
    @Override
    public void save(T t) throws BookException{
        try {
            if (t != null){
                mongoTemplate.save(t);
            }else{
                throw new BookException(new RestFulVO(SystemRest.BBS_CREATE_FAULT));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s save error for MongoDB:",JSON.toJSONString(t)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_CREATE_FAULT));
        }
    }

    /**
     * 批量保存
     * @param entities
     */
    @Override
    public void saveBatch(List<T> entities) throws BookException{
        try {
            if (entities != null && entities.size() > 0){
                mongoTemplate.insert(entities,clazz);
            }else {
                throw new BookException(new RestFulVO(SystemRest.BBS_CREATE_FAULT));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s saveBatch error for MongoDB:{}",JSON.toJSONString(entities)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_CREATE_FAULT));
        }
    }
    /**
     * 删除对象
     * @param id
     */
    @Override
    public void delete(Serializable id) throws BookException{
        try {
            if (id != null){
                Query query = new Query(Criteria.where("id").is(id));
                mongoTemplate.remove(query,clazz);
            }else {
                throw new BookException(new RestFulVO(SystemRest.BBS_NOT_EXISTS));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s delete error for MongoDB:{}",id),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_NOT_EXISTS));
        }
    }
    /**
     * 更新对象
     * @param t
     */
    @Override
    public void update(T t) throws BookException{
        try {
            if (t != null){
                Method getId = ReflectionUtils.findMethod(clazz,"getId");
                Object id = ReflectionUtils.invokeMethod(getId, t);
                Query query = new Query(Criteria.where("id").is(id));
                Update update = new Update();
                ReflectionUtils.doWithFields(clazz, field -> {
                    ReflectionUtils.makeAccessible(field);
                    if (!"id".equals(field.getName()) && field.get(t) != null){
                            update.set(field.getName(),field.get(t));
                    }
                });
                mongoTemplate.updateFirst(query,update,clazz);
            }else {
                throw new BookException(new RestFulVO(SystemRest.BBS_UPDATE_FAULT));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s update error for MongoDB:{}",JSON.toJSONString(t)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_UPDATE_FAULT));
        }
    }

    /**
     * 由id查询对象
     * @param id
     * @return
     */
    @Override
    public T findById(Serializable id) throws BookException {
       return this.findById(id, null);
    }

    /**
     * 由id查询对象,排除字段
     * @param id
     * @param excludeFields
     * @return
     */
    @Override
    public T findById(Serializable id, List<String> excludeFields) throws BookException{
        try {
            if (id != null){
                Query query = new Query(Criteria.where("id").is(id));
                if (excludeFields != null && excludeFields.size() > 0){
                    Field fields = query.fields();
                    excludeFields.stream().forEach(t -> fields.exclude(t));
                }
                return mongoTemplate.findOne(query,clazz);
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s findById error for MongoDB:{}",id),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
        return null;
    }

    /**
     * 搜索关键字,带分页、排序和排除字段
     * @param pageNum
     * @param pageSize
     * @param condition
     * @param field
     * @param keyword
     * @param sort
     * @param excludeFields
     * @return
     */
    @Override
    public List<T> search(int pageNum, int pageSize, Map<String, Object> condition, String field, String keyword, Sort sort, List<String> excludeFields) throws BookException{
        try {
            if(!StringUtils.isEmpty(keyword) && !StringUtils.isEmpty(field) ){
                Query query = new Query(Criteria.where(field).regex(keyword));
                if (condition != null) {
                    condition.forEach((k, v) -> {
                        query.addCriteria(Criteria.where(k).is(v));
                    });
                }
                if (excludeFields != null && excludeFields.size() > 0){
                    Field fields = query.fields();
                    excludeFields.stream().forEach(t -> fields.exclude(t));
                }
                if (sort != null){
                    query.with(sort);
                }
                query.skip((pageNum - 1) * pageSize).limit(pageSize);
                return mongoTemplate.find(query, clazz);
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s search error for MongoDB:{}",keyword),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
        return null;
    }

    /**
     * 搜索关键字,带分页、排序
     * @param pageNum
     * @param pageSize
     * @param field
     * @param keyword
     * @param sort
     * @return
     */
    @Override
    public List<T> search(int pageNum, int pageSize, String field, String keyword, Sort sort) throws BookException {
        return this.search(pageNum,pageSize,null,field,keyword,sort,null);
    }

    /**
     * 搜索关键字,带分页
     * @param pageNum
     * @param pageSize
     * @param field
     * @param keyword
     * @return
     */
    @Override
    public List<T> search(int pageNum, int pageSize, String field, String keyword) throws BookException {
        return this.search(pageNum,pageSize,null,field,keyword,null,null);
    }

    /**
     * 统计搜索的总记录数
     * @param field
     * @param keyword
     * @param condition
     * @return
     */
    @Override
    public Long searchTotal(Map<String, Object> condition, String field, String keyword) throws BookException{
        try {
            if(!StringUtils.isEmpty(keyword) && !StringUtils.isEmpty(field) ){
                Query query = new Query(Criteria.where(field).regex(keyword));
                if (condition != null) {
                    condition.forEach((k, v) -> {
                        query.addCriteria(Criteria.where(k).is(v));
                    });
                }
                return mongoTemplate.count(query,clazz);
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s searchTotal error for MongoDB:{}",keyword),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
        return null;
    }

    /**
     * 按对象条件查询
     * @param condition
     * @return
     */
    @Override
    public List<T> findByCondition(Map<String,Object> condition) throws BookException {
        return this.findByCondition(condition,null,null);
    }

    /**
     * 按对象条件查询,排序
     * @param condition
     * @return
     */
    @Override
    public List<T> findByCondition(Map<String,Object> condition,Sort sort) throws BookException {
        return this.findByCondition(condition,null,sort);
    }

    /**
     * 按对象条件查询，排除字段
     * @param condition
     * @param excludeFields
     * @return
     */
    @Override
    public List<T> findByCondition(Map<String,Object> condition, List<String> excludeFields, Sort sort) throws BookException {
        try {
            if (condition != null) {
                Query query = new Query();
                condition.forEach((k,v) -> {
                    query.addCriteria(Criteria.where(k).is(v));
                });
                if (excludeFields != null && excludeFields.size() > 0){
                    Field fields = query.fields();
                    excludeFields.stream().forEach(t -> fields.exclude(t));
                }
                if (sort != null){
                    query.with(sort);
                }
                return mongoTemplate.find(query,clazz);
            }
        } catch (Exception e) {
            LOGGER.error(String.format("%s findByExample error for MongoDB:{}",JSON.toJSONString(condition)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
        return new ArrayList<>();
    }
    /**
     * 带条件分页排序查询
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    @Override
    public List<T> pageList(int pageNum, int pageSize,Map<String,Object> condition,Sort sort) throws BookException {
         return this.pageList(pageNum, pageSize, condition, sort,null,null);
    }

    /**
     * 带条件分页排序查询，不带特殊查询条件
     * @param pageNum
     * @param pageSize
     * @param condition
     * @param sort
     * @param excludeFields
     * @return
     * @throws BookException
     */
    @Override
    public List<T> pageList(int pageNum, int pageSize, Map<String, Object> condition, Sort sort, List<String> excludeFields) throws BookException {
        return this.pageList(pageNum, pageSize, condition, sort,excludeFields,null);
    }

    /**
     * 带条件分页排序查询,排除字段
     * @param pageNum
     * @param pageSize
     * @param condition
     * @param sort
     * @param excludeFields
     * @return
     */
    @Override
    public List<T> pageList(int pageNum, int pageSize, Map<String,Object> condition, Sort sort, List<String> excludeFields, Criteria special) throws BookException {
        Query query = new Query();
        try {
            if (condition != null){
                condition.forEach((k,v) -> {
                    query.addCriteria(Criteria.where(k).is(v));
                });
            }
            if (special != null){
                query.addCriteria(special);
            }
            if (excludeFields != null && excludeFields.size() > 0){
                Field fields = query.fields();
                excludeFields.stream().forEach(t -> fields.exclude(t));
            }
            if (sort != null){
                query.with(sort);
            }
            query.skip((pageNum - 1) * pageSize).limit(pageSize);
            return  mongoTemplate.find(query,clazz);
        } catch (Exception e) {
            LOGGER.error(String.format("%s pageList error for MongoDB:{}",JSON.toJSONString(condition)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
    }

    /**
     * 带条件分页排序查询
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    @Override
    public List<T> pageList(int pageNum, int pageSize,Map<String,Object> condition) throws BookException {
        return this.pageList(pageNum, pageSize, condition, null);
    }

    /**
     * 按查询条件和特殊条件统计记录数
     * @param condition
     * @param special
     * @return
     * @throws BookException
     */
    @Override
    public Long recordTotal(Map<String,Object> condition, Criteria special) throws BookException {
        Query query = new Query();
        try {
            if (condition != null){
                condition.forEach((k,v) -> {
                    query.addCriteria(Criteria.where(k).is(v));
                });
            }
            if (special != null){
                query.addCriteria(special);
            }
            return mongoTemplate.count(query,clazz);
        } catch (Exception e) {
            LOGGER.error(String.format("%s recordTotal error for MongoDB:{}",JSON.toJSONString(condition)),e);
            throw new BookException(new RestFulVO(SystemRest.BBS_QUERY_FAULT));
        }
    }
    /**
     * 按查询条件统计记录数
     * @param condition
     * @return
     */
    @Override
    public Long recordTotal(Map<String, Object> condition) throws BookException {
        return this.recordTotal(condition,null);
    }
}
