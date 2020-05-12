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
import org.mockito.Mockito;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TestOrderDAO {
    private static OrderDAO orderDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        orderDAO = new HibernateOrderDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        orderDAO.findById(1L);
        Mockito.verify(sessionMock).get(Order.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<Order> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<Order> rootMock = Mockito.mock(Root.class);
        Query<Order> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(Order.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(Order.class)).thenReturn(rootMock);

        orderDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(Order.class);
        Mockito.verify(criteriaQueryMock).from(Order.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        Order order = new Order();
        orderDAO.makePersistent(order);
        Mockito.verify(sessionMock).saveOrUpdate(order);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        Order order = new Order();
        orderDAO.makeTransient(order);
        Mockito.verify(sessionMock).delete(order);
    }
}
