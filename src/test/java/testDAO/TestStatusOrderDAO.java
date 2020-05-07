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
import org.mockito.Mockito;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TestStatusOrderDAO {
    private static StatusOrderDAO statusOrderDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        statusOrderDAO = new HibernateStatusOrderDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        statusOrderDAO.findById(1L);
        Mockito.verify(sessionMock).get(StatusOrder.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<StatusOrder> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<StatusOrder> rootMock = Mockito.mock(Root.class);
        Query<StatusOrder> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(StatusOrder.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(StatusOrder.class)).thenReturn(rootMock);

        statusOrderDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(StatusOrder.class);
        Mockito.verify(criteriaQueryMock).from(StatusOrder.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        StatusOrder statusOrder = new StatusOrder();
        statusOrderDAO.makePersistent(statusOrder);
        Mockito.verify(sessionMock).saveOrUpdate(statusOrder);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        StatusOrder statusOrder = new StatusOrder();
        statusOrderDAO.makeTransient(statusOrder);
        Mockito.verify(sessionMock).delete(statusOrder);
    }

}
