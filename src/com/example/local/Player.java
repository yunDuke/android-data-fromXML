package com.example.local;

import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;



public class Player implements Serializable{
	private String  name = null;
	private String  age = null;
	private String  des = null;
	private String  num = null;
	private String  club = null;
	private String  country = null;
	private String  latitude = null;
	private String  longitude = null;
	private String  image = null;
	private String  marker = null;
	private String  url = null;
	private String position = null;
	private String reward= null;
	public  Player(String _name, String _age,String _des,String _num,String _club,String _country,String _latitude,String _longitude, String _image,String _marker, String _url,String _position,String _reward){
		name = _name;
		age =_age;
		des = _des;
		num = _num;
		club = _club;
		country = _country;
		latitude = _latitude;
		longitude = _longitude;
		
		image = _image;
		marker=_marker;
		url =_url;	
		position= _position;
		reward = _reward;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}

	public LatLng getLatLng() {
		double l = Double.parseDouble(getLatitude());
		double w = Double.parseDouble(getLongitude());
		LatLng latLng = new LatLng(l,w);
		return latLng;
	}

	
	
}
