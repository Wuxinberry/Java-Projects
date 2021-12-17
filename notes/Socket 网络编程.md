# Socket 网络编程

collected by wxb

MOOC CN Socket 网络编程快速入门课程笔记



## Ch 1 Intro

### 概念：网络编程

#### 网络

* 网络是信息传输、接收、共享的虚拟平台
* 无数个局域网/城域网/广域网组合起来，形成互联网

#### 网络编程

* 对信息的发送到接收

* 具体：通过操作响应API调度计算机硬件资源，利用传输管道(网线)进行数据交换的过程

* 需要对 OSI 网络模型 有概念

    * 基础层：物理层、数据链路层、网络层
    * 传输层：TCP-UDP 协议层、Socket
    * 高级曾：会话层、表示层、应用层

    ![image-20211215233733367](https://s2.loli.net/2021/12/15/sY2ohdD7BwNUQjG.png)

    ![image-20211215233841687](https://s2.loli.net/2021/12/15/mN3AHCbqDwpy1ME.png)

#### Socket 与 TCP、UDP

Socket

* 作用：在网络中用于唯一标识两个端点之间的连接
* 端点：包括 IP + Port   `IP Address  + Port number  = Socket`
* 四要素：客户端地址、客户端端口、服务器地址、服务器端口

TCP

* 面向连接的通信协议 => 只能用于端到端的通讯

* 通过三次握手建立连接，通讯完成时要拆除连接

* 只有发送成功 / 发送失败两种状态

    <img src="https://s2.loli.net/2021/12/15/qZcBVbEQCMIUPS8.png" alt="image-20211215235106916" style="zoom: 50%;" />

UDP

* 面向无连接的通讯协议 => 可以实现广播发送，不局限于端到端

* UDP数据包括**目的端口号**和**源端口号**

    ![image-20211215235143600](https://s2.loli.net/2021/12/15/6DRVW5a9nNQLjGs.png)

####C/S application

* TCP/IP协议中，两个进程中的主要模式为 C/S 模型
* 目的：协同网络中的计算机资源、服务模式、进程间数据共享

### Socket TCP 牛刀小试

