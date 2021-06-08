package top.zysite.rabbitmq.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Leo
 * @create 2021/6/8 10:07
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqProviderTests {
    @Resource
    private RabbitTemplate rabbitTemplate;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testSend() {
        String msg = "hello-first-rabbitmq-msg";
        rabbitTemplate.convertAndSend("directExchange", "directRk", msg);
    }

    @Test
    public void testSend2() {
        for (int i = 0; i < 10; i++) {
            String msg = "msg-" + i + "-" +  LocalDateTime.now().format(dateTimeFormatter);
            rabbitTemplate.convertAndSend("directExchange", "directRk", msg);
        }
    }

    @Test
    public void testFanoutExchange() {
        String fanoutMsg = "hello-fanout-exchange";
        rabbitTemplate.convertAndSend("fanoutExchange", null, fanoutMsg);
    }

    @Test
    public void testTopicExchange() {
        String routingKey1 = "top.zysite.topic";
        String topicMsg1 = "hello-topic-exchange1";

        String routingKey2 = "top.baidu.topic";
        String topicMsg2 = "hello-topic-exchange2";

        String routingKey3 = "top.zysite.topic.test";
        String topicMsg3 = "hello-topic-exchange3";

        rabbitTemplate.convertAndSend("topicExchange", routingKey1, topicMsg1);
        rabbitTemplate.convertAndSend("topicExchange", routingKey2, topicMsg2);
        rabbitTemplate.convertAndSend("topicExchange", routingKey3, topicMsg3);
    }

    @Test
    public void testReturnCallback() {
        String msg = "returnMsg";
        String routingKey = "com.baidu.topic";

        rabbitTemplate.convertAndSend("topicExchange", routingKey, msg);
    }

    @Test
    public void testConsumerAck() {
        String msg = "ackMsg";
        String routingKey = "ackRk";
        rabbitTemplate.convertAndSend("ackExchange", routingKey, msg);
    }
}