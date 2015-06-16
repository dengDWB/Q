package com.d.activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.q.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText etNameRegister, etPhoneNumbleRegister, etIdCardRegister,
			etPasswordRegister;
	private Button btnAffirmRegister;
	private String name = "";
	private String phone = "";
	private String password = "";
	private String card = "";
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		bindAndListener();
	}

	public void bindAndListener() {
		btnAffirmRegister = (Button) findViewById(R.id.btnAffirmRegister);
		
		etNameRegister = (EditText) findViewById(R.id.etNameRegister);
		etPhoneNumbleRegister = (EditText) findViewById(R.id.etPhoneNumbleRegister);
		etIdCardRegister = (EditText) findViewById(R.id.etIdCardRegister);
		etPasswordRegister = (EditText) findViewById(R.id.etPasswordRegister);
		

		btnAffirmRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = etNameRegister.getText().toString();
				phone = etPhoneNumbleRegister.getText().toString();
				card = etIdCardRegister.getText().toString();
				password = etPasswordRegister.getText().toString();

				check(name, card, phone, password);
				

			}

		});
	}
	
	public void check(String name, String card, String phone,String password){
		if(name.equals("")){
			Toast.makeText(context, "名字不能为空", Toast.LENGTH_SHORT).show();
		}else if(card.equals("")){
			Toast.makeText(context, "身份证不能为空", Toast.LENGTH_SHORT).show();
		}else if(phone.equals("")){
			Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
		}else if(password.equals("")){
			Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
		}else{
			register();
		}
	}
	
	public void register() {
		SignUpCallback signUp = new SignUpCallback() {

			@Override
			public void done(AVException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					Intent intent = new Intent(context,NormalActivity.class);
					startActivity(intent);
					finish();
					Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(context, "失败成功", Toast.LENGTH_SHORT).show();
				}
			}

		};
		AVUser user = new AVUser();
		user.setUsername(phone);
		user.put("name", name);
		user.put("ID", card);
		user.setPassword(password);
		user.signUpInBackground(signUp);

	}

}
