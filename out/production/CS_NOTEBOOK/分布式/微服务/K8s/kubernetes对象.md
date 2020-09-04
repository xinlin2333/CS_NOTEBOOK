kubernetes对象：持久化实体，表示整个集群的状态

kubernetes 对象是目标性记录，通过创建对象，本质上是在告知kubernetes系统所需要的集群工作负载看起来是什么样子的（disired state）



**对象（spec）与状态（status）**

kubernetes对象包含两个嵌套的对象字段，管理对象的配置；

spec：对象的期望状态

status：实际状态







二、对象管理（kubectl）

例子：
1、
创建deployment对象来运行nginx容器
kubectl create deployment nginx --image nginx

等同于：

kubectl run nginx --image nginx

2、创建配置文件中定义的对象
kubectl create -f nginx.yaml

3、删除两个配置文件中定义的对象
kubectl delete -f nginx.yaml -f redis.yaml
4、更新配置
kubectl replace -f nginx.yaml

5、声明式对象配置






