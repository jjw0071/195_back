package com._hackaton._back.repository;

import com._hackaton._back.domain.JobPosting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostingRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(JobPosting jobPosting) {
        em.persist(jobPosting);
    }

    public JobPosting findOne (Long id) {
        return em.find(JobPosting.class, id);
    }

    public List<JobPosting> findAll() {
        List<JobPosting> result = em.createQuery("select p from Posting p", JobPosting.class)
                .getResultList();
        return result;
    }





}
