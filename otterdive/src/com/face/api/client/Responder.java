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

import java.io.File;
import java.net.URI;
import java.util.List;

import org.apache.http.NameValuePair;

import com.face.api.client.exception.FaceClientException;

/**
 * Describes how a class making {@code HTTP} requests to an end point should behave.
 * 
 * @author mhendred
 *
 */
public interface Responder 
{
	/**
	 * Method for making {@code GET} requests to the server
	 * 
	 * @param uri The {@link URI} of the REST resource to make the {@code GET} request on
	 * @param params {@code List}<{@link NameValuePair} of {@code GET} parameters
	 * 
	 * @return The response string from the server
	 * 
	 * @throws FaceClientException if there was a problem making the request
	 */
	public String doGet (final URI uri) throws FaceClientException;
	
	/**
	 * Method for making {@code POST} requests to the server
	 * 
	 * @param uri The {@link URI} of the REST resource to make the {@code GET} request on
	 * @param params {@code List}<{@link NameValuePair} of {@code POST} parameters
	 * 
	 * @return The response string from the server
	 * 
	 * @throws FaceClientException if there was a problem making the request
	 */
	public String doPost (final URI uri, final List<NameValuePair> params) throws FaceClientException;
	
	/**
	 * Method for {@code POST}ing files to the server
	 * 
	 * @param uri The {@link URI} of the REST resource to make the {@code GET} request on
	 * @param file The {@code File} to {@code POST} to the server
	 * @param params {@code List}<{@link NameValuePair}> of {@code POST} parameters 
	 * 
	 * @return The response string from the server
	 * 
	 * @throws FaceClientException
	 */
	public String doPost (final File file, final URI uri, final List<NameValuePair> params) throws FaceClientException;	
}
