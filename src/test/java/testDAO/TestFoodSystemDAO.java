package testDAO;

import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.dao.tourPackage.foodSystem.HibernateFoodSystemDAOImpl;
import com.company.model.domain.tourPackage.FoodSystem;
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

public class TestFoodSystemDAO {
    private static FoodSystemDAO foodSystemDAO;
    private static Session sessionMock;

    @BeforeClass
    public static void setUp(){
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        Mockito.when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        foodSystemDAO = new HibernateFoodSystemDAOImpl(sessionFactoryMock);
    }

    @Before
    public void set(){
        Mockito.reset(sessionMock);
        Mockito.clearInvocations(sessionMock);
    }

    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod(){
        foodSystemDAO.findById(1L);
        Mockito.verify(sessionMock).get(FoodSystem.class, 1L);
    }

    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod(){
        CriteriaBuilder builderMock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<FoodSystem> criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        Root<FoodSystem> rootMock = Mockito.mock(Root.class);
        Query<FoodSystem> queryMock = Mockito.mock(Query.class);

        Mockito.when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        Mockito.when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        Mockito.when(builderMock.createQuery(FoodSystem.class)).thenReturn(criteriaQueryMock);
        Mockito.when(criteriaQueryMock.from(FoodSystem.class)).thenReturn(rootMock);

        foodSystemDAO.findAll();

        Mockito.verify(sessionMock).getCriteriaBuilder();
        Mockito.verify(sessionMock).createQuery(criteriaQueryMock);
        Mockito.verify(builderMock).createQuery(FoodSystem.class);
        Mockito.verify(criteriaQueryMock).from(FoodSystem.class);
        Mockito.verify(criteriaQueryMock).select(rootMock);
        Mockito.verify(queryMock).getResultList();
    }

    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod(){
        FoodSystem foodSystem = new FoodSystem();
        foodSystemDAO.makePersistent(foodSystem);
        Mockito.verify(sessionMock).saveOrUpdate(foodSystem);
    }

    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod(){
        FoodSystem foodSystem = new FoodSystem();
        foodSystemDAO.makeTransient(foodSystem);
        Mockito.verify(sessionMock).delete(foodSystem);
    }
}
