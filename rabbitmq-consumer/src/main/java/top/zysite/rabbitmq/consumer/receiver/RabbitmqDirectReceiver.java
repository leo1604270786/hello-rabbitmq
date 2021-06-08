package top.zysite.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq消费者
 *
 * @author Leo
 * @create 2021/6/8 10:26
 **/
//指定监听队列
@RabbitListener(queues = "directQueue")
@Component
@Slf4j
public class RabbitmqDirectReceiver {
    /**
     * 处理消息
     * @param msg
     */
    @RabbitHandler
    public void process(String msg) {
        log.info("RabbitmqDirectReceiver received msg: " + msg);
    }
}