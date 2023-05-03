package com.moti.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        /*
        //Reflection-Coupon
        System.out.println("Showing refletin of Coupon class");
        Method[] methods = CouponLogic.class.getMethods();
        for (Method method1 : methods) {
            System.out.println(method1.getName());
        }
        System.out.println("*************************************************************");
        //Reflection- Customer
        System.out.println("Showing Reflection of  Customer class");
        methods = CustomerLogic.class.getMethods();
        for (Method method1 : methods) {
            System.out.println(method1.getName());
        }
        System.out.println("*************************************************************");
        //Reflection- Purchase
        System.out.println("Showing Reflection of  Purchase class");
        methods = PurchaseLogic.class.getMethods();
        for (Method method1 : methods) {
            System.out.println(method1.getName());
        }

        //Reflection- Users
        System.out.println("Showing Reflection of  Users class");
        methods = UserLogic.class.getMethods();
        for (Method method1 : methods) {
            System.out.println(method1.getName());
        }

       ////Creating new Customer
//        User user = new User("MotiPOOOPP", UserType.Admin, 1, "1234");
//        Customer customer = new Customer(user, "Here", "050", 4);
//        CustomersDal customersDal = new CustomersDal();
//        UserDal userDal = new UserDal();
//        UserLogic userLogic = new UserLogic(userDal);
//        CustomerLogic customerLogic = new CustomerLogic(userLogic, customersDal);
//        customerLogic.addCustomer(customer, UserType.Admin);
    }

         */



    }
}
