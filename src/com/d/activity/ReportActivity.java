package com.d.activity;


import java.io.File;

import com.d.table.Evidence;
import com.example.q.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReportActivity extends Activity implements OnClickListener {

	private LinearLayout photoLayout;
	private EditText etTheme, etContent;
	private Button btnCancel, btnUpload;
	private TextView tvPhoto, tvRadition, tvAtmosphere, tvZoology, tvWater,
			tvBuild, tvSolid, tvNoise, tvOther;
	private String theme = "", content = "", pollutionCategory = "";
	private Context context = this;
	private Evidence evidence = new Evidence();
	private Intent intent;
	private final int TACK_PICTURE = 0;
	private final int PHOTO = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		bindAndListener();
	}

	public void bindAndListener() {
		photoLayout = (LinearLayout) findViewById(R.id.photoLayout);
		etTheme = (EditText) findViewById(R.id.etTheme);
		etContent = (EditText) findViewById(R.id.etContent);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnUpload = (Button) findViewById(R.id.btnUpload);

		tvPhoto = (TextView) findViewById(R.id.tvPhoto);
		tvRadition = (TextView) findViewById(R.id.tvRadition);
		tvAtmosphere = (TextView) findViewById(R.id.tvAtmosphere);
		tvZoology = (TextView) findViewById(R.id.tvZoology);
		tvWater = (TextView) findViewById(R.id.tvWater);
		tvBuild = (TextView) findViewById(R.id.tvBuild);
		tvSolid = (TextView) findViewById(R.id.tvSolid);
		tvNoise = (TextView) findViewById(R.id.tvNoise);
		tvOther = (TextView) findViewById(R.id.tvOther);

		btnCancel.setOnClickListener(this);
		btnUpload.setOnClickListener(this);
		tvPhoto.setOnClickListener(this);
		tvRadition.setOnClickListener(this);
		tvAtmosphere.setOnClickListener(this);
		tvZoology.setOnClickListener(this);
		tvWater.setOnClickListener(this);
		tvBuild.setOnClickListener(this);
		tvSolid.setOnClickListener(this);
		tvNoise.setOnClickListener(this);
		tvOther.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCancel:
			intent = new Intent(context, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btnUpload:

			theme = etTheme.getText().toString();
			content = etContent.getText().toString();
			break;
		case R.id.tvPhoto:
			showDialog();
			break;
		case R.id.tvRadition:
			pollutionCategory = "辐射污染";
			break;
		case R.id.tvAtmosphere:
			pollutionCategory = "大气污染";
			break;
		case R.id.tvZoology:
			pollutionCategory = "生态污染";
			break;
		case R.id.tvWater:
			pollutionCategory = "水污染";
			break;
		case R.id.tvBuild:
			pollutionCategory = "建设污染";
			break;
		case R.id.tvSolid:
			pollutionCategory = "固废污染";
			break;
		case R.id.tvNoise:
			pollutionCategory = "噪声污染";
			break;
		case R.id.tvOther:
			pollutionCategory = "其他污染";
			break;

		default:
			break;
		}
	}

	public void showDialog(){
		new AlertDialog.Builder(this).setTitle("图片选择").setPositiveButton("拍照", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, TACK_PICTURE);
			}
		}).setNegativeButton("相册", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PHOTO);
			}
		}).show();
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   if (requestCode == TACK_PICTURE && resultCode == RESULT_OK) {
	      Uri uri = data.getData();
	      File file = new File(uri.toString());
	      Cursor cursor = getContentResolver().query(uri, null, null, null,null);
	      if (cursor != null && cursor.moveToFirst()) {
	          String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
	    } else if (requestCode == PHOTO && resultCode == RESULT_OK) {
	        Bundle bundle = data.getExtras();
	        // 获取相机返回的数据，并转换为Bitmap图片格式 ，这是缩略图
	        Bitmap bitmap = (Bitmap) bundle.get("data");
	     }
	   }
	 }
}
