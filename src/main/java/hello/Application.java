package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Class Application.
 *
 * @author Boris Pronin (<a href="mailto:bpronin@bttprime.com">bpronin@bttprime.com</a>)
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}