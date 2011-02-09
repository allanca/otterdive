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

import java.util.Collections;
import java.util.List;

/**
 * Holds "Tag" JSON object
 * 
 * @author mhendred
 *
 */
public class Face
{
	static class UidHolder
	{
		String uid;
		
		int confidence;
		
		public String getUID()
		{
			return uid;
		}
		
		public int getConfidence()
		{
			return confidence;
		}
	}
	
	static final class FaceAttributes 
	{	
		private Attribute face;
		
		private Attribute gender;
		
		private Attribute smiling;
		
		private Attribute glasses;
		
		
		public Attribute getFace()
		{
			return Attribute.getNonNullAttrib(face);
		}
		
		public Attribute getSmiling()
		{
			return Attribute.getNonNullAttrib(smiling);
		}
		
		public Attribute getGender()
		{
			return Attribute.getNonNullAttrib(gender);
		}
		
		public Attribute getGlasses()
		{
			return Attribute.getNonNullAttrib(glasses);
		}
	}
	
	private String tid;
	
	private String label;
	
	private boolean confirmed;
	
	private boolean manual;
	
	private long taggerId;
	
	private float width;
	
	private float height;
	
	private double yaw;
	
	private double roll;
	
	private double pitch;
	
	private int threshold;
	
	private Point center;
	
	private Point eye_left;
	
	private Point eye_right;
	
	private Point mouth_left;
	
	private Point mouth_right;
	
	private Point mouth_center;
	
	private Point nose;
	
	private FaceAttributes attributes;
	
	private List<UidHolder> uids;
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getUids()
	 */
	@SuppressWarnings("unchecked")
	public List<UidHolder> getUids()
	{
		return (uids == null) ? Collections.EMPTY_LIST : uids;
	}
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getWidth()
	 */
	public float getWidth()
	{
		return this.width;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getHeight()
	 */
	public float getHeight()
	{
		return this.height;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getLabel()
	 */
	public String getLabel()
	{
		return this.label;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getTID()
	 */
	public String getTID()
	{
		return this.tid;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getTaggerId()
	 */
	public long getTaggerId()
	{
		return this.taggerId;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getThreshHold()
	 */
	public int getThreshHold()
	{
		return this.threshold;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#isConfirmed()
	 */
	public boolean isConfirmed()
	{
		return this.confirmed;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#isManual()
	 */
	public boolean isManual()
	{
		return this.manual;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getCenter()
	 */
	public Point getCenter()
	{
		return this.center;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getLeftEye()
	 */
	public Point getLeftEye()
	{
		return this.eye_left;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getRightEye()
	 */
	public Point getRightEye()
	{
		return this.eye_right;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getMouthCenter()
	 */
	public Point getMouthCenter()
	{
		return this.mouth_center;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getMouthRight()
	 */
	public Point getMouthRight()
	{
		return this.mouth_right;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getMouthLeft()
	 */
	public Point getMouthLeft()
	{
		return this.mouth_left;
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#isFace()
	 */
	public boolean isFace()
	{
		return attributes.getFace().value.equals("true");
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#isWearingGlasses()
	 */
	public boolean isWearingGlasses()
	{
		return this.attributes.getGlasses().value.equals("true");
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#isSmiling()
	 */
	public boolean isSmiling()
	{
		return this.attributes.getSmiling().equals("true");
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getGender()
	 */
	public Gender getGender()
	{
		String value = attributes.getGender().value.toUpperCase();
		
		return Gender.getNonEmpty(value);
	}
	
	/* (non-Javadoc)
	 * @see com.face.api.client.model.Face#getNose()
	 */
	public Point getNose()
	{
		return this.nose;
	}
	
	public double getYaw()
	{
		return yaw;
	}
	
	public double getRoll()
	{
		return roll;
	}
	
	public double getPitch()
	{
		return pitch;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("   id: ").append(tid);
		
		if (uids != null)
		{
			sb.append("   uids:").append("\n");
			
			for (UidHolder uh : uids)
			{
				sb.append("      uid: ").append(uh.getUID())
				  .append("\n")
				  .append("      confidence: ").append(uh.getConfidence())
				  .append("\n");
			}
		}
		  sb.append("\n")
		  .append("   label: ").append(label)
		  .append("\n")
		  .append("   left eye: ").append(getLeftEye())
		  .append("\n")
		  .append("   right eye: ").append(getRightEye())
		  .append("\n")
		  .append("   yaw: ").append(getYaw())
		  .append("\n")
		  .append("   roll:").append(getRoll())
		  .append("\n")
		  .append("   pitch:").append(getPitch())
		  .append("\n")
		  .append("   nose: ").append(getNose())
		  .append("\n")
		  .append("   glasses: ").append(isWearingGlasses())
		  .append("\n")
		  .append("   gender: ").append(getGender())
		  .append("\n")
		  .trimToSize();
		
		return sb.toString();
	}
}