/*
 * Copyright (c) 2010 Marlon Hendred
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.face.api.client.response.impl;

import java.util.Collections;
import java.util.List;

import com.face.api.client.model.UsageStats;
import com.face.api.client.response.BaseResponse;

public abstract class BaseResponseImpl implements BaseResponse 
{	
	private UsageStats usage;

	private String status;
	
	private String error_message;
	
	private int error_code;
	
	protected static String resource;
	
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append("-----STATUS-----\n")
		  .append("status: ").append(status)
		  .append("\n");
		  
		  if (error_code != 0)
		  {
			  sb.append("error text: ").append(error_message)
			  	.append("\n")
			  	.append("error code: ").append(error_code)
			  	.append("\n");
		  }
		  
		  if (usage != null) 
		  {
			  sb.append(usage.toString());
		  }
		  
		  else 
		  {
			  sb.append("----------------\n");
		  }
		
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.BaseResponse#getStatus()
	 */
	public final String getStatus()
	{
		return status;
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.response.BaseResponse#getErrorMsg()
	 */
	public final String getErrorMsg()
	{
		return error_message;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.BaseResponse#getErrorCode()
	 */
	public final int getErrorCode()
	{
		return error_code;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.BaseResponse#getUsage()
	 */
	public final UsageStats getUsage()
	{
		return usage;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.BaseResponse#getResource()
	 */
	public String getResource()
	{
		return resource;
	}
	
	@SuppressWarnings("unchecked")
	protected final <T extends Object> List<T> getNonNullList(List<T> list)
	{
		return (List<T>) ((list == null) ? Collections.EMPTY_LIST : list);
	}
}