package com.company.utils.logger;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;


@Aspect
public class LoggingAspect {

    private  Logger LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(* com.company.model.service.facade.FacadeOrder.makePayment(com.company.model.domain.order.Order, com.company.model.domain.tourPackage.TourPackage, String))&&args(order, tourPackageOrder, loginUser)")
    public void callFacadePaymentMethod(Order order, TourPackage tourPackageOrder, String loginUser) {
    }

    @Pointcut("execution(* com.company.model.service.order.OrderService.makePayment(com.company.model.domain.order.Order, com.company.model.domain.tourPackage.TourPackage, com.company.model.domain.user.User , com.company.model.domain.order.StatusOrder))&&args(order, tourPackageOrder, currentUser, statusOrder)")
    public void callServicePaymentMethod(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder) {
    }

    @Pointcut("execution(* com.company.model.dao.order.OrderDAO.makePersistent(com.company.model.domain.order.Order))&&args(order)")
    public void callOrderDAOMakePersistentMethod(Order order) {
    }

    @Pointcut("execution(* com.company.model.service.user.UserService.setDiscount(com.company.model.domain.user.User))&&args(user)")
    public void callUserServiceSetDiscountMethod(User user) {
    }

    @Pointcut("execution(* com.company.model.dao.user.UserDAO.makePersistent(com.company.model.domain.user.User))&&args(user)")
    public void callUserDAOMakePersistentMethod(User user) {
    }

    @Pointcut("within(com.company..*)")
    public void springBeanPointcut() {
    }

    @Before("callFacadePaymentMethod(order, tourPackageOrder, loginUser)")
    public void logBeforeCallFacadePaymentMethod(JoinPoint joinPoint, Order order, TourPackage tourPackageOrder, String loginUser){
       LOGGER.info(joinPoint
               +" with parameters: " +order
               +" "+ tourPackageOrder
               +" login - "+loginUser);
    }

    @Before("callServicePaymentMethod(order, tourPackageOrder, currentUser, statusOrder)")
    public void logBeforeCallServicePaymentMethod(JoinPoint joinPoint, Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder){
        LOGGER.info(joinPoint
                +" with parameters: " +order
                +" "+ tourPackageOrder
                +" "+currentUser
                +" "+statusOrder);
    }

    @Before("callOrderDAOMakePersistentMethod(order)")
    public void logBeforeCallOrderDAOMakePersistentMethod(JoinPoint joinPoint, Order order){
        LOGGER.info(joinPoint
                +" with parameters: " +order);
    }

    @Before("callUserServiceSetDiscountMethod(user)")
    public void logBeforeCallUserServiceSetDiscountMethod(JoinPoint joinPoint, User user){
        LOGGER.info(joinPoint
                +" with parameters: " +user);
    }

    @Before("callUserDAOMakePersistentMethod(user)")
    public void logBeforeCallUserDAOMakePersistentMethod(JoinPoint joinPoint, User user){
        LOGGER.info(joinPoint
                +" with parameters: " +user);
    }

    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "exception")
    public void logError(JoinPoint joinPoint, Exception exception){
        LOGGER.error("Error "+joinPoint+" message: "+exception.getMessage()+"\\r\\n"
                +"stack trace: "+ Arrays.stream(exception.getStackTrace()).collect(Collectors.toList())+"\\r\\n");
    }
}
