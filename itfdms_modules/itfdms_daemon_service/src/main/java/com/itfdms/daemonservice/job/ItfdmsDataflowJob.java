package com.itfdms.daemonservice.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.zen.elasticjob.spring.boot.annotation.ElasticJobConfig;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 任务调度
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.daemonservice.job
 * @ClassName: ItfdmsDataflowJob
 * @Description: 任务调度
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:08
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@ElasticJobConfig(cron = "0 0 0/1 * * ? ", shardingTotalCount = 3, shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou")
public class ItfdmsDataflowJob implements DataflowJob<Integer> {

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> list) {

    }
}
