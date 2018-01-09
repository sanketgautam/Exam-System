package Chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Instructor.ExamDetails;
import Login.dbConnector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class BarChart extends JFrame {
	
	private JPanel contentPane;
	JPanel panel = new JPanel();
	DefaultCategoryDataset barChartData;
	JFreeChart barChart;
	CategoryPlot barchrt;
	ChartPanel barPanel;
	JButton back,saveimg;
	JFrame frame;
	String ename;
	
	/*
	 * public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					double percentage[] = {10,15.5,30.6,25.1,18.8};
					BarChart frame = new BarChart("Samsung MockExam ",percentage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public BarChart(String ename,double data[]) {
		this.ename = ename;
		frame = this;
		//frame.setla
		barChartData = new DefaultCategoryDataset();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050,500);
		//pack();
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		back = new JButton("Back < ");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					frame.dispose();
					//Code to move back
			}
		});
		saveimg = new JButton("Save As Image ");
		saveimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveImage(ename);
			}
		});
		
		back.setBounds(900, 425, 80, 25);
		saveimg.setBounds(400, 425, 200, 25);
		contentPane.add(back);
		contentPane.add(saveimg);
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 10, 1000, 400);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		generateBarChart(data);
		frame.setVisible(true);
		
	}
	void generateBarChart(double data[])
	{
		int range =100/data.length;
		for(int i=0;i<data.length;i++)
		{
			barChartData.setValue(data[i],"Percentage Of Students", ""+(range*i)+" - "+(range*(i+1)+"%"));
		}
		barChart = ChartFactory.createBarChart3D("BarChart : "+ename, "Range Of Marks", "Percentage Of Students", 
														barChartData,PlotOrientation.VERTICAL,false,true,false);
		barChart.getTitle().setPaint(Color.BLACK);
		barChart.setBackgroundPaint(Color.WHITE);
		barchrt = barChart.getCategoryPlot();
		barchrt.setRangeGridlinePaint(Color.white);
		barPanel = new ChartPanel(barChart);
		
		panel.removeAll();
		panel.add(barPanel,BorderLayout.CENTER);
		panel.validate();
		
	}
	void saveImage(String ename)
	{
		try{
			
			// Selected Directory into filename
			String filename ="charts\\"+ ename.substring(0,Math.min(20, ename.length()))+"_BarChart.png";//Specify Path
			
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file = new File(filename);
			ChartUtilities.saveChartAsPNG(file, barChart, 1024, 400,info);
		}catch(Exception e){
			
		}
	}
}
