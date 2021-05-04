package com.daniil1380.coursedatabase.api.account;

import com.daniil1380.coursedatabase.entity.BrokerEntity;
import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.service.ClientService;
import io.swagger.client.ApiException;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientApiImpl extends UserApi {
    @Autowired
    private ClientService clientService;



    @RequestMapping(value = "/users/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<User> getUsers() throws ApiException {
        return clientService.listAll().stream().map(ClientEntity::toUser).collect(Collectors.toList());
    }



    @RequestMapping(value = "/users/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public User postUser(@RequestBody User body) throws ApiException {
        clientService.save(new ClientEntity(body));
        return body;
    }
}
