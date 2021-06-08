package top.zysite.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 * @create 2021/6/8 15:04
 **/
@Component
@Slf4j
@RabbitListener(queues = "topicQueue1")
public class RabbitmqTopicReceiver {

    @RabbitHandler
    public void process(String msg) {
        log.info("RabbitmqTopicReceiver received msg: " + msg);
    }
}