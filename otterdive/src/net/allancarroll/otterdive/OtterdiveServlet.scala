package net.allancarroll.otterdive

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class OtterdiveServlet extends HttpServlet {
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
		resp setContentType("text/plain")
		resp.getWriter().println("Hello, world!")
	}

}
