package org.bhargava.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentsResource {

	@GET
	public String testMethod() {
		return "sub resource";
	}

	@GET
	@Path("/{commentId}")
	public String forCommentId(@PathParam("commentId") long commentId) {
		return "comment " + commentId;
	}
}
