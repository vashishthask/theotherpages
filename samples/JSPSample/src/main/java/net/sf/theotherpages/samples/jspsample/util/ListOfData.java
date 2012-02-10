package net.sf.theotherpages.samples.jspsample.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ListOfData {
	private int size = 10;
	// this is to test how many time get Records.
	public static int hitCount = 0;
	List list;

	public ListOfData() {
		init();
	}
	
	private void init(){
		list = new ArrayList();
		for (int x = 0; x < size; x++) {
			list.add("data" + x);
		}
	}

	public ListOfData(int size) {
		this.size = size;
		init();
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List getListofData(Object queryParam, int startIndex, int endIndex) {

		List returnList = new ArrayList();
		if(endIndex+1 > list.size())
			endIndex = list.size();
		for (int i = startIndex; i < endIndex; i++) {
			returnList.add(list.get(i));
		}
		hitCount = hitCount + 1;
		return returnList;
	}

	public int getSize() {
		return size;
	}

	public void setHitCount(int value) {
		hitCount = value;
	}

	public int getHitCount() {
		return hitCount;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	
	}
}