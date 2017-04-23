package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.FileInputInitialPreviewConfigModel;
import com.app.model.FileInputResponseModel;
import com.app.model.Note;
import com.app.model.User;
import com.app.model.Zan;
import com.app.service.NoteService;
import com.app.service.RecordService;
import com.app.service.TypeService;
import com.app.service.UserService;
import com.app.service.ZanService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class NoteController {
	@Resource
	private UserService userService;
	@Resource
	private NoteService noteService;
	@Resource
	private TypeService typeService;
	@Resource
	private ZanService zanService;
	@Resource
	private RecordService recordService;
	String uname= "";
	String flag = "false";
	
	public static final String SAVE_PATH = "/upload/logo/";// 图片保存根目录
	public static final String SMALL_PIC_SUFFIX = "_350x250.jpg";// 缩略图后缀名
	public static final int FINAL_WIDTH = 350, FINAL_HEIGHT = 250;// 裁剪后的图片尺寸
	
	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> addNote(Note note,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		uname =(String) session.getAttribute("lovelxfName");
		if(uname==null){
			hashMap.put("status","timeout");
			return hashMap;
		}
		if(noteService.checkTitle(uname, note.getTitle())){
			hashMap.put("status","has");
			return hashMap;
		}else{
			note.setUname(uname);
			noteService.save(note);
			hashMap.put("status","true");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/fixNote", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> fixNote(Note note,HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		uname =(String) session.getAttribute("lovelxfName");
		Note oldNote = new Note();
		if(uname==null){
			hashMap.put("status","timeout");
			return hashMap;
		}
		oldNote = noteService.findById(note.getId());
		if(oldNote == null){
			hashMap.put("status","false");
			return hashMap;
		}
		if(oldNote.getTitle().equals(note.getTitle())){//说明标题并未更改。
			note.setUname(uname);
			note.setCreatetime(oldNote.getCreatetime());
			noteService.update(note);
			hashMap.put("status","true");
			oldNote = null;
			return hashMap;
		}else{
			if(noteService.checkTitle(uname, note.getTitle())){
				hashMap.put("status","has");
				oldNote = null;
				return hashMap;
			}else{
				note.setCreatetime(oldNote.getCreatetime());
				note.setUname(uname);
				noteService.update(note);
				hashMap.put("status","true");
				oldNote = null;
				return hashMap;
			}
		}
	}
	
	@RequestMapping(value = "/getNote", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getNote(HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		String idStr = (String) session.getAttribute("lovelxfNoteId");
		Note note = new Note();
		try{
			int id = Integer.parseInt(idStr);
			note = noteService.findById(id);
			if(note==null){
				hashMap.put("status","false");
				idStr = null;
				return hashMap;
			}else{
				if(zanService.isExist(uname,id)==true){//借用part来给前端判断是否为本人查看
					note.setPart("");;
				}
				if(note.getUname().equals(uname)){   //借用unlike来给前端判断是否为本人查看
					note.setUnlike(1);
				}
				hashMap.put("status","true");
				hashMap.put("data", note);
				idStr = null;
				note = null;
				return hashMap;
			}
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
		
	}
	
	@RequestMapping(value = "/addClick", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> addClick(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		Note note = new Note();
		String idStr = req.getParameter("noteId");
		try{
			int id = Integer.parseInt(idStr);
			note = noteService.findById(id);
			note.setClick(note.getClick()+1);
			noteService.update(note);
			hashMap.put("status","true");
			note = null;
			idStr = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			note = null;
			idStr = null;
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/getMain", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getMain(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//Note note = new Note();
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		addClick(req,uname);
		try{
			String[] keyWords =  req.getParameter("keyWord").toString().split("\\s+");
			HashMap<String,Object> data = null;
			String page = req.getParameter("page");
			data = noteService.findMain(keyWords, Integer.parseInt(page));
			hashMap.put("status","true");
			hashMap.put("data", data);
			page = null;
			data = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/getMainBlock", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getMainBlock(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//Note note = new Note();
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		addClick(req,uname);
		try{
			HashMap<String,Object>  data = null;
			String page = req.getParameter("page");
			data = noteService.findMainBlock(Integer.parseInt(page));
			hashMap.put("status","true");
			hashMap.put("data", data);
			page = null;
			data = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/getMainTool", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getMainTool(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		//Note note = new Note();
		addClick(req,uname);
		try{
			String[] keyWords =  req.getParameter("keyWord").toString().split("\\s+");
			String page = req.getParameter("page");
			String classifyT = req.getParameter("classify");
			String titleT = req.getParameter("title");
			String user = req.getParameter("user");
			if(user.equals("仅自己")){
				user = uname;
			}
			String fromTime = req.getParameter("fromTime");
			String toTime = req.getParameter("toTime");
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("title",titleT);
			map.put("classify",classifyT);
			map.put("user",user);
			map.put("fromTime",fromTime);
			map.put("toTime",toTime);
			
			HashMap<String,Object> data = null;
			data = noteService.findMainTool(keyWords, Integer.parseInt(page),map);
			hashMap.put("status","true");
			hashMap.put("data", data);
			map= null;
			data= null;
			fromTime= null;
			toTime= null;
			keyWords= null;
			page= null;
			classifyT = null;
			titleT = null;
			user = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	@RequestMapping(value = "/getMainToolBlock", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getMainToolBlock(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		//Note note = new Note();
		addClick(req,uname);
		try{
			String page = req.getParameter("page");
			String classifyT = req.getParameter("classify");
			String titleT = req.getParameter("title");
			String user = req.getParameter("user");
			if(user.equals("仅自己")){
				user = uname;
			}
			String fromTime = req.getParameter("fromTime");
			String toTime = req.getParameter("toTime");
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("title",titleT);
			map.put("classify",classifyT);
			map.put("user",user);
			map.put("fromTime",fromTime);
			map.put("toTime",toTime);
			
			HashMap<String,Object> data = null;
			data = noteService.findMainToolBlock(Integer.parseInt(page),map);
			hashMap.put("status","true");
			hashMap.put("data", data);
			map= null;
			data= null;
			fromTime= null;
			toTime= null;
			page= null;
			classifyT = null;
			titleT = null;
			user = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> deleteNote(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//String uname =(String) session.getAttribute("lovelxfName");
		Note note = new Note();
		String idStr = (String) session.getAttribute("lovelxfNoteId");
		try{
			int id = Integer.parseInt(idStr);
			note = noteService.findById(id);
			note.setIsdelete(1);;
			noteService.update(note);
			hashMap.put("status","true");
			note = null;
			idStr = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/likeNote", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> likeNote(HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		Note note = new Note();
		String idStr = (String) session.getAttribute("lovelxfNoteId");
		try{
			int id = Integer.parseInt(idStr);
			note = noteService.findById(id);
			note.setLikenum(note.getLikenum()+1);
			noteService.update(note);
			try {
				uname =(String) session.getAttribute("lovelxfName");
				Zan zan = new Zan();
				zan.setNoteid(id);
				zan.setUname(uname);
				zanService.save(zan);
			} catch (Exception e) {
				uname = "";
			}
			hashMap.put("status","true");
			note = null;
			idStr = null;
			return hashMap;
		}catch (Exception e) {
			hashMap.put("status","false");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/toxiang.do",method = RequestMethod.POST)  
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model,HttpSession session) {  
		try {
			uname =(String) session.getAttribute("lovelxfName");
		} catch (Exception e) {
			uname = "";
		}
		if(uname.equals("")){
			return "redirect:/user";
		} 
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename(); 
        fileName = new Date().getTime()+"."+getExtensionName(fileName);  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String savepath = request.getContextPath()+"/upload/"+fileName;
        User user1 = new User();
        user1 = userService.findByName(uname);
        user1.setPic(savepath);
        userService.update(user1);
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName); 
        return "redirect:/user";
    }  
	
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public FileInputResponseModel upload(HttpServletRequest request, MultipartFile file) throws Exception {
		String finalName = fileUpload(file, request);
		// fileinput组件需要包含图片地址信息的json
		FileInputResponseModel responseModel = new FileInputResponseModel();
		responseModel.setError("");
		List<String> initialPreview = new ArrayList<String>();
		initialPreview.add("<img src='" + SAVE_PATH + finalName + "' class='file-preview-image'>");
		List<FileInputInitialPreviewConfigModel> configList = new ArrayList<FileInputInitialPreviewConfigModel>();
		configList.add(new FileInputInitialPreviewConfigModel(finalName + "hah", "120px", "article_delete_pic", 0));
		responseModel.setAppend(true);
		responseModel.setFileName(finalName);
		responseModel.setInitialPreview(initialPreview);
		return responseModel;
	}
	
	//工具方法，SpringMVC方式图片上传
		private String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException, InterruptedException {
			String fileName = null;
			if (!file.isEmpty()) {
				fileName = System.currentTimeMillis() + ".jpg";
				File savePath = new File(request.getServletContext().getRealPath(SAVE_PATH));
				if (!savePath.exists())
					savePath.mkdirs();
				File saveFile = new File(savePath, fileName);
				FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
				Thumbnails.of(saveFile).size(FINAL_WIDTH, FINAL_HEIGHT)
						.toFile(new File(savePath, fileName + SMALL_PIC_SUFFIX));
			}
			return fileName;
		}

		//工具方法，删除文件名包含指定字符的文件
		public void deleteFilesLikeName(File file, String likeName) {
			if (file.isFile()) {
				String temp = file.getName().substring(0, file.getName().lastIndexOf("."));
				if (temp.indexOf(likeName) != -1) {
					file.delete();
				}
			}
		}
		
		public void addClick(HttpServletRequest request,String name){
			String ip = IpUtils.getIpAddr(request);
			String device = request.getHeader("user-agent");
			if(device!=null){
				if(device.contains("Android")) {
					device = "Android";
				} else if(device.contains("iPhone")) {
					device = "iPhone";
				}  else if(device.contains("iPad")) {
					device = "iPad";
				} else{
					device = "pc";
				}
			}
			recordService.addRecord(ip,name,device,0);
		}
		
	    public String getExtensionName(String filename) {   
	        if ((filename != null) && (filename.length() > 0)) {   
	            int dot = filename.lastIndexOf('.');   
	            if ((dot >-1) && (dot < (filename.length() - 1))) {   
	                return filename.substring(dot + 1);   
	            }   
	        }   
	        return filename;   
	    }
}
