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

import com.face.api.client.model.UserStatus;
import com.face.api.client.response.TrainResponse;

public final class TrainResponseImpl extends BaseResponseImpl implements TrainResponse 
{
	private List<UserStatus> created;

	private List<UserStatus> no_training_set;

	private List<UserStatus> updated;

	private List<UserStatus> unchanged;

	private List<UserStatus> in_progress;
	
	/* (non-Javadoc)
	 * @see com.face.api.client.response.TrainResponse#getCreated()
	 */
	public final List<UserStatus> getCreated() {
		return getNonNullList(created);
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.response.TrainResponse#getNoTrainingSet()
	 */
	public final List<UserStatus> getNoTrainingSet() {
		return (no_training_set);
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.response.TrainResponse#getUpdated()
	 */
	public final List<UserStatus> getUpdated() {
		return (updated);
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.response.TrainResponse#getUnchanged()
	 */
	public final List<UserStatus> getUnchanged() {
		return (unchanged);
	}

	/* (non-Javadoc)
	 * @see com.face.api.client.response.TrainResponse#getInProgress()
	 */
	public final List<UserStatus> getInProgress() {
		return (in_progress);
	}

	public final String toString()
	{
		StringBuilder sb = new StringBuilder();

		if (created != null)
		{	
			sb.append("created: ").append("\n");

			for (UserStatus t : created)
			{
				sb.append(t);
			}
		}

		if (no_training_set != null)
		{
			sb.append("no training set: ").append("\n");

			for (UserStatus t : no_training_set)
			{
				sb.append(t);
			}
		}

		if (updated != null)
		{
			sb.append("updated: ").append("\n");

			for (UserStatus t : updated)
			{
				sb.append(t);
			}
		}

		if (unchanged != null)
		{
			sb.append("unchagned: ").append("\n");;

			for (UserStatus t : unchanged)
			{
				sb.append(t);
			}
		}

		if (in_progress != null)
		{
			sb.append("in progress: ");

			for (UserStatus t : in_progress)
			{
				sb.append(t);
			}
		}		
		
		return sb.toString();
	}
}