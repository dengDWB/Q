package com.d.table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

public class Evidence {

	private String theme = "";
	private String content = "";
	private String pollutionCategory ="";
	private List<File> fileList = new ArrayList<File>();
	private List<AVFile> listFile = new LinkedList<AVFile>();
	private AVObject evidence = new AVObject("Evidence");
	private List<String> listPath = new ArrayList<String>();

	public Evidence() {}

	public Evidence(String theme, String content,List filePathArray,String pollutionCategory) {
		this.theme = theme;
		this.content = content;
		this.listPath = filePathArray;
		this.pollutionCategory = pollutionCategory;
		setEvidence(theme, content,listPath,pollutionCategory);
	}

	public void setEvidence(String theme, String content,List listPath,String pollutionCategory) {
		evidence.put("Theme", theme);
		evidence.put("Content", content);
		for (int i = 0; i < listPath.size(); i++) {
			setFile(listPath.get(i).toString());
		}
		evidence.put("File", listFile);
		evidence.put("pollutionCategory", pollutionCategory);
		evidence.saveInBackground();
	}
	
	
	public void setFile(String str){
		if(!str.equals("")){
			AVFile file = null;
			try {
				file = AVFile.withAbsoluteLocalPath(new File(str).getName(), str);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			listFile.add(file);
		}
	
	}
			

}
