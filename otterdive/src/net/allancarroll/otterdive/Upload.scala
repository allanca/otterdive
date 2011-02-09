package net.allancarroll.otterdive

import java.util.Map;

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.google.appengine.api.blobstore.BlobKey
import com.google.appengine.api.blobstore.BlobstoreService
import com.google.appengine.api.blobstore.BlobstoreServiceFactory


class Upload extends HttpServlet {

	val blobstoreService = BlobstoreServiceFactory.getBlobstoreService()

    override def doPost(req: HttpServletRequest, res: HttpServletResponse) = {
        val blobs = blobstoreService.getUploadedBlobs(req)
        val blobKey: BlobKey = blobs.get("myFile")

        if (blobKey == null) {
            res.sendRedirect("/")
        } else {
            res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString())
        }
    }
}
