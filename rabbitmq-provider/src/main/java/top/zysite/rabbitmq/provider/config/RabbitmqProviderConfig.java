package top.zysite.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq生产者配置类
 *
 * @author Leo
 * @create 2021/6/8 10:03
 **/
@Configuration
public class RabbitmqProviderConfig {
    /**
     * 创建一个 queue
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true);
    }

    /**
     * 创建一个 direct 类型的 exchange
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    /**
     * 绑定前面创建的 directQueue 和 directExchange ，指定 routing key 为 directRk
     * @return
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRk");
    }

    /**
     * 创建两个队列：fanoutQueue1、fanoutQueue2
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1", true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2", true);
    }

    /**
     * 创建一个 fanout 类型的 exchange
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange", true, false);
    }

    /**
     * 将队列：fanoutQueue1、fanoutQueue2 绑定 交换器：fanoutExchange
     * 这里不用配置 routing key，因为 fanout 类型的 exchange 在收到消息时会将消息发送所有与其绑定的队列里
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * 创建两个队列：topicQueue1、topicQueue2
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1", true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2", true);
    }

    /**
     * 创建一个 topic 类型的 exchange
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange", true, false);
    }

    /**
     * 对于 topic 类型的 exchange，指定 routing key 时，有两个特殊通配符： # 匹配 0 个或多个单词，* 匹配一个单词。
     * 单词用 . 隔开
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        //绑定 topicQueue1 和 topicExchange，指定 routing key 为 top.zysite.*
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("top.zysite.*");
    }

    @Bean
    public Binding topicBinding2() {
        //绑定 topicQueue2 和 topicExchange，指定 routing key 为 top.#
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("top.#");
    }

    /**
     * 创建一个 ackQueue ，并绑定到 ackExchange，指定 routing key 为 ackRk
     * @return
     */
    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue", true);
    }
    @Bean
    public DirectExchange ackExchange() {
        return new DirectExchange("ackExchange", true, false);
    }
    @Bean
    public Binding ackBinding() {
        return BindingBuilder.bind(ackQueue()).to(ackExchange()).with("ackRk");
    }
}