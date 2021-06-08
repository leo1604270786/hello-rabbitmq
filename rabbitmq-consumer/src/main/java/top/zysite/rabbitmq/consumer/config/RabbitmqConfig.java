package top.zysite.rabbitmq.consumer.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Leo
 * @create 2021/6/8 16:22
 **/
@Slf4j
@Configuration
public class RabbitmqConfig {

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        //改为手动提交确认（ack）
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //往该容器中添加队列
        simpleMessageListenerContainer.setQueueNames("ackQueue");
        //设置消息监听回调，
        simpleMessageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                try {
                    log.info("ChannelAwareMessageListener received msg : " + message.toString());
                    //第2个参数为 是否开启 批量 ack，即提交 <= 该 DeliveryTag 的所有消息的 ack
                    channel.basicAck(deliveryTag, true);
                } catch (IOException e) {
                    //发生异常时，向 rabbitmq 服务器拒绝处理该消息, 第2个参数表示是否重新入队该消息
                    channel.basicReject(deliveryTag, false);
                }
            }
        });
        return simpleMessageListenerContainer;
    }
}