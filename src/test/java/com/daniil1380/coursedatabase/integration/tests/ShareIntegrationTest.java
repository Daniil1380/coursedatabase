package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.RateApiImpl;
import com.daniil1380.coursedatabase.api.ShareApiImpl;
import com.daniil1380.coursedatabase.entity.RateEntity;
import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.ApiException;
import io.swagger.client.model.Rate;
import io.swagger.client.model.Share;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareIntegrationTest {

    @Autowired
    ShareApiImpl shareApi;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkSharePost() throws ApiException {
        List<Share> shareList = shareApi.getShares();
        int before = shareList.size();
        shareApi.postShare(new ShareEntity("NAME", 1, 100.98, 1000).toShare());
        shareList = shareApi.getShares();
        Assert.assertEquals(before+1, shareList.size());
    }

    @Test
    @Transactional
    public void checkShareGet() throws Exception {
        List<Share> shareList = shareApi.getShares();
        Assert.assertNotNull(shareList);
        Assert.assertEquals(4, shareList.size());
        Assert.assertEquals(shareList.get(0), new ShareEntity(1, "Энел", 2, 0.85, 100).toShare());
    }
}
