package com.helper.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Community {
	private List<Promotions> promotions;

	public List<Promotions> getPromotions() {
		return promotions;
	}
	public void setPerson(List<Promotions> promotions) {
		this.promotions = promotions;

	}

static class Promotions{
	private int ID;
	private String Name;
	private String Description;
	private float Price;
	private String MinimumPhotoCount;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	
	  public String getMinimumPhotoCount() { 
		  return MinimumPhotoCount; 
	} 
	  
	  public void setMinimumPhotoCount(String minimumPhotoCount) 
	  { 
		  MinimumPhotoCount = minimumPhotoCount; 
	  }
	
	  @Override
	  
	      public  String toString() {
	  
	        return "Promotions [ID=" + ID + ", Name=" + Name + ", Description=" + Description
	  	            + ", Price=" + Price + ", MinimumPhotoCount=" + MinimumPhotoCount + "]";
	  	      }

	
}
}
