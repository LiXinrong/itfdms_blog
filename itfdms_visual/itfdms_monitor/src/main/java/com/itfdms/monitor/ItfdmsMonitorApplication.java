package com.itfdms.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import de.codecentric.boot.admin.notify.LoggingNotifier;
import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.monitor
 * @ClassName: ItfdmsMonitorApplication
 * @Description: 监控模块
 * @Author: lxr
 * @CreateDate: 2018-08-26 17:27
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-26 17:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-26
 **/

@EnableAdminServer
@EnableTurbine
@EnableDiscoveryClient
@SpringCloudApplication
public class ItfdmsMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsMonitorApplication.class, args);
    }


    @Configuration
    public static class NotifierConfig {
        @Bean
        @Primary
        public RemindingNotifier remindingNotifier() {
            RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(loggerNotifier()));
            notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10));
            return notifier;
        }

        @Scheduled(fixedRate = 1_000L)
        public void remind() {
            remindingNotifier().sendReminders();
        }

        @Bean
        public FilteringNotifier filteringNotifier(Notifier delegate) {
            return new FilteringNotifier(delegate);
        }

        @Bean
        public LoggingNotifier loggerNotifier() {
            return new LoggingNotifier();
        }
    }

}
