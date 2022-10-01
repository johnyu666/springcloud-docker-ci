# 1. 启动MQ的方法：
## rabbit MQ的启动
docker run -d --name my-rabbit --rm -p 5672:5672  rabbitmq:3

## mq manager的启动
docker run -d --hostname my-rabbit --name some-rabbit --rm -p 15672:15672 rabbitmq:3-management

# 2. 启动后的测试：

```shell
http://localhost:1300/config/dev/master
```
解释以上地址：Service/文件名/profile/fork