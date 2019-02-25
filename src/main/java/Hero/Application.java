package Hero;

import Hero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HeroRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /* Do nothings.

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Hero("Roger"));
        repository.save(new Hero("Hunter"));
        repository.save(new Hero("Sam"));
        repository.save(new Hero("Mr.Nice"));
        repository.save(new Hero("Bombasto"));
        repository.save(new Hero("Celeritas"));
        repository.save(new Hero("Magneta"));
        repository.save(new Hero("RubberMan"));
        repository.save(new Hero("Dynama"));
        repository.save(new Hero("Dr IQ"));

        */
    }

}