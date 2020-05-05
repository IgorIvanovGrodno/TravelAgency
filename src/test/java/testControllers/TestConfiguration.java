package testControllers;

import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.facade.FacadeTourPackage;
import com.company.model.service.facade.FacadeTourPackageImpl;
import com.company.model.service.user.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public UserService userServiceMock(){
        return Mockito.mock(UserService.class);
    }

    @Bean
    public FacadeTourPackage facadeTourPackageMock(){
        return Mockito.mock(FacadeTourPackageImpl.class);
    }

    @Bean
    public FacadeOrder facadeOrderMock(){
        return Mockito.mock(FacadeOrder.class);
    }
}
