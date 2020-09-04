# JIT两个模式（Just-in_time compilation）

即时编译

client模式：占用内存小，启动快，但是执行效率没有server模式高，默认状态下不进行动态编译

server模式：重量级编译器，也叫C2编译器，启动慢，占用内存大，执行效率高，默认是开启动态编译的，适合服务器应用

## JIT动态编译优点

编译分为静态、动态

静态编译是字节码在装入虚拟机之前就转换成本地代码.动态编译是装入虚拟机之后,根据运行热度,把热度高的字节码转换成本地代码

静态很难预知程序需要优化的点。函数调用浪费系统时间（进栈出栈）

# 内存泄漏&内存溢出

## 内存泄漏

对象被分配的内存没有被回收；长生命周期的对象有一个短生命内存，导致OOM

例如：容器的泄漏，各种连接的close没有及时关闭，单例模式引用了其他短声明周期对象

```java
public class Simple {  
2.	   
3.	    Object object;  
4.	   
5.	    public void method1(){  
6.	        object = new Object();  
7.	    //...其他代码  
8.	    }  
9.	}  
```

method1方法执行完成后，object对象所分配的内存不会立即被认为是可以被释放的对象，只有在simple类创建的对象被释放后才会被释放

**解决方法**

减小对象的作用域

及时对对象赋值

各种连接需要close

## 内存溢出

# 一个类在内存里面的存储

Java运行时区域：

**方法区，虚拟机栈，本地方法栈，堆，程序计数器**

程序计数器：每条线程都需要一个独立的计数器

Java虚拟机栈：生命周期和线程相同，每个方法在执行的时候都会创建一个栈帧，用于存储局部变量表、操作数栈、动态数栈、方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。

堆：所有线程共享的一块内存区域，存放**对象实例**

方法区：存储已被虚拟机加载的**类信息，常量，静态变量**。即时编译器编译后的代码数据

## 运行时数据区域

栈：局部变量 程序计数器 虚拟机栈 native方法栈

（1）程序运行时，首先通过类加载器加载字节码文件，解析后装入方法区，在方法区存了各种信息，包括类变量、常量及方法。对于同一方法的调用，同一个类的不同实例调用的都是方法区的同一个方法

类变量的生命周期从程序开始运行时创建，到程序终止运行时结束

（2）new一个对象，存在堆中，对象的成员变量都存在堆中，当对象被回收时，成员变量随之消失

类变量：类的属性信息，与类的实例无关，多个实例共用一个类变量，存在与方法区中，类变量用static修饰

成员变量：实例的变量，只与实例有关

局部变量：方法中

类的成员变量在不同对象中各不相同，基本数据类型和引用数据类型都存储在这个对象中，作为一个整体存储在堆中。而类的方法是所有的对象共享的，方法是存在方法区的，只用当调用的时候才会被压栈，不用的时候是占内存的。



### 程序计数器

线程所执行的字节码的行号指示器

### 操作数栈里面装的是数

operand stack ：操作栈，

操作数栈每一个元素可以是任意数据类型，32位数据类型占用一个栈容量

### Java虚拟机栈

Java方法在执行的同时会创建一个栈帧用于存储局部变量表、操作数栈（需要执行的指令），常量池

从方法的调用到执行完成的时候，对应着一个栈帧在Java虚拟机中入栈和出栈的过程

异常

- 线程请求的栈深度超过最大值，会抛出stackoverflow
- 栈进行动态扩展如果申请到足够内存，outofMemoryError

### 本地方法栈

本地方法栈为本地方法服务

### 堆（对象、成员变量）

**所有对象实例都在这里分配内存，是垃圾收集的主要区域**

分代收集：针对不同类型的对象采取不同的垃圾回收算法。可以将堆分成两块：

- 新生代（Young Generation）
- 老年代（Old Generation）

堆不要连续内存，动态增加其内存，增加失败抛出outofMemoryError

```
java -Xms 1M -Xmx 2M HackTheJava指定内存大小

```

#### Java对象实例结构

对象：对象头（header）、实例数据（instance data）和对其填充（padding）

**对象头**

markword：存储对象自身的运行时数据，hashCode，GC分代年龄，锁状态标志，线程持有锁，偏向线程ID

**对其填充**

起着栈位符作用

VM的自动内存管理系统要求对象起始地址必须是8字节的整数倍，换句话说，就是对象的大小必须是8字节的整数倍。而对象头部分正好是8字节的倍数（1倍或者2倍），因此，当对象实例数据部分没有对齐时，就需要通过对齐填充来补全。

**配置**

```java
- : 标准VM选项，VM规范的选项
- -X: 非标准VM选项，不保证所有VM支持
- -XX: 高级选项，高级特性，但属于不稳定的选项参见 
  
  
其语义分别是：
-Xmx: 堆的最大内存数，等同于-XX:MaxHeapSize-Xms: 堆的初始化初始化大小 
-Xmn: 堆中新生代初始及最大大小，如果需要进一步细化，初始化大小用  
-XX:NewSize，最大大小用-XX:MaxNewSize -Xss: 线程栈大小，等同于
-XX:ThreadStackSize命名应该非简称，
  
```

### 方法区（类信息 类变量）

存放已被夹在的类信息、常量、静态变量、即时编译起编译后的代码等数据

方法去是一个JVM规范，永久代和原空间都是一种实现方法。1.8后，原来永久代的数据被分到堆和元空间。元空间存储类的元信息，静态变量和常量池放入堆中

### 运行时常量池

方法区的一部分

class文件中常量池会在类加载后被放入到这个区域

### 直接内存

引入NIO类，使用native函数句直接分配对外内存，通过directByteBuffer对象作为这块内存的引用进行擦欧总。避免了堆内外存的来回拷贝

# stackoverflow & outofmemoryError

## 1、stackoverflow

每当java程序启动一个新的线程时，**java虚拟机会为他分配一个栈**，java栈以帧为单位保持线程运行状态；**当线程调用一个方法是，jvm压入一个新的栈帧到这个线程的栈中，只要这个方法还没返回，这个栈帧就存在**。 如果方法的**嵌套调用层次太多(如递归调用**),随着java栈中的帧的增多，最终导致这个**线程的栈中的所有栈帧的大小的总和大于-Xss设置的值**，而产生生StackOverflowError溢出异常。

## 2、outofMemory

### 2.1 栈内存溢出

Java启动一个新线程，没有足够的空间为其分配Java栈

### 2.2 堆内存溢出

为对象实例分配内存

### 2.3  方法区内存溢出

在类加载器加载class文件到内存中的时候，JVM会提取其中的类信息，并将这些类信息放到方法区中。 当需要存储这些类信息，而方法区的内存占用又已经达到最大值

### OOM分类

### OOM for heap（java.lang.outofmemoryError:java heap space)

解决方案：

调高heap的最大值

### OOM for StackOverflowError (Exception in thread "main" java.lang.StackOverflowError)

线程请求的栈深度大于虚拟机所允许的最大深度

如果虚拟机在扩展栈时无法申请到足够的内存空间，抛OOM

一般单线程无法产生OOM异常，使用多线程方式也会出现OOM，因为栈是私有的，线程多也会方法区溢出

### OOM for Perm (java.lang.OutOfMemoryError: PermGen space)

内存的永久保存区域：存放过class和meta信息

Class在被Loader时就会被放到PermGen space中,它和存放类实例(Instance)的Heap区域不同,GC(Garbage Collection)不会在主程序运行期对PermGen space进行清理。

常见在web服务器对JSP进行pre compile。大量用了第三方jar包，超过jvm大小（64M）

解决方法很简单，增大JVM的 -XX:MaxPermSize 启动参数，就可以解决这个问题，如过使用的是默认变量通常是64M，改成128M就可以了，-XX:MaxPermSize=128m。如果已经是128m，就改成 256m。我一般在服务器上为安全起见，改成256m。

### OOM for GC (java.lang.OutOfMemoryError: GC overhead limit exceeded)

由于JVM在GC时，对象过多，导致内存溢出，建议调整GC的策略，在一定比例下开始GC而不要使用默认的策略，或者将新代和老代设置合适的大小，需要进行微调存活率。

改变GC策略，在老代80%时就是开始GC，并且将-XX:SurvivorRatio（-XX:SurvivorRatio=8）和-XX:NewRatio（-XX:NewRatio=4）设置的更合理。

### OOM for native thread created (java.lang.OutOfMemoryError: unable to create new native thread)

# 垃圾收集

堆和方法区

程序计数器、虚拟机栈和本地方法栈这三个区域属于线程私有的，只存在于线程的生命周期内，线程结束之后就会消失

## 判断一个对象是否可以回收

### 1、引用计数

为对象添加一个引用计数器，当对象增加一个引用时计数器加 1，引用失效时计数器减 1。引用计数为 0 的对象可被回收。

在两个对象出现循环引用的情况下，此时引用计数器永远不为 0，导致无法对它们进行回收。正是因为循环引用的存在，因此 Java 虚拟机不使用引用计数算法。

### 2、可达性分析

GC Roots 为起始点进行搜索，可达的对象都是存活的，不可达的对象可被回收

GC Root

- 虚拟机栈中局部变量表中引用的对象
- 本地方法栈中 JNI(Native方法) 中引用的对象
- 方法区中类静态属性引用的对象
- 方法区中的常量引用的对象

### 3、方法区回收

对废弃常量的回收和对无用类的回收

当一个常量对象不再任何地方被引用的时候，则被标记为废弃常量

方法区的类满足3个条件被标记为无用的类：

1、堆中不存在该类的任何实例对象

2、加载该类的类加载器已经被收

3、该类对应.class对象不在任何地方引用

**主要是对常量池的回收和对类的卸载。**

### 4、finalize（）

关闭外部资源

当一个对象可被回收时，如果需要执行该对象的 finalize() 方法，那么就有可能在该方法中让对象重新被引用，从而实现自救。自救只能进行一次，如果回收的对象之前调用了 finalize() 方法自救，后面回收时不会再调用该方法。

## 引用类型

1、强引用

使用new一个新对象来创建抢引用

2、软引用

被软引用关联的对象只有在内存不够的情况下被回收

3、弱引用

存活到下一次垃圾回收之前weakReference

4、虚引用

为一个对象设置虚引用的唯一目的是能在这个对象被回收时收到一个系统通知。

phantomReference

# 垃圾回收算法

## 1、标记-清除

在标记阶段，程序会检查每个对象是否为活动对象，如果是活动对象，则程序会在对象头部打上标记。

在清除阶段，会进行对象回收并取消标志位，另外，还会判断回收后的分块与前一个空闲分块是否连续，若连续，会合并这两个分块。回收对象就是把对象作为分块，连接到被称为 “空闲链表” 的单向链表，之后进行分配时只需要遍历这个空闲链表，就可以找到分块。

不足：效率不高，产生不连续内存碎片

## 2、标记-整理

 让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存。

优点:

- 不会产生内存碎片

不足:

- 需要移动大量对象，处理效率比较低。

## 3、复制

内存划分为大小相等的两块，每次只使用其中一块，当这一块内存用完了就将还存活的对象复制到另一块上面，然后再把使用过的内存空间进行一次清理。

不足：内存使用率不高

## 4、分代收集

一般将堆分为新生代和老年代。

- 新生代使用：复制算法
- 老年代使用：标记 - 清除 或者 标记 - 整理 算法

# 垃圾收集器

![截屏2020-07-29 下午10.17.31](/Users/yangxinlin/Desktop/截屏2020-07-29 下午10.17.31.png)

- 单线程与多线程：单线程指的是垃圾收集器只使用一个线程，而多线程使用多个线程；
- 串行与并行：串行指的是垃圾收集器与用户程序交替执行，这意味着在执行垃圾收集的时候需要停顿用户程序；并行指的是垃圾收集器和用户程序同时执行。除了 CMS 和 G1 之外，其它垃圾收集器都是以串行的方式执行。

## 1、serial收集器

![截屏2020-07-29 下午10.18.35](/Users/yangxinlin/Desktop/截屏2020-07-29 下午10.18.35.png)

它是**单线程的收集器**，只会使用一个线程进行垃圾收集工作。

它的优点是简单高效，在**单个 CPU 环境下，由于没有线程交互的开销，因此拥有最高的单线程收集效率**。

它是 Client 场景下的默认新生代收集器，因为在该场景下内存一般来说不会很大。它收集一两百兆垃圾的停顿时间可以控制在**一百多毫秒以内**，只要不是太频繁，这点停顿时间是可以接受的。

## 2、parNew收集器

![截屏2020-07-29 下午10.20.44](/Users/yangxinlin/Desktop/截屏2020-07-29 下午10.20.44.png)

Serial 收集器的多线程版本

多cpu下效率比serial好,但单核不如,因为有进程切换开销

## 3、parallel scanvenge 收集器（平行清楚）

尽可能缩短垃圾收集时用户线程的停顿时间（吞吐量优先级）

尽快完成程序的运算任务，适合在后台运算而不需要太多交互的任务。缩短停顿时间是以牺牲吞吐量和新生代空间来换取的：**新生代空间变小，垃圾回收变得频繁，导致吞吐量下降。**

## 4、Serial Old收集器

![截屏2020-07-29 下午10.26.56](/Users/yangxinlin/Desktop/截屏2020-07-29 下午10.26.56.png)

client场景下的虚拟机使用，如果用在server场景

与parallel scanvenge收集器搭配使用

作为CMS收集器的后备，在并发收集发生concurrent mode failure时使用

## 5、parallel old收集器

![截屏2020-07-29 下午10.30.02](/Users/yangxinlin/Desktop/截屏2020-07-29 下午10.30.02.png)

在注重吞吐量以及 CPU 资源敏感的场合，都可以优先考虑 Parallel Scavenge 加 Parallel Old 收集器。

### 

6、CMS收集器

concurrent mark sweep

- 初始标记：仅仅只是标记一下 GC Roots 能直接关联到的对象，速度很快，需要停顿。
- 并发标记：进行 GC Roots Tracing 的过程，它在整个回收过程中耗时最长，不需要停顿。
- 重新标记：为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，需要停顿。
- 并发清除：不需要停顿,耗时较长

**耗时最长的并发标记和并发清除**（需要进行停顿）

- 吞吐量低：低停顿时间是以牺牲吞吐量为代价的，导致 CPU 利用率不够高。
- 无法处理浮动垃圾，可能出现 Concurrent Mode Failure。浮动垃圾是指并发清除阶段由于用户线程继续运行而产生的垃圾，这部分垃圾只能到下一次 GC 时才能进行回收。由于浮动垃圾的存在，因此需要预留出一部分内存，意味着 CMS 收集不能像其它收集器那样等待老年代快满的时候再回收。如果预留的内存不够存放浮动垃圾，就会出现 Concurrent Mode Failure，这时虚拟机将临时启用 Serial Old 来替代 CMS。
- 标记 - 清除算法导致的空间碎片，往往出现老年代空间剩余，但无法找到足够大连续空间来分配当前对象，不得不提前触发一次 Full GC。

## 7、G1收集器

garbage-first面向服务端应用的

在多CPU和大内存的场景下有很好的性能

**堆被分为新生代和老年代**，其它收集器进行收集的范围都是整个新生代或者老年代，而 G1 可以直接对新生代和老年代一起回收。

**G1 把堆划分成多个大小相等的独立区域（Region），新生代和老年代不再物理隔离。**

- 初始标记
- 并发标记
- 最终标记：为了修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，虚拟机将这段时间对象变化记录在线程的 Remembered Set Logs 里面，最终标记阶段需要把 Remembered Set Logs 的数据合并到 Remembered Set 中。这阶段需要停顿线程，但是可并行执行。
- 筛选回收：首先对各个 Region 中的回收价值和成本进行排序，根据用户所期望的 GC 停顿时间来制定回收计划。此阶段其实也可以做到与用户程序一起并发执行，但是因为只回收一部分 Region，时间是用户可控制的，而且停顿用户线程将大幅度提高收集效率。

# 内存分配与回收策略

## minor GC & Full GC


Minor GC：回收新生代，因为新生代对象存活时间很短，因此 Minor GC 会频繁执行，执行的速度一般也会比较快。

Full GC：回收老年代和新生代，老年代对象其存活时间长，因此 Full GC 很少执行，执行速度会比 Minor GC 慢很多。



## 内存分配策略

### 1、优先分配Eden

### 2. 大对象直接进入老年代

### 3. 长期存活的对象进入老年代

### 4. 动态对象年龄判定

如果在 Survivor 中相同年龄所有对象大小的总和大于 Survivor 空间的一半

### 5、空间分配担保

## Full GC 触发

1、system.gc

2、老年代空间不足

3、空间分配担保失败

4、永久代不足

5、concurrent mark failure

执行 CMS GC 的过程中同时有对象要放入老年代，而此时老年代空间不足（可能是 GC 过程中浮动垃圾过多导致暂时性的空间不足），便会报 Concurrent Mode Failure 错误，并触发 Full GC。

# JVM类生命周期

## 类加载定义

一个.java文件在编译后会形成相应的一个或多个Class文件，这些Class文件中描述了类的各种信息，并且它们最终都需要被加载到虚拟机中才能被运行和使用。

虚拟机把描述类的数据从Class文件**加载到内存**，并对数据进行校验，转换解析和**初始化**，最终形成可以被虚拟机直接使用的Java类型的过程就是虚拟机的**类加载机制**。

## 类的生命周期

- **加载（Loading）**
- **验证（Verification）**
- **准备（Preparation）**
- **解析（Resolution）**
- **初始化（Initialization）**
- 使用（Using）
- 卸载（Unloading）

**加载、验证、准备、初始化和卸载**这5个阶段的顺序是确定的，类的加载过程必须按照这种顺序按部就班地开始，而解析阶段则不一定：它在某些情况下可以在初始化阶段之后再开始，这是为了支持Java语言的**运行时绑定（也称为动态绑定或晚期绑定）**

## 加载时机和初始化时机
