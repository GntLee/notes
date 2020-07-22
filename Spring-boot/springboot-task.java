----------------------
task					|
----------------------
	# SpringBoot自带的Task适合轻量级的
	# 注解配置
		* main程序入口配置:@EnableScheduling
		* 工作类配置:@Component
		* 工作方法配置:@Scheduled
			String cron() default "";
				* 定时任务表达式
			String zone() default "";
			long fixedDelay() default -1;
			String fixedDelayString() default "";
			long fixedRate() default -1;
			String fixedRateString() default "";
			long initialDelay() default -1;
			String initialDelayString() default "";

	
	#  处理异常
		No qualifying bean of type 'java.util.concurrent.ScheduledExecutorService' available
		* 好像是因为日志所产生的异常
		* 处理方式,修改日志配置文件
			<logger name="org.springframework.scheduling">
				<level value="info" />
			</logger>
	
	
	# 默认使用线程:scheduling 去执行
		* 可以使用 @Async 注解, 使用自定义的线程池去执行
	
	# properties配置(2.x版本好像不起作用)
		spring.task.execution.pool.max-threads = 16
			* 最大的线程数量

		spring.task.execution.pool.queue-capacity = 100
			* 任务队列的容量

		spring.task.execution.pool.keep-alive = 10s
			* 线程空闲多久就会被回收

---------------------
通过代码自定义配置	 |
---------------------
	# 实现接口 SchedulingConfigurer


	import java.util.concurrent.Executors;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.scheduling.annotation.SchedulingConfigurer;
	import org.springframework.scheduling.config.CronTask;
	import org.springframework.scheduling.config.ScheduledTaskRegistrar;
	import org.springframework.scheduling.support.CronTrigger;

	@Configuration
	public class SchedulingConfiguration implements SchedulingConfigurer {
		
		private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfiguration.class);

		@Override
		public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
			
			// 设置执行任务的线程池
			taskRegistrar.setScheduler(Executors.newScheduledThreadPool(20));
			
			// 动态添加新的定时任务，通过 Cron表达式执行
			taskRegistrar.addCronTask(new CronTask(new Runnable() {
				@Override
				public void run() {
					LOGGER.debug("任务执行...");
				}
			}, new CronTrigger("0/2 * * * * ?")));
		}
	}

	* 使用线程池的配置之后，再执行不仅以多线程来启动定时任务，而且也不会出现定时任务重复并发执行的问题
	* Executors.newScheduledThreadPool(20) // 很重要