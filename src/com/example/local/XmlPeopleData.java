package com.example.local;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.content.Context;

public class XmlPeopleData {
	
	Player [] data =null;
	Context context;
    
	
	public XmlPeopleData(Context c){
		
		context = c;
		
		// make the parsable doc 
		InputStream is = context.getResources().openRawResource(R.raw.people);
		DocumentBuilder builder =null;
		Document doc =null;
		
		try{
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.parse(is);
		}
		catch(Exception e){}
			//extract the nodelist for those 5 tags
			NodeList nameNodeList = doc.getElementsByTagName("name");
			NodeList ageNodeList = doc.getElementsByTagName("age");
			NodeList desNodeList = doc.getElementsByTagName("des");
			NodeList numNodeList = doc.getElementsByTagName("num");
			NodeList clubNodeList = doc.getElementsByTagName("club");
			NodeList countryNodeList = doc.getElementsByTagName("country");
			NodeList latitudeNodeList = doc.getElementsByTagName("latitude");
			NodeList longitudeNodeList = doc.getElementsByTagName("longitude");
			NodeList imageNodeList = doc.getElementsByTagName("image");
			NodeList markerNodeList = doc.getElementsByTagName("marker");
			NodeList urlNodeList = doc.getElementsByTagName("url");
			NodeList positionNodeList = doc.getElementsByTagName("position");
			NodeList rewardNodeList = doc.getElementsByTagName("reward");
			//create data
			
			data  = new Player[nameNodeList.getLength()];
			
			for(int i=0;i<data.length;i++){
				data[i]= new Player(
						nameNodeList.item(i).getFirstChild().getNodeValue(),
						ageNodeList.item(i).getFirstChild().getNodeValue(),
						desNodeList.item(i).getFirstChild().getNodeValue(),
						numNodeList.item(i).getFirstChild().getNodeValue(),
						clubNodeList.item(i).getFirstChild().getNodeValue(),
						countryNodeList.item(i).getFirstChild().getNodeValue(),
						latitudeNodeList.item(i).getFirstChild().getNodeValue(),
						longitudeNodeList.item(i).getFirstChild().getNodeValue(),
						imageNodeList.item(i).getFirstChild().getNodeValue(),
						markerNodeList.item(i).getFirstChild().getNodeValue(),
						urlNodeList.item(i).getFirstChild().getNodeValue(),
						positionNodeList.item(i).getFirstChild().getNodeValue(),
					   rewardNodeList.item(i).getFirstChild().getNodeValue()
					
				);
				
			}
			
			
		}
		
		
		public Player[] getData(){return data;}
		
		
		public Player getPlayer(int i){return data[i];}
		
		public int getLength(){return data.length;}
		
		
		
		
		
		
	
}
