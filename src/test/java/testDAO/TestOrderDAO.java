package testDAO;

import com.company.model.dao.order.HibernateOrderDAOImpl;
import com.company.model.dao.order.OrderDAO;
import com.company.model.domain.order.Order;
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
import static org.mockito.Mockito.mock;

/**
 * This class is unit test class for {@link OrderDAO}.
 *
 * @author Igor Ivanov
 */
public class TestOrderDAO
{
    /**
     * This field is order DAO.
     */
    private static OrderDAO orderDAO;

    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * order DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        orderDAO = new HibernateOrderDAOImpl(sessionFactoryMock);
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
     * This test method tests findById(Long id) method of order DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        orderDAO.findById(1L);
        verify(sessionMock).get(Order.class, 1L);
    }

    /**
     * This test method tests findAll() method of order DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<Order> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<Order> rootMock = mock(Root.class);
        Query<Order> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(Order.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(Order.class)).thenReturn(rootMock);

        orderDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(Order.class);
        verify(criteriaQueryMock).from(Order.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(Order order) method of order DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        Order order = new Order();
        orderDAO.makePersistent(order);
        verify(sessionMock).saveOrUpdate(order);
    }

    /**
     * This test method tests makeTransient(Order order) method of order DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        Order order = new Order();
        orderDAO.makeTransient(order);
        verify(sessionMock).delete(order);
    }
}
