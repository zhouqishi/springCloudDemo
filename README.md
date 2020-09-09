spring cloud 全家桶demo工程

1. 使用eureka做注册中心
2. 使用gateway做网关，同一拦截（zuul不支持spring boot 2.0）
   访问 http://127.0.0.1:9001/USER-SERVICE/user/get (即通过loadbalance转发到 http://USER-SERVICE/user/get)
3. 使用dashboard做服务监控
   访问url：http://localhost:9003/hystrix，访问管理平台
   输入：http://localhost:9002/actuator/hystrix.stream，监控某个应用的流量情况
4. 使用sleuth + zipkin做链路跟踪
   zipkin server在zipkin文件夹下，暂时为jar，启动命令 java -jar zipkin-server-2.21.7-exec.jar
5. usercenter为简版的用户中心
   http://127.0.0.1:9002/user/get