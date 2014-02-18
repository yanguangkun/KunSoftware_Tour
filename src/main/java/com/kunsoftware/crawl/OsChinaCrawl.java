package com.kunsoftware.crawl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kunsoftware.entity.News;
import com.kunsoftware.service.NewsService;
import com.kunsoftware.util.WebUtil;

public class OsChinaCrawl {

	private static Logger logger = LoggerFactory.getLogger(StcnCrawl.class);	
	private static String parentLink = "http://www.oschina.net/";
	public static void main(String[] args) {
		logger.info(parentLink + " 抓取数据");
		try {
			List<News> list = new ArrayList<News>();
			
			Document doc = Jsoup.connect("http://www.oschina.net/")
					.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36")
					.get();
			Elements hrefs = doc.getElementById("IndustryNews").select("a[href]");
			for(Element element :hrefs) {
				News news = new News();
				String link = element.attr("href");
				String title = element.attr("title");
				if(StringUtils.isEmpty(title))
					title = element.text();
				news.setParentLink("http://www.oschina.net/");
				news.setTitle(title);
				news.setLink(WebUtil.getLink(link,parentLink));
				news.setIsRead("0");
				news.setCreateTime(new Date());
				news.setTitleMd5(DigestUtils.md5Hex(title));
				list.add(news);
			}
			
			NewsService newsService = WebUtil.getBean("newsService", NewsService.class);
			newsService.inserUser(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
