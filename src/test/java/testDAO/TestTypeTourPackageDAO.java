package testDAO;

import com.company.model.dao.tourPackage.typeTourPackage.HibernateTypeTourPackageDAOImpl;
import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
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

import static org.mockito.Mockito.*;

/**
 * This class is unit test class for {@link TypeTourPackageDAO}.
 *
 * @author Igor Ivanov
 */
public class TestTypeTourPackageDAO
{
    /**
     * This field is type tour package DAO.
     */
    private static TypeTourPackageDAO typeTourPackageDAO;
    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * type tour package DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        typeTourPackageDAO = new HibernateTypeTourPackageDAOImpl(sessionFactoryMock);
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
     * This test method tests findById(Long id) method of type tour package DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        typeTourPackageDAO.findById(1L);
        verify(sessionMock).get(TypeTourPackage.class, 1L);
    }

    /**
     * This test method tests findAll() method of type tour package DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<TypeTourPackage> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<TypeTourPackage> rootMock = mock(Root.class);
        Query<TypeTourPackage> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(TypeTourPackage.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(TypeTourPackage.class)).thenReturn(rootMock);

        typeTourPackageDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(TypeTourPackage.class);
        verify(criteriaQueryMock).from(TypeTourPackage.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(TypeTourPackage typeTourPackage) method of type tour package DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        TypeTourPackage typeTourPackage = new TypeTourPackage();
        typeTourPackageDAO.makePersistent(typeTourPackage);
        verify(sessionMock).saveOrUpdate(typeTourPackage);
    }

    /**
     * This test method tests makeTransient(TypeTourPackage typeTourPackage) method of type tour package DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        TypeTourPackage typeTourPackage = new TypeTourPackage();
        typeTourPackageDAO.makeTransient(typeTourPackage);
        verify(sessionMock).delete(typeTourPackage);
    }
}
