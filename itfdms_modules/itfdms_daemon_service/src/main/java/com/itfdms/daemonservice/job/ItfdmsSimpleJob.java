package com.itfdms.daemonservice.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zen.elasticjob.spring.boot.annotation.ElasticJobConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.daemonservice.job
 * @ClassName: ${CLASS_NAME}
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:11
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
@Slf4j
@ElasticJobConfig(cron = "0 0 0/1 * * ?", shardingTotalCount = 3,
        shardingItemParameters = "0=itfdms1,1=itfdms2,2=itfdms3",
        startedTimeoutMilliseconds = 5000L,
        completedTimeoutMilliseconds = 10000L,
        eventTraceRdbDataSource = "dataSource")
public class ItfdmsSimpleJob implements SimpleJob {
    
    
    /**
      * 业务执行逻辑
      * @className:      ItfdmsSimpleJob
      * @methodName
      * @description:    业务执行逻辑
      * @author          lxr
      * @createDate      2018-08-28 20:11
      * @updateUser:     lxr
      * @updateDate:     2018-08-28 20:11
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           shardingContext 分片信息
      * @return          
      * @exception       
    **/
    
    
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("shardingContext:{}", shardingContext);
    }
}
