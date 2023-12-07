package com.secure.jwtimplementation.helper;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public class RefreshableCRUDRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements RefreshableCRUDRepository<T, ID> {
    private final EntityManager em;

    public RefreshableCRUDRepositoryImpl(JpaEntityInformation jpaEntityInformation, EntityManager em) {
        super(jpaEntityInformation, em);
        this.em = em;
    }

    @Override
    @Transactional
    public void flush() {
        em.flush();
    }
    @Override
    @Transactional
    public void refresh(T t) {
        em.refresh(t);
    }

    @Override
    @Transactional
    public void refresh(Collection<T> s) {
        for( T t: s)
            em.refresh(t);
    }
}
