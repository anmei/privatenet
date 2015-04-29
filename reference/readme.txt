#/usr/bin/which: no unzip in (/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin)
考虑session集成存储，现在比较好的方案就是nosql存储，修改tomcat、jetty和jboss等session的存储方式是很容易的
根结构下的任何目录都可以作为挂载点，而您也可以将同一文件系统同时挂载于不同的挂载点上,但是不能在同一个挂载点上挂载多个不同的分区》》》
-------others----------
jndi
服务器：resin\weblogic\jety、glassfish
亲和式集群
逆向ajax技术(comet\server site push)
directory memory：无法主动需要gc、只能full gc后再顺便为其收集垃圾，NIO频繁的操作需要慎重该内存区域
32位系统,linux中每个线程只能使用4G(2^32)内存；windows中最多只能使用2G
RPC、RMI
远程通信主要考虑两个问题：通信协议；序列化协议



-------english----------
inconceivable\prohibits\stipulations\for convenience
From a performance standpoint
ineligible\More generally
violates\perspective\Virtually
Caution should be exercised
comform to
on a best-effort basis
vice-versa
in preference to
metadata\schema




---------java基础--------------



-------------------
单一、隔离、开闭、里氏替换、依赖倒置、迪米特 原则





------concurrent-------------
Java语言规范规定对任何引用变量和基本变量的赋值都是原子的，除了long和double以外。
DCL(Double Check Lock)就是一个典型



-----JVM--------------
java技术体系：java语言、javac等工具、类库、JVM
同一个类被不同的类加载器加载，也会被视为不同的类
jdk7开始“去持久代”，方法区常连池不再保存String的缓存常量，而是只保存一个堆区的引用
新生代——年老代(数组、长字符串等大对象直接进入年老代)——持久代(常量池一般是持久代)

(线程共享)类级别内存区域：堆区(存储对象等)、方法区(存储类信息，包括全局常量、静态变量、类信息等)
(线程隔离)方法级别的内存区域：程序计数器(标记当前线程执行到的指令)、jvm栈(存储方法中局部变量、返回类型等)、本地方法栈(类似于jvm栈，存储native方法相关信息)

判断一个对象是否可以被回收：计数法；参见java.lang.ref的各种引用
回收步骤：判断对象是否重写了finalize()方法或者是否被jvm调用过finalize()方法，若没有重写 或者 已经被调用过了，则直接回收；否则，放入F-Queue队列等待执行finalize()方法。一旦执行完finalize(),该对象基本就要死亡了
垃圾收集算法：标记-清楚、标记-复制、标记-整理
jvm主要：分配内存给对象；回收分配给对象的内存。且提供了不同的收集器与大量的调节参数以供调优性能



------密码学-------------
* 编码字符集
* UTF-8\GBK\GB2312
* 散列算法
* MD5、SHA-0\1\2(secure Hash Algorithm)
* 加密工具类
* RSA\DES\AES\BASE64
* 
* 目前 MD5 已在 2005 年被中国数学家王小云发现有抗强冲突性的漏洞，给定一个散列值，
* 可以在几分钟内用普通计算机找到一个碰撞，即具有相同散列值的信息原文。
* 
* SHA-0 被发现漏洞可以将寻找碰撞的难度从 2^80 次暴力破解降低到 2^39 次，
* SHA-1 被发现漏洞可以将寻找碰撞的难度从 2^80 次降低到 2^63 次，
* SHA-2 系列函数还未发现漏洞。
* 
* 抗强冲突性，即给定一个散列值，无法找到另一个具有同样散列值的信息原文。
* 抗弱冲突性，即无法找到两个具有同样散列值的信息原文。

SSL/TSL
https:是一套安全措施，URL表明它使用了HTTPS，但HTTPS存在不同于HTTP的默认端口(443端口)及一个加密/身份验证层（在HTTP与TCP之间），这个系统的最初研发由网景公司进行
通过安装X.509数字证书(单向验证/双向验证)实现HTTPS协议传输，从而实现加密同时被认证
客户端是否能够信任这个站点的证书，首先取决于客户端程序是否导入了证书颁发者的根证书。
自己签名的证书只是用户产生的证书，没有正式在大家所熟知的认证权威那里注册过，因此不能确保它的真实性。但却能保证数据传输的安全性。
Keytool将密钥（key）和证书（certificates）存在一个称为keystore的文件中 
 一个证书是一个实体的数字签名,还包含这个实体的公钥.


-------lucene------------
由于数据库索引不是为全文索引设计的，因此，使用like "%keyword%"时，数据库索引是不起作用的，在使用like查询时，搜索过程又变成类似于一页页翻书的遍历过程了，
Lucene是一个高性能的java全文检索工具包，它使用的是倒排文件索引结构
我们注意到关键字是按字符顺序排列的（lucene没有使用B树结构），因此lucene可以用二元搜索算法快速定位关键词
对于检索系统来说核心是一个排序问题。
大部分的搜索（数据库）引擎都是用B树结构来维护索引，索引的更新会导致大量的IO操作，Lucene在实现中，对此稍微有所改进：不是维护一个索引文件，而是在扩展索引的时候不断创建新的索引文件，然后定期的把这些新的小索引文件合并到原先的大索引中
另外一个解决的办法是采用自动切分算法：将单词按照2元语法(bigram)方式切分出来，比如：
　　　　"北京天安门" ==> "北京 京天 天安 安门"。
这样，在查询的时候，无论是查询"北京" 还是查询"天安门"，将查询词组按同样的规则进行切分："北京"，"天安安门"，多个关键词之间按与"and"的关系组合，同样能够正确地映射到相应的索引中。
目前比较大的搜索引擎的语言分析算法一般是基于以上2个机制的结合(自动切分、词表切分)。
Lucene中的一些比较复杂的词法分析是用JavaCC生成的（JavaCC：JavaCompilerCompiler，纯Java的词法分析生成器）
分词：paoding、IKAnalyzer
非结构化数据又一种叫法叫全文数据。
lucene既保存了正向信息也保存了反向信息
没有被存储的域是不会作为搜索结果展现的
所有的索引存在多个segment
大文本一般不要store，这样会消耗大量存储空间
Luke：用于检查索引细节
线程安全控制：多个线程可以共享indexreader、indexwriter，不仅是线程安全而且是线程友好的，单不能同时打开多个indexwriter
norm(加权基准)：记录域的加权等附属信息
日期、数字域的索引
域截取


>>创建索引
index——document(如一个web页、doc文档等)——field(索引选项、存储选项、域向量选项、域排序选项、多值域)——term(主题)
表——记录——字段
索引库——document——field
一个索引(index)就相当于是一张表，索引中有许多document(就是对非结构化数据结构化，如web网页等)好比是表记录，document由众多field组成
索引包含多个段，每个段都是独立的索引，它是所有文档索引的一个子集。每当增加、删除索引时都会增加段,同时会自动周期性的合并段
每个文档都可以有多个域，每个域都可以有自己的选项
新增索引，调用commit()或close()——向索引提交更改，使得可以读到最新更改
删除索引(deleteDocument)，不会立即删除，只是多了一个删除的标记位,optimize()优化时才真正删除对应的存储块或者合并相关索引段
更新索引：updateDocument(),其实就是先删后增
要看到最新索引，前提是写方已提交，同时必须重新打开Reader，可通过以下方法：可以通过reopen()方法，获取最新索引，或者重建Reader，或者从IndexWriter中获取近实时Reader
IndexWriter与IndexReader执行增删改的区别，最好使用IndexWriter完成所有操作
提交(关闭IndexWriter也会自动提交)是为了让更新对新的reader可见，将缓存刷新到磁盘；合并优化是为了物理地合并、删除索引段，提高搜索速度


关键类
Field<Store\Index>
Document
MergePolicy

索引优化
通过optimize()合并索引，在这优化期间会消耗大约平常的3倍内存，合并完后执行commit()提交，内存恢复。所以不要频繁优化
MergePolicy、MergeScheduler

索引过程对文档和域进行加权
setBoost()——设置域或文档的加权值
Norms(加权基准)，可通过Field.index.NO_NORMS关闭
Similarity



>>搜索
新建IndexReader的代价是很高的，每次都要重新加载索引结构，所以尽量用单例共用之.
要看到最新索引，前提是写方已提交，同时必须重新打开Reader，可通过以下方法：可以通过reopen()方法，获取最新索引，或者重建Reader，或者从IndexWriter中获取近实时Reader
通过文档号访问某个文档被存储的域值
域缓存：只能用于包含单个域的项，即索引时必须是:not_analyzed；可以将所有文档的指定域值以数组形式加载到缓冲
过滤器、高亮显示、近实时搜索

关键类
analyzer——去噪、分词等
QueryParser——解析用户输入的搜索表达式，使用该类解析时会出现大量奇怪的情况
Query——lucene搜索引擎能识别的语句
IndexSearch
TopDocs<ScoreDocs>——存储索引结果
BooleanQuery合并子查询

对搜索结果排序(Sort、SortField),使用该对象会有额外的开销
1、按相关程度排序(Sort.RELEVANCE)即lucene默认的评分方式
2、通过文档索引排序，即只根据文档id升序排序
3、根据单个域排序，该域值必须是单个索引单元，不能被分词
4、针对多个域进行排序
在一个域上通过多个项查询(MultiPhraseQuery)
针对多个域的一次性查询
1、MultiFieldQueryParser
2、DisjunctionMaxQuery
项向量：搜索相似文档或者自动归类文档
对搜索结果分页

		/**创建索引*/
//		BeanFactory fac = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
//		ILuceneService luceServ = (ILuceneService)fac.getBean("luceneService");
//				
//		List<NewsDetails> newslist = luceServ.FindAllNewsDetail("dgnewsdetail");
//		String[] analyzedFiled = {"content","title"};
//		String[] excludField = new String[]{"tableName"};
//		for(NewsDetails news:newslist){
//			IndexUtils.createIndex(news, excludField, analyzedFiled, "dgnews");
//		}
//		System.out.println("ok");
		
//		IndexUtils.deleteAll();
//		IndexUtils.mergeIndex();

		
		
		// 搜索
		/**内置查询类*/
		Query query = null;
		// 关键字搜索
//		query = new TermQuery(new Term("date","2015-03-21 10:49:00"));
		query = new TermQuery(new Term("content","天气"));
		// 文本范围搜索
//		query = new TermRangeQuery("date", "2015-03-21 10:49:00", "2015-03-22 10:49:00", true, true);
		// 数字类型范围搜索
//		query = NumericRangeQuery.newIntRange("date", 20150321, 20150322, true,true);
//		query = new PrefixQuery(new Term("content","东莞"));
		//组合查询
		/*BooleanQuery bquery = new BooleanQuery();
		bquery.add(query, BooleanClause.Occur.MUST);*/
		//短语搜索(某个距离范围内的项对应的文档)
//		PhraseQuery
		//通配符搜索
//		WildCardQuery
		//模糊查询
//		query = new FuzzyQuery(new Term("date","2016-03-21 10:49:00"));
			
		////高级搜索功能////
		//对搜索结国排序
		//MultiPhraseQuery
		//DisjunctionMaxQuery
			
		/**解析用户输入语句*/
//		QueryParser queryParser = new QueryParser(Version.LUCENE_36, "content", IndexUtils.getAnalyzer());
//		query= queryParser.parse("天气");
		//MultiFieldQueryParser
			
		/** 执行搜索*/
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		TopDocs hits = searcher.search(query, 3);
		/*TopDocs hits = searcher.search(query, 1, new Sort());*///指定排序
		ScoreDoc[] scoreDocs = hits.scoreDocs;
		for(ScoreDoc sc:scoreDocs){
			Document dc = searcher.doc(sc.doc);
			System.out.println(dc.get("content")+" "+dc.get("date"));
		}


--------缓存----------------
》》通过spring在服务端缓存
Spring仅仅是提供了对缓存的支持，但它并没有任何的缓存功能的实现，spring使用的是第三方的缓存框架来实现缓存的功能。
spring支持的缓存机制，是方法级的缓存，而不关注底层是否使用了数据库以及通过什么方式访问的数据库；因此这种缓存不止可以放到DAO层，也可以放置到Service层，甚至可以对各种代码数据进行缓存。
@CacheEvict、@Cacheable
由于Spring的缓存机制是基于Spring的AOP，那么在Spring Cache中应该存在着一个Advice
ehcache：页面缓存、对象缓存

》》AOP
CGLIB:通过继承，无法代理final、无默认构造方法的类
JDK：通过实现接口，无法代理无接口的类
首选如<aop:config>，而不是自己定义如×××AutoProxyCreator，而且使用<aop:config>方式能更好的描述切面，且这样一个容器永远只有一个AutoProxyCreator。
    AspectJAwareAdvisorAutoProxyCreator会创建一个代理（因为<aop:config proxy-target-class="true">），这个代理是CGLIB代理；
    DefaultAdvisorAutoProxyCreator会对代理对象再创建代理，但是因为没有告诉它代理类，所以默认代理接口，即代理是JDK动态代理；
观察类是$Proxy…… 还是 ……$$EnhancerByCGLIB$$……，来判断是JDK动态代理还是CGLIB代理。
通过观察$Proxy的实现中是否包含org.springframework.cglib.proxy.Factory来判断是否是二次代理。
<aop:aspectj-autoproxy expose-proxy="true"/> 实现@AspectJ注解的，默认使用AnnotationAwareAspectJAutoProxyCreator进行AOP代理，它是BeanPostProcessor的子类，在容器启动时Bean初始化开始和结束时调用进行AOP代理的创建，因此只对当容器启动时有效，使用时注意此处。
<context:component-scan>——使用务必注意，使用不好，最典型的错误就是：事务不起作用

》》IOC
表现层配置文件，只应加装表现层Bean，否则可能引起问题。

















