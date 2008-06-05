package net.sf.theotherpages.samples.jspsample.util;

import java.util.ArrayList;
import java.util.List;

public class ListOfData {
	private int size = 10;
	// this is to test how many time get Records.
	public static  int hitCount = 0;


	public ListOfData(){
		
	}

	public void setSize(int size){
		this.size = size;
	}

	public List getListofData(Object queryParam,int startIndex, int endIndex){
	 
		List list = new ArrayList();
	for(int x=1; x<=size;x++){
		list.add("data"+x);
	}
	List temp = new ArrayList();
	System.err.println("The startIndex is:&&&&&&&&&&&&" + startIndex
			+ " The endIndex is:" + endIndex);
	/*
	 * for(int z=startIndex; z<=endIndex && z+1<=list.size();z++){
	    temp.add((String)list.get(z));
        } * 
	 * */
	
	for(int z=startIndex; z<endIndex && z+1<=list.size();z++){
		temp.add((String)list.get(z));
	}
	System.out.println("*******return lis from ListOfData********");
	hitCount =hitCount+1;
	return temp;
	}

	public int getSize(){
		return size;
	}
	public void setHitCount(int value){
		hitCount =value;
	}
	public int getHitCount(){
		return hitCount;
	}
}
