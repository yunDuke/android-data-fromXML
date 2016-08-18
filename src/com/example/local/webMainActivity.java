package com.example.local;




import com.google.android.gms.maps.GoogleMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class webMainActivity extends ActionBarActivity {
	WebView web;
	TextView tx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.web);
		 
		 Intent intent = getIntent();
		 Player person = (Player) intent.getSerializableExtra("pass");	
		 tx = (TextView)findViewById(R.id.web);
		 tx.setText(person.getName());
		 display();
	    Toast.makeText(getBaseContext(), person.getUrl(), Toast.LENGTH_LONG).show();
	}
	@SuppressLint("SetJavaScriptEnabled")
	private void display() {
		 Intent intent = getIntent();
		 Player person = (Player) intent.getSerializableExtra("pass");	
		web = (WebView) findViewById(R.id.webView1);
		web.getSettings().setJavaScriptEnabled(true);
		
		web.setWebViewClient(new WebViewClient());
		web.loadUrl(person.getUrl());
		web.setWebChromeClient(new WebChromeClient());
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
       int itemId = item.getItemId();
		switch (itemId) {
		
		
		case R.id.back:
            
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
			break;
		
		default:
			break;
			
		}

		return true;
	}


	
}
