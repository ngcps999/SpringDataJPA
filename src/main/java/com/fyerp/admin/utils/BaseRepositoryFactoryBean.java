package com.fyerp.admin.utils;

import com.fyerp.admin.respository.BaseRespository.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em){
        return new MyRepositoryFactory(em);
    }

    private static class MyRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory{
        private final EntityManager em;
        public MyRepositoryFactory(EntityManager em){
            super(em);
            this.em = em;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {

            return new BaseRepositoryImpl<T,I>((Class<T>) information.getDomainType(), em);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
