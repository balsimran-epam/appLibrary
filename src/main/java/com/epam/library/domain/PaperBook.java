package com.epam.library.domain;

public class PaperBook extends Book {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeOfCover;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeOfCover() {
		return typeOfCover;
	}

	public void setTypeOfCover(String typeOfCover) {
		this.typeOfCover = typeOfCover;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((typeOfCover == null) ? 0 : typeOfCover.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaperBook other = (PaperBook) obj;
		if (id != other.id)
			return false;
		if (typeOfCover == null) {
			if (other.typeOfCover != null)
				return false;
		} else if (!typeOfCover.equals(other.typeOfCover))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaperBook [id=" + id + ", typeOfCover=" + typeOfCover + "]";
	}

}
