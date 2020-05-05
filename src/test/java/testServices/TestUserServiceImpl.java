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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestUserServiceImpl {
    private static UserDAO userDAOMock;
    private static UserServiceImpl userService;

    @BeforeClass
    public static void setUp() {
        userDAOMock= Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(userDAOMock);
    }

    @Test
    public void shouldReturnExpectedOptionalListOfUsers_whenCallGetAllUsers(){
        List<User> users = new ArrayList<>();
        Mockito.when(userDAOMock.findAll()).thenReturn(users);
        Assert.assertEquals(Optional.ofNullable(users), userService.getAllUsers());
    }

    @Test
    public void shouldReturnOptionalOfNull_whenCallGetAllUsersWithFail(){
        Mockito.when(userDAOMock.findAll()).thenReturn(null);
        Assert.assertEquals(Optional.ofNullable(null), userService.getAllUsers());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameter() throws ServiceException {
        userService.setDiscount(null);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenDAOReturnNullResult() throws ServiceException {
        User user= new User();
        user.setId(1L);
        user.setDiscount(1);
        Mockito.when(userDAOMock.setDiscountById(Mockito.anyLong(), Mockito.anyInt())).thenReturn(0);
        userService.setDiscount(user);
    }

    @Test
    public void shouldCallMethodOfDAO_whenCallSetDiscount() throws ServiceException {
        User user= new User();
        user.setId(1L);
        user.setDiscount(1);
        Mockito.when(userDAOMock.setDiscountById(Mockito.anyLong(), Mockito.anyInt())).thenReturn(1);
        userService.setDiscount(user);
        Mockito.verify(userDAOMock).setDiscountById(Mockito.anyLong(), Mockito.anyInt());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetUserByLogin() throws ServiceException {
        userService.getUserByLogin(null);
    }

    @Test
    public void shouldReturnOptionalOfUser_whenCallGetUserByLoginMethod() throws ServiceException {
        User user =new User();
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(Optional.ofNullable(user), userService.getUserByLogin("login"));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByIsExistUserWithLoginMethod() throws ServiceException {
        userService.isExistUserWithLogin(null);
    }

    @Test
    public void shouldReturnTrue_whenCallIsExistUserWithLoginMethod() throws ServiceException {
        User user =new User();
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(true, userService.isExistUserWithLogin("login"));
    }

    @Test
    public void shouldReturnFalse_whenCallIsExistUserWithLoginMethod() throws ServiceException {
        User user =new User();
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Assert.assertEquals(false, userService.isExistUserWithLogin("login"));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByRegisterMethod() throws ServiceException {
        userService.register(null);
    }

    @Test
    public void shouldCallMakePersistDAOMethod_whenCallRegisterMethod() throws ServiceException {
        User user =new User();
        Authorization authorization=new Authorization();
        authorization.setPassword("1");
        user.setAuthorization(authorization);
        PasswordEncoder passwordEncoderMock =Mockito.mock(PasswordEncoder.class);
        userService.setPasswordEncoder(passwordEncoderMock);
        userService.register(user);
        Mockito.verify(userDAOMock).makePersistent(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetUsersOrdersMethod() throws ServiceException {
        userService.getUsersOrders(null);
    }

    @Test
    public void shouldReturnExpectedList_whenCallByGetUsersOrdersMethod() throws ServiceException {
        User user =new User();
        List<Order> orders = Arrays.asList(new Order());
        user.setOrders(orders);
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(orders, userService.getUsersOrders("login"));
    }

    @Test
    public void shouldReturnEmptyList_whenCallByGetUsersOrdersMethod() throws ServiceException {
        Mockito.when(userDAOMock.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Assert.assertEquals(0, userService.getUsersOrders("login").size());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterByGetDiscountMethod() throws ServiceException {
        userService.getDiscount(null);
    }

    @Test
    public void shouldReturnExpectedValue_whenCallGetDiscountMethod() throws ServiceException {
        User user=new User();
        user.setDiscount(1);
        Mockito.when(userDAOMock.getUserByLogin(Mockito.any())).thenReturn(user);
        Assert.assertEquals(1, userService.getDiscount(Mockito.mock(Principal.class)));
    }

    @Test
    public void shouldReturnNullValue_whenCallGetDiscountMethod() throws ServiceException {
        Mockito.when(userDAOMock.getUserByLogin(Mockito.any())).thenReturn(null);
        Assert.assertEquals(0, userService.getDiscount(Mockito.mock(Principal.class)));
    }

}
