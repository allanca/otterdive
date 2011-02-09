package com.face.api.client.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.face.api.client.DefaultFaceClient;

public abstract class TestCase
{
	protected static final String API_KEY = "YOUR KEY";
	protected static final String API_SEC = "YOUR SECRET";

	protected DefaultFaceClient api;
	
	protected String validUrl;
	protected List<String> imageUrls;

	@Before
	public void setUp()
	{
		imageUrls = new ArrayList<String>();

		imageUrls.add("http://seedmagazine.com/images/uploads/attractive_article.jpg");
		imageUrls.add("http://www.uni-regensburg.de/Fakultaeten/phil_Fak_II/Psychologie/Psy_II/beautycheck/english/durchschnittsgesichter/m(01-32)_gr.jpg");
		imageUrls.add("http://kk.org/ct2/FaceSw2.jpg");
		imageUrls.add("http://kk.org/ct2/faceswapper.jpg");
		
		validUrl = "http://westnilevirus.okstate.edu/poultry/chickens/araucana/Araucana-Chicken-3.jpg";
		
		api = new DefaultFaceClient(API_KEY, API_SEC);
	}
	
	protected File getFile(String resource)
	{
		return new File(resource);
	}
}