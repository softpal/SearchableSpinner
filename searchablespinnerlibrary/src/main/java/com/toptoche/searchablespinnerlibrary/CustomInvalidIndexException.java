package com.toptoche.searchablespinnerlibrary;



import java.io.IOException;

/**
 The type Custom invalid index exception.
 */
public class CustomInvalidIndexException extends IOException
{
	//Exception Messges to show the user
	public final static String msgCustomInvalidIndexException = "Please Sync the Data to proceed";
	private static final String TAG = CustomInvalidIndexException.class.getSimpleName();
	
	@Override
	public String getMessage()
	{
		return msgCustomInvalidIndexException;
	}
}
