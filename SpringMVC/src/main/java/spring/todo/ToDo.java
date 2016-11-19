package spring.todo;

import java.util.Date;

import javax.validation.constraints.Size;

public class ToDo {

	public ToDo() {
		super();
	}

	private int id;

	private String user;

	@Size(min=6, message="Enter at least 6 characters.")
	private String description;

	private Date finishBy;

	private boolean isDone;
	
	public ToDo(int id, String user, String description, Date finishBy, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.finishBy = finishBy;
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return String.format("From toString - ToDo [id=%s, user=%s, description=%s, isDone=%s]", id, user, description,
				isDone);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinishBy() {
		return finishBy;
	}

	public void setFinishBy(Date finishBy) {
		this.finishBy = finishBy;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
