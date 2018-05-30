## Dubbo + Zookeeper
启动程序前请务必保证电脑已经安装了Zookeeper
配置时,要保证端口选择正确:
- zookeeper默认端口为2181
- 修改zookeeper端口需进入zookeeper目录下的conf子目录, 创建zoo.cfg,内容为:
```properties
tickTime=2000    
dataDir=$zk_install_home/data    
dataLogDir=$zk_install_home/logs    
clientPort=2111  
```
- 程序中关于zk的配置为
```java
// zk的端口要与zk的配置端口一致
registryConfig.setAddress("zookeeper://219.223.196.9:2111");  
```

## Dubbo + Redis
启动程序前务必保证电脑已经安装了Redis
配置时,要保证端口选择正确:
- Redis的默认端口为6379
- 程序中关于Redis的配置:
```java
registryConfig.setAddress("redis://127.0.0.1:6379");
```

## log4j
文件log4j.properties的路径很重要: 
${root2nativedubboprovider}/nativedubboprovider/src/main/resources
log4j不起效, 出现以下warning:
```
log4j:WARN No appenders could be found for logger (org.apache.hadoop.util.Shell).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
```
Log4j RollingFileAppender和DailyRollingFileAppender的配置: https://blog.csdn.net/zhanglu0223/article/details/78094076


## 关于函数回调
Consumer端调用服务端的服务:CallbackService.addListener(**没有返回值**);
当服务端完成addListener服务之后, 将调用Consumer端服务: CallbackListener.change(**没有返回值**), 通知Consumer端addListener服务已经完成了.
> Consumer端和Provider端构成了一种双向调用的关系
 