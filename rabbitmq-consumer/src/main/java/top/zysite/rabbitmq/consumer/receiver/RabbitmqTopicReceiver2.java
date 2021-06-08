package top.zysite.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 * @create 2021/6/8 15:05
 **/
@Component
@Slf4j
@RabbitListener(queues = "topicQueue2")
public class RabbitmqTopicReceiver2 {

    @RabbitHandler
    public void process(String msg) {
        log.info("RabbitmqTopicReceiver2 received msg: " + msg);
    }
}