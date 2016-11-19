package org.bhargava.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.bhargava.messenger.datebase.DataBaseClass;
import org.bhargava.messenger.exception.DataNotFoundException;
import org.bhargava.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DataBaseClass.getMessages();

	public MessageService() {
		messages.put((long) 1, new Message(1, "Hello world", "Bhargav"));
		messages.put((long) 2, new Message(2, "Hello again", "Siri"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getMessageBasedOnYear(int year) {
		List<Message> returnMessages = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		List<Message> messagesAsList = new ArrayList<Message>(messages.values());
		for (int i = 0; i < messagesAsList.size(); i++) {
			Message message = messagesAsList.get(i);
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				returnMessages.add(message);
			}
		}
		return returnMessages;
	}

	public List<Message> getMessagesForPagination(int start, int end) {
		List<Message> messageList = new ArrayList<Message>(messages.values());
		if (start + end > messageList.size()) {
			return messageList.subList(start, messageList.size());
		}
		return messageList.subList(start, end);

	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found.");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() == 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
