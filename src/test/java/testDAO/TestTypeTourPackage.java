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

public class TestTypeTourPackage {
    private static TypeTourPackageDAO typeTourPackageDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        typeTourPackageDAO = new HibernateTypeTourPackageDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        typeTourPackageDAO.findById(1L);
        Mockito.verify(sessionMock).get(TypeTourPackage.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<TypeTourPackage> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<TypeTourPackage> rootMock = Mockito.mock(Root.class);
        Query<TypeTourPackage> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(TypeTourPackage.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(TypeTourPackage.class)).thenReturn(rootMock);

        typeTourPackageDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(TypeTourPackage.class);
        Mockito.verify(criteriaQueryMock).from(TypeTourPackage.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        TypeTourPackage typeTourPackage = new TypeTourPackage();
        typeTourPackageDAO.makePersistent(typeTourPackage);
        Mockito.verify(sessionMock).saveOrUpdate(typeTourPackage);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        TypeTourPackage typeTourPackage = new TypeTourPackage();
        typeTourPackageDAO.makeTransient(typeTourPackage);
        Mockito.verify(sessionMock).delete(typeTourPackage);
    }
}
