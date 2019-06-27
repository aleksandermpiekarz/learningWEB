package aleksander.hello;

import aleksander.lang.Lang;
import aleksander.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService {

    static final String FALLBACK_NAME = "Marcin";
    static final Lang FALLBACK_LANG = new Lang(1,"Hello","en");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);
    private LangRepository repository;

    HelloService(){
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    /**
     *
     *    String prepareGreeting(String name) {
     *         return prepareGreeting(name,null);
     *     }
     */


    String prepareGreeting(String name, String lang){

        Integer langId;
        try {
            langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG.getId());
        } catch (NumberFormatException e){
            logger.warn("wyjebalo blad ;////"); //"non-numeric language i used"  + lang
            langId = FALLBACK_LANG.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        String nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
        //return "Hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }
}
