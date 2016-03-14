
import java.util.*;

public class MyCrawler {
	
	private void initCrawlerWithSeeds(String[] seeds)
	{
		for(int i=0;i<seeds.length;i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	private void crawling(String[] seeds)
	{
		initCrawlerWithSeeds(seeds);
		
		while(!LinkQueue.unVisitedUrlIsEmpty()&&LinkQueue.getVisitedUrlNum()<1000)
		{
			String visitUrl = (String)LinkQueue.unVisitedUrlDeQueue();
			
			if(visitUrl == null)
				continue;
			
			DownLoadFile downLoader = new DownLoadFile();
			try{
				
			
				downLoader.downloadFIle(visitUrl);
			} catch(Exception e)
			{
				System.out.println("DownLoadFile Error");
			}
			LinkQueue.addVisitedUrl(visitUrl);
			
			Set<String> links = HtmlParserTool.extracLinkds(visitUrl);
			
			for(String link:links)
			{
				LinkQueue.addUnvisitedUrl(link);
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		String[] urls = new String[1];
		String url = new String("http://www.baidu.com");
		
		urls[0]=url;
		
		MyCrawler crawler = new MyCrawler();
		
		crawler.crawling(urls);
		System.out.println(url);
	}
}
