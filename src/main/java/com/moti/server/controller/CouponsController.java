package com.moti.server.controller;

import com.moti.server.dto.CouponDto;
import com.moti.server.entities.CouponEntity;
import com.moti.server.logic.CouponLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {
    private CouponLogic couponLogic;

    @Autowired
    public CouponsController(CouponLogic couponLogic) {
        this.couponLogic = couponLogic;
    }

    @PostMapping
    public void createCoupon(@RequestBody CouponEntity coupon) throws Exception {
        couponLogic.createCoupon(coupon);
    }

    //Delete Coupon
    @DeleteMapping("{couponId}")
    public void deleteCoupon(@PathVariable("couponId") long id) throws Exception {
        couponLogic.removeCoupon(id);
    }

    @PutMapping
    public void updateCoupon(@RequestBody CouponEntity coupon) throws Exception {
        couponLogic.updateCoupon(coupon);
    }

    @GetMapping
    public List<CouponDto> getAllCoupons() throws Exception {
        List<CouponDto> listOfCoupons = couponLogic.getAllCoupons();
        return listOfCoupons;
    }

    @GetMapping("/byPage")
    public List<CouponDto> getAllCoupons(long pageNumber) throws Exception {
        List<CouponDto> listOfCoupons = couponLogic.getAllCoupons(pageNumber);
        return listOfCoupons;
    }


}
