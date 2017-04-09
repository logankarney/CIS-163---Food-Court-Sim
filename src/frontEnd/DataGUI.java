package frontEnd;

import java.awt.BorderLayout; 

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backEnd.Clock;
import backEnd.Eatery;
import backEnd.EmptyQException;
import backEnd.PersonProducer;

public class DataGUI extends JFrame implements ActionListener {
	
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;

	//input JPanel information
	private JPanel input;
	
	private JLabel inputInfo;
	private JLabel line;
	private JLabel secToNextPerson;
	private JLabel avgSecondsCashier;
	private JLabel totalTime;
	private JLabel avgSecondsEatery;
	private JLabel secBeforePersonLeaves;
	private JLabel numOfEateries;
	
	private JTextField secToNextPersonLabel;
	private JTextField avgSecondsCashierLabel;
	private JTextField totalTimeLabel;
	private JTextField avgSecondsEateryLabel;
	private JTextField secBeforePersonLeavesLabel;
	private JTextField numOfEateriesLabel;
	
	private JButton start;
	private JButton stop;
	
	private JLabel outputInfo;
	private JLabel line2;
	
	private JLabel throughput;
	private JLabel throughput2;
	
	private JLabel avgTimePerson;
	private JLabel avgTimePerson2;
	
	private JLabel numPeopleInLine;
	private JLabel numPeopleInLine2;
	
	private JLabel maxQLengthCashier;
	private JLabel maxQLengthCashier2;
	
	public DataGUI(){
		
		a = "";
		b = "";
		c = "";
		d = "";
		e = "";
		f = "";
		
		/*
		 * Stuff added from the sim class that was given
		 */
		Clock clk = new Clock();
		Eatery booth = new Eatery();

		//	    int numOfTicksNextPerson = 20
		//      int averageBoothTime = 20
		
		PersonProducer produce = new PersonProducer(booth, 20, 18, 18, new Eatery());	
		clk.add(produce);
		clk.add(booth);
		try {
			clk.run(10000); 
		} catch (EmptyQException e){
			JOptionPane.showMessageDialog(this, "Queue is empty.");
		}
		//BorderLayout used to organize the JPanels in the JFrame
		this.setLayout(new BorderLayout());
		
		//Creating the input panel
		input = new JPanel();
		input.setLayout(new GridLayout(13, 2));
		
		inputInfo 	          = new JLabel("Input Information");
		line                  = new JLabel("---------------------------------------");
		secToNextPerson       = new JLabel("Seconds to the Next Person");
		avgSecondsCashier     = new JLabel("Average Seconds per cashier");
		totalTime             = new JLabel("Total time in seconds");
		avgSecondsEatery      = new JLabel("Average Seconds per Eatery");
		secBeforePersonLeaves = new JLabel("Seconds Before Person Leaves");
		numOfEateries         = new JLabel("Number of Eateries");
		
		secToNextPersonLabel       = new JTextField();
		avgSecondsCashierLabel     = new JTextField();
		totalTimeLabel             = new JTextField();
		avgSecondsEateryLabel      = new JTextField();
		secBeforePersonLeavesLabel = new JTextField();
		numOfEateriesLabel         = new JTextField();
		

		outputInfo         = new JLabel("Output Information");
		line2              = new JLabel("---------------------------------------");
		throughput         = new JLabel("Throughput ");
		throughput2        = new JLabel(booth.getThroughPut() + " people with Max = 500");
		avgTimePerson      = new JLabel("Average time for a Person from start to finish: " );
		avgTimePerson2     = new JLabel( "ADD IN AVG SEC METHOD people");
		numPeopleInLine    = new JLabel("Number of people left in line" );
		numPeopleInLine2   = new JLabel("ADD IN PEOPLE LEFT IN LINE METHOD" + " people");
		maxQLengthCashier  = new JLabel("Max Q length cashier line "); 
		maxQLengthCashier2 = new JLabel(booth.getMaxQlength() + "");
		
		start = new JButton("Start Simulation");
		start.addActionListener(this);
		
		stop = new JButton("Quit Simulation");
		stop.addActionListener(this);
		
		this.add(input, BorderLayout.NORTH);
		
		input.add(inputInfo);
		input.add(line);
		input.add(secToNextPerson);
		input.add(secToNextPersonLabel);
		
		input.add(avgSecondsCashier);
		input.add(avgSecondsCashierLabel);
		
		input.add(totalTime);
		input.add(totalTimeLabel);
		
		input.add(avgSecondsEatery);
		input.add(avgSecondsEateryLabel);
		
		input.add(secBeforePersonLeaves);
		input.add(secBeforePersonLeavesLabel);
		
		input.add(numOfEateries);
		input.add(numOfEateriesLabel);
		
		input.add(start);
		input.add(stop);
		
		input.add(outputInfo);
		input.add(line2);
		
		input.add(throughput);
		input.add(throughput2);
		
		input.add(avgTimePerson);
		input.add(avgTimePerson2);
		
		input.add(numPeopleInLine);
		input.add(numPeopleInLine2);
		
		input.add(maxQLengthCashier);
		input.add(maxQLengthCashier2);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			this.a = secToNextPersonLabel.getText();    
			this.b = avgSecondsCashierLabel.getText();    
			this.c = totalTimeLabel.getText();              
			this.d = avgSecondsEateryLabel .getText();      
			this.e = secBeforePersonLeavesLabel.getText();  
			this.f = numOfEateriesLabel.getText();  
			try{
				Integer.parseInt(this.a);
				Integer.parseInt(this.b);
				Integer.parseInt(this.c);
				Integer.parseInt(this.d);
				Integer.parseInt(this.e);
				Integer.parseInt(this.f);
			}
			catch(Exception f){
				JOptionPane.showMessageDialog(input , "Put in valid integers");
			}
		}
		if(e.getSource() == stop){
			
		}
	}
	public static void main(String[] args){
		DataGUI frame = new DataGUI();
		frame.setTitle("Food Court Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 500));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
