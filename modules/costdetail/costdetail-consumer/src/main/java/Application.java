import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
//"com.bjike.goddess.common.consumer"
@ComponentScan(basePackages = {"com.bjike.goddess.costdetail.action","com.bjike.goddess.common.consumer", "com.bjike.goddess.costdetail.config"})
@PropertySource(value = {"classpath:permission.properties"}, encoding = "utf-8")
@ImportResource("classpath:app.xml")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);
        System.in.read();
    }


}
