#### XML的简介

* eXtensible Markup Language：可扩展标记型语言

  1. 标记型语言：html是标记型语言，也是使用标签来操作。

  2. 可扩展：

  ​	- html里面的标签是固定，每个标签都有特定的含义 ```<h1> <hr/><br>```

  ​	- xml里标签可以自己定义,可以写中文的标签 <person></person、<猫></猫>

* xml用途
  1. html是用于显示数据，xml也可以显示数据（不是主要功能）
  2. xml主要功能，为了存储数据

* xml是w3c组织发布的技术

* xml有两个版本 1.0  1.1，目前使用1.0版本，1.1版本不向下兼容。



#### XML的应用

* 不同的系统之间传输数据

  <img src="https://ws4.sinaimg.cn/large/006tKfTcgy1g0ih3eacegj31fg0nytib.jpg" width=50% />

* 用来表示生活中有关系的数据

* 经常用在文件配置：

  1. 比如现在连接数据库 肯定知道数据库的用户名和密码，数据名称。

  2. 如果修改数据库的信息，不需要修改源代码，只要修改配置文件就可以了。



#### xml的语法

1. xml的文档声明：

   创建一个文件 后缀名是 .xml；

   如果写xml，第一步 必须要有 一个文档声明（写了文档声明之后，表示写xml文件的内容）；

   ```<?xml version="1.0" encoding="gbk"?>```

    文档声明必须写在 第一行第一列。

   属性：

    - version：xml的版本 1.0(使用) 1.1
    - encoding：xml编码 gbk  utf-8  iso8859-1(不包含中文)
    - standalone：是否需要依赖其他文件 yes/no

2. xml的元素（标签）定义

   标签定义有开始必须要有结束：```<person></person>```

   标签没有内容，可以在标签内结束 ：```<aa/>```

   标签可以嵌套，必须要合理嵌套：合理嵌套 ```<aa><bb></bb></aa>```

   一个xml中，只能有一个根标签，其他标签都是这个标签下面的标签。

   在xml中把空格和换行都当成内容来解析。

   xml中标签的名称规则：

    - xml标签可以是中文；
    - xml代码区分大小写；
    - xml的标签不能以数字和下划线(_)开头；
    - xml的标签不能以xml、XML、Xml等开头；
    - xml的标签不能包含空格和冒号；

3. xml中属性的定义

   例子：```<person id1="aaa" id2="bbb"></person>```

   属性定义的要求：

   - 一个标签上可以有多个属性；
   - 属性名称不能相同；
   - 属性名称和属性值之间使用= ，属性值使用引号包起来 （可以是单引号，也可以是双引号 ）；
   - xml属性的名称规范和元素（标签）的名称规范一致；

4. xml中的注释

   写法 ```<!-- xml的注释 -->```

   注释也不能放到第一行，第一行第一列必须放文档声明

5. xml中的特殊字符

   - 如果想要在xml中现在 a<b ,不能正常显示，因为把<当做标签
   - 如果就想要显示，需要对特殊字符 < 进行转义，<变为```&lt;```，>变为```&gt;```；

6. CDATA区：把特殊字符，当做文本内容，而不是标签

   可以解决多个字符都需要转义的操作，把这些内容放到CDATA区里面，不需要转义了。

   写法：```<![CDATA[  内容  ]]>```

7. PI指令

   1. 可以在xml中设置样式；
   2. 写法： ```<?xml-stylesheet type="text/css" href="css的路径"?>```
   3. 设置样式，只能对英文标签名称起作用，对于中文的标签名称不起作用的；

8. xml的约束

   * 为什么需要约束？
   * 比如现在定义一个person的xml文件，只想要这个文件里面保存人的信息，比如name age等，但是如果在xml文件中写了一个标签<猫>，发现可以正常显示，因为符合语法规范。但是猫肯定不是人的信息，xml的标签是自定义的，需要技术来规定xml中只能出现的元素，这个时候需要约束。
   * xml的约束的技术 ： dtd约束 和 schema约束



#### dtd语法

1. 创建一个文件 后缀名 .dtd

2. 步骤：

   1. 看xml中有多少个元素 ，有几个元素，在dtd文件中写几个 <!ELEMENT>
   2. 判断元素是简单元素还是复杂元素；
      - 复杂元素：有子元素的元素``` <!ELEMENT 元素名称 (子元素)>```
      - 简单元素：没有子元素 ```<!ELEMENT 元素名称 (#PCDATA)>```

   3. 在xml文件中引入dtd文件 ```<!DOCTYPE 根元素名称 SYSTEM "dtd文件的路径">```

3. 三种引入dtd的方式：

   * 引入外部的dtd文件：```<!DOCTYPE 根元素名称 SYSTEM "dtd路径">```

   * 使用内部的dtd文件：

     ​	<img src="https://ws3.sinaimg.cn/large/006tKfTcgy1g0iiw5dlrcj30ew03qdgc.jpg" width=30% />

   * 使用外部的dtd文件（网络上的dtd文件）

     <img src="https://ws3.sinaimg.cn/large/006tKfTcgy1g0iixlc0kbj310a03cgmp.jpg" width=60%/>

4. 使用dtd定义元素

   1. 语法： ```<!ELEMENT 元素名 约束>```
   2. 简单元素：没有子元素的元素 ```<!ELEMENT name (#PCDATA)>```
      * (#PCDATA): 约束name是字符串类型
      * EMPTY : 元素为空（没有内容）``` <sex></sex>```
      * ANY:任意
   3. 复杂元素：```<!ELEMENT person (name,age,sex,school)>```
      * 表示子元素出现的次数（+ : 表示一次或者多次；? ：表示零次或者一次；* ：表示零次或者多次）
      * 子元素直接使用逗号进行隔开，表示元素出现的顺序
      * 子元素直接使用|隔开，表示元素只能出现其中的任意一个
      * 使用括号来给元素进行分组。

5.  使用dtd定义属性

   1. 语法：

      <img src="https://ws3.sinaimg.cn/large/006tKfTcgy1g0imd1e6vbj30da03gwen.jpg" width=30% />

   2. 属性值的类型：

      <img src="https://ws1.sinaimg.cn/large/006tKfTcgy1g0ime78q2sj30lc0c6abp.jpg" width=40%/>

   3. 属性的约束：

      <img src="https://ws2.sinaimg.cn/large/006tKfTcgy1g0imf4y7y1j30f40butab.jpg" width=35% />

6. 使用dtd定义实体

   * 语法：``` <!ENTITY 实体名称 "实体的值">```：<!ENTITY TEST "HAHAHEHE">

   * 使用实体 &实体名称;  比如 &TEST;

   * 注意：
       * 定义实体需要写在内部dtd里面；
       * 如果写在外部的dtd里面，有某些浏览器下，内容得不到；

#### 

#### 解析XML文档

* xml是标记型文档；

* js使用dom解析标记型文档：
  * 根据html的层级结构，在内存中分配一个树形结构，把html的标签，属性和文本都封装成对象。
  * document对象、element对象、属性对象、文本对象、Node节点对象
* xml的解析方式（技术）：dom 和 sax
  * dom解析：根据xml的层级结构在内存中分配一个树形结构，把xml的标签，属性和文本都封装成对象；缺点：如果文件过大，造成内存溢出；优点：很方便实现增删改操作。
  * sax解析：采用事件驱动，边读边解析；从上到下，一行一行的解析，解析到某一个对象，返回对象名称；缺点：不能实现增删改操作；优点：如果文件过大，不会造成内存溢出，方便实现查询操作。
  * 不同的公司和组织提供了 针对dom和sax方式的解析器，通过api方式提供：
    * sun公司提供了针对dom和sax解析器：jaxp
    * dom4j组织，针对dom和sax解析器：dom4j（实际开发）
    * jdom组织，针对dom和sax解析器：jdom