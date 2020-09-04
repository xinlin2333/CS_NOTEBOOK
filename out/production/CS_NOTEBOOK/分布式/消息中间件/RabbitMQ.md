# 一、概念

- 可靠：持久化、传输、发布确认

- 灵活路由：提供内置交换器实现路由消息。对于复杂的路由功能，将多个交换器bind

- 扩展性：组成或者扩展集群

- 高可用性：队列在集群机器上设置镜像，使得部分节点在实际业务情况下队列仍然可用

- 支持多种协议：原声AMQP，还支持STOMP、MQTT等多种消息中间件

- 多语言Client

- 管理界面：提供了一个易用的用户界面，使得用户可以监控和管理消息、集群中的节点等、

- 插件机制： RabbitMQ 提供了许多插件，以实现从多方面进行扩展，当然也可以编写自己的插件。感觉这个有点类似 

  [Dubbo]: http://dubbo.apache.org/zh-cn/docs/source_code_guide/dubbo-spi.html	"SPI机制"

  

# 二、原理

![截屏2020-07-29 下午3.23.27](/Users/yangxinlin/Desktop/截屏2020-07-29 下午3.23.27.png)

## 2.1 Producer 、Consumer

- **Producer(生产者)** :生产消息的一方（邮件投递者）
- **Consumer(消费者)** :消费消息的一方（邮件收件人

消息一般由 2 部分组成：**消息头**（或者说是标签 Label）和 **消息体**（payLoad）

消息头：可选属性组成，routing-key（路由键）、priority、delivery-mode（指出消息可能需要持久存储）

## 2.2 Exchange

**消息经过交换机分配到对应的Queue中**

**Exchange(交换器)** 用来接收生产者发送的消息并将这些消息路由给服务器中的队列中，如果路由不到，或许会返回给 **Producer(生产者)** ，或许会被直接丢弃掉 

RabbitMQ的Exchange 四种类型：不同类型对应着不同路由策略：direct（默认），fanout，topic，headers

生产者制定**RoutingKey**消息发给交换器，用来指定这个消息的路由规则，而这个RoutingKey需要与 交换器类型和绑定键（bindingKey）联合使用

RabbitMQ 中通过 **Binding(绑定)** 将 **Exchange(交换器)** 与 **Queue(消息队列)** 关联起来

**一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则**

## 2.3 QUEUE

**Queue(消息队列)** 用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

**RabbitMQ** 中消息只能存储在 **队列** 中，这一点和 **Kafka** 这种消息中间件相反。Kafka 将消息存储在 **topic（主题）** 这个逻辑层面，而相对应的队列逻辑只是topic实际存储文件中的位移标识。 RabbitMQ 的生产者生产消息并最终投递到队列中，消费者可以从队列中获取消息并消费。

**多个消费者可以订阅同一个队列**，这时队列中的消息会被平均分摊（Round-Robin，即轮询）给多个消费者进行处理，而不是每个消费者都收到所有的消息并处理，这样避免的消息被重复消费。

## 2.4 Broker消息中间件服务节点

broker 服务实例：大多数情况下也可以将一个 RabbitMQ Broker 看作一台 RabbitMQ 服务器

![截屏2020-07-29 下午3.49.29](/Users/yangxinlin/Desktop/截屏2020-07-29 下午3.49.29.png)

## 2.5 Exchange交换类型

1、fanout

会把所有发送到该Exchange的消息路由到所有与它绑定的Queue，不需要做任何判断操作，所以 fanout 类型是所有的交换机类型里面速度最快的。

2、direct

消息路由到那些 Bindingkey 与 RoutingKey 完全匹配的 Queue 中。

![img](https://camo.githubusercontent.com/7177328efe9e95372a9b7fb8b51022312d2e601b/687474703a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f31382d31322d31362f33373030383032312e6a7067)

direct 类型常用在处理有优先级的任务，根据任务的优先级把消息发送到对应的队列，这样可以指派更多的资源去处理高优先级的队列

3、topic

- RoutingKey 为一个点号“．”分隔的字符串（被点号“．”分隔开的每一段独立的字符串称为一个单词），如 “com.rabbitmq.client”、“java.util.concurrent”、“com.hidden.client”;
- BindingKey 和 RoutingKey 一样也是点号“．”分隔的字符串；
- BindingKey 中可以存在两种特殊字符串“*”和“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词(可以是零个)。

![截屏2020-07-29 下午4.03.01](/Users/yangxinlin/Desktop/截屏2020-07-29 下午4.03.01.png)

4、headers

根据消息头的headers进行匹配