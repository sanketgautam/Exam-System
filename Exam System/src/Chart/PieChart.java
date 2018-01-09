package Chart;

import java.awt.BasicStroke;
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Login.dbConnector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;



public class PieChart extends JFrame {
	
	private JPanel contentPane;
	JPanel panel = new JPanel();
	DefaultPieDataset pieChartData;
	JFreeChart pieChart;
	PiePlot piechrt;
	ChartPanel piePanel;
	JButton back,saveimg;
	JFrame frame;
	String ename;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					double percentage[] = {10,15.5,30.6,25.1,18.8};
					PieChart frame = new PieChart("Samsung MockExam ",percentage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public PieChart(String ename,double data[]) {
		this.ename = ename;
		frame = this;
		
		pieChartData = new DefaultPieDataset();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,500);
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
		
		back.setBounds(500, 425, 80, 25);
		saveimg.setBounds(200, 425, 200, 25);
		contentPane.add(back);
		contentPane.add(saveimg);
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 10, 600, 400);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		generatePieChart(data);
		frame.setVisible(true);
	}
	void generatePieChart(double data[])
	{
		
		int range =100/data.length;
		
		for(int i=0;i<data.length;i++)
		{
			pieChartData.setValue("Range "+(range*i)+" - "+(range*(i+1))+"%",data[i]);
		}
		
		pieChart = ChartFactory.createPieChart("Pie Chart : "+ename,pieChartData, true, true, true);
		
		pieChart.getTitle().setPaint(Color.BLACK);

		pieChart.setBackgroundPaint(Color.WHITE);
		
		piechrt = (PiePlot)pieChart.getPlot();
		
		piePanel = new ChartPanel(pieChart);
		
		panel.removeAll();
		panel.add(piePanel,BorderLayout.CENTER);
		panel.validate();
		
	}
	void saveImage(String ename)
	{
		try{
			
			// Selected Directory into filename
			String filename = "charts\\"+ename.substring(0,Math.min(20, ename.length()))+"_PieChart.png";//Specify Path
			
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file = new File(filename);
			ChartUtilities.saveChartAsPNG(file, pieChart, 600, 400,info);
		}catch(Exception e){
			
		}
	}
}
