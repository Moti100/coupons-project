package com.moti.server.dal;

import com.moti.server.dto.CouponDto;
import com.moti.server.entities.CouponEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICouponDal extends CrudRepository<CouponEntity, Long> {


    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getNotExpiredCouponsForCategoryCheck();

    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getAllCoupons();


    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getAllCoupons(Pageable paging);


    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup " +
            "WHERE coup.id= :id")
    CouponDto getCouponById(long id);

    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getAllDataOfCouponsByPriceRange(long minPrice, long maxPrice);

    @Query("SELECT NEW com.moti.server.dto.CouponDto (coup.id,coup.price)  FROM " +
            "CouponEntity coup " +
            "WHERE coup.id= :id")
    List<CouponDto> getExpiredCoupons();
/*
    @Query("select  id, company_Id,  start_date,  end_date,  amount   from coupons where end_date <= (curdate()-1)")
    void deleteExpeiredCoupons();
    */

/*
    @Query("select  id, company_Id,  start_date,  end_date,  amount from coupons where end_date <= (curdate()-1)")
    List<CouponDto> CompanyInNotExpireCoupons(long id);
   */

    /*


    @Query("SELECT NEW com.moti.server.newdto.PurchaseDto (cus.id,cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CustomerEntity cus" +
            "WHERE us.id= :id")
    List<CouponDto> getNotExpiredCouponsForCategoryCheck();

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getAllCoupons();


    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CouponEntity coup")
    List<CouponDto> getAllCoupons(Pageable paging);


    @Query("SELECT NEW com.moti.server.nto.CouponDto (cus.id,cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CouponEntity coup" +
            "WHERE coup.id= :id")
    CouponDto getCouponById(long id);

    @Query("SELECT NEW com.moti.server.dto.CouponDto (cus.id,cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CouponEntity coup" +
            "WHERE coup.id= :id")
    List<CouponDto> getAllDataOfCouponsByPriceRange(long minPrice, long maxPrice);

    @Query("SELECT NEW com.moti.server.dto.PurchaseDto (cus.id,cus.userName,cus.address,cus.phone,cus.amountOfKids)  FROM " +
            "CouponEntity coup " +
            "WHERE coup.id= :id")
    List<CouponDto> getExpiredCoupons();

    @Query("select  id, company_Id,  start_date,  end_date,  amount   from coupons where end_date <= (curdate()-1)")
    void deleteExpeiredCoupons();

    @Query("select  id, company_Id,  start_date,  end_date,  amount   from coupons where end_date <= (curdate()-1)")
    List<CouponDto> CompanyInNotExpireCoupons(long id);

     */
}
