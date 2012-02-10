package net.sf.theotherpages.samples.disptagint.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.theotherpages.samples.disptagint.data.ReportList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReportService {
	private int size = 10;
	// this is to test how many time get Records.
	public static int hitCount = 0;
	List list;

	public ReportService() {
		list = new ReportList();
	}
	
	public ReportService(int size) {
		list = new ReportList(size);
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
		return list.size();
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