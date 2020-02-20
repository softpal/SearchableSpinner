package com.toptoche.searchablespinnerlibrary;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 The type Spinner utils.
 */
public final class SpinnerUtils
{
	private static final String TAG = SpinnerUtils.class.getSimpleName();
	
	/**
	 Add items for spinner and set selection.
	 
	 @param list                  the list
	 @param spn                   the spn
	 @param mActivity             the m activity
	 @param col_content           the col content
	 @param AlreadyContainsSelect the already contains select
	 @param spn_item              the spn item
	 */
	public static void addItemsForSpinnerAndSetSelection(List<DropDownItem> list,SearchableSpinner spn,AppCompatActivity mActivity,CoordinatorLayout col_content,boolean AlreadyContainsSelect,String spn_item)
	{
		addItemsForSpinner(list,spn,mActivity,col_content,AlreadyContainsSelect);
		selectSpinnerItem(spn,col_content,spn_item);
	}
	
	/**
	 Add items for spinner.
	 
	 @param list           the list
	 @param spn            the spn
	 @param mActivity      the m activity
	 @param col_content    the col content
	 @param containsSelect the contains select
	 */
	public static void addItemsForSpinner(@NonNull List<DropDownItem> list,SearchableSpinner spn,AppCompatActivity mActivity,CoordinatorLayout col_content,boolean containsSelect)
	{
		if(! (mActivity.isFinishing()))
		{
			String select_item = mActivity.getResources().getString(R.string.select_item);
			if(! containsSelect)
			{
				if(list.size() > 0)
				{
					if(! ((list.get(0).getName()).equals(mActivity.getResources().getString(R.string.select_item))))
					{
						DropDownItem downItem = new DropDownItem(0,select_item);
						list.add(0,downItem);
					}
				}
				
			}
		}
		else
		{
			return;
		}
		
		CustomSearchableSpinnerAdapter adapter = (CustomSearchableSpinnerAdapter)spn.getAdapter();
		
		if(adapter != null)
		{
			if(adapter.getDropdownList() != null)
			{
				if(! ((adapter.getDropdownList()).equals(list)))
				{
					adapter.setDropdownList(list);
					adapter.notifyDataSetChanged();
				}
				else
				{
					adapter.notifyDataSetChanged();
				}
			}
			else
			{
				adapter.setDropdownList(list);
				adapter.notifyDataSetChanged();
			}
		}
		else
		{
			adapter = new CustomSearchableSpinnerAdapter(mActivity,R.layout.spinner_search_item,list);
			spn.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}
	
	/**
	 Select spinner item.
	 
	 @param spn         the spn
	 @param col_content the col content
	 @param spn_item    the spn item
	 */
	public static void selectSpinnerItem(SearchableSpinner spn,CoordinatorLayout col_content,String spn_item)
	{
		
		CustomSearchableSpinnerAdapter adapter = ((CustomSearchableSpinnerAdapter)(spn.getAdapter()));
		if(adapter != null)
		{
			try
			{
				int position = adapter.getItemPosition(spn_item);
				spn.setSelection(position);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//MyUtils.getSnackbar(col_content,e.getMessage());
			}
		}
	}
	
	/**
	 Add item for existing spinner.
	 
	 @param item      the item
	 @param spn       the spn
	 @param mActivity the m activity
	 */
	public static void addItemForExistingSpinner( DropDownItem item,SearchableSpinner spn,AppCompatActivity mActivity)
	{
		CustomSearchableSpinnerAdapter adapter = (CustomSearchableSpinnerAdapter)spn.getAdapter();
		List<DropDownItem> list;
		if(adapter != null)
		{
			if(adapter.getDropdownList() != null)
			{
				list = adapter.getDropdownList();
				list.add(item);
				adapter.setDropdownList(list);
				adapter.notifyDataSetChanged();
			}
			else
			{
				list = new ArrayList<DropDownItem>();
				list.add(item);
				adapter.setDropdownList(list);
				adapter.notifyDataSetChanged();
			}
		}
		else
		{
			
			if(! (mActivity.isFinishing()))
			{
				
				list = new ArrayList<DropDownItem>();
				list.add(item);
				adapter = new CustomSearchableSpinnerAdapter(mActivity,R.layout.spinner_search_item,list);
				spn.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
			
		}
	}
}


