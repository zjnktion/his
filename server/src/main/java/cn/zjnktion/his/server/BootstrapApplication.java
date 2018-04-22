package cn.zjnktion.his.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zjnktion
 */
@EnableTransactionManagement
@SpringBootApplication
public class BootstrapApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootstrapApplication.class).web(true).run(args);
    }
}
