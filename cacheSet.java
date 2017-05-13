
import java.util.ArrayList;

/**
 * This class models the cache set.
 * @author Ethan Roland and Nick Sprinkle
 * @version 4/29/2016
 *
 */
public class CacheSet {
	
	private boolean dirtyBit;
	
	private boolean validBit;
	
	private int index;
	
	private int setSize;
	
	private ArrayList<String> tag;
	
	private String[] tagPrioty;
	
	/**
	 * This is the constructor for the CacheSet
	 * @param index for the CacheSet
	 * @param tag for the tag
	 * @param setSize for the setSize
	 */
	public CacheSet(int index, String tag, int setSize){
		this.index = index;
		//this.tag = tag;
		this.dirtyBit = true;
		this.validBit = true;
		this.tag = new ArrayList<String>();
		this.tag.add(tag);
		this.setSize = setSize;
		this.tagPrioty = new String[setSize];
		this.tagPrioty[0] = tag;
	}
	
	/**
	 * Adds a string to the tag's ArrayList, but also makes sure to not go over the
	 * setSize. Plus it suppose to make a prioty for the tags.
	 * @param anotherTag
	 */
	public void addTag(String anotherTag){
		if(tag.size() < setSize){
			tag.add(anotherTag);
			tagPrioty[tagPrioty.length] = anotherTag;
		}
		else{
			String temp = tagPrioty[0];
			for(int i = 0; i < tagPrioty.length-1;i++){
				tagPrioty[i] = tagPrioty[i+1];
				
			}
			tag.remove(temp);
			tag.add(anotherTag);
		}
	}

	/**
	 * Getter method for setSize
	 * @return setSize
	 */
	public int getSetSize() {
		return setSize;
	}

	/**
	 * Setter method for setSize.
	 * @param setSize
	 */
	public void setSetSize(int setSize) {
		this.setSize = setSize;
	}

	/**
	 * Getter method for the dirty bit
	 * @return dirtyBit
	 */
	public boolean isDirtyBit() {
		return dirtyBit;
	}

	/**
	 * Setter for the dirtyBit
	 * @param dirtyBit
	 */
	public void setDirtyBit(boolean dirtyBit) {
		this.dirtyBit = dirtyBit;
	}

	/**
	 * Getter for the validBit
	 * @return validBit
	 */
	public boolean isValidBit() {
		return validBit;
	}

	/**
	 * Setter for the validBit
	 * @param validBit
	 */
	public void setValidBit(boolean validBit) {
		this.validBit = validBit;
	}

	/**
	 * Getter for the index.
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setter for the index
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Getter for the tag ArrayList
	 * @return tag
	 */
	public ArrayList<String> getTag() {
		return tag;
	}

	/**
	 * Setter for the tag ArrayList
	 * @param tag
	 */
	public void setTag(ArrayList<String> tag) {
		this.tag = tag;
	}

}
