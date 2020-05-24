package testDAO;

import com.company.model.dao.user.HibernateUserDAOImpl;
import com.company.model.dao.user.UserDAO;
import com.company.model.domain.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.mockito.Mockito.*;

/**
 * This class is unit test class for {@link TestUserDAO}.
 *
 * @author Igor Ivanov
 */
public class TestUserDAO
{
    /**
     * This field is type tour package DAO.
     */
    private static UserDAO userDAO;

    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * user DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        userDAO = new HibernateUserDAOImpl(sessionFactoryMock);
    }

    /**
     * This method executes before each method, resets hibernate session mock.
     */
    @Before
    public void set()
    {
        reset(sessionMock);
        clearInvocations(sessionMock);
    }

    /**
     * This test method tests findById(Long id) method of user DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        userDAO.findById(1L);
        verify(sessionMock).get(User.class, 1L);
    }

    /**
     * This test method tests findAll() method of user DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<User> rootMock = mock(Root.class);
        Query<User> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(User.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(User.class)).thenReturn(rootMock);

        userDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(User.class);
        verify(criteriaQueryMock).from(User.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(User user) method of user DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        User user = new User();
        userDAO.makePersistent(user);
        verify(sessionMock).saveOrUpdate(user);
    }

    /**
     * This test method tests makeTransient(User user) method of user DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        User user = new User();
        userDAO.makeTransient(user);
        verify(sessionMock).delete(user);
    }

    /**
     * This test method tests setDiscountById(Long id, int discount) method of user DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfQuery_whenCallSetDiscountByIdMethod()
    {
        Query<User> queryMock = mock(Query.class);
        when(sessionMock.createQuery("update User set discount=:discount where id=:id")).thenReturn(queryMock);
        when(queryMock.setCacheable(true)).thenReturn(queryMock);
        userDAO.setDiscountById(1L, 1);
        verify(queryMock).setParameter("id", 1L);
        verify(queryMock).setParameter("discount", 1);
        verify(queryMock).executeUpdate();
    }

    /**
     * This test method tests getUserByLogin(String login) method of user DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfQuery_whenCallGetUserByLoginMethod()
    {
        Query<User> queryMock = mock(Query.class);
        when(sessionMock.createQuery("from User where authorization.login=:login")).thenReturn(queryMock);
        when(queryMock.setCacheable(true)).thenReturn(queryMock);
        userDAO.getUserByLogin("login");
        verify(queryMock).setParameter("login", "login");
        verify(queryMock).getSingleResult();
    }
}
