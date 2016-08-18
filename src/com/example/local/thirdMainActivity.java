package com.example.local;




import java.io.IOException;
import java.io.InputStream;

import com.google.android.gms.maps.GoogleMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class thirdMainActivity extends ActionBarActivity {
	TextView name;
	TextView club;
	TextView age;
	TextView num;
	TextView country;
	TextView des;
	ImageView icon;
	TextView reward;
	private static XmlPeopleData data = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		 data = new XmlPeopleData(this);
		name = (TextView)findViewById(R.id.name);
		club = (TextView)findViewById(R.id.club);
		age = (TextView)findViewById(R.id.age);
		country = (TextView)findViewById(R.id.country);
		num = (TextView)findViewById(R.id.num);
		icon = (ImageView)findViewById(R.id.imageView1);
		des = (TextView)findViewById(R.id.descText);
		reward = (TextView)findViewById(R.id.rewardText);
		 Intent intent = getIntent();
	    Player person = (Player) intent.getSerializableExtra("ssss");
        name.setText(person.getName());
        club.setText(person.getClub());
        age.setText(person.getAge());
        num.setText(person.getNum());
        country.setText(person.getCountry());
        des.setText(person.getDes());
        reward.setText(person.getReward());
        icon.setImageBitmap(getBitmapFromAssets(this, person.getImage()));
	}
	
	public static Bitmap getBitmapFromAssets(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }

	 
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
					getMenuInflater().inflate(R.menu.two, menu);
			return true;
		}
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
	       int itemId = item.getItemId();
			switch (itemId) {
			case R.id.page:
	            view();
	       
				break;
			case R.id.back:
				 finish();
				 break;
			default:
				break;
				
			}

			return true;
		}

		private void view() {
			
			 Intent intent = getIntent();
			Player person = (Player) intent.getSerializableExtra("ssss");
			Log.i("player", person.getClub());
//			startActivity(intent);
			
			Intent pass = new Intent(this, webMainActivity.class);
			pass.putExtra("pass", person);
			startActivity(pass);
		}
	  
	
	  
	
	
	
}
