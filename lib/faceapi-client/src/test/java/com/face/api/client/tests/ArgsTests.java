package com.face.api.client.tests;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.face.api.client.DefaultFaceClient;
import com.face.api.client.exception.FaceClientException;
import com.face.api.client.exception.FaceServerException;

public class ArgsTests extends TestCase
{
	private DefaultFaceClient api;

	@Before
	public void setUp()
	{
		api = new DefaultFaceClient(API_KEY, API_SEC);
	}
	
	
	@Test(expected=IllegalArgumentException.class) 
	public void recognize_null_uids() throws FaceServerException, FaceClientException
	{
		api.recognize(getFile("dad1.jpg"), (String) null);
	}

	
	@Test(expected=IllegalArgumentException.class) 
	public void recognize_null_both_string() throws FaceServerException, FaceClientException
	{
		api.recognize((File)null, (String) null);
	}
	
	
	@Test(expected=IllegalArgumentException.class) 
	public void saveTags_null_uid() throws FaceServerException, FaceClientException
	{
		api.saveTags(null, "label", "blab,asdf,asdf");
	}
	
	// invalid tags
	@Test(expected=IllegalArgumentException.class) 
	public void saveTags_null_label() throws FaceServerException, FaceClientException
	{
		api.saveTags("foo@msquaredh", null, "asdf,asdf");
	}
	
	@Test(expected=FaceServerException.class) 
	public void saveTags_null_list_tids() throws FaceServerException, FaceClientException
	{
		api.saveTags("foo@msquaredh", "label", null);
	}
	
	@Test(expected=FaceServerException.class) 
	public void saveTags_null_list_tid() throws FaceServerException, FaceClientException
	{
		api.saveTags("foo@msquaredh", "label", null);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void removeTags_null_arr() throws FaceServerException, FaceClientException
	{
		api.removeTags(null);
	}

	@Test(expected=IllegalArgumentException.class) 
	public void removeTags_null_list() throws FaceServerException, FaceClientException
	{
		api.removeTags("");
	}
	
	@Test(expected=FaceServerException.class) 
	public void getTags_null_uid() throws FaceServerException, FaceClientException
	{
		api.getTags(null, null, null, null, false, 0);
	}
	
	@Test(expected=FaceServerException.class) 
	public void getTags_null_uid2() throws FaceServerException, FaceClientException
	{
		api.getTags("pid", null, null, null, null, false, 5);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void detectBadFile() throws FaceServerException, MalformedURLException, FaceClientException
	{
		File f = new File("test");
		api.detect(f);
	}
}