package com.moti.server.dal;

import com.moti.server.dto.CompanyDto;
import com.moti.server.entities.CompanyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyDal extends CrudRepository<CompanyEntity, Long> {
    @Query("SELECT NEW com.moti.server.dto.CompanyDto(comp.id,comp.companyName,comp.email,comp.phoneNumber,comp.address) FROM " +
            "CompanyEntity comp")
    List<CompanyDto> getAllCompanies();

    @Query("SELECT NEW com.moti.server.dto.CompanyDto (comp.id,comp.companyName,comp.email,comp.phoneNumber,comp.address)  FROM " +
            "CompanyEntity comp")
    List<CompanyDto> getAllCompanies(Pageable paging);

    @Query("SELECT NEW com.moti.server.dto.CompanyDto (comp.id,comp.companyName,comp.email,comp.phoneNumber,comp.address)  FROM " +
            "CompanyEntity comp " +
            "WHERE comp.id= :id")
    CompanyDto findCompanyById(@Param("id") long id);
}


