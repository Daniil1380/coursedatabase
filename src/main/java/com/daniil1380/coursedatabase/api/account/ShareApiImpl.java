package com.daniil1380.coursedatabase.api.account;

import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.service.ShareService;
import io.swagger.client.ApiException;
import io.swagger.client.api.ShareApi;
import io.swagger.client.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShareApiImpl extends ShareApi {
    @Autowired
    private ShareService shareService;


    @RequestMapping(value = "/shares/{shareId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    @Override
    public void deleteShare(@PathVariable("shareId") Long shareId) throws ApiException {
        shareService.delete(Math.toIntExact(shareId));
    }


    @RequestMapping(value = "/shares/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Share> getShares() throws ApiException {
        return shareService.listAll()
                .stream()
                .map(ShareEntity::toShare)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/shares/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Share postShare(@RequestBody Share body) throws ApiException {
        shareService.save(new ShareEntity(body));
        return body;
    }
}
