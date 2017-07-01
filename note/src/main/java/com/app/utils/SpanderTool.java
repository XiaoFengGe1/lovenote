package com.app.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.app.utils.baidu.BaiduTool;
import com.app.utils.baidu.SearchResult;
import com.app.utils.baidu.Searcher;
import com.app.utils.baidu.Webpage;

public class SpanderTool {
	public static void main(String[] args) {
		SpanderTool d = new SpanderTool();
		// String url =
		// "http://search.sina.com.cn/?c=blog&q=java+eclipse&range=article&by=&sort=time";
		System.out.println(d.getBaidu(" java  eclipse ", 10));
	}

	public List<HashMap<String, String>> getFromCSDN(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://so.csdn.net/so/search/s.do?q=" + keywords
									+ "&t=blog&o=&s=&l=null").timeout(3000)
					.get();
			Elements data = doc.getElementsByClass("search-list");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements authors = data.get(i).getElementsByClass(
							"author-time");
					Elements contents = data.get(i).getElementsByClass(
							"search-detail");
					map.put("title", titles.get(0).toString());
					map.put("author", authors.get(0).html().toString());
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromBokeyaun(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup
					.connect("http://zzk.cnblogs.com/s?w=" + keywords + "&t=b")
					.timeout(3000).get();
			Elements data = doc.getElementsByClass("searchItem");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements authors = data.get(i).getElementsByClass(
							"searchItemInfo");
					Elements contents = data.get(i).getElementsByClass(
							"searchCon");
					map.put("title", titles.get(0).toString());
					map.put("author", authors.get(0).html().toString());
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromJiaoben(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://so.jb51.net/cse/search?q=" + keywords
									+ "&click=1&s=10520733385329581432&nsid=")
					.timeout(3000).get();
			Elements data = doc.getElementsByClass("result");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements contents = data.get(i).getElementsByClass(
							"c-abstract");
					map.put("title", titles.get(0).toString());
					map.put("author", "无");
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			System.out.println("脚本之家无法搜索了");
			// e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromSina(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://search.sina.com.cn/?c=blog&q=" + keywords
									+ "&range=article&by=&sort=time")
					.timeout(3000).get();
			Elements data = doc.getElementsByClass("box-result");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements authors = data.get(i).getElementsByTag("p");
					Elements contents = data.get(i).getElementsByClass(
							"content");
					map.put("title", titles.get(0).toString());
					map.put("author", authors.get(1).html().toString());
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromWangyi(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "%20");
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=site%3Ablog.163.com%20"
									+ keywords
									+ "&rsv_spt=1&oq=site%3Ablog.16%26lt%3B.com%20java%20eclipse&rsv_pq=871e4e260000cb52&rsv_t=e7dfVds0izvwRKw6lUTpqWWR4JYJ1si3eixYyjUh3uqshR6l60AevRDrIKWBc3VlFePh&rsv_enter=0")
					.timeout(3000).get();
			Elements data = doc.getElementsByClass("c-container");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements contents = data.get(i).getElementsByClass(
							"c-abstract");
					map.put("title", titles.get(0).toString());
					map.put("author", "无");
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromManongwang(String keyword,
			int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://zhannei.baidu.com/cse/search?s=5192211232745119302&entry=1&q="
									+ keywords).timeout(3000).get();
			Elements data = doc.getElementsByClass("result");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements contents = data.get(i).getElementsByClass(
							"c-abstract");
					map.put("title", titles.get(0).toString());
					map.put("author", "无");
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromQianduanli(String keyword,
			int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.yyyweb.com/?s=" + keywords)
					.timeout(3000).get();
			Elements data = doc.getElementsByClass("excerpt");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements authors = data.get(i).getElementsByClass(
							"text-muted");
					Elements contents = data.get(i).getElementsByClass("note");
					map.put("title", titles.get(1).toString());
					map.put("author", authors.get(1).html().toString());
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getFromQianduankaifa(String keyword,
			int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.wufangbo.com/?s=" + keywords)
					.timeout(3000).get();
			Elements data1 = doc.getElementsByClass("Nlist3");
			Elements data = data1.get(0).getElementsByClass("clearfix");
			for (int i = 0; i < num; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					Elements titles = data.get(i).getElementsByTag("a");
					Elements authors = data.get(i).getElementsByClass("zinfo");
					Elements contents = data.get(i).getElementsByClass("tinfo");
					map.put("title", titles.get(1).toString());
					map.put("author", authors.get(0).html().toString());
					map.put("content", contents.get(0).html().toString());
				} catch (Exception e) {
					break;
				}
				list.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<HashMap<String, String>> getBaidu(String keyword, int num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String keywords = keyword.trim().replaceAll("\\s+", "+");
		Searcher searcher = new BaiduTool();
		SearchResult searchResult = searcher.search(keywords, 1);
		List<Webpage> webpages = searchResult.getWebpages();
		if (webpages != null) {
			for (Webpage webpage : webpages) {
				HashMap<String, String> map = new HashMap<String, String>();
				String title = "<a href=\"" + webpage.getUrl()
						+ "\" target=\"_blank\"><em>" + webpage.getTitle()
						+ "</em></a>";
				map.put("title", title);
				map.put("author", "");
				map.put("content", webpage.getSummary());
				list.add(map);
			}
		} else {
			System.err.println("没有搜索到结果");
		}
		return list;
	}
}
