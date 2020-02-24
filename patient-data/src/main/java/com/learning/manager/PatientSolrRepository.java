package com.learning.manager;

import com.learning.entity.PatientSolrEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface PatientSolrRepository extends SolrCrudRepository<PatientSolrEntity, String> {

    public List<PatientSolrEntity> findByName(String name);

    @Query("id:*?0* OR name:*?0*")
    public Page<PatientSolrEntity> findByCustomQuery(String searchTerm);

}
