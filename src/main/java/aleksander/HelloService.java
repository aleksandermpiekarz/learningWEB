package aleksander;

import java.util.Optional;

public class HelloService {

    static final String FALLBACK_NAME = "Marcin";
    String prepareGreeting(String name) {
        return "Hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }
}
