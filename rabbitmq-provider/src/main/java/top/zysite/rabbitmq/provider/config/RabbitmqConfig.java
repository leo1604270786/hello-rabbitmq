package top.zysite.rabbitmq.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 RabbitTemplate
 *
 * @author Leo
 * @create 2021/6/8 15:45
 **/
@Slf4j
@Configuration
public class RabbitmqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //mandatory 为 true 时，当消息无法路由到队列时，会将消息返还给消费者客户端（通过Basic.Return）
        //可以添加回调，接收并处理返回的消息
        rabbitTemplate.setMandatory(true);
        //添加 rabbitmq 服务器返回的消息回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.info("Received Returned Msg: " + returned.toString());
            }
        });

        return rabbitTemplate;
    }
}