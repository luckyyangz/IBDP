package com.sdu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sdu.ToolsUse.MysqlConnect;
import com.sdu.ToolsUse.ReadFileNew;
import com.sdu.biz.impl.DataFileBizImpl;
import com.sdu.dao.impl.DataFileDaoImpl;
import com.sdu.entity.DataFile;
import com.sdu.entity.DataNew;




public class ReadFileToShowAction {
	private int dataFileId;
	private Map<String,Object> map;
	private DataFileBizImpl dataFileBiz;
	private int pos;
	private int lines;
	//--------------------------
	
	public void setDataFileId(int dataFileId) {
		this.dataFileId = dataFileId;
	}
	public void setDataFileBiz(DataFileBizImpl dataFileBiz) {
		this.dataFileBiz = dataFileBiz;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	//------------------------------------
	public String execute()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Logger log  =  Logger.getLogger(ReadFileToShowAction.class);
		//这个类注册到log，调用log4j进行日志输出
		String forward = "tab";
		String rowid=request.getParameter("did");
//		System.out.println(rowid);
		String filePath=MysqlConnect.getpath(rowid);//由id从本地数据库获取Path
		System.out.println(filePath);
		//获取文件类型
		String filetype = "";
		int dot = filePath.lastIndexOf('.');   //从右往左找filePath,找到.的位置
        if ((dot >-1) && (dot < (filePath.length() - 1))) {  
        	filetype = filePath.substring(dot + 1);//提取字符串,在dot+1到最后
        }  
        //获取文件名称
        String filename = "";
		int iline = filePath.lastIndexOf('/');   
        filename = filePath.substring(iline + 1);
       //toLowerCase把字符串转化成小写
		if ("xls".equals(filetype.toLowerCase()) || "xlsx".equals(filetype.toLowerCase()) || "txt".equals(filetype.toLowerCase()) || "csv".equals(filetype.toLowerCase())) {
			List<DataNew> datalist = new ArrayList<DataNew>();
			datalist.clear();//为了避免数据的叠加。就需要在加载前 用 数组.clear()；清除数据
			int colnum = 0;
			
			if ("txt".equals(filetype.toLowerCase())) {
				datalist = ReadFileNew.readtxt(filePath);
				colnum = ReadFileNew.txtlen(filePath);
				forward= "txt";
			} else if ("csv".equals(filetype.toLowerCase())) {
				datalist = ReadFileNew.readcsv(filePath);
				colnum = ReadFileNew.csvlen(filePath);
				
			} else if ("xls".equals(filetype.toLowerCase()) || "xlsx".equals(filetype.toLowerCase())) {
				datalist = ReadFileNew.readexcel(filePath);
				colnum = ReadFileNew.excellen(filePath);
				
			} else {
				datalist = ReadFileNew.readcsv(filePath);
				colnum = ReadFileNew.csvlen(filePath);				
			}
			String colNum = colnum +"";
			request.setAttribute("colNum", colNum);			
			request.setAttribute("dataList", datalist);
		} else if ("jpg".equals(filetype.toLowerCase()) || "jpeg".equals(filetype.toLowerCase()) || "png".equals(filetype.toLowerCase())) {				
			request.setAttribute("filename", filename);
			forward="picture";
		}else if ("pdf".equals(filetype.toLowerCase())) {	
			request.setAttribute("filename", filename);
			forward="pdffile";
		}
		
		return forward;
	}
	public String dynamicView() throws Exception{
		DataFile dataFile= dataFileBiz.getById(dataFileId);
		System.out.println("dataFileBiz:"+dataFileBiz);
		String filePath = dataFile.getD_localpath();
		String suffix = dataFile.getD_suffix();
		List<DataNew> datalist = null;
		JSONArray array =null;
		int position =-1;
		int jpgFalg=-1;
		String isNext = "true";
		if("xls".equals(suffix.toLowerCase()) || "xlsx".equals(suffix.toLowerCase()) || "txt".equals(suffix.toLowerCase()) || "csv".equals(suffix.toLowerCase())){
			datalist = new ArrayList<DataNew>();
			if("txt".equals(suffix.toLowerCase()) || "csv".equals(suffix.toLowerCase())){
				position = (int) ReadFileNew.readTextByPos(datalist, filePath, pos, lines);
			}else if("xls".equals(suffix.toLowerCase()) || "xlsx".equals(suffix.toLowerCase()) ){
				position = (int)ReadFileNew.readExcelByPos(datalist, filePath, pos, lines);
			}
			if(position<0){
				isNext = "false";
			}
			//对返回数据的处理
			array =new JSONArray();
			for(int i = 0;i<datalist.size();i++){
				List<String> rowData = datalist.get(i).getData();
				JSONArray arr = new JSONArray();
				for(int j=0;j<rowData.size();j++){
					arr.put(rowData.get(j));
				}
				array.put(arr);
			}
		}else if ("jpg".equals(suffix.toLowerCase()) || "jpeg".equals(suffix.toLowerCase()) || "png".equals(suffix.toLowerCase())) {
		            array =new JSONArray();
			        String filename = "";
					int iline = filePath.lastIndexOf('/');   
			        filename = filePath.substring(iline + 1);
			        array.put(filename);
					System.out.println("array==="+array);
					jpgFalg = 0;
		}
		map = new HashMap<String, Object>();
		map.put("jsonData", array.toString());
		map.put("pos",position);
		map.put("isNext",isNext);
		map.put("jpgFalg", jpgFalg);
		return "dynamicViewSuccess";
	}
}
