package aleksander;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private HelloService SUT = new HelloService();

    @Test
    public void test_perpareGreeting_null_returnsGreetingWithFallback() {
        // gieven + when
        var result = SUT.prepareGreeting(null);

        // then
        assertEquals("Hello " + HelloService.FALLBACK_NAME + "!",result);
    }

    @Test
    public void test_perpareGreeting_name_returnsGreetingWithName() {
        // gieven
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name);

        // then
        assertEquals("Hello " + name + "!",result);
    }
}
