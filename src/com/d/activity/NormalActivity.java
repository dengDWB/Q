package com.d.activity;


import com.d.fragment.HomePageFragment;
import com.d.fragment.MeFragment;
import com.d.fragment.ReportFragment;
import com.example.q.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class NormalActivity extends Activity implements OnClickListener {

	private TextView tvHome, tvReport, tvMy;

	private HomePageFragment homeFragment;
	private MeFragment meFragment;
	private ReportFragment reportFragment;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fragments);
		init();
		fragmentManager = getFragmentManager();
		setSelection(0);
	}

	public void init() {
		bindId();
		setListener();
	}

	public void bindId() {
		tvHome = (TextView) findViewById(R.id.tvHome);
		tvReport = (TextView) findViewById(R.id.tvReport);
		tvMy = (TextView) findViewById(R.id.tvMy);

	}

	public void setListener() {
		tvHome.setOnClickListener(this);
		tvReport.setOnClickListener(this);
		tvMy.setOnClickListener(this);
	}

	public void setSelection(int index) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragment(transaction);
		switch (index) {
		case 0:
			tvHome.setTextColor(Color.BLUE);
			if (homeFragment == null) {
				homeFragment = new HomePageFragment();
				transaction.add(R.id.frameLayout, homeFragment);
			} else {
				transaction.show(homeFragment);
			}

			break;

		case 1:
			tvReport.setTextColor(Color.BLUE);
			if (reportFragment == null) {
				reportFragment = new ReportFragment();
				transaction.add(R.id.frameLayout, reportFragment);
			} else {
				transaction.show(reportFragment);
			}
			break;

		case 2:
			tvMy.setTextColor(Color.BLUE);
			if (meFragment == null) {
				meFragment = new MeFragment();
				transaction.add(R.id.frameLayout, meFragment);
			} else {
				transaction.show(meFragment);
			}
			break;
		}
		transaction.commit();
	}

	public void hideFragment(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (meFragment != null) {
			transaction.hide(meFragment);
		}
		if (reportFragment != null) {
			transaction.hide(reportFragment);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvHome:
			setSelection(0);
			break;

		case R.id.tvReport:
			setSelection(1);
			break;
		case R.id.tvMy:
			setSelection(2);
			break;
		}

	}

}
