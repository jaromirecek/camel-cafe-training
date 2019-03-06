package uni.training.camel.camelcafe;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CamelCafeApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CamelCafeApplication.class, args);
        CamelSpringBootApplicationController applicationController =
                applicationContext.getBean(CamelSpringBootApplicationController.class);
        runCafeRouteDemo(applicationContext);
        applicationController.blockMainThread();

    }

    public static void runCafeRouteDemo(ApplicationContext applicationContext) {
        CamelContext camelContext = applicationContext.getBean(CamelContext.class);
        // create a producer so we can send messages to Camel
        ProducerTemplate template = camelContext.createProducerTemplate();

        Order order = new Order(2);
        order.addItem(DrinkType.ESPRESSO, 2, true);
        order.addItem(DrinkType.CAPPUCCINO, 4, false);
        order.addItem(DrinkType.LATTE, 4, false);
        order.addItem(DrinkType.MOCHA, 2, false);

        template.sendBody("direct:cafe", order);


    }

}
