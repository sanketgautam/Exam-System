package Instructor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ExamEvaluation extends JPanel implements ItemListener {
	List list;
	private JTable table;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	// If Exam Status is submitted(finished) then display option of evaluation 
	//  If Exam Status is not submitted then display option of edit exam
	JButton evaluate,edit;
	
	public ExamEvaluation() 
	{
		//setSize((int)((width*3)/4),(int)height);
		setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		//ll.setBounds(208, 60,100, 100);
		setLayout(null);
		setBackground(Color.blue);
	//	JLabel title = new JLabel("Exam Analysis");
	//	title.setBounds(23, 11, 125, 14);
	//	add(title);
		
		edit = new JButton("Edit Exam");
		evaluate = new JButton("Evaluate Exam");
		
		edit.setBounds(60, 60, (int)(width*3/4)-100, (int)height-200);
		evaluate.setBounds(60, 60, (int)(width*3/4)-100, (int)height-200);
		
		createList();
		//repaint();
	    //createTable();
	    //repaint();
		
	}
	public void createList()
	{
		list = new List();
		list.setBounds(60, 60, (int)(width*3/4)-100, (int)height-200);
		String papers[] = {"Paper 1 ","Paper 2","Paper 3"}; //Replace this with string of papers recieved from database
		int l = papers.length;
		for(int i=0;i<l;i++)
			list.add(papers[i]);
	  //String st= list.getItem(0);
		add(list);
		list.addItemListener(this);
	}
	void createTable()
	{
		TableModel m =new TableModel(15);
		table = new JTable(m);
		
		TableColumn c = new TableColumn();
		
		JTableHeader th = table.getTableHeader();
		
		//table.setCellSelectionEnabled(false);
		//table.removeEditor();
		//table.addColumn(c);
		//table.addColumn();
		
		
		TableColumn column = null;
		
		
		for(int i=0;i<3;i++)
		{
			column = table.getColumnModel().getColumn(i);
			if(i==1)
				column.setWidth(530);
			else
				column.setWidth(200);					
		}
		
		th.setBounds(60, 30, (int)(width*3/4)-100, 30);
		th.setBackground(Color.green);
		table.setBounds(60, 60, (int)(width*3/4)-100, (int)height-200);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(th);
		add(table);
		table.setVisible(true);
		//table.addVetoableChangeListener();
		//th.repaint();
		//table.repaint();
		//repaint();
		//table.updateUI();
		
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String st=e.getItem().toString();
		int choice = Integer.parseInt(st);
		//Get paper which is at index choice
		String paperChosen = list.getItem(choice);
		//Create Object Of Evaluate which will take as input the paper and will display the questions with answers 
		//and with students
		
		
		
	}
	
}

class TableModel extends AbstractTableModel
{
	String columnName[] = {"Date","Exam Name","Time"};
	int n;
	Object[][] data;
	TableModel(int n)
	{
		this.n=n;
		data = new Object[n][3];
	}
	
	@Override
	public int getColumnCount() {
		
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public String getColumnName(int col)
	{
		return columnName[col];
	}
	 
	
	
}

class MyTableModel extends DefaultTableModel
{
	String columnName[] = {"Date","Exam Name","Time"};
	int n;
	Object[][] data;
	
	@Override
	public int getColumnCount() {
		
		return columnName.length;
		 
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public void setSize(int n)
	{
		this.n=n;
	}
	
	
	
}