package com.app.utils.baidu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaiduTool extends AbstractBaiduSearcher {

	@Override
	public SearchResult search(String keyword) {
		return search(keyword, 1);
	}

	@Override
	public SearchResult search(String keyword, int page) {
		int pageSize = 10;
		// 百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
		// 如获取第一页则pn=0，第二页则pn=10，第三页则pn=20，以此类推，抽象出模式：(page-1)*pageSize
		String url = "http://www.baidu.com/s?pn=" + (page - 1) * pageSize
				+ "&wd=" + keyword;

		SearchResult searchResult = new SearchResult();
		searchResult.setPage(page);
		List<Webpage> webpages = new ArrayList<Webpage>();
		try {
			Document document = Jsoup.connect(url).get();

			// 获取搜索结果数目
			int total = getBaiduSearchResultCount(document);
			searchResult.setTotal(total);
			int len = 10;
			if (total < 1) {
				return null;
			}
			// 如果搜索到的结果不足一页
			if (total < 10) {
				len = total;
			}
			for (int i = 0; i < len; i++) {
				String titleCssQuery = "html body div div div div#content_left div#"
						+ (i + 1 + (page - 1) * pageSize)
						+ ".result.c-container h3.t a";
				String summaryCssQuery = "html body div div div div#content_left div#"
						+ (i + 1 + (page - 1) * pageSize)
						+ ".result.c-container div.c-abstract";
				Element titleElement = document.select(titleCssQuery).first();
				String href = "";
				String titleText = "";
				if (titleElement != null) {
					titleText = titleElement.text();
					href = titleElement.attr("href");
				} else {
					// 处理百度百科
					titleCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op h3.t a";
					summaryCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op div p";
					titleElement = document.select(titleCssQuery).first();
					if (titleElement != null) {
						titleText = titleElement.text();
						href = titleElement.attr("href");
					}
				}
				Element summaryElement = document.select(summaryCssQuery)
						.first();
				// 处理百度知道
				if (summaryElement == null) {
					summaryCssQuery = summaryCssQuery.replace("div.c-abstract",
							"font");
					summaryElement = document.select(summaryCssQuery).first();
				}
				String summaryText = "";
				if (summaryElement != null) {
					summaryText = summaryElement.text();
				}

				if (titleText != null && !"".equals(titleText.trim())
						&& summaryText != null
						&& !"".equals(summaryText.trim())) {
					Webpage webpage = new Webpage();
					webpage.setTitle(titleText);
					webpage.setUrl(href);
					webpage.setSummary(summaryText);
					if (href != null) {
						String content = Tools.getHTMLContent(href);
						webpage.setContent(content);
					} else {

					}
					webpages.add(webpage);
				} else {

				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		searchResult.setWebpages(webpages);
		;
		return searchResult;
	}

	/**
	 * 获取百度搜索结果数 获取如下文本并解析数字： 百度为您找到相关结果约13,200个
	 * 
	 * @param document
	 *            文档
	 * @return 结果数
	 */
	private int getBaiduSearchResultCount(Document document) {
		String cssQuery = "html body div div div div.nums";
		Element totalElement = document.select(cssQuery).first();
		String totalText = totalElement.text();

		String regEx = "[^0-9]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(totalText);
		totalText = matcher.replaceAll("");
		int total = Integer.parseInt(totalText);
		return total;
	}

	public static void main(String[] args) {
		Searcher searcher = new BaiduTool();
		SearchResult searchResult = searcher.search("杨尚川", 1);
		List<Webpage> webpages = searchResult.getWebpages();
		if (webpages != null) {
			int i = 1;
			System.out.println("搜索结果 当前第 " + searchResult.getPage()
					+ " 页，页面大小为：" + searchResult.getPageSize() + " 共有结果数："
					+ searchResult.getTotal());
			for (Webpage webpage : webpages) {
				System.out.println("搜索结果 " + (i++) + " ：");
				System.out.println("标题：" + webpage.getTitle());
				System.out.println("URL：" + webpage.getUrl());
				System.out.println("摘要：" + webpage.getSummary());
				System.out.println("正文：" + webpage.getContent());
				System.out.println("");
			}
		} else {
			System.err.println("没有搜索到结果");
		}
	}
}