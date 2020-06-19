# flow-limit-dashboard-springboot-starter
flow-limit仪表盘组件，可以动态监控使用flow-limit组件系统的流控情况
# 使用
新建springboot项目，引入依赖：
```xml
<dependency>
   <groupId>com.xiaobai</groupId>
   <artifactId>flow-limit-dashboard-springboot-starter</artifactId>
   <version>1.0.0</version>
</dependency>
```
启动工程，浏览器访问http://ip:1017 :

![dashboard主页面](https://xiaobai-picture.oss-cn-beijing.aliyuncs.com/flow-limit-dashboard/dashboard%E4%B8%BB%E9%A1%B5%E9%9D%A2.png)
输入需要监控工程的ip和端口，点击确定，会动态显示工程总的请求（调用使用了@FlowLimit注解的接口的请求）通过和限制情况，横轴为时间，纵轴为请求数量：
![动态监控](https://xiaobai-picture.oss-cn-beijing.aliyuncs.com/flow-limit-dashboard/%E9%80%9A%E8%BF%87%E7%8E%87.png)
在输入框输入使用@FlowLimit注解的单个接口名,点击搜索，会显示该接口的通过和限制的请求数量：
![接口详情](https://xiaobai-picture.oss-cn-beijing.aliyuncs.com/flow-limit-dashboard/%E6%8E%A5%E5%8F%A3%E8%AF%A6%E7%BB%86%E4%BF%A1%E6%81%AF.png)
# 下载
