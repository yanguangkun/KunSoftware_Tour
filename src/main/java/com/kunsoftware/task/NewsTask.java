package com.kunsoftware.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsTask {

	private static Logger logger = LoggerFactory.getLogger(NewsTask.class);	
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public void newsJob() {
		logger.info("抓取数据.");
		//OsChinaCrawl.main(null);
		//StcnCrawl.main(null);
	}
}
