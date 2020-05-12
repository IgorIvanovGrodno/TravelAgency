package testDAO;

import com.company.model.dao.tourPackage.transport.HibernateTransportDAOImpl;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
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

public class TestTransportDAO {
    private static TransportDAO transportDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        transportDAO = new HibernateTransportDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        transportDAO.findById(1L);
        Mockito.verify(sessionMock).get(Transport.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<Transport> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<Transport> rootMock = Mockito.mock(Root.class);
        Query<Transport> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(Transport.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(Transport.class)).thenReturn(rootMock);

        transportDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(Transport.class);
        Mockito.verify(criteriaQueryMock).from(Transport.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        Transport transport = new Transport();
        transportDAO.makePersistent(transport);
        Mockito.verify(sessionMock).saveOrUpdate(transport);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        Transport transport = new Transport();
        transportDAO.makeTransient(transport);
        Mockito.verify(sessionMock).delete(transport);
    }
}
