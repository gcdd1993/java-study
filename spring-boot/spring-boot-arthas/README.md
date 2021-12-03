# SpringBoot集成Arthas（阿尔萨斯）

# 部署Arthas-Tunnel-Server

```bash
$ vim docker-compose.yml
version: "3.1"

services:
  arthas-tunnel-server:
    image: leixuewen/arthas-tunnel-server:3.5.4
    restart: unless-stopped
    ports:
      - "7777:7777"
      - "8080:8080"

$ docker-compose up -d && docker-compose logs -f
Attaching to arthas-tunnel-server_arthas-tunnel-server_1
arthas-tunnel-server_1  |
arthas-tunnel-server_1  |   .   ____          _            __ _ _
arthas-tunnel-server_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
arthas-tunnel-server_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
arthas-tunnel-server_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
arthas-tunnel-server_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
arthas-tunnel-server_1  |  =========|_|==============|___/=/_/_/_/
arthas-tunnel-server_1  |  :: Spring Boot ::        (v2.3.1.RELEASE)
arthas-tunnel-server_1  |
arthas-tunnel-server_1  | 2021-12-03 06:18:59.725  INFO 1 --- [           main] c.a.a.t.s.app.ArthasTunnelApplication
 : Starting ArthasTunnelApplication v3.5.4 on cf8731ba76ba with PID 1 (/arthas-tunnel-server-3.5.4-fatjar.jar started by
 root in /)
...
```

# 集成arthas-spring-boot-starter

## 引入依赖

```groovy
implementation("com.taobao.arthas:arthas-spring-boot-starter:3.5.4")
```

## 修改配置`application.yml`

```yaml
spring:
  application:
    name: spring-boot-arthas
arthas:
  tunnel-server: "ws://127.0.0.1:7777/ws" # 外部arthas-tunnel-server地址
  # app-name: ${spring.application.name} # 默认使用的就是spring.application.name
  # agent-id: ${spring.application.name}-${random.uuid} # 这么配置是错误的，生成的agent-id不符合要求，实际上，默认就会生成唯一的agent-id：app-name_随机字符串
```

然后启动项目，将会看到注册成功的日志

```
arthas-tunnel-server_1  | 2021-12-03 08:29:42.754  INFO 1 --- [ver-worker-3-13] c.a.a.t.server.TunnelSocketFrameHandler  : websocket handshake complete, uri: /ws?me
thod=agentRegister&arthasVersion=3.5.4&appName=spring-boot-arthas
```

同时，SpringBoot项目也会打印出这么一行

```
arthas-tunnel-server_1  | 2021-12-03 08:29:42.754  INFO 1 --- [ver-worker-3-13] c.a.a.t.server.TunnelSocketFrameHandler  : websocket handshake complete, uri: /ws?me
thod=agentRegister&arthasVersion=3.5.4&appName=spring-boot-arthas
```

# 简单使用

打开http://localhost:8080/apps.html，将会看到全部注册上去的应用

![image-20211203163219585](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112031632670.png)

点击进入详情

![image-20211203163229740](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112031632790.png)

emm...，界面是有点朴素了点。如果能加个一键连接就好了。现在让我们复制AgentId，回到首页，输入AgentId，点击Connect，顺利的话，就能看到熟悉的界面了！

![image-20211203163404629](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112031634699.png)

试了下，命令啥的都行，使用`dashboard`命令时，界面每次刷新都会抖一下，希望能优化下，不然眼睛受不了啊。

![image-20211203163514108](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112031635201.png)

# FAQ排坑

## Docker下使用



# 相关资料

- [官方文档](https://arthas.aliyun.com/)
- [arthas-tunnel-server-docker](https://hub.docker.com/r/leixuewen/arthas-tunnel-server)
