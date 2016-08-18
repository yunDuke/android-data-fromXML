package com.example.local;




import java.io.IOException;
import java.io.InputStream;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.os.Parcelable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;







public class secondMainActivity extends ActionBarActivity {
  private TextView name = null;
  private TextView num = null;
  private TextView club = null;
 
  private TextView des = null;
  private static XmlPeopleData data = null;
	GoogleMap mMap;
	
	MapFragment sMapFragment;
  Button more;
 ImageView icon;
  private Button web;
   int currentPerson;
  private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details);
        
        settings = getSharedPreferences("loginPrefs", 0);
        data = new XmlPeopleData(this);
        MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        
        mMap = fragment.getMap();         
        currentPerson = 4;
        name = (TextView)findViewById(R.id.textView4);
        num = (TextView)findViewById(R.id.textView6);
        club=(TextView)findViewById(R.id.textView8);
        des = (TextView)findViewById(R.id.descText) ;
       web=(Button)findViewById(R.id.button1);
        icon = (ImageView)findViewById(R.id.inner);
        final Intent intent = getIntent();
      
        final Player person = (Player) intent.getSerializableExtra("s");
      
        icon.setImageBitmap(getBitmapFromAssets(this, person.getImage()));
        String id  = settings.getString("i", "");
        Log.d("s", id);
        int d = Integer.parseInt(id);
      currentPerson = d;
        name.setText(person.getName());
        des.setText(person.getDes());
        num.setText(person.getNum());
        club.setText(person.getClub());
        
      web.setOnClickListener(
    		  
    		  new OnClickListener(){

				@Override
				public void onClick(View v) {
					 String id  = settings.getString("i", "");
					 int i = Integer.parseInt(id);
					 Log.i("string id", id);
			    	final Player person = (Player) intent.getSerializableExtra("s");
					Intent intent = new Intent(getApplicationContext(),thirdMainActivity.class);
					intent.putExtra("ssss", data.getPlayer(i));
					startActivity(intent);
				}}
    		  
    		  
    		  );
      
//        icon.setImageResource(R.drawable.messi);
        
        if(mMap!=null){
      
         CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(person.getLatLng(),17);
         mMap.animateCamera(cameraUpdate);
         
         String markerTitle = person.getMarker().equals("") ?
					person.getName() :
					person.getMarker();
					
			mMap.addMarker(new MarkerOptions()
				.position(person.getLatLng())
				.title(markerTitle)
				.anchor(.5f, .5f)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
					);
         
        }
        
        

         
       }
        
      
       
        
	
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
       int itemId = item.getItemId();
		switch (itemId) {
		
		case R.id.mapTypeNormal:
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.mapTypeSatellite:
			mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.mapTypeTerrain:
			mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		case R.id.previous:
            privous();
       
			break;
		case R.id.first:
           finish();
       
			break;
		
		default:
			break;
			
		}

		return true;
	}
  
//
//	private void moreinfo() {
//		Intent intent = getIntent();
//		Player person = (Player) intent.getSerializableExtra("s");
//		
//		Toast.makeText(getBaseContext(), person.getClub(), Toast.LENGTH_LONG).show();
//		
//		Intent zhuan = new Intent(this,thirdMainActivity.class);
//		zhuan.putExtra("ssss", person);
//		startActivity(zhuan);
//		
//		
//	}






	
	private void privous() {
		
        		
      currentPerson--;
      
		if(currentPerson<0) {currentPerson = data.getLength()-1;
		Log.i("daosda", currentPerson+"");}
		  Player p = data.getPlayer(currentPerson);
		  int id = currentPerson;
		 
		 
		  StringBuilder sb = new StringBuilder();
		  sb.append("");
		  sb.append(id);
		  String strI = sb.toString();
		  SharedPreferences.Editor editor = settings.edit();
		  editor.putString("i", strI);
		  editor.commit();
		  Log.i("id cun", strI);
		  
		  String id2  = settings.getString("i", "");
			 int i = Integer.parseInt(id2);
			 Log.i("string idsss", id2);
		  
		 
		  name.setText(p.getName());
	    icon.setImageBitmap(getBitmapFromAssets(getApplicationContext(), p.getImage()));
	    des.setText(p.getDes());
		Toast.makeText(getBaseContext(), p.getClub(), Toast.LENGTH_LONG).show();
		 num.setText(p.getNum());
	        club.setText(p.getClub());
        if(mMap!=null){
      
         CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(p.getLatLng(),17);
         mMap.animateCamera(cameraUpdate);
         
         String markerTitle = p.getMarker().equals("") ?
					p.getName() :
					p.getMarker();
					
			mMap.addMarker(new MarkerOptions()
				.position(p.getLatLng())
				.title(markerTitle)
				.anchor(.5f, .5f)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
					);
         
        }
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

  
    
}
