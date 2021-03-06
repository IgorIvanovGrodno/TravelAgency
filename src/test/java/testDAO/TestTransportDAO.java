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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.mockito.Mockito.*;

/**
 * This class is unit test class for {@link TransportDAO}.
 *
 * @author Igor Ivanov
 */
public class TestTransportDAO
{
    /**
     * This field is status order DAO.
     */
    private static TransportDAO transportDAO;

    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * transport DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        transportDAO = new HibernateTransportDAOImpl(sessionFactoryMock);
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
     * This test method tests findById(Long id) method of transport DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        transportDAO.findById(1L);
        verify(sessionMock).get(Transport.class, 1L);
    }

    /**
     * This test method tests findAll() method of transport DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<Transport> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<Transport> rootMock = mock(Root.class);
        Query<Transport> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(Transport.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(Transport.class)).thenReturn(rootMock);

        transportDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(Transport.class);
        verify(criteriaQueryMock).from(Transport.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(Transport transport) method of transport DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        Transport transport = new Transport();
        transportDAO.makePersistent(transport);
        verify(sessionMock).saveOrUpdate(transport);
    }

    /**
     * This test method tests makeTransient(Transport transport) method of transport DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        Transport transport = new Transport();
        transportDAO.makeTransient(transport);
        verify(sessionMock).delete(transport);
    }
}