package com.example.local;




import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.plus.model.people.Person;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private static XmlPeopleData data = null;
	  private ImageView image = null;
	  View listColor;
	  private Player p = null;
	  TextView textview;
	  private SharedPreferences settings;
	  int[] imageid ={R.drawable.rl,R.drawable.qc1,R.drawable.en,R.drawable.q,R.drawable.qc};
		 public int currentimageindex=0;
		ImageView i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		 textview =(TextView) findViewById(R.id.textView1);
		 data = new XmlPeopleData(this);
		 settings = getSharedPreferences("loginPrefs", 0);
	     ListView lv = (ListView) findViewById(android.R.id.list);
		 setListAdapter (new IconAdapter(this));
		 final Handler mHandler = new Handler();
	        
	        final Runnable mUpdateResults = new Runnable() {
	            public void run() {
	                
	                AnimateandSlideShow();
	                
	            }

				private void AnimateandSlideShow() {
					// TODO Auto-generated method stub

			    	i = (ImageView)findViewById(R.id.imageView1);
			   		i.setImageResource(imageid[currentimageindex%imageid.length]);
			   		
			   		currentimageindex++;
			    	
			   		Animation rotateimage = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
			    	  
			        
			    	  i.startAnimation(rotateimage);
			          
				}
	        };	        
	        int delay = 50; 
	        int period = 6000; 
	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {

	             mHandler.post(mUpdateResults);

	        }

	        }, delay, period);
	        
	        
	        
	        
		 
		 
		 
		 OnItemClickListener listen = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {			     
				Player player = data.getPlayer(position);	
				if(listColor!=null){					
				    listColor.setBackgroundColor(Color.YELLOW);
				    listColor=view;
				}else{
				    listColor=view;
				}
				listColor.setBackgroundColor(Color.YELLOW);				
				Intent intent = new Intent(MainActivity.this,secondMainActivity.class);				
				intent.putExtra("s", player);	
				SharedPreferences.Editor editor = settings.edit();
				  editor.putString("i", position+"");
				  editor.commit();			
		    	startActivity(intent);			  		    		
			}			 
		 };
		lv.setOnItemClickListener(listen);
		 		
   OnItemLongClickListener listener = new  OnItemLongClickListener(){
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
//		Toast.makeText(getBaseContext(), "sdsd", Toast.LENGTH_LONG).show();
		Player player = data.getPlayer(position);	 
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Name:    "+player.getName());
		builder.setMessage("Age:    "+player.getAge()+"\n"+
				           "Num:    "+player.getNum()+"\n"+
				           "Country:    "+player.getCountry());	
		builder.setPositiveButton("very like", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "very like",
					       Toast.LENGTH_LONG).show();				
			}}).setNeutralButton("not like!", new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "not like!",
					       Toast.LENGTH_LONG).show();	
			}}).setNegativeButton("just soso!", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "just soso",
					       Toast.LENGTH_LONG).show();	
			}});
		
		
		builder.show();
		return true;		
	}	
   };   	   
   lv.setOnItemLongClickListener(listener);  
	}

	class  IconAdapter extends ArrayAdapter{
		    Player[]  p =  data.getData();
		   
		   Activity context;
		   IconAdapter( Activity context){
			   super(context,R.layout.row,data.data);
			   this.context = context;
			
			   
		   }
		   public View getView(int position,View convertView,ViewGroup parent){
			   LayoutInflater inflater = context.getLayoutInflater();
			   View row = inflater.inflate(R.layout.row,null);
			   TextView name = (TextView) row.findViewById(R.id.name);
			   TextView club = (TextView) row.findViewById(R.id.showclub);
			   TextView pseat = (TextView) row.findViewById(R.id.showposition);
			
			   ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
			   Player p = data.getPlayer(position);
			   iv.setImageBitmap(getBitmapFromAssets(context,p.getImage()));
			
			   name.setText(p.getName());
			   club.setText(p.getClub());
			   pseat.setText(p.getPosition());
			   
			   

			   return (row);
		   }
		


//		   
	   }
	 public Bitmap getBitmapFromAssets(Context context, String filePath) {
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
			switch (item.getItemId()) {

			
				
			default:
				break;
			}
			return super.onOptionsItemSelected(item);
		}

}
