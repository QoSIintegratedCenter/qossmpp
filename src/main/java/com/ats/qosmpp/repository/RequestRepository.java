package com.ats.qosmpp.repository;

import com.ats.qosmpp.domain.Requests;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Requests, Long> {
    Page<Requests> findById(Long id, Pageable pageRequest);
//    Page<Requests> findByIdAndOrderByResquestDateDesc(Long id, Pageable pageRequest);
}
