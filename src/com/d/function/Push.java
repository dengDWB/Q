package com.d.function;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVPush;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SendCallback;

public class Push {

	Context context;

	public Push() {

	}

	public void setPush() {
		AVPush push = new AVPush();
		JSONObject object = new JSONObject();
		object.put("name", "dengwenbin");
		object.put("sex", "男");
		object.put("QQ", "402847229");
		push.setPushToAndroid(true);
		push.setData(object);
		push.sendInBackground(new SendCallback() {

			@Override
			public void done(AVException e) {
				if (e == null) {
					System.out.println("推送成功");
				} else {
					Toast.makeText(context, "推送失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void saveId() {
		AVInstallation.getCurrentInstallation().saveInBackground(
				new SaveCallback() {

					@Override
					public void done(AVException e) {
						if (e == null) {
							System.out.println("保存成功");
						}else{
							Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
						}

					}
				});
	}

}
