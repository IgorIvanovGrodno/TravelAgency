package testDAO;

import com.company.model.dao.tourPackage.HibernateTourPackageDAOImpl;
import com.company.model.dao.tourPackage.TourPackageDAO;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.utils.ParametersSelectedForTourPackages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.criteria.*;



public class TestTourPackageDAO {
    private static TourPackageDAO tourPackageDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        tourPackageDAO = new HibernateTourPackageDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        tourPackageDAO.findById(1L);
        Mockito.verify(sessionMock).get(TourPackage.class, 1L);
    }

    @Test
    public void shouldCallGetResultListMethodOfQuery_whenCallFindAllMethod(){
        Query<TourPackage> queryMock = Mockito.mock(Query.class);
        Mockito.when(sessionMock.createQuery(Mockito.anyString())).thenReturn(queryMock);
        Mockito.when(queryMock.setCacheable(true)).thenReturn(queryMock);
        tourPackageDAO.findAll();
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        TourPackage tourPackage = new TourPackage();
        tourPackageDAO.makePersistent(tourPackage);
        Mockito.verify(sessionMock).saveOrUpdate(tourPackage);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        TourPackage tourPackage = new TourPackage();
        tourPackageDAO.makeTransient(tourPackage);
        Mockito.verify(sessionMock).delete(tourPackage);
    }

    @Test
    public void shouldCallExpectedMethodsOfQuery_whenCallDeleteByIdMethod(){
        Query<TourPackage> queryMock = Mockito.mock(Query.class);
        Mockito.when(sessionMock.createQuery("delete TourPackage where id=:id")).thenReturn(queryMock);
        tourPackageDAO.deleteById(1L);
        Mockito.verify(queryMock).setParameter("id", 1L);
        Mockito.verify(queryMock).executeUpdate();
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallGetSelectedTourPackagesMethod() {
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<TourPackage> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<TourPackage> rootMock = Mockito.mock(Root.class);
        Query<TourPackage> queryMock = Mockito.mock(Query.class);
        Path path=Mockito.mock(Path.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(TourPackage.class)).thenReturn(criteriaQueryMock);
        Mockito.when(builderMock.asc(Mockito.any())).thenReturn(Mockito.mock(Order.class));
        Mockito.when(criteriaQueryMock.from(TourPackage.class)).thenReturn(rootMock);
        Mockito.when(criteriaQueryMock.select(Mockito.any())).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.orderBy(Mockito.anyList())).thenReturn(criteriaQueryMock);
        Mockito.when(rootMock.get(Mockito.anyString())).thenReturn(path);

        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        parametersSelectedForTourPackages.setIdOfFoodSystem(1L);
        parametersSelectedForTourPackages.setIdOfTransport(1L);
        parametersSelectedForTourPackages.setIdOfType(1L);
        parametersSelectedForTourPackages.setMaxDay("1");
        parametersSelectedForTourPackages.setMinDay("1");
        parametersSelectedForTourPackages.setMaxPrice("1");
        parametersSelectedForTourPackages.setMinPrice("1");
        parametersSelectedForTourPackages.setStatusHot(true);

        tourPackageDAO.getSelectedTourPackages(parametersSelectedForTourPackages);

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(TourPackage.class);
        Mockito.verify(criteriaQueryMock).from(TourPackage.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(criteriaQueryMock).orderBy(Mockito.any(), Mockito.any());
        Mockito.verify(queryMock).getResultList();
    }
}
