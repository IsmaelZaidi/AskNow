package nl.tudelft.oopp.g72;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class on the server side. From here the server is started.
 */
@SpringBootApplication
public class ServerApplication {
    /**
     * The main class. Here the run method in SpringApplication gets run and the server gets started.
     *
     * @param args the args get passed on to the run method.
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
