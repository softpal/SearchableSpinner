package com.toptoche.searchablespinnerlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/**
 The type Custom searchable spinner adapter.
 */
public class CustomSearchableSpinnerAdapter extends ArrayAdapter<DropDownItem> implements SpinnerAdapter
{
	private static final String TAG = CustomSearchableSpinnerAdapter.class.getSimpleName();
	private final int mResource;
	private Context mContext = null;
	private List<DropDownItem> mFilteredList;
	
	/**
	 Instantiates a new Custom searchable spinner adapter.
	 
	 @param activity the activity
	 @param resource the resource
	 @param objects  the objects
	 */
	public CustomSearchableSpinnerAdapter(@NonNull AppCompatActivity activity,int resource,@NonNull List<DropDownItem> objects)
	{
		super(activity,resource,objects);
		if(! (activity.isFinishing()))
		{
			mContext = activity;
		}
		
		mResource = resource;
		mFilteredList = new ArrayList<DropDownItem>(objects);
	}
	
	/**
	 Gets dropdown list.
	 
	 @return the dropdown list
	 */
	public List<DropDownItem> getDropdownList()
	{
		return mFilteredList;
	}
	
	/**
	 Sets dropdown list.
	 
	 @param dropdownList the dropdown list
	 */
	public void setDropdownList(List<DropDownItem> dropdownList)
	{
		mFilteredList = dropdownList;
	}
	
	/**
	 Gets item position.
	 
	 @param itemName the item name
	 
	 @return the item position
	 
	 @throws CustomInvalidIndexException the custom invalid index exception
	 */
	public int getItemPosition(String itemName) throws CustomInvalidIndexException
	{
		int i, position = - 1;
		if(! StringUtils.isEmpty(itemName))
		{
			if(mFilteredList != null && mFilteredList.size() > 0)
			{
				for(i = 0;i < mFilteredList.size();i++)
				{
					if(mFilteredList.get(i).getName().equals(itemName))
					{
						position = i;
						break;
					}
				}
			}
		}
		if(position < 0)
		{
			throw new CustomInvalidIndexException();
		}
		
		return position;
	}
	
	/**
	 @return
	 */
	public int getCount()
	{
		int size = 0;
		if(mFilteredList != null && mFilteredList.size() > 0)
		{
			size = mFilteredList.size();
		}
		return size;
	}
	
	/**
	 @param i
	 
	 @return
	 */
	@Nullable
	@Override
	public DropDownItem getItem(int i)
	{
		DropDownItem item = null;
		if(mFilteredList != null && mFilteredList.size() > 0 && i < mFilteredList.size())
		{
			item = mFilteredList.get(i);
		}
		return item;
	}
	
	/**
	 @param i
	 
	 @return
	 */
	public long getItemId(int i)
	{
		if(mFilteredList != null && mFilteredList.size() > 0 && i < mFilteredList.size())
		{
			return (long)i;
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 @param i
	 @param view
	 @param viewgroup
	 
	 @return
	 */
	//This is for view of spinner item before it is opened
	/*
	The value shown on the spinner before the user presses the spinner, where each value view can be adjusted with the convertView parameter
	 */
	
	public View getView(int i,View view, ViewGroup viewgroup)
	{
		final View viewItem = LayoutInflater.from(mContext).inflate(mResource,viewgroup,false);
		
		AppCompatTextView itemTv = viewItem.findViewById(R.id.spinner_search_item);
		
		if(mContext != null)
		{
			VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(mContext.getResources(),R.drawable.ic_arrow_drop_down_black_24dp,itemTv.getContext().getTheme());
			itemTv.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,drawableCompat,null);
		}
		
		if(i < getCount())
		{
			itemTv.setText(mFilteredList.get(i).getName());
		}
		
		return itemTv;
	}
	
	/**
	 @param position
	 @param convertView
	 @param parent
	 
	 @return
	 */
	//This is for view of spinner item after it is opened in popup
	/*
	 List of values the user can select after the user presses the spinner, where each value in the mFilteredList can be adapted with the convertView parameter
	 */
	@Override
	public View getDropDownView(int position,View convertView, ViewGroup parent)
	{
		
		final View viewItem = LayoutInflater.from(mContext).inflate(mResource,parent,false);
		
		AppCompatTextView itemTv = viewItem.findViewById(R.id.spinner_search_item);
		itemTv.setText(mFilteredList.get(position).getName());
		
		return itemTv;
	}
}
