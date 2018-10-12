package com.fyerp.admin.respository.BaseRespository.impl;


import com.fyerp.admin.respository.BaseRespository.BaseRepository;
import com.fyerp.admin.utils.UpdateUtil;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BaseRepositoryImpl<T, ID extends Serializable>  extends SimpleJpaRepository<T,ID> implements BaseRepository<T, ID> {
    private final Class<T> domainClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
    }

    @Override
    public void baseTest() {
        System.out.println("test!!!!");
    }

    @Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }



    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        ID id = getClassId(entity);
        //是否是更新操作
        boolean isUpdate = false;

        //id如果小于等于0，新增
        if(id instanceof Integer){
            if(((Integer)id).intValue() > 0){
                isUpdate = true;
            }
        }
        if(id instanceof Long){
            if(((Long)id).longValue() > 0L){
                isUpdate = true;
            }
        }
        if ( !isUpdate){
            return super.save(entity);
        }

        //如果是更新，不更新为null字段
        T s = this.findOne(id);
        if(s != null){
            UpdateUtil.copyNullProperties(s,entity);
        }
        return super.save(entity);
    }

    //获取id
    private ID getClassId(T entity){
        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        ID id = null;
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                Annotation anno = field.getAnnotation(Id.class);
                if(anno != null){
                    try {
                        field.setAccessible(true);
                        id = (ID)field.get(entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return id;
    }



}
