import com.solution.RateLimiter;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class RateLimitCalculatorTests {


    @Test
    public void shouldGiveLimitForSpecifiedInput(){
        //Arrange
        List<String> requests = List.of("www.xyz.com", "www.abc.com", "www.xyz.com", "www.pqr.com"
                , "www.abc.com", "www.xyz.com", "www.xyz.com","www.abc.com","www.xyz.com");
        List<String>expectedResult = List.of("{status:200, message: Ok}", "{status:200, message: Ok}", "{status:200, message: Ok}",
                "{status:200, message: Ok}", "{status:200, message: Ok}", "{status:200, message: Ok}",
                "{status:429, message: Too many requests}", "{status:200, message: Ok}", "{status:429, message: Too many requests}");

        //Act
        RateLimiter rateLimiter = new RateLimiter();
        List<String> result = rateLimiter.getRateLimiterOptions(requests);

        //Assert
        assertThat("The rate limit caluclation is false", result, is(expectedResult));
    }

}
