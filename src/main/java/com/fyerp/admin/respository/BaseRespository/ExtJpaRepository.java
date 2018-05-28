///*
// * 作者：xuda
// * 创建时间：18-5-25 下午1:47
// * 模块名称：admin
// */
//
//package com.fyerp.admin.respository.BaseRespository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.io.Serializable;
//
//public interface ExtJpaRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {
//    /**
//     * insert or dynamic update entity (will findOne first)
//     * @param id entity id
//     * @param entity entity
//     * @return entity
//     */
//    T dynamicSave(ID id, T entity);
//}
