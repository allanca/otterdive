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

import java.util.Date;

public class UserStatus
{
	private String uid;
	
	private int training_set_size;
	
	private long last_trained;
	
	private boolean training_in_progress;
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.UserStatus#getUID()
	 */
	public String getUID()
	{
		return this.uid;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.UserStatus#setSize()
	 */
	public int setSize()
	{
		return this.training_set_size;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.UserStatus#isInProgress()
	 */
	public boolean isInProgress()
	{
		return this.training_in_progress;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.UserStatus#getLastTrained()
	 */
	public Date getLastTrained()
	{
		return new Date(this.last_trained);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("   uid: ").append(uid)
		  .append("\n")
		  .append("   training set size: ").append(training_set_size)
		  .append("\n")
		  .append("   in progress: ").append(training_in_progress)
		  .append("\n")
		  .append("   last trained: ").append(getLastTrained())
		  .append("\n")
		  .trimToSize();
		
		return sb.toString();
	}
}