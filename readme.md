itfdms
├── itfdms-ui -- element-vue-admin实现[9528]
├── itfdms-auth -- 授权服务提供[3000]
├── itfdms-common -- 系统公共模块 
├── itfdms-config -- 配置中心[4001]
├── itfdms-eureka -- 服务注册与发现[1025]
├── itfdms-gateway -- ZUUL网关[9999]
├── itfdms-modules -- 微服务模块
├    ├── itfdms-daemon-service -- 分布式调度中心[4060]
├    ├── itfdms-mc-service -- 消息中心[4050]
├    ├── itfdms-sso-client-demo -- 单点登录客户端示例[4040]
├    └── itfdms-upms-service -- 权限管理提供[4000]
└── itfdms-visual  -- 图形化模块 
     ├── itfdms-monitor -- 服务状态监控、turbine [5001]
     ├── itfdms-zipkin-elk -- zipkin、ELK监控[5002、5601]
     └── itfdms-cache-cloud -- 缓存管理、统一监控[5005]
     
     
启动顺序：
1.eureka  --注册中心
2.config  --配置中心
3.auth    --认证中心
4.gateway --网关中心
5.upms    --业务中心
