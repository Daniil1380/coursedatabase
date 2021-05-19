package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.OperationEntity;
import com.daniil1380.coursedatabase.service.OperationService;
import io.swagger.client.api.OperationApi;
import io.swagger.client.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OperationApiImpl extends OperationApi {
    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/operations/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Operation> getOperations(){
        return operationService.listAll()
                .stream()
                .map(OperationEntity::toOperation)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/operations/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Operation postOperation(@RequestBody Operation body){
        operationService.save(new OperationEntity(body));
        return body;
    }
}
