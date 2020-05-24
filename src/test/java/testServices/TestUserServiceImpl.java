package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.user.UserDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.Authorization;
import com.company.model.domain.user.User;
import com.company.model.service.user.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.*;

/**
 * This class is unit test class for {@link UserServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestUserServiceImpl
{
    /**
     * This field is user DAO mock.
     */
    private static UserDAO userDAOMock;

    /**
     * This field is user service.
     */
    private static UserServiceImpl userService;

    /**
     * This method executes before all methods, creates user DAO mock, user service.
     */
    @BeforeClass
    public static void setUp()
    {
        userDAOMock = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAOMock);
    }

    /**
     * This method executes before each method, resets user DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(userDAOMock);
    }

    /**
     * This method tests returned result userService.getAllUsers() method.
     */
    @Test
    public void shouldReturnExpectedOptionalListOfUsers_whenCallGetAllUsers()
    {
        List<User> users = new ArrayList<>();
        Mockito.when(userDAOMock.findAll()).thenReturn(users);
        Assert.assertEquals(Optional.of(users), userService.getAllUsers());
    }

    /**
     * This method tests returned result userService.getAllUsers() method when DAO return null.
     */
    @Test
    public void shouldReturnOptionalOfNull_whenCallGetAllUsersWithFail()
    {
        Mockito.when(userDAOMock.findAll()).thenReturn(null);
        Assert.assertEquals(Optional.empty(), userService.getAllUsers());
    }

    /**
     * This method tests throwing exception when invoke userService.setDiscount with null parameter.
     *
     * @throws ServiceException when invoke userService.setDiscount with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameter() throws ServiceException
    {
        userService.setDiscount(null);
    }

    /**
     * This method tests throwing exception when invoke userService.setDiscount and DAO return null.
     *
     * @throws ServiceException when invoke userService.setDiscount and DAO return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenDAOReturnNullResult() throws ServiceException
    {
        User user = new User();
        user.setId(1L);
        user.setDiscount(1);
        Mockito.when(userDAOMock.setDiscountById(Mockito.anyLong(), Mockito.anyInt())).thenReturn(0);
        userService.setDiscount(user);
    }

    /**
     * This method tests invoking userDAOMock.setDiscountById method when invoke userService.setDiscount(user).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMethodOfDAO_whenCallSetDiscount() throws ServiceException
    {
        User user = new User();
        user.setId(1L);
        user.setDiscount(1);
        Mockito.when(userDAOMock.setDiscountById(Mockito.anyLong(), Mockito.anyInt())).thenReturn(1);
        userService.setDiscount(user);
        Mockito.verify(userDAOMock).setDiscountById(Mockito.anyLong(), Mockito.anyInt());
    }

    /**
     * This method tests throwing exception when invoke userService.getUserByLogin with null parameter.
     *
     * @throws ServiceException when invoke userService.getUserByLogin and DAO return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetUserByLogin() throws ServiceException
    {
        userService.getUserByLogin(null);
    }

    /**
     * This method tests returned result userService.getUserByLogin("login") method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnOptionalOfUser_whenCallGetUserByLoginMethod() throws ServiceException
    {
        User user = new User();
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(Optional.of(user), userService.getUserByLogin("login"));
    }

    /**
     * This method tests throwing exception when invoke userService.isExistUserWithLogin with null parameter.
     *
     * @throws ServiceException when invoke userService.isExistUserWithLogin with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByIsExistUserWithLoginMethod() throws ServiceException
    {
        userService.isExistUserWithLogin(null);
    }

    /**
     * This method tests returned result userService.getUserByLogin("login") method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnTrue_whenCallIsExistUserWithLoginMethod() throws ServiceException
    {
        User user = new User();
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertTrue(userService.isExistUserWithLogin("login"));
    }

    /**
     * This method tests returned result userService.isExistUserWithLogin("login") method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnFalse_whenCallIsExistUserWithLoginMethod() throws ServiceException
    {
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Assert.assertFalse(userService.isExistUserWithLogin("login"));
    }

    /**
     * This method tests throwing exception when invoke userService.register with null parameter.
     *
     * @throws ServiceException when invoke userService.register with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByRegisterMethod() throws ServiceException
    {
        userService.register(null);
    }

    /**
     * This method tests invoking userDAOMock.makePersistent method when invoke userService.register(user).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMakePersistDAOMethod_whenCallRegisterMethod() throws ServiceException
    {
        User user = new User();
        Authorization authorization = new Authorization();
        authorization.setPassword("1");
        user.setAuthorization(authorization);
        PasswordEncoder passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        userService.setPasswordEncoder(passwordEncoderMock);
        userService.register(user);
        Mockito.verify(userDAOMock).makePersistent(Mockito.any());
    }

    /**
     * This method tests throwing exception when invoke userService.getUsersOrders with null parameter.
     *
     * @throws ServiceException when invoke userService.getUsersOrders with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetUsersOrdersMethod() throws ServiceException
    {
        userService.getUsersOrders(null);
    }

    /**
     * This method tests returned result userService.getUsersOrders("login") method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedList_whenCallByGetUsersOrdersMethod() throws ServiceException
    {
        User user = new User();
        List<Order> orders = Collections.singletonList(new Order());
        user.setOrders(orders);
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(orders, userService.getUsersOrders("login"));
    }

    /**
     * This method tests returned result userService.getUsersOrders("login") method when DAO return null.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnEmptyList_whenCallByGetUsersOrdersMethod() throws ServiceException
    {
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Assert.assertEquals(0, userService.getUsersOrders("login").size());
    }

    /**
     * This method tests throwing exception when invoke userService.getDiscount with null parameter.
     *
     * @throws ServiceException when invoke userService.getDiscount with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetDiscountMethod() throws ServiceException
    {
        userService.getDiscount(null);
    }

    /**
     * This method tests returned result userService.getDiscount method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedValue_whenCallGetDiscountMethod() throws ServiceException
    {
        User user = new User();
        user.setDiscount(1);
        Mockito.when(userDAOMock.getUserByLogin(Mockito.any())).thenReturn(user);
        Assert.assertEquals(1, userService.getDiscount(Mockito.mock(Principal.class)));
    }

    /**
     * This method tests returned result userService.getDiscount method when DAO return null.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnNullValue_whenCallGetDiscountMethod() throws ServiceException
    {
        Mockito.when(userDAOMock.getUserByLogin(Mockito.any())).thenReturn(null);
        Assert.assertEquals(0, userService.getDiscount(Mockito.mock(Principal.class)));
    }
}