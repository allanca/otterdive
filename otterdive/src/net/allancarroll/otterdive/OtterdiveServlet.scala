package net.allancarroll.otterdive

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.face.api.client.DefaultFaceClient;


class OtterdiveServlet extends HttpServlet {
	val API_KEY = "c967915c590dfba336487ece1150051c";
	val API_SEC = "03c42ee0ea73d1485442f4e402191570";
	
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
		resp setContentType("text/plain")
		resp.getWriter().println("Hello, world!")

		val api = new DefaultFaceClient(API_KEY, API_SEC)
		val photos = api.detect("http://farm3.static.flickr.com/2566/3896283279_0209be7a67.jpg")
		print(photos)
	}

}
