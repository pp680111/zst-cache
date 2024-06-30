DONE：
* 完成RESP协议的Encoder和Decoder
* 


TODO:
* 基于ApplicationRunner的方式来启动缓存业务主进程
* 仿照Redis的思路，只使用1个线程来操作缓存核心的数据
  > 大致的思路就是，操作核心缓存的部分使用单个线程，其他的则使用多线程来加速，目前的运行模式中数据的反序列化等步骤都还是放在核心线程中运行的，这些步骤是放在IO线程中合适一点，不然会吃掉核心线程不少的性能
* 完成RESP协议的命令Handler，实现Redis命令的分发
* 想想怎么优化聚合器对象的频繁创建和销毁
* 待实现的命令
  * GET
  * SET
  * COMMAND
  * CLIENT系列（可以直接mock，返回个ok）
  * DEL
  * INCR
  * EXISTS
  * INCR
  * DECR
  * STRLEN
* 研究一下Redis的SCAN命令的实现方式
* 模拟redis的线程模式，实现一个类似的

总之就是自己挑一些用到的实现了

