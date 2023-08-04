package com._hackaton._back.repository;

import com._hackaton._back.domain.Posting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostingRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Posting posting) {
        em.persist(posting);
    }

    public Posting findOne (Long id) {
        return em.find(Posting.class, id);
    }

    public List<Posting> findAll() {
        List<Posting> result = em.createQuery("select p from Posting p", Posting.class)
                .getResultList();
        return result;
    }





}
