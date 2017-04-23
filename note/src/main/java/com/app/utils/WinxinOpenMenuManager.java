package com.app.utils;

public class WinxinOpenMenuManager {

	  //  private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
	  
	    public static void main(String[] args) {  
	        // 第三方用户唯一凭证  
	        String appId = "wxf79028ac73bea5a9";  
	        // 第三方用户唯一凭证密钥  
	        String appSecret = "6ad67103807e352d6886688eaae6ac09";  
	  
	        // 调用接口获取access_token  
	       // AccessToken at = WeixinBuilderTool.getAccessToken(appId, appSecret);  
	  
	      //  if (null != at) {  
	            // 调用接口创建菜单  
	            int result = WeixinBuilderTool.createMenu(getMenu(),"exQbTPPY-NcensHB1vVaOeeUEV8WkjSKXu0ZlGnCD83C9iwkr1hsMkMBRoBjFH_AbIEn2Z2wBv9vqSds5ayCp14EbibwcK2vMb_C5hAs4vUQVWhAGAYDE");  
	  
	            // 判断菜单创建结果  
	            if (0 == result)  
	               System.out.println("菜单创建成功！");  
	            else  
	            	 System.out.println("菜单创建失败，错误码：" + result);  
	        }  
	    //}  
	  
	    /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getMenu() {  
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("lovanote");  
	        btn11.setType("click");  
	        btn11.setKey("11");  

	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("周边搜索");  
	        btn12.setType("click");  
	        btn12.setKey("13");  
	 
	  
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("歌曲点播");  
	        btn21.setType("click");  
	        btn21.setKey("21");  
	  
	    
	  
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("生活助手");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12});  
	  
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("休闲驿站");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21});  
	  
	       
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2});  
	  
	        return menu;  
	    }  
	}  