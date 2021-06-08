package top.zysite.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 * @create 2021/6/8 13:32
 **/
//和 RabbitmqDirectReceiver 监听同一个队列
@RabbitListener(queues = "directQueue")
@Component
@Slf4j
public class RabbitmqDirectReceiver2 {
    /**
     * 处理消息
     * @param msg
     */
    @RabbitHandler
    public void process(String msg) {
        log.info("RabbitmqDirectReceiver2 received msg: " + msg);
    }
}