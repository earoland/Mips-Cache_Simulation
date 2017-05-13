
/**
 * This class models the reference for the Cache. Each d
 * @author Ethan Roland and Nick Sprinkle
 * @version 4/29/2016
 *
 */
public class Reference {
	
	/** Holds the access */
	private String access;
	
	/** Holds the address */
	private String address;
	
	/** Holds the tag */
	private String tag;
	
	/** Holds the index */
	private int index;
	
	/** Holds the offset */
	private int offset;
	
	/** Holds the result */
	private String result;
	
	/** Holds the memref for the class*/
	private int memrefs;

	/**
	 * Default constructor
	 */
	public Reference() {
		this("", "", "", 0, 0, "", 0);
	}
	
	/**
	 * Contructor for the Reference in which it builds all the fields for Reference.
	 * 
	 * @param access given to reference
	 * @param address given to reference
	 * @param tag given to reference
	 * @param index given to reference
	 * @param offset given to reference
	 * @param result given to reference
	 * @param memrefs given to reference
	 */
	public Reference(String access, String address, String tag, int index, int offset, 
			String result, int memrefs){
		
		this.access = access;
		this.address = address;
		this.tag = tag;
		this.index = index;
		this.offset = offset;
		this.result = result;
		this.memrefs = memrefs;
	}

	/**
	 * Getter method for access.
	 * @return access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * Setter method for access.
	 * @param access
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * Getter method for address field.
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter method for address field.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter method for the tag.
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Setter method for the tag.
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Getter method for the index.
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setter method for the index.
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Getter method for the offset.
	 * @return offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Setter method for the offset.
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Getter method for the result
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Setter method for the result
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Getter method for the memRef
	 * @return memrefs
	 */
	public int getMemrefs() {
		return memrefs;
	}

	/**
	 * Setter method for the memRef
	 * @param memrefs
	 */
	public void setMemrefs(int memrefs) {
		this.memrefs = memrefs;
	}

}
