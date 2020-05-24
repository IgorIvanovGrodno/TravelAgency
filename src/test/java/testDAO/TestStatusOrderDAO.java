package testDAO;

import com.company.model.dao.order.statusOrder.HibernateStatusOrderDAOImpl;
import com.company.model.dao.order.statusOrder.StatusOrderDAO;
import com.company.model.domain.order.StatusOrder;
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
 * This class is unit test class for {@link StatusOrderDAO}.
 *
 * @author Igor Ivanov
 */
public class TestStatusOrderDAO
{
    /**
     * This field is status order DAO.
     */
    private static StatusOrderDAO statusOrderDAO;

    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * status order DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        statusOrderDAO = new HibernateStatusOrderDAOImpl(sessionFactoryMock);
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
     * This test method tests findById(Long id) method of status order DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        statusOrderDAO.findById(1L);
        verify(sessionMock).get(StatusOrder.class, 1L);
    }

    /**
     * This test method tests findAll() method of status order DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<StatusOrder> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<StatusOrder> rootMock = mock(Root.class);
        Query<StatusOrder> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(StatusOrder.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(StatusOrder.class)).thenReturn(rootMock);

        statusOrderDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(StatusOrder.class);
        verify(criteriaQueryMock).from(StatusOrder.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(StatusOrder statusOrder) method of status order DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        StatusOrder statusOrder = new StatusOrder();
        statusOrderDAO.makePersistent(statusOrder);
        verify(sessionMock).saveOrUpdate(statusOrder);
    }

    /**
     * This test method tests makeTransient(StatusOrder statusOrder) method of status order DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        StatusOrder statusOrder = new StatusOrder();
        statusOrderDAO.makeTransient(statusOrder);
        verify(sessionMock).delete(statusOrder);
    }
}
