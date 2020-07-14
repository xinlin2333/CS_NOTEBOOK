 ![截屏2020-07-14 下午3.52.38](/Users/yangxinlin/Desktop/截屏2020-07-14 下午3.52.38.png)

kubernetes 集群：由一组被称为节点的机器组成。集群至少一个工作节点和一个主节点



工作节点托管作为 应用程序组建的pod。主节点管理集群中的工作节点和pod。多个主节点用于为集群提供故障转移和高可用性

conrtol plane components：控制平面组件

控制平面对集群作出全局决策以及检测和响应集群事件；



kube-apiserver：主节点上负责提供kubernetes API服务的组件



etcd：保存kubernetes所有集群数据的后台数据库



kube-scheduler：主节点上的组件，该组件监视那些新创建的未指定运行节点的pod，并选择节点让pod在上面运行



kube-controller-manager：主节点上运行控制器的组件；

控制器包括：节点控制器（node controller）节点出现故障时进行通知和响应

副本控制器：replication controller：负责为系统中的每个副本控制器对象维护正确数量pod

端点控制器（endpoint controller）：填充端点对象

服务账户和令牌控制器（service account & token controllers）新的命名空间创建默认账户和API访问令牌



云控制管理器（cloud-controller-manager）：





NODE组件：节点组件在每个节点上运行，维护运行的pod并提供kubernetes运行环境

kubelet：一个在集群中每个节点上运行的代理。保证容器都运行在pod中；kubelet接受一组各类机制提供给他的podspecs，确保这些podspecs描述的哦容器处于运行和健康



kube-proxy：集群中每个节点上运行的网络代理



container runtime：容器运行环境是负责运行容器的软件





