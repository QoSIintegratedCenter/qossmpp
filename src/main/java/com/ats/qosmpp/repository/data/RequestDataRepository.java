package com.ats.qosmpp.repository.data;

import com.ats.qosmpp.domain.Requests;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface RequestDataRepository extends DataTablesRepository<Requests, Integer> {
}
