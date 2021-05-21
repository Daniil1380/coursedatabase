package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.RateEntity;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;


@NamedStoredProcedureQuery(
        name = "delrate",
        procedureName = "delrate",
        parameters = {
                @StoredProcedureParameter(
                        name = "brok",
                        type = Integer.class,
                        mode = ParameterMode.IN)})

public interface RateRepository extends CrudRepository<RateEntity, Integer> {

    @Procedure(procedureName = "delrate")
    void deleteLessUsedRate(@Param("brok") int brok);

}

