package top.zysite.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 * @create 2021/6/8 14:35
 **/
@Component
@Slf4j
@RabbitListener(queues = "fanoutQueue2")
public class RabbitmqFanoutReceiver2 {

    @RabbitHandler
    public void process(String msg) {
        log.info("RabbitmqFanoutReceiver2 received msg: " + msg);
    }
}