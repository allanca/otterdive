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
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.face.api.client.exception.FaceClientException;
import com.face.api.client.exception.FaceServerException;
import com.face.api.client.model.Photo;
import com.face.api.client.model.RemovedTag;
import com.face.api.client.model.SavedTag;
import com.face.api.client.model.UserStatus;
import com.face.api.client.response.AddTagResponse;
import com.face.api.client.response.BaseResponse;
import com.face.api.client.response.GroupResponse;
import com.face.api.client.response.PhotoResponse;
import com.face.api.client.response.RemoveTagResponse;
import com.face.api.client.response.SaveTagResponse;
import com.face.api.client.response.StatusResponse;
import com.face.api.client.response.TrainResponse;
import com.face.api.client.response.impl.AddTagResponseImpl;
import com.face.api.client.response.impl.BaseResponseImpl;
import com.face.api.client.response.impl.GroupResponseImpl;
import com.face.api.client.response.impl.PhotoResponseImpl;
import com.face.api.client.response.impl.RemoveTagResponseImpl;
import com.face.api.client.response.impl.SaveTagResponseImpl;
import com.face.api.client.response.impl.StatusResponseImpl;
import com.face.api.client.response.impl.TrainResponseImpl;
import com.google.gson.Gson;

/**
 * Default implementation of {@link FaceClient} interface
 * 
 * @author Marlon Hendred 
 * 
 * @see {@link FaceClient}  
 * @see <a href="http://developers.face.com/docs/">Developer's page</a>
 */
public class DefaultFaceClient implements FaceClient
{	
	/**
	 * Default API end point
	 */
	private static final String DEFAULT_FACE_API_ENDPOINT = "api.face.com";
	
	/**
	 * "failure" string constant
	 */
	private static final String FAILURE = "failure";
	
	/**
	 * Handles {@code POST}s to the face.com endpoint
	 */
	private final Responder responder;
	
	/**
	 * Used for deserialization of JSON
	 */
	private final Gson gson;

	/**
	 * Facebook and twitter credentials
	 */
	private final Credentials creds;
	
	/**
	 * Parameters that are required for every call
	 */
	private final Parameters reqd;
	
	/**
	 * Logger
	 */
	private final Log logger;
	
	/**
	 * Base {@link URI} endpoint
	 */
	private final URI baseURI;

	/**
	 * Convenience constructor with default {@link Responder} implementation
	 * 
	 * @see {@link DefaultResponder}
	 * @see {@link #DefaultFaceClient(String, String, Responder)}
	 */
	public DefaultFaceClient (final String apiKey, final String apiSecret)
	{
		this (apiKey, apiSecret, new DefaultResponder());
	}
	
	/**
	 * Constructs a Face.com API client pointing to {@code host}. You need to obtain an API key/secret pair.
	 * You can get an API key/secret from face.com 
	 * 
	 * @param apiKey Your aplication's API key
	 * @param apiSecret Your applications API secret
	 * @param host The REST api end point
	 *  
	 * @see {@link Responder}
	 * @see <a href="http://developers.face.com/docs/">Developer's page</a> for information on obtaining an
	 * 		API key/secret
	 */
	public DefaultFaceClient (final String apiKey, final String apiSecret, final Responder responder)
	{
		this.baseURI     = URI.create("http://" + DEFAULT_FACE_API_ENDPOINT);
		this.logger      = LogFactory.getLog(DefaultFaceClient.class);
		this.responder   = responder;
		this.creds       = new Credentials();
		this.reqd		 = new Parameters();
		this.gson        = new Gson();
		
		reqd.put("api_key", apiKey);
		reqd.put("api_secret", apiSecret);
		reqd.put("detector", "Normal");
	}	
	
	/**
	 * @see {@link FaceClient#removeTags(String)}
	 */
	public List<RemovedTag> removeTags (final String tids) throws FaceServerException, FaceClientException
	{
		Validate.notEmpty(tids, "Tag ids cannot be empty");
		
		final Parameters params = new Parameters("tids", tids);
		
		params.putAll(reqd);
		params.put("user_auth", creds.getAuthString());

		final String json = responder.doPost(baseURI.resolve(Api.REMOVE_TAGS), params.asList());
		final RemoveTagResponse response = gson.fromJson(json, RemoveTagResponseImpl.class);

		checkResponse(response, Api.REMOVE_TAGS);
		
		return response.getRemovedTags();	
	}
	
	/**
	 * @see {@link FaceClient#train(String)}
	 */
	public TrainResponse train (final String uids) throws FaceServerException, FaceClientException
	{
		final Parameters params = new Parameters("uids", uids);
		
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);
		
		final String json = responder.doPost(baseURI.resolve(Api.TRAIN), params.asList());
		final TrainResponse response = gson.fromJson(json, TrainResponseImpl.class);

		checkResponse(response, Api.TRAIN);
		
		return response;
	}
	
	/**
	 *@see {@link FaceClient#addTag(String, float, float, int, int, String, String, String)}
	 */
	public String addTag (
			final String url, 
			final float x, 
			final float y,
			final int width, 
			final int height, 
			final String uid, 
			final String label, 
			final String taggerId) 
		throws FaceServerException, FaceClientException
	{
		Validate.notNull(uid, "UID cannot be null");
		
		final Parameters params = new Parameters();
		
		params.put("x", x);
		params.put("y", y);
		params.put("width", width);
		params.put("height", height);
		params.put("tagger_id", taggerId);
		params.put("url", url);
		params.put("uid", uid);
		params.put("label", label);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.ADD_TAG), params.asList());
		final AddTagResponse response = gson.fromJson(json, AddTagResponseImpl.class);
		
		checkResponse(response, Api.ADD_TAG);
		
		return response.getTID();
	}
	
	/**
	 * @see {@link FaceClient#getTags(String, String, String, String, boolean, int)}
	 */
	public List<Photo> getTags (
			final String urls,
			final String uids,
			final String order,
			final String filter,
			final boolean together,
			final int limit)
	throws FaceServerException, FaceClientException
	{
		return getTags(null, urls, uids, order, filter, together, limit);
	}
	
	/**
	 * @see {@link FaceClient#getTags(String, String, String, String, String, boolean, int)}
	 */
	public List<Photo> getTags (
			final String pids, 
			final String urls, 
			final String uids, 
			final String order,
			final String filter,
			final boolean together,
			final int limit) 
		throws FaceServerException, FaceClientException
	{
		final Parameters params = new Parameters();
		
		params.put("pids", pids);
		params.put("urls", urls);
		params.put("uids", uids);
		params.put("order", order);
		params.put("filter", filter);
		params.put("together", together);
		params.put("limit", limit);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.GET_TAGS), params.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);

		checkResponse(response, Api.GET_TAGS);
		
		return response.getPhotos();
	}

	/**
	 * @see {@list FaceClient#saveTags(String, String, String)}
	 */
	public List<SavedTag> saveTags (final String tids, final String uid, final String label) 
		throws FaceServerException, FaceClientException
	{
		Validate.notEmpty(uid, "User IDs cannot be null");
		Validate.notEmpty(tids, "Tag IDs cannot be null");
		
		final Parameters params = new Parameters("tids", tids);
		
		params.put("tids", tids);
		params.put("uid", uid);
		params.put("label", label);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.SAVE_TAGS), params.asList());
		final SaveTagResponse response = gson.fromJson(json, SaveTagResponseImpl.class);

		checkResponse(response, Api.SAVE_TAGS);

		return response.getSavedTags();
	}
	
	/**
	 * @see {@link FaceClient#recognize(URL, String)}
	 */
	public Photo recognize (final File imageFile, final String uids) throws FaceServerException, FaceClientException
	{	
		Validate.notNull(imageFile, "File is null");
		Validate.isTrue(imageFile.exists(), "File does not exist!");
		Validate.notEmpty(uids, "User IDs cannot be null");
			
		final Parameters params = new Parameters("uids", uids);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json =  responder.doPost(imageFile, baseURI.resolve(Api.RECOGNIZE), params.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);
		
		checkResponse(response, Api.RECOGNIZE);
		
		return response.getPhoto();
	}
	
	/**
	 * @see {@link FaceClient#recognize(String, String)}
	 */
	public List<Photo> recognize (final String urls, final String uids) throws FaceClientException, FaceServerException
	{
		Validate.notEmpty(urls, "URLs cant be empty");
		Validate.notEmpty(uids, "User IDs can't be empty");
				
		final Parameters params = new Parameters("uids", uids);
		
		params.put("urls", urls);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.RECOGNIZE), params.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);
		
		checkResponse(response, Api.RECOGNIZE);
		
		return response.getPhotos();
	}
	
	/**
	 * @see {@link FaceClient#detect(URL)}
	 */
	public Photo detect (final File imageFile) throws FaceServerException, FaceClientException
	{	
		Validate.notNull(imageFile, "File is null");
		Validate.isTrue(imageFile.exists(), "File doesn't exist!");
		
		final String json = responder.doPost(imageFile, baseURI.resolve(Api.DETECT), reqd.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);
		
		checkResponse(response, Api.DETECT);
		
		return response.getPhoto();
	}

	/**
	 * @see {@link FaceClient#detect(String)}
	 */
	public List<Photo> detect (final String urls) throws FaceClientException, FaceServerException
	{
		Validate.notNull(urls, "URLs cannot be null");
		
		final Parameters params = new Parameters();
		
		params.put("urls", urls);
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.DETECT), params.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);
		
		checkResponse(response, Api.DETECT);
		
		return response.getPhotos();
	}

	/**
	 * @see {@link FaceClient#status(String)}
	 */
	public List<UserStatus> status (final String uids) throws FaceServerException, FaceClientException
	{
		Validate.notEmpty(uids, "UIDs cant be empty");
				
		final Parameters params = new Parameters();
		
		params.put("user_auth", creds.getAuthString());
		params.put("uids", uids);
		params.putAll(reqd);
		
		final String json = responder.doPost(baseURI.resolve(Api.STATUS), params.asList());
		final StatusResponse response = gson.fromJson(json, StatusResponseImpl.class);
		
		checkResponse(response, Api.STATUS);
		
		return response.getTrainingStatus();
	}
	
	/**
	 * @see {@link FaceClient#facebookGet(String)}
	 */
	public List<Photo> facebookGet (final String uids) throws FaceServerException, FaceClientException
	{
		Validate.notEmpty(uids, "User IDs cannot be empty");
		
		Parameters params = new Parameters();
		params.put("user_auth", creds.getAuthString());
		params.put("uids", uids);
		params.putAll(reqd);
		
		final String json = responder.doPost(baseURI.resolve(Api.FACEBOOK), params.asList());
		final PhotoResponse response = gson.fromJson(json, PhotoResponseImpl.class);
		
		checkResponse(response, Api.FACEBOOK);
		
		return response.getPhotos();	
	}

	/**
	 * @see {@link FaceClient#group(String, String)}
	 */
	public GroupResponse group(String urls, String uids) throws FaceClientException, FaceServerException
	{
		Validate.notEmpty(urls, "URLs cannot be empty");
		Validate.notEmpty(uids, "UIDs cannot be empty");
		
		final Parameters params = new Parameters();
		
		params.put("uids", uids);
		params.put("urls", urls);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);

		final String json = responder.doPost(baseURI.resolve(Api.GROUP), params.asList());
		final GroupResponse response = gson.fromJson(json, GroupResponseImpl.class);
		
		checkResponse(response, Api.GROUP);
		
		return response;
	}

	/**
	 * @see {@link FaceClient#group(File, String)}
	 */
	public GroupResponse group (File imageFile, String uids) throws FaceServerException, FaceClientException 
	{
		Validate.isTrue(imageFile.exists(), "File does not exist");
		Validate.notEmpty(uids, "UIDs cannot be empty");
		
		final Parameters params = new Parameters();
		
		params.put("uids", uids);
		params.put("user_auth", creds.getAuthString());
		params.putAll(reqd);


		final String json = responder.doPost(imageFile, baseURI.resolve(Api.GROUP), params.asList());
		final GroupResponse response = gson.fromJson(json, GroupResponseImpl.class);
		
		checkResponse(response, Api.GROUP);
		
		return response;
	}
	
	/**
	 * @see {@link FaceClient#setFacebookOauth2(String, String)}
	 */
	public void setFacebookOauth2(final String fbUserId, final String oauthToken)
	{
		creds.setFacebookUser(fbUserId);
		creds.setFacebookOauth(oauthToken);
	}
	
	/**
	 * @see {@link FaceClient#setTwitterOauth(String, String, String)}
	 */
	public void setTwitterOauth(final String oauthUser, final String oauthSecret, final String oauthToken)
	{
		creds.setTwitterUser(oauthUser);
		creds.setTwitterSecret(oauthSecret);
		creds.setTwitterToken(oauthToken);
	}
	
	/**
	 * @see {@link FaceClient#clearFacebookCreds()}
	 */
	public void clearFacebookCreds()
	{
		creds.clearFacebook();
	}
	
	/**
	 * @see {@link FaceClient#clearTwitterCreds()}
	 */
	public void clearTwitterCreds()
	{
		creds.clearTwitter();
	}
	
	/**
	 * @see {@link FaceClient#setAggressive(boolean)} 
	  */
	public void setAggressive(final boolean isAggressive)
	{
		reqd.put("detector", isAggressive ? "Aggressive" : "Normal");
	}
	
	/**
	 * Checks the server response status line for failure
	 * 
	 * @param response {@link BaseResponseImpl} sublcass
	 * 
	 * @throws FaceServerException with information about the error that was encountered
	 **/
	private void checkResponse(BaseResponse response, String api) throws FaceServerException
	{	
		if (response.getStatus().equalsIgnoreCase(FAILURE))
		{
			final FaceServerException fse = new FaceServerException(response); 
			
			logger.warn("Server exception while posting to " + api, fse);	
			throw fse;
		}
	}
}