package com.gedcom.elements;

import java.util.LinkedList;
import java.util.List;

import com.gedcom.util.XMLGenerator;


/**
 * GedComNode -- represents parsed gedcom data, with parent, childrens, node name, whether it represents a
 * tag or id element. can generate XML representation of the node.
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComNode implements XMLGenerator {
	
	/**
	 * Level of this node in the GEDCOM data.
	 */
	private int level;
	
	/**
	 * Name of the node
	 */
	private String nodeName;
	
	/**
	 * Children of this node
	 */
	private List<GedComNode> childrens;
	
	/**
	 * Whether this elment is a tag or id, if it is null means it represents an element with out children
	 * Ex: 1 SEX M   --> <SEX>M</SEX>
	 */
	private GedComAttribute attribute;
	
	
	/**
	 * <br> if the node is {@link GedComAttribute.ID} then it holds id value Ex : id="@L0001@"
	 * <br> if the node is {@link GedComAttribute.TAG} then it holds tag value Ex : value="Elizabeth Alexandra Mary /Windsor/" 
	 * <br> and if the nod is tag, and doesnt have children then it holds data of the node. Ex: F, <SEX>F</SEX>
	 */
	private String data;

	/**
	 * Parent of this node
	 */
	private GedComNode parent;
	
	public GedComNode(int level, String nodeName,GedComAttribute attribute, String data, GedComNode parent) {
		this.level = level;
		this.nodeName = nodeName;
		this.attribute = attribute;
		this.parent = parent;
		this.data = data;
	}
	
	public GedComNode(LineData lineData, GedComNode parent) {
		this.level = lineData.getLevel();
		if(lineData.isTag()){
			this.nodeName = lineData.getTag();
			this.data = lineData.getData();
			//Ex: 1 BIRT --> here data is null, so the generated xml should not be <BIRT value="null"
			if(data != null)
				this.attribute = GedComAttribute.TAG;
		} else {
			this.nodeName = lineData.getData();
			this.attribute = GedComAttribute.ID;
			this.data = lineData.getId();
		}
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	private List<GedComNode> getChildrens() {
		if(childrens == null) {
			childrens = new LinkedList<GedComNode>();
		}
		return childrens;
	}
	
	/**
	 * Same parent childrens can be added.
	 * @param children
	 * @return
	 */
	public boolean addChildren(GedComNode children) {
		if(children.getParent() == null){
			return false;
		}
		boolean equals = children.getParent().equals(this);
		if(equals) {
			boolean add = getChildrens().add(children);
			return add;
		}
		return false;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @return the attribute
	 */
	public GedComAttribute getAttribute() {
		return attribute;
	}


	/**
	 * @return the parent
	 */
	public GedComNode getParent() {
		return parent;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @return true if the node is root, else false
	 */
	public boolean isRootNode() {
		if(parent == null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @return true if the node doesnt have children, else false
	 */
	public boolean isLeafNode() {
		if(childrens == null || childrens.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Generates XML from the GedComNode data -- parsed gedcom data is represented as a GedComNode
	 * and this method is used to generate XML.
	 * <br> 0 @I0001@ INDI
	 * <br> 1 NAME Elizabeth Alexandra Mary /Windsor/
	 * <br> 1 SEX F
	 * <br> 1 BIRT
	 * <br> 2 DATE 21 Apr 1926
	 * <br> 2 PLAC 17 Bruton Street, London, W1
	 * <br> 
	 * <br> Result :
	 * <pre>
	 * {@code
	 *   <gedcom> 
	 *  	<INDI id="@I0001@">
	 *  		<NAME>Elizabeth Alexandra Mary /Windsor/</NAME>
	 *  		<SEX>F</SEX>
	 *  		<BIRT>
	 * 			<DATE>21 Apr 1926</DATE>
	 * 			<PLAC>17 Bruton Street, London, W1</PLAC>
	 * 		</BIRT>
	 * 	</INDI>
	 * </gedCom>
	 * }
	 * </pre>
	 * @return
	 */
	@Override
	public String getXMLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<"+nodeName);
		
		if(isLeafNode()) {
			stringBuilder.append(">"+((data!=null)?data:""));
		} else {
			if(attribute == null) {
				stringBuilder.append(">");
			} else {
				stringBuilder.append(" "+attribute.getName()+"=\""+data+"\">");
			}
			stringBuilder.append("\n");
			for(GedComNode gedComChild : childrens) {
				stringBuilder.append(gedComChild.getXMLString());
			}
			//stringBuilder.append("\n");
		}
		stringBuilder.append("</"+nodeName+">");
		if(!isRootNode())
			stringBuilder.append("\n");
		
		return stringBuilder.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GedComNode [nodeName=" + nodeName + ", childrens=" + childrens
				+ ", attribute=" + attribute + ", data=" + data + ", parent="
				+ parent + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result
				+ ((childrens == null) ? 0 : childrens.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + level;
		result = prime * result
				+ ((nodeName == null) ? 0 : nodeName.hashCode());
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
		GedComNode other = (GedComNode) obj;
		if (attribute != other.attribute)
			return false;
		if (childrens == null) {
			if (other.childrens != null)
				return false;
		} else if (!childrens.equals(other.childrens))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (level != other.level)
			return false;
		if (nodeName == null) {
			if (other.nodeName != null)
				return false;
		} else if (!nodeName.equals(other.nodeName))
			return false;
		return true;
	}

}
