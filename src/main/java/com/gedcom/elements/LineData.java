package com.gedcom.elements;

/**
 * GEDCOM - each parsed line is represented as a LineData
 * If id is null, then it is representing a tag(should not be null) and vice versa.
 * @author Venkata S S R Murthy Manda
 *
 */
public class LineData {

	/**
	 * representing the depth of gedcom data
	 */
	private int level;
	/**
	 * TAG-OR-ID is either a tag that identifies the type of data in that node, or it is a unique
	 * identifier. Tags are 3- or 4-letter words in uppercase. 
	 */
	private String tag;
	/**
	 * The unique identifiers are always text
	 * surrounded by "@" characters (i.e., "@I54@"). If an ID is given, the DATA is the type of the
	 * subtree that is identified.
	 */
	private String id;
	/**
	 * data in the current line
	 */
	private String data;
	
	public LineData(int level, String tag, String id, String data) {
		this.level = level;
		this.tag = tag;
		this.id = id;
		this.data = data;
	}

	/**
	 * @return the isTag
	 */
	public boolean isTag() {
		if(id==null)
			return true;
		return false;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + level;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineData other = (LineData) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level != other.level)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineData [level=" + level + ", tag=" + tag + ", id=" + id
				+ ", data=" + data + "]";
	}

}
