
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a Cache. This class runs the calculations for the references, and 
 * it figures out the hits and misses. Plus even the hit ratio and miss ratio.
 * @author Ethan Roland, and Nick Sprinkle
 * @version 4/29/2016 
 */
public class Cache {
	
	private int hits;
	
	private ArrayList<Reference> references;
	
	private int misses;
	
	private int accessed;
	
	private int numberOfSets;
	private int setSize;
	private int lineSize;
	
	private ArrayList<CacheSet> cacheSim;
	
	public Cache(String[] args) throws FileNotFoundException{
		hits = 0;
		misses = 0;
		String filePath = args[0];
		Scanner input = new Scanner(new FileReader(filePath));
		references = new ArrayList<Reference>();

		input.findInLine(": ");
		numberOfSets = input.nextInt();
		
		input.nextLine();
		input.findInLine(": ");
		setSize = input.nextInt();
		

		input.nextLine();
		input.findInLine(": ");
		lineSize = input.nextInt();
		if(lineSize < 4 || lineSize%2 != 0){
			System.out.println("Error: line size must be at least 4 and a power of 2");
			System.exit(0);
		}
		cacheSim = new ArrayList<CacheSet>();
		//gets address for a single reference
		while(input.hasNext()){
			
			input = this.fillData(input);
			//calculates the tag/offset/index/result/Memref
			this.fillInReference(references.get(references.size()-1));
		}
		this.accessed = references.size();
		
		System.out.println(this.referenceResult());
		System.out.println(this.simSummary());
		
		input.close();
		
	}
	
	/**
	 * This method is suppose to fill in the rest of the data for the reference.
	 * That data includes tag, offset, index, result, and memref.
	 * @param reference
	 * @return 
	 */
	private void fillInReference(Reference reference) {
		int i = Integer.decode("0X" + reference.getAddress());

		//calculates the offset
		int bitMask =(int) (Math.log10(lineSize)/Math.log10(2));

		//masks a bit and puts offset into reference*/
		 int offsetAmt = (lineSize -1);
		reference.setOffset( (offsetAmt & i));
		
		//removes the bits that was put into the offset
		i = i >>> bitMask;
		
		//calculates the index
		//calculates the number of bits to be placed into index
		int indexAmt = (int) (Math.log10(numberOfSets)/ Math.log10(2));
		int indexGetter = (numberOfSets-1);
		reference.setIndex(indexGetter& i);
		
		//removes the bits put into the index
		i = i >>> indexAmt;
		
		//calculates the tag
		reference.setTag(String.valueOf(i));
		
		calculateResult(reference);
		
		memRefill(reference);
	}
	
	/**
	 * This method calculates if the reference has a hit or miss.
	 * @param reference in which the calculations are happening.
	 */
	public void calculateResult(Reference reference){
		
		//check to see if there not another index
		boolean newIndex = true;
		boolean newTag = false;
		for(int i = 0; i < cacheSim.size();i++){
			if(cacheSim.get(i).getIndex() == reference.getIndex()){
				ArrayList<String> temp = cacheSim.get(i).getTag();
				for(int p = 0; p < temp.size();p++){
					if(temp.get(p).equals(reference.getTag())){
						newIndex = false;
					}
			}
			}
		}
		if(newIndex){
			CacheSet newSet = new CacheSet(reference.getIndex(),reference.getTag(),setSize);
			if(setSize <= 1){
				cacheSim.add(0,newSet);
				reference.setResult("miss");
				misses++;
			}else{
			cacheSim.add(newSet);
			reference.setResult("miss");
			misses++;
			}
		}
		else{
			//trying for new tags
			for(int j = 0; j < cacheSim.size(); j++){
				ArrayList<String> temp = cacheSim.get(j).getTag();
				System.out.println(setSize);
				if(setSize> 1){
				for(int k = 0; k < temp.size();k++ ){
					if(temp.get(k).equals(reference.getTag())){
						reference.setResult("hit");
						hits++;
						newTag = true;
					}
				}
				}
					else{
						int k = temp.size()-1;
						if(temp.get(k).equals(reference.getTag())){
							reference.setResult("hit");
							hits++;
							newTag = true;
					}
				}
			}
		}
	}
	
	/**
	 * Fills in the memRef for the reference that is entered into thi method.
	 * @param reference that memRef going to be filled in
	 */
	public void memRefill(Reference reference){
		
		if(reference.getAccess().equals("write") && reference.getResult().equals("miss") ){
			if(cacheSim.size() == 1 ){
				reference.setMemrefs(1);
			}else{
			reference.setMemrefs(2);
			}
		}
		else if(reference.getAccess().equals("read") && reference.getResult().equals("miss")){
			reference.setMemrefs(1);
		}
		
	}

	/**
	 * This method fills one reference address/write/byte size in while reading
	 * in from a input file. This method also returns what is left of the input.
	 * @param i the input file to be taken from
	 * @return i which is what is left of the scanner
	 */
	public Scanner fillData(Scanner i){
		
		Reference newReference = new Reference();
		
		String address = i.next();
		
		String[] s = address.split(":");

		newReference.setAddress(String.valueOf(s[0]));
		
		//decides what to put in the access of the newReference
		if(s[1].equals("R")){
			newReference.setAccess("read");
		}
		else if(s[1].equals("W")){
			newReference.setAccess("write");
		}
		
		references.add(newReference);
		
		return i;
	}
	
	/**
	 * Builds the String for the reference results.
	 * @return s which has the string for the results of the references.
	 */
	public String referenceResult(){
		String s = "Cache Configuration\n\n\t" + numberOfSets + " " +numberOfSets 
				+"-set associative entries\n"
				+ "\tof line size " + lineSize + " bytes\n\n"
				+ "Results for Each Reference\n\n"
				+ "Access\tAddress\tTag\tIndex\tOffset\tResult\tMemrefs \n"
				+ "------------------------------------------------------\n";
		for(int i = 0; i< references.size(); i++){
			s += references.get(i).getAccess() + "\t" + references.get(i).getAddress() 
					+ "\t"+ references.get(i).getTag()
					+ "\t"+ references.get(i).getIndex() + "\t" + references.get(i).getOffset()
					+ "\t"+ references.get(i).getResult() + "\t" + references.get(i).getMemrefs() 
					+ "\n";
		}
		return s;
	}
	
	/**
	 * Creates the string for the simulation summary report.
	 * @return s which is the String of the simulation summary
	 */
	public String simSummary(){
		String s = "Simulation Summary Statistics \n --------------------------- \n"
				+ " Total hits\t: " + this.hits + "\n"
				+ " Total misses\t: " + this.misses + "\n"
				+ " Total accesses\t: " + this.accessed + "\n"
				+ " Hit ratio\t: " + this.hitRatio() + "\n"
				+ " Miss Ratio\t: "	+ this.missRatio();
		
		return s;
	}
	

	
	/**
	 * Calculates the hit ratio for the Cache
	 * @return
	 */
	public double hitRatio(){
		return intToDouble(hits)/intToDouble(accessed);
	}
	
	/**
	 * Calculates the miss ratio for the Cache
	 * @return
	 */
	public double missRatio(){
		return intToDouble(misses)/intToDouble(accessed); 
	}
	
	/**
	 * Converts a integer into a double
	 * @param i for input integer
	 * @return d double
	 */
	public double intToDouble(int i){
		double d = i;
		
		return d;
	}

}
