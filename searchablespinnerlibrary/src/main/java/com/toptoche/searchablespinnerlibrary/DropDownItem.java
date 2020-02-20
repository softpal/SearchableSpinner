package com.toptoche.searchablespinnerlibrary;
import java.io.Serializable;

/**
 The type Drop down item.
 */
public class DropDownItem implements Serializable
{
	private int id;
	private String name;
	
	/**
	 Instantiates a new Drop down item.
	 
	 @param id   the id
	 @param name the name
	 */
	public DropDownItem(int id,String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 @param obj
	 
	 @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof DropDownItem)
		{
		DropDownItem c = (DropDownItem)obj;
		return c.getName().equals(name) && c.getId() == id;
	}
		
		return false;
	}
	
	/**
	 Gets name.
	 
	 @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 Gets id.
	 
	 @return the id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 Sets id.
	 
	 @param id the id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 Sets name.
	 
	 @param name the name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	//to display object as a string in spinner

	@Override
	public String toString()
	{
		return name;
	}
}

