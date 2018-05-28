///*
// * 作者：xuda
// * 创建时间：18-5-25 下午1:49
// * 模块名称：admin
// */
//
//package com.fyerp.admin.respository.BaseRespository;
//
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//
//import javax.persistence.EntityManager;
//import java.io.Serializable;
//
//public class SimpleExtJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtJpaRepository<T, ID> {
//    private final EntityManager em;
//
//    public SimpleExtJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
//        super(entityInformation, em);
//        this.em = em;
//    }
//
//    public SimpleExtJpaRepository(Class<T> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.em = em;
//    }
//
//    @Override
//    @Transactional
//    public T dynamicSave(ID id, T entity) {
//        T managedEntity = this.findOne(id);
//        T mergedEntity;
//        if (managedEntity == null) {
//            em.persist(entity);
//            mergedEntity = entity;
//        } else {
//            BeanUtils.copyProperties(entity, managedEntity, BeanUtils.);
//            em.merge(managedEntity);
//            mergedEntity = managedEntity;
//        }
//        return mergedEntity;
//    }
//}
