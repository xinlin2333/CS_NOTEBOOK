# 一、概念

分布式流式处理平台

流平台：

1. **消息队列**：发布和订阅消息流，这个功能类似于消息队列，这也是 Kafka 也被归类为消息队列的原因。
2. **容错的持久方式存储记录消息流**： Kafka 会把消息持久化到磁盘，有效避免了消息丢失的风险·。
3. **流式处理平台：** 在消息发布的时候进行处理，Kafka 提供了一个完整的流式处理类库。

Kafka应用场景：

1. **消息队列** ：建立实时流数据管道，以可靠地在系统或应用程序之间获取数据。
2. **数据处理：** 构建实时的流数据处理程序来转换或处理数据流。

同一个topic的消息,有多个分区,会被平均分配(不是复制)到这几个分区,
同一个组的多个消费者,会分别负责一个分区,来取他们中的消息消费;如果是多个消费者负责一个分区,会有offset来控制消费顺序.

# 二、架构组成

## Broker

物理概念，Kafka集群包含一个或多个服务器

## Topic

用来区分、隔离不同的消息数据，屏蔽了底层复杂的存储方式（逻辑概念）

## Partition

用来区分、隔离不同的消息数据，屏蔽了底层复杂的存储方式；**同一个topic的数据，会被分散的存储到多个partition中**，其优势在于：有利于水平扩展，避免单台机器在磁盘空间和性能上的限制，为了做到均匀分布，通常partition的数量通常是Broker Server数量的整数倍

分区多replica：保证消息存储的安全性。当leader发生故障会从follower选举出一个leader，但是follower如果有一和leader同步程序达不到要求的不能精选

优点：

1、Kafka 通过给特定 Topic 指定多个 Partition, 而各个 Partition 可以分布在不同的 Broker 上, 这样便能提供比较好的并发能力（负载均衡）

2、容灾能力



## Consumer Group

是Kafka实现单播和广播两种消息模型的手段。

同一个topic的数据，会广播给不同的group

同一个group中的worker，只有一个worker能拿到这个数据

**worker的数量通常不超过partition的数量，且二者最好保持整数倍关系**（因为Kafka在设计时假定了一个partition只能被一个worker消费（同一group内）

**一个topic有多个分区,每个group可以订阅多个topic,group中的 每个consumer instance都可以消费同一个topic下每个分区的数据,但是group存有每个分区的offset（记录某个user Group在某个partition中当前已经消费到达位置）,所以保证一个topic 多个分区下的每个数据都只被 一个group 消费一次**

# 三、消息的同步机制

At most once 消息可能会丢，但绝不会重复传输

At least one 消息绝不会丢，但可能会重复传输

Exactly once 每条消息肯定会被传输一次且仅传输一次，很多时候这是用户所想要的

## producer的三种保证

producer 的deliver guarantee 可以通过request.required.acks参数的设置来进行调整

0 ，相当于异步发送，消息发送完毕即offset增加，继续生产；相当于At most once

1，leader收到leader replica 对一个消息的接受ack才增加offset，然后继续生产；

-1，leader收到所有replica 对一个消息的接受ack才增加offset，然后继续生产

当producer向broker发送消息时，一旦消息被commit，因数replication的存在，就不会丢失。但是如果producer发送数据给broker后，遇到的网络问题而造成通信中断，那么producer无法判断该条消息是否已经commit。

虽然Kafka无法确定网络故障期间发生了什么，但是producer可以生成一种类似于primary key的东西，发生故障时幂等性的retry多次，这样就做到了Exactly one



## Consumer的三种保证

consumer读取数据进行处理，这两个顺序决定消息从broker和consumer的delivery guarantee semantic

读完先commit 再处理消息。这种模式下，如果consumer在commit后还没来得及处理消息就crash了，下次重新开始工作后就无法读到刚刚已提交而未处理的消息，这就对应于At most once

读完消息先处理再commit。这种模式下，如果处理完了消息在commit之前consumer crash了，下次重新开始工作时还会处理刚刚未commit的消息，实际上该消息已经被处理过了。这就对应于At least once（默认）

**如果一定要做到Exactly once，就需要协调offset和实际操作的输出。精典的做法是引入两阶段提交。**



## 消息传递过程

producer发布消息到某个partition，先通过zookeeper找到该分区的leader，并且只需要把topic发给leader，其他的follower会从leader pull数据。保证了follower存的数据顺序和leader保持一致。follower收到该消息写入log，向leader发送Ack。一旦收到replica ACK，该消息已经被commit，leader增加offset 向producer发送ack。

基于性能考虑，follower收到数据立马发送ack，不等到写入log，此时数据在内存中，不保证持久化道磁盘中，意味着不能保证发生异常后该条消息一定能被consumer消息。



## kafka的offset 与提交关系

### 自动提交

enable_auto_commit、auto_commit_interval_ms

提交到哪个分区：partition=hash（group_id)%50计算

调用consumer.close()时候也会触发自动提交，因为它默认autocommit=True

对于自动提交偏移量，如果auto_commit_interval_ms的值设置的过大，当消费者在自动提交偏移量之前异常退出，将导致kafka未提交偏移量，进而出现重复消费的问题，所以建议auto_commit_interval_ms的值越小越好。

如果让kafka自动去维护offset，消费者拉到消息还没消费完成,它就会认为这条数据已经被消费了，那么会造成**数据丢失**.

### 手动提交

consumer端手动提交offset之后,该offset之前的消息都会被当成已经消费,之后的数据即使消费了也会被当成没消费.比如:服务端有10条数据,consumer消费到第5条,commit了,消费到第10条没有commit程序退出,下次consumer重启之后,会从第6条开始消费.

如果让消费者手动提交，如果在上面的场景中，那么需要我们手动commit，如果comsumer挂了,程序就不会执行commit,其他同group的消费者又可以消费这条数据，保证数据不丢.但可能会导致**重复消费**,

**手动提交三种方式 同步 异步 混合**

**同步**

同步模式下提交失败的时候一直尝试提交，直到遇到无法重试的情况下才会结束，同时同步方式下消费者线程在拉取消息会被阻塞，在broker对提交的请求做出响应之前，会一直阻塞直到偏移量提交操作成功或者在提交过程中发生异常，**限制了消息的吞吐量**。

consumer.commit()

**异步**

异步手动提交offset时，消费者线程不会阻塞，提交失败的时候也不会进行重试，并且可以配合回调函数在broker做出响应的时候记录错误信息。

对于异步提交，由于不会进行失败重试，当消费者异常关闭或者触发了再均衡前，如果偏移量还未提交就会造成偏移量丢失。

consumer.commit_async(callback=_on_send_response)

**混合**

解决offset丢失的问题，通过对消费者进行异步批次提交并且在关闭时同步提交的方式，这样即使上次失败，还可以通过异步提交进行补救，同步会一直重试，直到提交成功

## zookeeper 在Kafka中的作用

1、broker注册

有一个专门**用来进行 Broker 服务器列表记录**的节点

每个 Broker 在启动时，都会到 Zookeeper 上进行注册，即到/brokers/ids 下创建属于自己的节点。每个 Broker 就会将自己的 IP 地址和端口等信息记录到该节点中去

2、topic注册

在 Kafka 中，同一个**Topic 的消息会被分成多个分区**并将其分布在多个 Broker 上，**这些分区信息及与 Broker 的对应关系**也都是由 Zookeeper 在维护。

3、负载均衡

对于同一个 Topic 的不同 Partition，Kafka 会尽力将这些 Partition 分布到不同的 Broker 服务器上。当生产者产生消息后也会尽量投递到不同 Broker 的 Partition 里面。当 Consumer 消费的时候，Zookeeper 可以根据当前的 Partition 数量以及 Consumer 数量来实现动态负载均衡

## Kafka如何保证消息的消费顺序

消息在被追加到 Partition(分区)的时候都会分配一个特定的偏移量（offset）。Kafka 通过偏移量（offset）来保证消息在分区内的顺序性。

所以。发送消息指定key/partition

## Kafka如何保证消息不丢失

### 生产者丢失消息

生产者发送消息调用send（）（异步操作），通过ge t()获取调用结果判断丢失

```java
ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send(topic,o);
funture.addCallBack(result->logger.info(""))
```

其次消息发送失败进行重试（retries）

### 消费者丢失消息

**解决办法也比较粗暴，我们手动关闭闭自动提交 offset，每次在真正消费完消息之后之后再自己手动提交 offset** 。

但是会存在重复消费

### kafka丢失消息

**试想一种情况：假如 leader 副本所在的 broker 突然挂掉，那么就要从 follower 副本重新选出一个 leader ，但是 leader 的数据还有一些没有被 follower 副本的同步的话，就会造成消息丢失。**

**设置acks=all**。acks是生产者很重要的参数，ack=1，代表消息被leader的副本接受。当我们配置 **acks = all**代表则所有副本都要接收到该消息之后该消息才算真正成功被发送。

设置**replication.factor >= 3**

为了保证 leader 副本能有 follower 副本能同步消息，我们一般会为 topic 设置 **replication.factor >= 3**。这样就可以保证每个 分区(partition) 至少有 3 个副本。虽然造成了数据冗余，但是带来了数据的安全性

**设置 min.insync.replicas > 1**

配置代表消息至少要被写入到 2 个副本才算是被成功发送。**min.insync.replicas** 的默认值为 1 ，在实际生产中应尽量避免默认值 1。

**设置 unclean.leader.election.enable = false**

当 leader 副本发生故障时就不会从 follower 副本中和 leader 同步程度达不到要求的副本中选择出 leader，降低丢失可能性

