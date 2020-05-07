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
import org.mockito.Mockito;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TestUserDAO {
    private static UserDAO userDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        userDAO = new HibernateUserDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        userDAO.findById(1L);
        Mockito.verify(sessionMock).get(User.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<User> rootMock = Mockito.mock(Root.class);
        Query<User> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(User.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(User.class)).thenReturn(rootMock);

        userDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(User.class);
        Mockito.verify(criteriaQueryMock).from(User.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        User user = new User();
        userDAO.makePersistent(user);
        Mockito.verify(sessionMock).saveOrUpdate(user);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        User user = new User();
        userDAO.makeTransient(user);
        Mockito.verify(sessionMock).delete(user);
    }

    @Test
    public void shouldCallExpectedMethodsOfQuery_whenCallSetDiscountByIdMethod(){
        Query<User> queryMock = Mockito.mock(Query.class);
        Mockito.when(sessionMock.createQuery("update User set discount=:discount where id=:id")).thenReturn(queryMock);
        Mockito.when(queryMock.setCacheable(true)).thenReturn(queryMock);
        userDAO.setDiscountById(1L, 1);
        Mockito.verify(queryMock).setParameter("id", 1L);
        Mockito.verify(queryMock).setParameter("discount", 1);
        Mockito.verify(queryMock).executeUpdate();
    }

    @Test
    public void shouldCallExpectedMethodsOfQuery_whenCallGetUserByLoginMethod(){
        Query<User> queryMock =  Mockito.mock(Query.class);
        Mockito.when(sessionMock.createQuery("from User where authorization.login=:login")).thenReturn(queryMock);
        Mockito.when(queryMock.setCacheable(true)).thenReturn(queryMock);
        userDAO.getUserByLogin("login");
        Mockito.verify(queryMock).setParameter("login", "login");
        Mockito.verify(queryMock).getSingleResult();
    }
}
