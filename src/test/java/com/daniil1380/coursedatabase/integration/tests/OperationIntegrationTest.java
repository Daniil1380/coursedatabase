package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.ClientApiImpl;
import com.daniil1380.coursedatabase.api.OperationApiImpl;
import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.entity.OperationEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.ApiException;
import io.swagger.client.model.Operation;
import io.swagger.client.model.User;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationIntegrationTest {

    @Autowired
    OperationApiImpl operationApi;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkOperationPost() throws ApiException {
        List<Operation> operationList = operationApi.getOperations();
        int before = operationList.size();
        operationApi.postOperation(new OperationEntity(1, 2, 100, "b").toOperation());
        operationList = operationApi.getOperations();
        Assert.assertEquals(before+1, operationList.size());
    }

    @Test
    @Transactional
    public void checkOperationGet() throws Exception {
        List<Operation> operationList = operationApi.getOperations();
        Assert.assertNotNull(operationList);
        Assert.assertEquals(4, operationList.size());
        Assert.assertEquals(operationList.get(0), new OperationEntity(1, 2, 1, 100, "b").toOperation());
    }
}
