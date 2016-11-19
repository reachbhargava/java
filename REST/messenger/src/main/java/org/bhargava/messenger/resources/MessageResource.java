package org.bhargava.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.bhargava.messenger.model.Message;
import org.bhargava.messenger.resources.beans.MessageParamsBean;
import org.bhargava.messenger.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {

	MessageService ms = new MessageService();

	/*
	 * @GET public List<Message> getMessages(@QueryParam("year") int year,
	 * 
	 * @QueryParam("start") int start,
	 * 
	 * @QueryParam("end") int end) { if (year > 0) { return
	 * ms.getMessageBasedOnYear(year); } else if (start > 0 && end > 0) { return
	 * ms.getMessagesForPagination(start, end); } return ms.getAllMessages(); }
	 */

	@GET
	public List<Message> getMessages2(@BeanParam MessageParamsBean messageBean) {
		if (messageBean.getYear() > 0) {
			return ms.getMessageBasedOnYear(messageBean.getYear());
		} else if (messageBean.getStart() > 0 && messageBean.getEnd() > 0) {
			return ms.getMessagesForPagination(messageBean.getStart(), messageBean.getEnd());
		}
		return ms.getAllMessages();
	}

	/*
	 * Observed and tested that the forward slash is not needed in the below
	 * Path. It can simply be @Path("{messageId}")
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		return ms.getMessage(messageId);
	}

	/*
	 * @POST public Message addMessage(Message message) { return
	 * ms.addMessage(message); }
	 */

	/*@POST
	public Response addMessage(Message message) {
		Message newMessage = ms.addMessage(message);
		return Response.status(Status.CREATED)
				.entity(newMessage)
				.build();
		
	}*/
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = ms.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(""+newMessage.getId()).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();		
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return ms.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long messageId) {
		return ms.removeMessage(messageId);
	}

	@Path("/{messageId}/comments")
	public CommentsResource getCommentsResource() {
		return new CommentsResource();
	}

}
