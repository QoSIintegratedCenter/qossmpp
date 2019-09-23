package com.ats.qosmpp.repository.data;

import com.ats.qosmpp.domain.Responses;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ResponseDataRepository extends DataTablesRepository<Responses, Integer> {
}
