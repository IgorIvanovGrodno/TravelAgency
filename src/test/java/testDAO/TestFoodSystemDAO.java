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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.mockito.Mockito.*;

/**
 * This class is unit test class for {@link FoodSystemDAO}.
 *
 * @author Igor Ivanov
 */
public class TestFoodSystemDAO
{
    /**
     * This field is food system DAO.
     */
    private static FoodSystemDAO foodSystemDAO;

    /**
     * This field is hibernate session mock.
     */
    private static Session sessionMock;

    /**
     * This method executes before all methods, creates hibernate session factory mock, hibernate session mock,
     * food system DAO mock.
     */
    @BeforeClass
    public static void setUp()
    {
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        foodSystemDAO = new HibernateFoodSystemDAOImpl(sessionFactoryMock);
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
     * This test method tests findById(Long id) method of food system DAO.
     */
    @Test
    public void shouldCallGetMethodOfSession_whenCallFindByIdMethod()
    {
        foodSystemDAO.findById(1L);
        verify(sessionMock).get(FoodSystem.class, 1L);
    }

    /**
     * This test method tests findAll() method of food system DAO.
     */
    @Test
    public void shouldCallExpectedMethodsOfMocks_whenCallFindAllMethod()
    {
        CriteriaBuilder builderMock = mock(CriteriaBuilder.class);
        CriteriaQuery<FoodSystem> criteriaQueryMock = mock(CriteriaQuery.class);
        Root<FoodSystem> rootMock = mock(Root.class);
        Query<FoodSystem> queryMock = mock(Query.class);

        when(sessionMock.getCriteriaBuilder()).thenReturn(builderMock);
        when(sessionMock.createQuery(criteriaQueryMock)).thenReturn(queryMock);
        when(builderMock.createQuery(FoodSystem.class)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(FoodSystem.class)).thenReturn(rootMock);

        foodSystemDAO.findAll();

        verify(sessionMock).getCriteriaBuilder();
        verify(sessionMock).createQuery(criteriaQueryMock);
        verify(builderMock).createQuery(FoodSystem.class);
        verify(criteriaQueryMock).from(FoodSystem.class);
        verify(criteriaQueryMock).select(rootMock);
        verify(queryMock).getResultList();
    }

    /**
     * This test method tests makePersistent(FoodSystem foodSystem) method of food system DAO.
     */
    @Test
    public void shouldCallSaveOrUpdatedMethod_whenCallMakePersistentMethod()
    {
        FoodSystem foodSystem = new FoodSystem();
        foodSystemDAO.makePersistent(foodSystem);
        verify(sessionMock).saveOrUpdate(foodSystem);
    }

    /**
     * This test method tests makeTransient(FoodSystem foodSystem) method of food system DAO.
     */
    @Test
    public void shouldCallDeleteMethod_whenCallMakeTransientMethod()
    {
        FoodSystem foodSystem = new FoodSystem();
        foodSystemDAO.makeTransient(foodSystem);
        verify(sessionMock).delete(foodSystem);
    }
}
