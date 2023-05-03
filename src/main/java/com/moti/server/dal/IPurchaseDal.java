package com.moti.server.dal;

import com.moti.server.entities.PurchaseEntity;
import com.moti.server.dto.PurchaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchaseDal extends CrudRepository<PurchaseEntity, Long> {
    /*
    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.customerId,pur.couponld,pur.totalCost,pur.companyId,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB();

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.customerId,pur.couponld,pur.totalCost,pur.companyId,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB(Pageable pageable);

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.customerId,pur.couponld,pur.totalCost,pur.companyId,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur " +
            "WHERE pu.id= :id")
    PurchaseDto findPurchaseById(@Param("id") long id);

*/




    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB();

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB(Pageable pageable);

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost)  FROM " +
            "PurchaseEntity pur " +
            "WHERE pur.id= :id")
    PurchaseDto findPurchaseById(@Param("id") long id);
}



// Date issue
    /*
    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB();

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur")
    List<PurchaseDto> getAllPurchasesFromDB(Pageable pageable);

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (pur.id,pur.amount,pur.totalCost,pur.purchaseDate)  FROM " +
            "PurchaseEntity pur " +
            "WHERE pu.id= :id")
    PurchaseDto findPurchaseById(@Param("id") long id);
}
*/