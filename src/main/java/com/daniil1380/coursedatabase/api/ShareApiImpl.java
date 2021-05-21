package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.entity.TopSharesEntity;
import com.daniil1380.coursedatabase.service.ShareService;
import io.swagger.client.ApiException;
import io.swagger.client.api.ShareApi;
import io.swagger.client.model.Share;
import io.swagger.client.model.TopShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShareApiImpl extends ShareApi {
    @Autowired
    private ShareService shareService;


    @RequestMapping(value = "/shares/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Share> getShares(){
        return shareService.listAll()
                .stream()
                .map(ShareEntity::toShare)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/shares/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Share postShare(@RequestBody Share body) {
        shareService.save(new ShareEntity(body));
        return body;
    }

    @RequestMapping(value = "/shares/topTen",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<TopShare> getTopShares() throws ApiException {
        return shareService.getTopTenShares().stream().map(TopSharesEntity::toTopShare)
                .collect(Collectors.toList());
    }
}
