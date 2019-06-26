package aleksander;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private static String WELCOME = "Hello";
    private static String FALLBACK_ID_WELCOME = "Hola";
    //private HelloService SUT = new HelloService();

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        var mokRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mokRepository);
        // when
        var result = SUT.prepareGreeting(null,"-1");

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!",result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var mokRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mokRepository);
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name,"-1");

        // then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        // given

        var mokRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mokRepository);

        // when
        var result = SUT.prepareGreeting(null,null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        // given

        var mokRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mokRepository);

        // when
        var result = SUT.prepareGreeting(null,"abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang(){
        // given
        var mokRepository = nonExistingLangIdRepository();
        var SUT = new HelloService(mokRepository);

        // when
        var result = SUT.prepareGreeting(null,"-1");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);

    }

    private LangRepository nonExistingLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null,"Hola",null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null,WELCOME,null));
            }
        };
    }
}
