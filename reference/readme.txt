#/usr/bin/which: no unzip in (/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin)
考虑session集成存储，现在比较好的方案就是nosql存储，修改tomcat、jetty和jboss等session的存储方式是很容易的
根结构下的任何目录都可以作为挂载点，而您也可以将同一文件系统同时挂载于不同的挂载点上,但是不能在同一个挂载点上挂载多个不同的分区》》》
-------others----------



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



------concurrent-------------




-----JVM--------------
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


------------Netty----------------------

java NIO采用了双向通道（channel）进行数据传输，而不是单向的流（stream），在通道上可以注册我们感兴趣的事件
等待读写（阻塞\非阻塞）——读写期间（同步\非同步）

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




------
(BootStrap(Channel(ChannelPipeline(ChannelHandler))))

>>ChannelBuffer:

>>Channel：ChannelEvent\ChannelPipeline\ChannelHandler\ChannelHandlerContext\ChannelSink\ChannelFactory

All I/O operations in Netty are asynchronous.
you will be returned with a {@link ChannelFuture} instance which will notify you when the requested I/O operation has succeeded, failed, or canceled.

 
>>handler:Codec\




