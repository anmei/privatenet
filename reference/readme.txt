#/usr/bin/which: no unzip in (/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin)
考虑session集成存储，现在比较好的方案就是nosql存储，修改tomcat、jetty和jboss等session的存储方式是很容易的
根结构下的任何目录都可以作为挂载点，而您也可以将同一文件系统同时挂载于不同的挂载点上,但是不能在同一个挂载点上挂载多个不同的分区》》》
-------others----------
压测：LoadRunner\jmeter
java ee
对于消息的传递有两种类型，一种是点对点的，即一个生产者和一个消费者一一对应；另一种是发布/订阅模式，即一个生产者产生消息并进行发送后，可以由多个消费者进行接收。
在ActiveMQ中实现了两种类型的Destination，一个是点对点的ActiveMQQueue，另一个就是支持订阅/发布模式的ActiveMQTopic。
按照软件工程思想，软件测试可以分为单元测试、集成测试、功能测试、系统测试等。功能测试和系统测试一般来说是测试人员的职责，但单元测试和集成测试则必须由开发人员保证。 

spring:
jdbc——JdbcDaoSupport\JdbcTemplate
事务——提供了多种事务管理器，如DataSourceTransactionManager
资源配置文件——PropertyPlaceholderConfigure
AOP——（切点、增强）——>切面;静态代理、动态代理（jdk、cglib）
IOC——bean的注入、管理（通过xml配置方式注入、注解注入）
spring-mvc——前端控制器、视图解析器(内容协商……)、页面控制器
外部缓存——ehcache……

多线程
一、原子性、可见性等基础
实际上final的语义和volatile是有冲突的，这两个关键字不能同时存在。
尽管volatile变量的特性不错，但是volatile并不能保证线程安全的，也就是说volatile字段的操作不是原子性的，volatile变量只能保证可见性（一个线程修改后其它线程能够理解看到此变化后的结果），要想保证原子性，目前为止只能加锁！
指令重排序——>happens-before原则
CAS——乐观锁，有可能会出现ABA问题。AtomicInteger一系列原子操作类的实现就是通过CAS实现的
synchronize——悲观锁
标准的JAVA API里面是无法挂起（阻塞）一个线程，然后在将来某个时刻再唤醒它的。JDK 1.0的API里面有Thread.suspend和Thread.resume，并且一直延续了下来。但是这些都是过时的API，而且也是不推荐的做法。
这是因为通常情况下挂起的线程重新开始与它真正开始运行，二者之间会产生严重的延时。因此非公平锁就可以利用这段时间完成操作。这是非公平锁在某些时候比公平锁性能要好的原因之一。
java.util.concurrent.locks.AbstractQueuedSynchronizer （AQS)是Lock的基础
首先对于ReentrantLock而言，不管是公平锁还是非公平锁，都是独占锁，也就是说同时能够有一个线程持有锁。
这是因为ReentrantLock是可重入锁，同一个线程每持有一次就+1。 
AQS支持共享锁（例如读写锁）
也即是说公平锁和非公平锁只是在入AQS的CLH队列之前有所差别，一旦进入了队列，所有线程都是按照队列中先来后到的顺序请求锁。
也就是说await()操作实际上就是释放锁，然后挂起线程，一旦条件满足就被唤醒，再次获取锁！
AQS(AbstractQueuedSynchronizer)是锁机制实现的基础，大概就是通过队列与状态变量实现锁机制，队列中存储等待锁的线程节点
所谓共享锁是说所有共享锁的线程共享同一个资源，一旦任意一个线程拿到共享资源，那么所有线程就都拥有的同一份资源。

二、并发工具
CountDownLatch——基于共享锁实现
其实FutureTask就可以看成一个闭锁



三、并发容器、线程池



------nginx----------------
进入nginx根目录
start nginx
nginx -s stop





-------english----------
dispose
boundary
delimiter
specific primitive
arbitrary
intimately




---------java基础--------------
System.arraycopy()
Arrays.copyOf()
String result = new String(chars, i1, i2 - i1);





-------------------
单一、隔离、开闭、里氏替换、依赖倒置、迪米特 原则

这种通过方法、参数的名称就能够得到函数意义的写法是非常值得称赞的


------concurrent-------------




--------JVM-----------------
System.gc()只是建议jvm执行GC，但是到底GC执行与否是由jvm决定的
当新建一个对象时，会置位该对象的一个内部标识finalizable，当某一点GC检查到该对象不可达时，就把该对象放入finalize queue(F queue)，GC会在对象销毁前执行finalize方法并且清空该对象的finalizable标识。
ReferenceQueue中存储的是执行GC后等待被finalized的对象，最终有可能会重生
后来该static对象也被置null,然后GC，可以从结果看到finalize方法只运行了1次。为什么呢，因为第一次finalize运行过后，该对象的finalizable置为false了，所以该对象即使以后被gc运行，也不会执行finalize方法了。
简而言之，一个简单的对象生命周期为，Unfinalized Finalizable Finalized Reclaimed。
我记着java编程语言书中说是一切可以finalize的对象的finalize方法的执行顺序是不确定的





------密码学-------------


---------------网络通信-------------
应用层协议(数据格式、数据格式与流之间转换、接收流)如：RMI、XML-RPC、Binary-RPC、SOAP
传输协议

在底层层面去看，网络通信需要做的就是将流从一台计算机传输到另外一台计算机，基于传输协议和网络IO来实现，其中传输协议比较出名的有http、tcp、udp等等，http、tcp、udp都是在基于Socket概念上为某类应用场景而扩展出的传输协议，网络IO，主要有bio、nio、aio三种方式，所有的分布式应用通讯都基于这个原理而实现
应用级协议
远程服务通讯，需要达到的目标是在一台计算机发起请求，另外一台机器在接收到请求后进行相应的处理并将结果返回给请求端，这其中又会有诸如one way request、同步请求、异步请求等等请求方式，按照网络通信原理，需要实现这个需要做的就是将请求转换成流，通过传输协议传输至远端，远端计算机在接收到请求的流后进行处理，处理完毕后将结果转化为流，并通过传输协议返回给调用端。
原理是这样的，但为了应用的方便，业界推出了很多基于此原理之上的应用级的协议，使得大家可以不用去直接操作这么底层的东西，通常应用级的远程通信协议会提供：
1、为了避免直接做流操作这么麻烦，提供一种更加易用或贴合语言的标准传输格式；
2、网络通信机制的实现，就是替你完成了将传输格式转化为流，通过某种传输协议传输至远端计算机，远端计算机在接收到流后转化为传输格式，并进行存储或以某种方式通知远端计算机。
所以在学习应用级的远程通信协议时，我们可以带着这几个问题进行学习：
1、传输的标准格式是什么？
2、怎么样将请求转化为传输的流？
3、怎么接收和处理流？
4、传输协议是？
不过应用级的远程通信协议并不会在传输协议上做什么多大的改进，主要是在流操作方面，让应用层生成流和处理流的这个过程更加的贴合所使用的语言或标准，至于传输协议则通常都是可选的，在java领域中知名的有：RMI、XML-RPC、Binary-RPC、SOAP、CORBA、JMS，来具体的看看这些远程通信的应用级协议：
目前java领域可用于实现远程通讯的框架或library，知名的有：JBoss-Remoting、Spring-Remoting、Hessian、Burlap、XFire(Axis)、ActiveMQ、Mina、Mule、EJB3等等，来对每种做个简单的介绍和评价，其实呢，要做分布式服务框架，这些东西都是要有非常深刻的了解的，因为分布式服务框架其实是包含了解决分布式领域以及应用层面领域两方面问题的。
NIO在并发量增长时对比BIO而言会有明显的性能提升，而java性能的提升，与其NIO这块与OS的紧密结合是有不小的关系的


------------IO(阻塞)----------------------
(1)These are good reasons not to work with filenames as Strings.Using java.io.File instead handles many of the above cases nicely. 
(2)Make sure you're properly buffering streams when reading or writing streams. especially when working with files. Just decorate your FileInputStream with a BufferedInputStream
If you use our CopyUtils or IOUtils you don't need to additionally buffer the streams you use as the code in there already buffers the copy process.

The default buffer size of 4K has been shown to be efficient in tests.
An OutputStreamWriter is a bridge from character streams to byte streams. 

/*
 * java.io
 * 
 * InputStream\OutputStream（操作字节数组）\Reader\Writer（操作字符数组） —— 好比是连接器，使得用户能够方便地与File等交互.
 * 连接器模型：
 * |----------------------------|
 * |			 |				|
 * |		File | Writer		|
 * |			 |				|
 * |OutputStream | Writer		|
 * |			 |				|
 * |	Buffered | Writer		|
 * |			 |				|
 * |		File | OutputSteram	|
 * |			 |				|
 * |	Buffered | OutputStream	|
 * |			 |				|
 * | InputStream | Reader		|
 * |			 |				|
 * |	 ……		 |	……			|
 * |----------------------------|
 * 
 * 数组扩容、装饰器模式
 * 不论是字节还是字符类型，底层最终操作的都是字节
 * 
 * 字节：
 * FileInputStream\FileOutputStream\ObjectOutputStream\ObjectInputStream\ByteArrayInputStream\ByteArrayOutputStream
 * 字符：
 * FileReader\FileWriter\CharArrayReader\CharArrayWriter\InputStreamReader\OutputStreamWriter\
 * 缓冲：
 * BufferedInputStream\BufferedOutputStream\BufferedReader\BufferedWriter\
 * 
 * commons.io.IOUtils中的思想：先转换为CharArrayWriter\ByteArrayOutputStream\StringWriter，然后再进一步操作。其中基本涵盖了所有可能情况
 * 

------------NIO(非阻塞)----------------
>>Buffer
Invariants: mark <= position <= limit <= capacity
get\put方法
使用buffers中的复杂的方面,譬如buffer的allocation,wrapping,slicing
read-only的buffer,可以保护数据不被修改;direct的buffer,可以直接和底层的OS进行映射（A direct byte buffer whose content is a memory-mapped region of a file.）；
Memory-mapped file I/O 是一种比I/O stream和 channel-based-I/O还要快得多的处理读写的方式;Memory-mapped file I/O的实现,是通过把file中的数据处理地看起来好像memory array中的内容的方式;实际上,Memory-mapped file并没有把整个文件一次性的读进memory，它只把实际读写的部分map到内存中

>>SelectableChannel
>>Selector
>>Provider
All three sets are empty in a newly-created selector.

|-----------|							|---------------|
|(Server	|							| (client		|				
| Selector)	|							|	Selector	|
|			|							|				|
| Channel1	|							|	Channel1	|
| Channel2	|<------communicate------->	|	Channel2	|
| Channel3	|							|	Channel3	|
| Channel4	|							|	Channel4	|
| Channel5	|							|	Channel5	|
| Channel6	|							|	Channel6	|
|	……		|							|		……		|
|-----------|							|---------------|



-------------NIO2.0-----------------


------------Netty----------------------

A {@link ChannelEvent} is handled by a series of {@link ChannelHandler}s in a {@link ChannelPipeline}.

* When your server receives a message from a client, the event associated with
* the received message is an upstream event.  When your server sends a message
* or reply to the client, the event associated with the write request is a
* downstream event.  The same rule applies for the client side. 
 
* Upstream events are
* often the result of inbound operations such as {@link InputStream#read(byte[])},
* and downstream events are the request for outbound operations such as
* {@link OutputStream#write(byte[])}, {@link Socket#connect(SocketAddress)},
* and {@link Socket#close()}.

It is because a {@link Channel} is always open when it is created by a {@link ChannelFactory}

a connectionless transport such as UDP/IP which does not accept an incoming connection but receives messages by itself without creating a child channel

* A parent channel is a channel which is supposed to accept incoming
* connections.  It is created by this bootstrap's {@link ChannelFactory} via
* {@link #bind()} and {@link #bind(SocketAddress)}.
* <p>
* Once successfully bound, the parent channel starts to accept incoming
* connections, and the accepted connections become the children of the
* parent channel.

{@link ServerBootstrap} is just a helper class.  It neither allocates nor manages any resources.  What manages the resources is the
{@link ChannelFactory} implementation you specified in the constructor of {@link ServerBootstrap}.

* A {@link ChannelFuture} is either <em>uncompleted</em> or <em>completed</em>.
* When an I/O operation begins, a new future object is created.  The new future
* is uncompleted initially - it is neither succeeded, failed, nor cancelled
* because the I/O operation is not finished yet.  If the I/O operation is
* finished either successfully, with failure, or by cancellation, the future is
* marked as completed with more specific information, such as the cause of the
* failure.  Please note that even failure and cancellation belong to the
* completed state.


----------
(BootStrap(Channel(ChannelPipeline(ChannelHandler))))

然而，一个适合普通目的的协议或其实现并不具备其规模上的扩展性。
Netty 是一个吸收了多种协议的实现经验，这些协议包括FTP,SMPT,HTTP，各种二进制，文本协议，并经过相当精心设计的项目
一个ChannelFuture 对象代表了一个尚未发生的I/O操作。这意味着，任何已请求的操作都可能是没有被立即执行的，因为在Netty内部所有的操作都是异步的
对于例如TCP/IP这种基于流的传输协议实现，接收到的数据会被存储在socket的接受缓冲区内。不幸的是，这种基于流的传输缓冲区并不是一个包队列，而是一个字节队列。
在你的ChannelHandler实现中使用POJO的优势是很明显的；从你的ChannelHandler实现中分离从ChannelBuffer获取数据的代码，将有助于提高你的ChannelHandler实现的可维护性和可重用性。在时间协议服务的客户/服务端代码中，直接使用ChannelBuffer读取一个32位的整数并不是一个主要的问题。然而，你会发现，当你试图实现一个真实的协议的时候，这种代码上的分离是很有必要的。
因为这个encoder是无状态的，所以其使用的ChannelPipelineCoverage注解值是“all”。实际上，大多数encoder实现都是无状态的。

一个典型的网络应用的关闭过程由以下三步组成：
    关闭负责接收所有请求的server socket。
    关闭所有客户端socket或服务端为响应某个请求而创建的socket。
    释放ChannelFactory使用的所有资源。

ChannelGroup是Java 集合 API的一个特有扩展，ChannelGroup内部持有所有打开状态的Channel通道。
不管这个Channel对象属于服务端，客户端，还是为响应某一个请求创建，任何一种类型的Channel对象都会被加入ChannelGroup。因此，你尽可在关闭服务时关闭所有的Channel对象
传统的Java I/O API在应对不同的传输协议时需要使用不同的类型和方法，这种模式上的不匹配使得在更换一个网络应用的传输协议时变得繁杂和困难。由于（Java I/O API）缺乏协议间的移植性，当你试图在不修改网络传输层的前提下增加多种协议的支持，这时便会产生问题
让这种情况变得更糟的是，Java新的I/O（NIO）API与原有的阻塞式的I/O（OIO）API并不兼容，NIO.2(AIO)也是如此。由于所有的API无论是在其设计上还是性能上的特性都与彼此不同

Netty有一个叫做Channel的统一的异步I/O编程接口，这个编程接口抽象了所有点对点的通信操作。也就是说，如果你的应用是基于Netty的某一种传输实现，那么同样的，你的应用也可以运行在Netty的另一种传输实现上。Netty提供了几种拥有相同编程接口的基本传输实现：
    NIO-based TCP/IP transport (See org.jboss.netty.channel.socket.nio),
    OIO-based TCP/IP transport (See org.jboss.netty.channel.socket.oio),
    OIO-based UDP/IP transport, and
    Local transport (See org.jboss.netty.channel.local).
切换不同的传输实现通常只需对代码进行几行的修改调整，例如选择一个不同的ChannelFactory实现。

一个定义良好并具有扩展能力的事件模型是事件驱动开发的必要条件。Netty具有定义良好的I/O事件模型。
在一个ChannelPipeline内部一个ChannelEvent被一组ChannelHandler处理。这个管道是拦截过滤器 模式的一种高级形式的实现，因此对于一个事件如何被处理以及管道内部处理器间的交互过程，你都将拥有绝对的控制力。
从业务逻辑代码中分离协议处理部分总是一个很不错的想法。
因此，一个好的网络应用框架应该提供一种可扩展，可重用，可单元测试并且是多层的codec框架，为用户提供易维护的codec代码。
HTTP无疑是互联网上最受欢迎的协议，并且已经有了一些例如Servlet容器这样的HTTP实现，netty实现http高度可扩展
TimeClientHandler共享的问题
接收到不一致的字节流的问题

--------------------------
>>ServerBootstrap\ClientBootstrap\ConnectionlessBootstrap


>>ChannelBuffer:ChannelBuffers\
Netty使用新的buffer类型ChannelBuffer，ChannelBuffer被设计为一个可从底层解决ByteBuffer问题，并可满足日常网络应用开发需要的缓冲类型。这些很酷的特性包括：
    如果需要，允许使用自定义的缓冲类型。
    复合缓冲类型中内置的透明的零拷贝实现。
    开箱即用的动态缓冲类型，具有像StringBuffer一样的动态缓冲能力。
    不再需要调用的flip()方法。
    正常情况下具有比ByteBuffer更快的响应速度。



>>Channel：Channels\ChannelEvent\ChannelPipeline\ChannelHandler\ChannelHandlerContext\ChannelSink\ChannelFactory\ChannelFuture\ChannelConfig
All I/O operations in Netty are asynchronous.
you will be returned with a {@link ChannelFuture} instance which will notify you when the requested I/O operation has succeeded, failed, or canceled.



>>handler:Codec\


=======


