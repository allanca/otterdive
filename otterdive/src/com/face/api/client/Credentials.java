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
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Class to handle Facebook and Twitter account credentials. Currently only
 * supports OAuth.
 * 
 * @author Marlon Hendred
 *
 */
class Credentials 
{
	private final Map<String, String> credentials;
	
	public Credentials() 
	{
		this.credentials = new HashMap<String, String>();
	}
	
	public void setFacebookUser(final String fbUser)
	{
		credentials.put("fb_user", fbUser);
	}
	
	public void setFacebookOauth(final String oauthToken)
	{
		credentials.put("fb_oauth_token", oauthToken);
	}
	
	public void setTwitterUser(final String oauthUser)
	{
		credentials.put("twitter_oauth_user", oauthUser);
	}
	
	public void setTwitterSecret (final String oauthSecret)
	{
		credentials.put("twitter_oauth_secret", oauthSecret);
	}
	
	public void setTwitterToken (final String oauthToken)
	{
		credentials.put("twitter_oauth_token", oauthToken);
	}
	
	public void clearFacebook ()
	{
		credentials.remove("fb_oauth_token");
		credentials.remove("fb_user");
	}
	
	public void clearTwitter ()
	{
		credentials.remove("twitter_oauth_user");
		credentials.remove("twitter_oauth_secret");
		credentials.remove("twitter_oauth_token");
	}
	
	public String getAuthString()
	{		
		if (isEmpty())
		{
			return null;
		}
	
		final List<String> vals = new LinkedList<String>();

		for (String key : credentials.keySet())
		{
			vals.add(key + ":" + credentials.get(key));
		}
		
		return StringUtils.join(vals, ",");
	}
	
	public boolean isEmpty()
	{
		return credentials.isEmpty();
	}
}