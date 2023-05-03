package com.moti.server.dal;

import com.moti.server.entities.CustomerEntity;
import com.moti.server.dto.CustomerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerDal extends CrudRepository<CustomerEntity, Long> {
/*
    @Query("SELECT NEW com.moti.server.dto.CustomerDto (cus.id,us.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CustomerEntity cus Join UserEntity us" +
            "WHERE cus.id= :id")
    CustomerDto findCustomerById(@Param("id") long id);

 */

    @Query("SELECT NEW com.moti.server.dto.CustomerDto(cus.id,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CustomerEntity cus " +
            "WHERE cus.id= :id")
    CustomerDto findCustomerById(@Param("id") long id);

    @Query("SELECT NEW com.moti.server.dto.CustomerDto(cus.id,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CustomerEntity cus")
    List<CustomerDto> getAllCustomers();

    @Query("SELECT NEW com.moti.server.dto.CustomerDto (cus.id,cus.address,cus.phone,cus.amountOfKids)  FROM " +
                    "CustomerEntity cus")
    List<CustomerDto> getAllCustomers(Pageable paging);
}
