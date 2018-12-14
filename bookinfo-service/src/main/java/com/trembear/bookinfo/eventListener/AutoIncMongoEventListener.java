package com.trembear.bookinfo.eventListener;

import com.trembear.bookinfo.annotation.AutoInc;
import com.trembear.bookinfo.entity.SeqInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


/**
 * 事件监听器，mongodb保存数据会触发该事件
 *
 * @author MaChuang
 * @since 2018/6/27
 */
@Component
public class AutoIncMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        if (event != null) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    //判断添加自定义AutoInc注解
                    if (field.isAnnotationPresent(AutoInc.class)) {
                        // 设置自增ID
                        field.set(event.getSource(), getNextId(event.getCollectionName()));
                    }
                }
            });
        }
    }

    private Long getNextId(String collectionName) {
        Query query = new Query(Criteria.where("collName").is(collectionName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SeqInfo seqInfo = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
        return seqInfo.getSeqId();
    }
}
