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

package com.face.api.client.model;


public class UsageStats 
{
	private int used;
	
	private int limit;

	private int remaining;
		
	private long reset_time;	

	private String reset_time_text;
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.UsageStats#getUsed()
	 */
	public int getUsed() 
	{
		return used;
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.model.UsageStats#getRemaining()
	 */
	public int getRemaining() 
	{
		return remaining;
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.model.UsageStats#getLimit()
	 */
	public int getLimit() 
	{
		return limit;
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.model.UsageStats#getReset_time_text()
	 */
	public String getReset_time_text() 
	{
		return reset_time_text;
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.model.UsageStats#getReset_time()
	 */
	public long getReset_time() 
	{
		return reset_time;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("---USAGE STATS---\n")
		  .append("used: ").append(used)
		  .append("\n")
		  .append("limit: ").append(limit)
		  .append("\n")
		  .append("remaining: ").append(remaining)
		  .append("\n")
		  .append("reset: ").append(reset_time_text)
		  .append("\n")
		  .append("-----------------\n")
		  .trimToSize();
		
		return sb.toString();
	}
}