package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.AccountWithBrokerIdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountWithBrokerIdRepository extends CrudRepository<AccountWithBrokerIdEntity, Integer> {

    @Query("select new AccountWithBrokerIdEntity(account.id, account.cash, broker.name, rate.name) " +
            "FROM AccountEntity account " +
            "left join BrokerEntity broker on account.brokerId = broker.id " +
            "left join RateEntity rate on account.rateId = rate.id where account.clientId = :id " +
            "order by account.cash DESC")
    List<AccountWithBrokerIdEntity> getAccountWithBrokerIdEntity(@Param("id") int id);
}
