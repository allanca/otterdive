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

import java.util.List;

import com.face.api.client.model.Photo;
import com.face.api.client.response.PhotoResponse;

public class PhotoResponseImpl extends BaseResponseImpl implements PhotoResponse
{
	private List<Photo> photos;
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.PhotoReponse#getPhotos()
	 */
	public List<Photo> getPhotos()
	{
		return this.photos;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.PhotoReponse#getPhoto()
	 */
	public Photo getPhoto()
	{
		return photos.get(0);
	}
	
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString())
		  .append("\n")
		  .trimToSize();
		
		for (Photo p : photos)
		{
			sb.append(p);
		}
		
		return sb.toString();
	}
}