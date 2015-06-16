package com.d.activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.q.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends Activity implements OnClickListener {
	private TextView tvForgetPassword, tvRegister;
	private Button btnEnter;
	private Intent intent;
	private EditText etUserName, etPassword;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bindAndListener();
		
	}

	public void bindAndListener() {

		tvForgetPassword = (TextView) findViewById(R.id.tvForgetPassword);
		tvRegister = (TextView) findViewById(R.id.tvRegister);

		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);

		btnEnter = (Button) findViewById(R.id.btnEnter);

		tvForgetPassword.setOnClickListener(this);
		tvRegister.setOnClickListener(this);

		btnEnter.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvForgetPassword:

			break;
		case R.id.tvRegister:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btnEnter:
			check(etUserName.getText().toString(),etPassword.getText().toString());
			
			break;
		}
	}
	
	public void check(String phoneNumber,String password) {
		if (phoneNumber.equals("")) {
			Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
		} else if (password.equals("")) {
			Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
		} else {
			logIn(phoneNumber,password);
		}
	}
	
	public void logIn(String phoneNumber, String password){
		
		AVUser.logInInBackground(phoneNumber, password,
				new LogInCallback<AVUser>() {

					@Override
					public void done(AVUser user, AVException e) {
						if (user != null) {
							Intent intent = new Intent(context,NormalActivity.class);
							startActivity(intent);
							finish();
							Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();

						} else {
							Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
						}

					}
				});
		
	}
}
