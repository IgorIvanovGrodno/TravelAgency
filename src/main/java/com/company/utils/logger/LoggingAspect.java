package com.company.utils.logger;

import com.company.model.dao.order.OrderDAO;
import com.company.model.dao.user.UserDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.order.OrderService;
import com.company.model.service.user.UserService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class is aspect for logging.
 *
 * @author Igor Ivanov
 */
@Aspect
public class LoggingAspect
{
    /**
     * This field is logger.
     */
    private Logger LOGGER = Logger.getLogger(this.getClass());

    /**
     * This method is pointcut for payment method of {@link FacadeOrder}.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param loginUser        - login of user.
     */
    @Pointcut(value = "execution(* com.company.model.service.facade.FacadeOrder.makePayment(com.company.model.domain.order.Order, com.company.model.domain.tourPackage.TourPackage, String))" +
            "&&args(order, tourPackageOrder, loginUser)", argNames = "order,tourPackageOrder,loginUser")
    public void callFacadePaymentMethod(Order order, TourPackage tourPackageOrder, String loginUser)
    {
    }

    /**
     * This method is pointcut for payment method of {@link OrderService}.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param currentUser      - current user.
     * @param statusOrder      - status of order.
     */
    @Pointcut(value = "execution(* com.company.model.service.order.OrderService.makePayment(com.company.model.domain.order.Order, com.company.model.domain.tourPackage.TourPackage, com.company.model.domain.user.User , com.company.model.domain.order.StatusOrder))" +
            "&&args(order, tourPackageOrder, currentUser, statusOrder)", argNames = "order,tourPackageOrder,currentUser,statusOrder")
    public void callServicePaymentMethod(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder)
    {
    }

    /**
     * This method is pointcut for make persistent method of {@link OrderDAO}.
     *
     * @param order - order.
     */
    @Pointcut("execution(* com.company.model.dao.order.OrderDAO.makePersistent(com.company.model.domain.order.Order))&&args(order)")
    public void callOrderDAOMakePersistentMethod(Order order)
    {
    }

    /**
     * This method is pointcut for set discount method of {@link UserService}.
     *
     * @param user - user.
     */
    @Pointcut("execution(* com.company.model.service.user.UserService.setDiscount(com.company.model.domain.user.User))&&args(user)")
    public void callUserServiceSetDiscountMethod(User user)
    {
    }

    /**
     * This method is pointcut for make persistent method of {@link UserDAO}.
     *
     * @param user - user.
     */
    @Pointcut("execution(* com.company.model.dao.user.UserDAO.makePersistent(com.company.model.domain.user.User))&&args(user)")
    public void callUserDAOMakePersistentMethod(User user)
    {
    }

    /**
     * This method is pointcut for all classes within this project.
     */
    @Pointcut("within(com.company..*)")
    public void springBeanPointcut()
    {
    }

    /**
     * This method is aspect for callFacadePaymentMethod pointcut. It receives and logs input parameters before execute method.
     *
     * @param joinPoint        - join point.
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param loginUser        - login of user.
     */
    @Before(value = "callFacadePaymentMethod(order, tourPackageOrder, loginUser)", argNames = "joinPoint,order,tourPackageOrder,loginUser")
    public void logBeforeCallFacadePaymentMethod(JoinPoint joinPoint, Order order, TourPackage tourPackageOrder, String loginUser)
    {
        LOGGER.info(joinPoint
                + " with parameters: " + order
                + " " + tourPackageOrder
                + " login - " + loginUser);
    }

    /**
     * This method is aspect for callServicePaymentMethod pointcut. It receives and logs input parameters before execute method.
     *
     * @param joinPoint        - join point.
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param currentUser      - current user.
     * @param statusOrder      - status of order.
     */
    @Before(value = "callServicePaymentMethod(order, tourPackageOrder, currentUser, statusOrder)", argNames = "joinPoint,order,tourPackageOrder,currentUser,statusOrder")
    public void logBeforeCallServicePaymentMethod(JoinPoint joinPoint, Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder)
    {
        LOGGER.info(joinPoint
                + " with parameters: " + order
                + " " + tourPackageOrder
                + " " + currentUser
                + " " + statusOrder);
    }

    /**
     * This method is aspect for callOrderDAOMakePersistentMethod pointcut. It receives and logs input parameters before execute method.
     *
     * @param joinPoint - join point.
     * @param order     - order.
     */
    @Before(value = "callOrderDAOMakePersistentMethod(order)", argNames = "joinPoint,order")
    public void logBeforeCallOrderDAOMakePersistentMethod(JoinPoint joinPoint, Order order)
    {
        LOGGER.info(joinPoint
                + " with parameters: " + order);
    }

    /**
     * This method is aspect for callUserServiceSetDiscountMethod pointcut. It receives and logs input parameters before execute method.
     *
     * @param joinPoint - join point.
     * @param user      - user.
     */
    @Before(value = "callUserServiceSetDiscountMethod(user)", argNames = "joinPoint,user")
    public void logBeforeCallUserServiceSetDiscountMethod(JoinPoint joinPoint, User user)
    {
        LOGGER.info(joinPoint
                + " with parameters: " + user);
    }

    /**
     * This method is aspect for callUserDAOMakePersistentMethod pointcut. It receives and logs input parameters before execute method.
     *
     * @param joinPoint - join point.
     * @param user      - user.
     */
    @Before(value = "callUserDAOMakePersistentMethod(user)", argNames = "joinPoint,user")
    public void logBeforeCallUserDAOMakePersistentMethod(JoinPoint joinPoint, User user)
    {
        LOGGER.info(joinPoint
                + " with parameters: " + user);
    }

    /**
     * This method is aspect for springBeanPointcut pointcut. It receives throwing exception and logs name exception, exception message, stack trace.
     *
     * @param joinPoint - join point.
     * @param exception - exception.
     */
    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "exception")
    public void logError(JoinPoint joinPoint, Exception exception)
    {
        LOGGER.error("Error " + joinPoint + "name Error:" + exception.getClass().getName() + " message: " + exception.getMessage() + "\\r\\n"
                + "stack trace: " + Arrays.stream(exception.getStackTrace()).collect(Collectors.toList()) + "\\r\\n");
    }
}
