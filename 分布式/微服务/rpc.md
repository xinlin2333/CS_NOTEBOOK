RPC

从单机到分布式-》分布式通信-〉最基本：二进制数据传输TCP/IP



1、

```java
package common;

import java.io.Serializable;

/**
 * @author yangxinlin
 * @date 2020/07/24
 */
public class User implements Serializable {
    private static final long serialVersionID =1L;
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```



RPC序列化框架

1、Java.io.serializable

2、Hessian

3、Google protobuf

4、facebook thrift

5、kyro

6、fst

7、json序列化框架（Jackson； google Gson；Ali FastJson

8、xmlrpc（xstream）

PRC 网络协议（每种协议的应用场景）

