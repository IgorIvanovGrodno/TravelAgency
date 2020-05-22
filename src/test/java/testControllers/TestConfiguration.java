package testControllers;

import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.facade.FacadeTourPackage;
import com.company.model.service.user.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is configuration for integration controller tests.
 *
 * @author Igor Ivanov
 */
@Configuration
public class TestConfiguration
{
    /**
     * This method creates and returns mock of user's service.
     *
     * @return mock of user's service.
     */
    @Bean
    public UserService userServiceMock()
    {
        return Mockito.mock(UserService.class);
    }

    /**
     * This method creates and returns mock of tour package's facade.
     *
     * @return mock of tour package's facade.
     */
    @Bean
    public FacadeTourPackage facadeTourPackageMock()
    {
        return Mockito.mock(FacadeTourPackage.class);
    }

    /**
     * This method creates and returns mock of order's facade.
     *
     * @return mock of order's facade.
     */
    @Bean
    public FacadeOrder facadeOrderMock()
    {
        return Mockito.mock(FacadeOrder.class);
    }
}
