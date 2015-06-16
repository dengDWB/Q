package com.d.function;

import org.json.JSONException;
import org.json.JSONObject;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.search.R;
import com.d.activity.MainActivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	Context context;
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (intent.getAction().equals("com.pushdemo.action")) {
				JSONObject object = new JSONObject(intent.getExtras()
						.getString("com.avos.avoscloud.Data"));
				String name = object.getString("name");
				String QQ = object.getString("QQ");
				Intent resultIntent = new Intent(AVOSCloud.applicationContext,
						MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(
						AVOSCloud.applicationContext, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
						AVOSCloud.applicationContext)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(
								AVOSCloud.applicationContext
										.getResources()
										.getString(
												R.string.avoscloud_search_result_title))
						.setContentText(name + QQ);
				mBuilder.setContentIntent(pendingIntent);
				mBuilder.setAutoCancel(true);
				int mNotificationId = 10086;
				NotificationManager nNotifyMgr = (NotificationManager) AVOSCloud.applicationContext
						.getSystemService(Context.NOTIFICATION_SERVICE);
				nNotifyMgr.notify(mNotificationId,mBuilder.build());
				System.out.println("运行成功");
			}

		} catch (JSONException e) {
			Toast.makeText(context, e.toString(),Toast.LENGTH_SHORT).show();
		}

	}

}
