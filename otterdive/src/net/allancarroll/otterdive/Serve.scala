package net.allancarroll.otterdive

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.google.appengine.api.blobstore.BlobKey
import com.google.appengine.api.blobstore.BlobstoreService
import com.google.appengine.api.blobstore.BlobstoreServiceFactory


class Serve extends HttpServlet {
    val blobstoreService = BlobstoreServiceFactory.getBlobstoreService()

	override def doGet(req: HttpServletRequest, res: HttpServletResponse ) = {
        val blobKey = new BlobKey(req.getParameter("blob-key"))
        blobstoreService.serve(blobKey, res)
    }
}
