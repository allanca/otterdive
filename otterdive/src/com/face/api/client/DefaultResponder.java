/*
 * Copyright (c) 2010 Marlon Hendred
 * Copyright (c) 2011 Allan Carroll
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

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.face.api.client.exception.FaceClientException;

/**
 * Default implementation of {@link Responder} interface. It seems as though
 * face.com always returns an HTTP status code of 200 even for 404 not founds.
 * This is why there is no need to check the status line.
 * 
 * @author Marlon Hendred
 *
 */
class DefaultResponder implements Responder
{
	
	/**
	 * Logger
	 */
	private final Log logger;
		
	public DefaultResponder()
	{
		this.logger     = LogFactory.getLog(DefaultResponder.class);
	}

	/**
	 * @see {@link Responder#doPost(URI, List)}
	 */
	public String doPost(final URI uri, final List<NameValuePair> params) throws FaceClientException
	{		
		UrlEncodedFormEntity entity;
		
		try
		{
			entity = new UrlEncodedFormEntity(params, "UTF-8");
		}
		
		catch (UnsupportedEncodingException uee)
		{
			logger.error("Error adding entity", uee);
			throw new FaceClientException(uee);
		}
		
		return doPost(uri, entity);
	}
	
	/**
	 * @see {@link Responder#doPost(File, URI, List)}
	 */
	public String doPost(final File file, final URI uri, final List<NameValuePair> params) 
	throws FaceClientException
	{		
		final MultipartEntity entity = new MultipartEntity();	
		entity.addPart("image", new FileBody(file));

		try
		{
			for (NameValuePair nvp : params)
			{
				entity.addPart(nvp.getName(), new StringBody(nvp.getValue()));
			}
		}
		
		catch (UnsupportedEncodingException uee)
		{
			logger.error("Error adding entity", uee);
			throw new FaceClientException(uee);
		}
		
		return doPost(uri, entity);

	}
	
	private String doPost(final URI uri, final HttpEntity entity) throws FaceClientException
	{
		try
		{
	    	URL url = uri.toURL();
		    
	    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setDoOutput(true);
		    connection.setDoInput(true);
		    connection.setRequestMethod("POST");
			
		    writeRequest(entity.getContent(), connection.getOutputStream());
	        return readResponse((InputStream) connection.getContent());
	    }
	    
	    catch (MalformedURLException mue)
	    {
			logger.error("Protocol error while POSTing to " + uri, mue);
			throw new FaceClientException(mue);
	    }
	
	    catch (IOException ioe)
		{
			logger.error("Error while POSTing to " + uri, ioe);
			throw new FaceClientException(ioe);
		}
		
	}
	
	/**
	 * @see {@code Responder#doGet(URI)}
	 */
	public String doGet (final URI uri) throws FaceClientException
	{
        try
        {
        	return readResponse(uri.toURL().openStream());
        }
        
        catch (MalformedURLException mue)
        {
			logger.error("Protocol error while POSTing to " + uri, mue);
			throw new FaceClientException(mue);
        }
        
        catch (IOException ioe)
        {
			logger.error("Error while POSTing to " + uri, ioe);
			throw new FaceClientException(ioe);
        }		
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String readResponse (final InputStream is) throws IOException
	{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer response = new StringBuffer();

        String line;
        while ((line = reader.readLine()) != null)
        {
            response.append(line);
        }
        reader.close();
        
        return response.toString();
	}
	
	/**
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private void writeRequest(final InputStream is, final OutputStream os) 
	throws IOException
	{
	    int c;
	    while ((c = is.read()) != -1) {
	    	os.write(c);
	    }
    	os.flush();
	    os.close();
	}
}