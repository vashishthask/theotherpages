package net.sf.theotherpages.samples.swingsample.ui;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel{

private final Vector columnNames = new Vector();
private final Vector data = new Vector();


public  MyTableModel(){
	columnNames.add("Page");
}
public int getColumnCount() {
return columnNames.size();
}

public int getRowCount() {
return data.size();
}

public String getColumnName(int col) {
return (String)columnNames.get(col);
}

public Object getValueAt(int row, int col) {
return data.get(row);
}




public void loadPage(List list){
 data.removeAllElements();
 int size = list.size();
 for(int x =0; x<size; x++){
	 data.addElement(list.get(x));
 }
}

}
