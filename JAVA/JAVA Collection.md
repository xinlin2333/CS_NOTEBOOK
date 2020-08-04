# 一、概览

包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对（两个对象）的映射表

## Collection

### 1、set

- TreeSet：基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如 HashSet，HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。
- HashSet：基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
- LinkedHashSet：具有 HashSet 的查找效率，且内部使用双向链表维护元素的插入顺序。

### 2、List

**ArrayList：基于动态数组实现，支持随机访问。**

ArrayList 通过数组实现，一旦我们实例化 ArrayList 无参数构造函数默认为数组初始化长度为 10 如果增加的元素个数超过了 10 个，那么 ArrayList 底层会新生成一个数组，长度为原数组的 1.5 倍+1，然后将原数组的内容复制到新数组当中，并且后续增加的内容都会放到新数组当中。

当新数组无法容纳增加的元素时，重复该过程。一旦数组超出长度，就开始扩容数组。扩容数组调用的方法 Arrays.copyOf(objArr, objArr.length + 1);

**动态数组的本质**就是，当数据超出当前数组的内存范围，这个时候，重新开辟一块大小为当前容量两倍的数组，把原数据拷贝过去，释放掉旧的数组， 这个时候内存有很多是没有使用的，这个时候就可以执行增删操作。

**Vector(已过时)：和 ArrayList 类似，但它是线程安全的。**

**LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删**

不仅如此，LinkedList 还可以用作栈、队列和双向队列。LinkedList 底层的数据结构是基于双向循环链表的，且头结点中不存放数据.

**LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用**。

## 3、queue

- LinkedList：可以用它来实现双向队列。LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
- PriorityQueue：基于堆结构实现，可以用它来实现优先队列和大顶堆小顶堆(见LeetCode的**topK问题**).PriorityQueue的逻辑结构是一棵完全二叉树，存储结构其实是一个数组。逻辑结构层次遍历的结果刚好是一个数组。

offer，add 区别：

一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。

这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到**由 offer() 返回的 false**。

poll，remove 区别：

remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似， 但是**新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null**。因此新的方法更适合容易出现异常条件的情况。

peek，element区别：

element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 **peek() 返回 null**。

## Map

- TreeMap:基于红黑树实现

- HashMap:基于哈希表实现。

- HashTable:和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。

- LinkedHashMap:使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。

  ```java
   public LinkedHashMap(int initialCapacity,
                             float loadFactor,
                             boolean accessOrder) {
            super(initialCapacity, loadFactor);
            this.accessOrder = accessOrder;
        }
  ```

# 二、容器中的设计模式

## 迭代器模式

Collection继承了Iterable接口，其中的iterator能够产生一个Iterator对象，通过这个对象可以迭代遍历Collection中的元素

JDK1.5后foreach遍历实现了iterator接口的聚合对象

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
    System.out.println(item);
}
```

## 适配器模式

java.util.Arrays#asList可以把数组类型转换为List类型

```java
@SafeVarargs
public static <T> List<T> asList(T... a)
```

应该注意的是 asList() 的参数为泛型的**变长参数**，不能使用基本类型数组作为参数，**只能使用相应的包装类型数组**

# 三、源码分析

## ArrayList

### 1、概览

因为 ArrayList 是基于数组实现的，所以支持快速随机访问。**RandomAccess** 接口标识着该类支持快速随机访问。

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

数组默认大小为10

```java
private static final int DEFAULT_CAPACITY = 10;
```

### 2、扩容

添加元素时使用ensureCapacity（）方法确保容量足够，如果不够，使用grow（）方法进行扩容，新的容量大小为oldCapacity(oldCapacity>>1)**（1.5倍）**

扩容操作需要copyOf把原数组整个复制到新数组中，这个操作代价很高，最好在创建ArrayList对象时指定容量大小，减少扩容操作次数

### 3、删除元素

需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，该操作的时间复杂度为 O(N)，可以看出 ArrayList 删除元素的代价是非常高的。

```java
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
}
```

### 4、Fail-Fast

modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}
```

### 5、序列化

ArrayList 基于数组实现，并且具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组elementData使用transient修饰，该关键字声明数组不会被序列化

```java
transient Object[] elementData; // non-private to simplify nested class access
```

Arraylist实现了writeObject 、readObject控制只序列化数组中有元素填充的那部分内容

```java 
private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    elementData = EMPTY_ELEMENTDATA;

    // Read in size, and any hidden stuff
    s.defaultReadObject();

    // Read in capacity
    s.readInt(); // ignored

    if (size > 0) {
        // be like clone(), allocate array based upon size not capacity
        ensureCapacityInternal(size);

        Object[] a = elementData;
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            a[i] = s.readObject();
        }
    }
}
```

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }

```

序列化时需要使用ObejctOutPutStream的writeObject将对象转换为字节流输出，而writeObject在传入对象存在writeOBject（）的时候会反射调用该对象的writeObject来实现序列化

反序列化使用的是 ObjectInputStream 的 readObject() 方法，原理类似。

```java
ArrayList list = new ArrayList();
ObjectOutPutStream oos = new ObjectOutPutStream(new FileOutputStream(file));
oos.writeObject(list);

```

## Vector

### 1、同步

使用了 synchronized 进行同步。

```java
public synchronized boolean add(E e) {
    modCount++;
    ensureCapacityHelper(elementCount + 1);
    elementData[elementCount++] = e;
    return true;
}

public synchronized E get(int index) {
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);

    return elementData(index);
}
```

### 2、与arrayList对比

- Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。最好使用 ArrayList 而不是 Vector，因为同步操作完全可以由程序员自己来控制；
- Vector 每次扩容请求其大小的 2 倍空间，而 ArrayList 是 1.5 倍。



### 3、替代方案

可以使用Collections.synchronizedList()得到一个线程安全的ArrayList

```java
List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);
```

也可以使用concurrent并发包下的CopyOnWriteArrayList类

```java
List<String> list = new CopyOnWriteArrayList<>();
```

## CopyOnWriteArrayList

### 读写分离

写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。

写操作需要加锁，防止并发写入时导致写入数据丢失。

写操作结束之后需要把原始数组指向新的复制数组。

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}

final void setArray(Object[] a) {
    array = a;
}
@SuppressWarnings("unchecked")
private E get(Object[] a, int index) {
    return (E) a[index];
}
```

### 适用场景

CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

但是 CopyOnWriteArrayList 有其缺陷：

- 内存占用：在写操作时需要复制一个新的数组，使得内存占用为原来的两倍左右；
- 数据不一致：读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中。

所以 CopyOnWriteArrayList 不适合内存敏感以及对实时性要求很高的场景。

## LinkedList

### 1、原理

基于双向链表实现，使用Node存储节点信息

```java
private static class Node<E>{
  E item;
  Node<E> next;
  Node<E> prev;
}
```

每个链表存储first和last指针

```java
transient Node<E> first;
transient Node<E> last;
```

### 2、与ArrayList比较

ArrayList基于动态数组实现，LinkedList基于链表，优点缺点体现在CRUD

- ArrayList 基于动态数组实现，LinkedList 基于双向链表实现；
- ArrayList 支持随机访问，LinkedList 不支持；
- LinkedList 在任意位置添加删除元素更快。

在数据的更新和查找方面,因为arraylist的数据在同一个地址上,所以可以直接定位数据,查找和更新很快;LinkedList的每一个数据都有一个不同的地址,需要顺序查询然后更新,速度会慢很多;

在增加和删除上面,arraylist需要移动其他数据,LinkList只需要修改对应数据处的指向,所以LinkList快很多;

## HashMap

### 1、存储结构

内部包含了一个Entry类型的数组table

```java
transient Entry[] table;
```

Entry 存储着键值对。它包含了四个字段，从 next 字段我们可以看出 Entry 是一个链表。即数组中的每个位置被当成一个桶，一个桶存放一个链表。HashMap 使用拉链法来解决冲突，同一个链表中存放哈希值和散列桶取模运算结果相同的 Entry

```java
Entry(int h, K k, V v, Entry<K,V> n) {
    value = v;
    next = n;
    key = k;
    hash = h;
}

public final K getKey() {
    return key;
}

public final V getValue() {
    return value;
}

public final V setValue(V newValue) {
    V oldValue = value;
    value = newValue;
    return oldValue;
}

public final boolean equals(Object o) {
    if (!(o instanceof Map.Entry))
        return false;
    Map.Entry e = (Map.Entry)o;
    Object k1 = getKey();
    Object k2 = e.getKey();
    if (k1 == k2 || (k1 != null && k1.equals(k2))) {
        Object v1 = getValue();
        Object v2 = e.getValue();
        if (v1 == v2 || (v1 != null && v1.equals(v2)))
            return true;
    }
    return false;
}

public final int hashCode() {
    return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
}

public final String toString() {
    return getKey() + "=" + getValue();
}
}
```

### 2、拉链法工作原理

链表的插入是以头插法方式进行的，例如上面的 <K3,V3> 不是插在 <K2,V2> 后面，而是插入在链表头部

查找需要分成两步进行：

- 计算键值对所在的桶；
- 在链表上顺序查找，时间复杂度显然和链表的长度成正比。

### 3、put

```java
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    // 键为 null 单独处理
    if (key == null)
        return putForNullKey(value);
    int hash = hash(key);
    // 确定桶下标
    int i = indexFor(hash, table.length);
    // 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }

    modCount++;
    // 插入新键值对
    addEntry(hash, key, value, i);
    return null;
    //put的返回值类型是V,本来以为会是插入成功的newValue,
    //但看了源码之后才发现,如果key存在则替换oldValue并返回oldValue,
    //如果不是更新而是插入新的entry则返回null.
}
```

HashMap 允许插入键为 null 的键值对。但是因为无法调用 null 的 hashCode() 方法，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。

```java
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
}
```

使用链表头插法

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    if ((size >= threshold) && (null != table[bucketIndex])) {
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }

    createEntry(hash, key, value, bucketIndex);
}

void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    // 头插法，链表头部指向新的键值对
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}
```

### 4、确定桶下标

```java
int hash = hash(key);
int i = indexFor(hash, table.length);
```

#### 4.1计算hash值

```java
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCode();

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}
```

#### 4.2取模（待学）

**扩容长度是2n次方的原因**

### 5、扩容-基本原理

设 HashMap 的 table 长度为 M，需要存储的键值对数量为 N，如果哈希函数满足均匀性的要求，那么每条链表的长度大约为 N/M，因此平均查找次数的复杂度为 O(N/M)。

为了让查找的成本降低，应该尽可能使得 N/M 尽可能小，因此需要保证 M 尽可能大，也就是说 table 要尽可能大。HashMap 采用动态扩容来根据当前的 N 值来调整 M 值，使得空间效率和时间效率都能得到保证。

| 参数       | 含义                                                         |
| ---------- | ------------------------------------------------------------ |
| capacity   | table 的容量大小，默认为 16。需要注意的是 capacity 必须保证为 2 的 n 次方。 |
| size       | 键值对数量。                                                 |
| threshold  | size 的临界值，当 size 大于等于 threshold 就必须进行扩容操作。 |
| loadFactor | 装载因子，table 能够使用的比例，threshold = capacity * loadFactor。 |

```java
static final int DEFAULT_INITIAL_CAPACITY = 16;

static final int MAXIMUM_CAPACITY = 1 << 30;

static final float DEFAULT_LOAD_FACTOR = 0.75f;

transient Entry[] table;

transient int size;

int threshold;

final float loadFactor;

transient int modCount;
```

当需要扩容时，令 capacity 为原来的两倍,capacity必须是2的n次方。

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    if (size++ >= threshold)
        resize(2 * table.length);
}
```

扩容使用 resize() 实现，需要注意的是，扩容操作同样需要把 oldTable 的所有键值对重新插入 newTable 中，因此这一步是很费时的。

### 6、扩容-重新计算桶下标

在进行扩容时，需要把键值对重新放到对应的桶上。HashMap 使用了一个特殊的机制，可以降低重新计算桶下标的操作。

假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32：

```
capacity     : 00010000
new capacity : 00100000
```

对于一个 Key，

- 它的哈希值如果在第 5 位上为 0，那么取模得到的结果和之前一样；
- 如果为 1，那么得到的结果为原来的结果 +16。因为假设原来是17对16取模变成了1,如果现在是对32取模,17=16+1;

### 7、计算数组容量

HashMap 构造函数允许用户传入的容量不是 2 的 n 次方，因为它可以自动地将传入的容量转换为 2 的 n 次方。

### 8、链表转红黑树

JDK1.8 一个桶存储的链表长度大于 8 时会将链表转换为红黑树。

### 9、与HashTable比较（不支持null entry)

- HashTable 使用 synchronized 来进行同步。
- HashMap 可以插入键为 null 的 Entry。
- HashMap 的迭代器是 fail-fast 迭代器。
- HashMap 不能保证随着时间的推移 Map 中的元素次序是不变的。



**hashMap不支持并发的原因**

1、向HashMap中插入数据

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    if ((size >= threshold) && (null != table[bucketIndex])) {
        resize(2 * table.length); //扩容2倍
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }

    createEntry(hash, key, value, bucketIndex);

}
//创建一个Entry
void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];//先把table中该位置原来的Entry保存
    //在table中该位置新建一个Entry，将原来的Entry挂到该Entry的next
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    //所以table中的每个位置永远只保存一个最新加进来的Entry，其他Entry是一个挂一个，这样挂上去的
    size++;
}

```

现在假如A线程和B线程同时进入addEntry，然后计算出了相同的哈希值对应了相同的数组位置，因为此时该位置还没数据，然后对同一个数组位置调用createEntry，两个线程会同时得到现在的头结点，然后A写入新的头结点之后，B也写入新的头结点，那B的写入操作就会覆盖A的写入操作造成A的写入操作丢失。

　　现在假如A线程和B线程同时进入addEntry，然后计算出了相同的哈希值对应了相同的数组位置，因为此时该位置还没数据，然后对同一个数组位置调用createEntry，两个线程会同时得到现在的头结点，然后A写入新的头结点之后，B也写入新的头结点，那B的写入操作就会覆盖A的写入操作造成A的写入操作丢失。

2、HashMap扩容的时候

有个扩容的操作，这个操作会新生成一个新的容量的数组，然后对原数组的所有键值对重新进行计算和写入新的数组，之后指向新生成的数组。

当多个线程同时进来，检测到总数量超过门限值的时候就会同时调用resize操作，**各自生成新的数组并rehash后赋给该map底层的数组table**，结果最终只有最后一个线程生成的新数组被赋给table变量，其他线程的均会丢失。而且当某些线程已经完成赋值而其他线程刚开始的时候，就会用已经被赋值的table作为原始数组，这样也会有问题。所以在扩容操作的时候也有可能会引起一些并发的问题。

**Get resize导致死循环**（cpu100%）

我们假设有两个线程同时需要执行resize操作，我们原来的桶数量为2，记录数为3，需要resize桶到4，原来的记录分别为：[3,A],[7,B],[5,C]，在原来的map里面，我们发现这三个entry都落到了第二个桶里面。 假设线程thread1执行到了transfer方法的Entry next = e.next这一句，然后时间片用完了，此时的e = [3,A], next = [7,B]。线程thread2被调度执行并且顺利完成了resize操作，需要注意的是，此时的[7,B]的next为[3,A]。此时线程thread1重新被调度运行，此时的thread1持有的引用是已经被thread2 resize之后的结果。线程thread1首先将[3,A]迁移到新的数组上，然后再处理[7,B]，而[7,B]被链接到了[3,A]的后面，处理完[7,B]之后，就需要处理[7,B]的next了啊，而通过thread2的resize之后，[7,B]的next变为了[3,A]，此时，[3,A]和[7,B]形成了环形链表，在get的时候，如果get的key的桶索引和[3,A]和[7,B]一样，那么就会陷入死循环。

如果取链表的头，可以保证节点之间的顺序

### 如何线程安全的使用map

- 所有public方法都加上synchronized，相当于设置一把全局锁，所有操作先获取锁
- **由于每个桶在逻辑上是相互独立的，将每个桶都加一把锁，如果两个线程各自访问不同的桶，就不需要争抢同一把锁了。这个方案的并发性比单个全局锁的性能要好，不过锁的个数太多，也有很大的开销。**
- lock stripping（锁分离） 。**第2个方法把锁的压力分散到了多个桶，理论上是可行的的，但是假设有1万个桶，就要新建1万个ReentrantLock实例，开销很大。可以将所有的桶均匀的划分为16个部分，每一部分成为一个段(Segment)，每个段上有一把锁，这样锁的数量就降低到了16个。JDK 7里的java.util.concurrent.ConcurrentHashMap就是这个思路。**
- 在JDK8里，ConcurrentHashMap的实现又有了很大变化，它在锁分离的基础上，大量利用了了CAS指令。并且底层存储有一个小优化，当链表长度太长（默认超过8）时，链表就转换为红黑树。链表太长时，增删查改的效率比较低，改为红黑树可以提高性能。JDK8里的ConcurrentHashMap有6000多行代码，JDK7才1500多行

### 解决Hash冲突的三种方法

1、拉链法

HashSet其实都是采用的拉链法来解决哈希冲突的，就是在每个位桶实现的时候，我们采用链表（jdk1.8之后采用链表+红黑树）的数据结构来去存取发生哈希冲突的输入域的关键字（也就是被哈希函数映射到同一个位桶上的关键字）。

2、开放地址法

开放地址法有个非常关键的特征，就是所有输入的元素全部存放在哈希表里，也就是说，位桶的实现是不需要任何的链表来实现的，换句话说，也就是这个哈希表的装载因子不会超过1。它的实现是在插入一个元素的时候，先通过哈希函数进行判断，若是发生哈希冲突，就以当前地址为基准，根据再寻址的方法（探查序列），去寻找下一个地址，若发生冲突再去寻找，直至找到一个为空的地址为止。所以这种方法又称为再散列法。

3、重新哈希

再散列法其实很简单，就是再使用哈希函数去散列一个输入的时候，输出是同一个位置就再次散列，直至不发生冲突位置

## **ConcurrentHashMap**

### 1、存储结构

```java
static final class HashEntry<K,V>{
  final int hash;
  final K key;
  volatile V value;
  volatile HashEntry<K,V> next;
}
```

ConcurrentHashMap 和 HashMap 实现上类似，最主要的差别是 ConcurrentHashMap 采用了**分段锁**（Segment），每个分段锁维护着几个桶（HashEntry），多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（**并发度就是 Segment 的个数**）。

segment继承自ReentrantLock，使得每一个segment充当锁的角色

```java
static final class Segment<K,V> extends ReentrantLock implements Serializable {

    private static final long serialVersionUID = 2249069246763182397L;

    static final int MAX_SCAN_RETRIES =
        Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;

    transient volatile HashEntry<K,V>[] table;

    transient int count;

    transient int modCount;

    transient int threshold;

    final float loadFactor;
}
```

默认的并发级别为 16，也就是说默认创建 16 个 Segment。

```java
static final int DEFAULT_CONCURRENCY_LEVEL = 16;
```

### HashEntry

#### 1、HashEntry

**key，hash和next域都被声明为final的，**

**value域被volatile所修饰(所以其可以确保被读线程读到最新的值)，因此HashEntry对象几乎是不可变的，这是ConcurrentHashmap读操作并不需要加锁的重要原因。**

next域被声明为final本身就意味着我们不能从hash链的中间或尾部添加或删除节点，因为这需要修改next引用值，因此所有的节点的修改只能从头部开始。**remove的时候需要全部重新new一遍**

#### 2、size操作

**每个 Segment 维护了一个 count 变量来统计该 Segment 中的键值对个数。维护一个modCount变量来统计每个segment内部的操作次数,这个值只增不减.**

**size操作就是遍历了两次所有的Segments，每次记录Segment的modCount值，然后将两次的modCount进行比较，如果modCount相同，则表示期间没有发生过写入操作，就将原先遍历的count的求和结果返回，如果modCount求和不相同，则把这个过程再重复做一次;如果modCount求和再不相同，则就需要将所有的Segment都锁住，然后一个一个遍历了,求出segment的求和并返回.**

在执行 size 操作时，需要遍历所有 Segment 然后把 count 累计起来。

ConcurrentHashMap 在执行 size 操作时先尝试不加锁，如果连续两次不加锁操作得到的结果一致，那么可以认为这个结果是正确的。

尝试次数使用 RETRIES_BEFORE_LOCK 定义，该值为 2，retries 初始值为 -1，因此尝试次数为 3。

如果尝试的次数超过 3 次，就需要对每个 Segment 加锁。

```java
/**
 * Number of unsynchronized retries in size and containsValue
 * methods before resorting to locking. This is used to avoid
 * unbounded retries if tables undergo continuous modification
 * which would make it impossible to obtain an accurate result.
 */
static final int RETRIES_BEFORE_LOCK = 2;

public int size() {
    // Try a few times to get accurate count. On failure due to
    // continuous async changes in table, resort to locking.
    final Segment<K,V>[] segments = this.segments;
    int size;
    boolean overflow; // true if size overflows 32 bits
    long sum;         // sum of modCounts
    long last = 0L;   // previous sum
    int retries = -1; // first iteration isn't retry
    try {
        for (;;) {
            // 超过尝试次数，则对每个 Segment 加锁
            if (retries++ == RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    ensureSegment(j).lock(); // force creation
            }
            sum = 0L;
            size = 0;
            overflow = false;
            for (int j = 0; j < segments.length; ++j) {
                Segment<K,V> seg = segmentAt(segments, j);
                if (seg != null) {
                    sum += seg.modCount;
                    int c = seg.count;
                    if (c < 0 || (size += c) < 0)
                        overflow = true;
                }
            }
            // 连续两次得到的结果一致，则认为这个结果是正确的
            if (sum == last)
                break;
            last = sum;
        }
    } finally {
        if (retries > RETRIES_BEFORE_LOCK) {
            for (int j = 0; j < segments.length; ++j)
                segmentAt(segments, j).unlock();
        }
    }
    return overflow ? Integer.MAX_VALUE : size;
}
```


JDK 1.7 使用分段锁机制来实现并发更新操作，核心类为 Segment，它继承自重入锁 ReentrantLock，并发度与 Segment 数量相等。

JDK 1.8 使用了 CAS 操作来支持更高的并发度，在 CAS 操作失败时使用内置锁 synchronized。

并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。



## LinkedHashMap

### 1、存储结构

继承自 HashMap，因此具有和 HashMap 一样的快速查找特性。

```java
public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
```

通过每一个entry内部增加before和after,内部维护了一个双向链表，用来维护插入顺序或者 LRU(Least Recently Used) 顺序。

```java
K key
V value
Entry<K, V> next
int hash
Entry<K, V> before
Entry<K, V> after

/**
 * The head (eldest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> head;

/**
 * The tail (youngest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> tail;
```

accessOrder 决定了顺序，默认为 false，此时维护的是插入顺序。为true的时候,将会维护最近访问顺序(get的entry放在链表末尾)

```java
final boolean accessOrder;
```

linkedHashMap最重要的是维护顺序函数，会在put、get中调用

```java
void afterNodeAccess(Node<K,V> p) { }
void afterNodeInsertion(boolean evict) { }
```

#### afterNodeAccess

当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

#### afterNodeInsertion(保证缓存空间大小合适)

在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。

evict 只有在构建 Map 的时候才为 false，在这里为 true。

```java
void afterNodeInsertion(boolean evict) { // possibly remove eldest
    LinkedHashMap.Entry<K,V> first;
    if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key), key, null, false, true);
    }
}
```

removeEldestEntry() 默认为 false，如果需要让它为 true，需要继承 LinkedHashMap 并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。

#### LRU缓存

- 设定最大缓存空间 MAX_ENTRIES 为 3；
- 使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
- 覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }
}
```

## WeakHashMap

### 1、存储结构

继承自weakReference（弱引用），被weakReference关联的对象在下一次垃圾回收时会被回收。（软引用会在内存不够的时候回收）

**WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。**



### 2、ConcurrentHashMap

tomcat中concurrentHashMap使用了weakHashMap实现缓存功能

concurrentCache采取分代缓存

- 经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
- 不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
- 当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
- 当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。

```java
public final class ConcurrentCache<K, V> {

    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            v = this.longterm.get(k);
            if (v != null)
                this.eden.put(k, v);
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            //先把eden中的全部放入longterm
            this.longterm.putAll(this.eden);
            //然后清空eden
            this.eden.clear();
        }
        //然后在清空的eden中放入要插入的k,v
        this.eden.put(k, v);
    }
}
```



## 红黑树

### 二叉查找树

若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 任意节点的左、右子树也分别为二叉查找树。 没有键值相等的节点（no duplicate nodes）。

因为一棵由n个结点随机构造的二叉查找树的高度为lgn，所以顺理成章，二叉查找树的一般操作的执行时间为O(lgn)。**但二叉查找树若退化成了一棵具有n个结点的线性链后，则这些操作最坏情况运行时间为O(n)。**

### 红黑树

但它是如何保证一棵n个结点的红黑树的高度始终保持在logn的呢？这就引出了红黑树的5个性质：

1. 每个结点要么是红的要么是黑的。
2. 根结点是黑的。
3. 每个叶结点（叶结点即指树尾端NIL指针或NULL结点）都是黑的。
4. 一个结点是红的，那么它的两个儿子都是黑的。
5. 对于任意结点而言，其到叶结点树尾端NIL指针的每条路径都包含相同数目的黑结点。

**红黑树的查找、插入、删除的时间复杂度最坏为O(log n)**”

**红黑树比AVL好的地方**

红黑树的**查询性能略微逊色于AVL树**，因为他比avl树会稍微不平衡最多一层，也就是说红黑树的查询性能只比相同内容的avl树最多多一次比较，

但是，红黑树在插**入和删除上完爆avl树**，avl树每次插入删除会进行大量的平衡度计算，而红黑树为了维持红黑性质所做的红黑变换和旋转的开销，相较于avl树为了维持平衡的开销要小得多