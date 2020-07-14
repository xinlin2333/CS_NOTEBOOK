kubernetes对象：持久化实体，表示整个集群的状态

kubernetes 对象是目标性记录，通过创建对象，本质上是在告知kubernetes系统所需要的集群工作负载看起来是什么样子的（disired state）



**对象（spec）与状态（status）**

kubernetes对象包含两个嵌套的对象字段，管理对象的配置；

spec：对象的期望状态

status：实际状态







二、对象管理（kubectl）

例子：
kubectl create deployment nginx --image nginx

等同于：

kubectl run nginx --image nginx





