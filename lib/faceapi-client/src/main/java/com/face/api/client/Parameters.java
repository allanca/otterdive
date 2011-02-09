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

package com.face.api.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
/**
 * Class to hold parameters for post requests
 * 
 * @author mhendred
 *
 */
public class Parameters extends HashMap<String, String>
{
	private static final long serialVersionUID = 1L;
	
	public Parameters()
	{
		super();	
	}
	
	/**
	 * Convenience constructor for initialize single name value pairs
	 * 
	 * @param name
	 * @param value
	 */
	public Parameters(String key, String value)
	{
		this.put(key, value);
	}

	public String put(String key, float value)
	{
		return put(key, String.valueOf(value));
	}
	
	public String put(String key, boolean value)
	{
		return put(key, String.valueOf(value));
	}
	
	public String put(String key, int value)
	{
		return put(key, String.valueOf(value));
	}
	
	/**
	 * Removes keys with {@code null} values
	 */
	@Override
	public String put (String key, String value)
	{
		if (value != null)
		{
			return super.put(key, value);
		}
		
		else
		{
			this.remove(key);
			return null;
		}
	}
	
	public List<NameValuePair> asList()
	{
		final List<NameValuePair> list = new LinkedList<NameValuePair>();
		
		for(String key : this.keySet())
		{
			list.add(new BasicNameValuePair(key, this.get(key)));
		}
		
		return list;
	}
}