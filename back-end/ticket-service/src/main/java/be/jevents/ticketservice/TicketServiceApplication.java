package be.jevents.ticketservice;

import be.jevents.ticketservice.model.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class TicketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }

//    @Bean
//    public Supplier<Message<Ticket>> orderBuySupplier() {
//        return () -> {
//            if (buyOrders.peek() != null) {
//                Message<Order> o = MessageBuilder
//                        .withPayload(buyOrders.peek())
//                        .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(buyOrders.poll()).getId())
//                        .build();
//                log.info("Order: {}", o.getPayload());
//                return o;
//            } else {
//                return null;
//            }
//        };
//    }
}
