package com.eshop.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration  
@EnableScheduling    //开启定时任务
public class ScheduleConfig implements SchedulingConfigurer {
	
	    //使用默认的线程池
	    //@Override
	    //public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	    //    taskRegistrar.setScheduler(taskExecutor());
	    //}
	 
	   // @Bean(destroyMethod="shutdown")
	   // public Executor taskExecutor() {
	   //     return Executors.newScheduledThreadPool(10); //指定线程池大小
	   // }
	
	  //使用自定义线程池
	@Autowired
    private TaskScheduler myThreadPoolTaskScheduler;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		//自定义的线程池，方便线程的使用与维护
		taskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler);
	}
	
	  @Bean(name = "myThreadPoolTaskScheduler")
	  public TaskScheduler getMyThreadPoolTaskScheduler() {
	      ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
	      taskScheduler.setPoolSize(10);
	      taskScheduler.setThreadNamePrefix("Vehicle-Scheduled-");
	      taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	      //调度器shutdown被调用时等待当前被调度的任务完成
	      taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
	      //等待时长
	      taskScheduler.setAwaitTerminationSeconds(60);
	      return taskScheduler;
	  }	

}
