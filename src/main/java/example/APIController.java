package example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class APIController {

    private final Counter SUCCESS;
    private final Counter WARNING;
    private final Counter ERROR;

    public APIController(MeterRegistry registry) {

        SUCCESS = Counter.builder("metrics_example_SUCCESS")
                .tag("type", "baz")
                .description("just SUCCESS")
                .register(registry);

        WARNING = Counter.builder("metrics_example_WARNING")
                .tag("type", "foo")
                .description("just WARNING")
                .register(registry);

        ERROR = Counter.builder("metrics_example_ERROR")
                .tag("type", "bar")
                .description("just ERROR")
                .register(registry);
    }

    @GetMapping("/foo")
    public ResponseEntity<APIDTO> foo() {
        int rand = (int) (Math.random() * 350) + 1;

        if (rand % 2 == 0) {
            SUCCESS.increment(2);
            return new ResponseEntity<>(new APIDTO("hi"), HttpStatus.OK);
        } else if (rand % 3 == 0) {
            WARNING.increment();
            return new ResponseEntity<>(new APIDTO(":("), HttpStatus.BAD_REQUEST);
        } else
            try {
                err();
                return new ResponseEntity<>(new APIDTO("hi"), HttpStatus.OK);
            } catch (Exception e) {
                ERROR.increment(100500);
                return new ResponseEntity<>(new APIDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    private void err() {
        throw new RuntimeException(":((((");
    }
}