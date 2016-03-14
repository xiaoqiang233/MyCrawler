
import java.util.*;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTool {

	public static Set<String> extracLinkds(String url)
	{
		Set<String> links = new HashSet<String>();
		HasAttributeFilter filter = new HasAttributeFilter("href");
		
		try{
			Parser parser = new Parser(url);
			parser.setEncoding("utf-8");
			
			NodeList list = parser.parse(filter);
			int count = list.size();
			
			for(int i=0; i<count; i++)
			{
				Node node = list.elementAt(i);
				
				if(node instanceof LinkTag)
				{
					LinkTag link = (LinkTag)node;
					
					String nextlink = link.extractLink();
					
					
					links.add(nextlink);
				}
			}
			//过滤<frame>标签的filter,用来提取frame标签里的src属性
				
			} catch (ParserException e)
			{
				e.printStackTrace();
			}
		
		return links;
	}

}
