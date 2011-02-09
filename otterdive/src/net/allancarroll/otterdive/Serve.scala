package net.allancarroll.otterdive

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.face.api.client.DefaultFaceClient;

import com.google.appengine.api.blobstore.BlobKey
import com.google.appengine.api.blobstore.BlobstoreService
import com.google.appengine.api.blobstore.BlobstoreServiceFactory
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;



class Serve extends HttpServlet {
	val API_KEY = "c967915c590dfba336487ece1150051c";
	val API_SEC = "03c42ee0ea73d1485442f4e402191570";
    val blobstoreService = BlobstoreServiceFactory.getBlobstoreService()

	override def doGet(req: HttpServletRequest, res: HttpServletResponse ) = {
        val blobKey = new BlobKey(req.getParameter("blob-key"))

        val imagesService = ImagesServiceFactory.getImagesService()
		val servingUrl = imagesService.getServingUrl(blobKey)
		
		print(servingUrl)
        
		val api = new DefaultFaceClient(API_KEY, API_SEC)
		val photos = api.detect(servingUrl)
		res.getWriter().print(photos)
    }
}
