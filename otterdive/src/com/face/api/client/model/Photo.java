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

import java.util.ArrayList;
import java.util.List;


/**
 * Photo POJO
 * 
 * @author Marlon Hendred
 *
 */
public class Photo 
{	
	private String url;
	
	private String pid;

	private float width;
	
	private float height;
	
	private List<Face> tags;

	private List<String> tids;
	
	public  String toString()
	{
		 StringBuilder sb = new StringBuilder();
		
		sb.append("  url: ").append(url)
		  .append("\n")
		  .append("  pid: ").append(pid)
		  .append("\n")
		  .append("  width: ").append(width)
		  .append("\n")
		  .append("  height: ").append(height)
		  .append("\n\n");
		
		for (Face f : tags)
		{
			sb.append(f).append("\n");
		}
		
		sb.trimToSize();

		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getFaceCount()
	 */
	public int getFaceCount()
	{
		return tags.size();
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getURL()
	 */
	public String getURL()
	{
		return this.url;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getPID()
	 */
	public String getPID()
	{
		return this.pid;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getWidth()
	 */
	public float getWidth()
	{
		return this.width;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getHeight()
	 */
	public float getHeight()
	{
		return this.height;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getFaces()
	 */
	public List<Face> getFaces()
	{
		return this.tags;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Photo#getTIDs()
	 */
	public List<String> getTIDs()
	{
		if (tids == null)
		{
			tids = new ArrayList<String>();
			
			for (Face f : getFaces())
			{
				tids.add(f.getTID());
			}
		}
		
		return tids;
	}
}