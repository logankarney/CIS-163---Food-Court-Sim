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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backEnd.Clock;
import backEnd.Eatery;
import backEnd.EmptyQException;
import backEnd.FoodCourtLogic;
import backEnd.PersonProducer;
/**
 * @author Ryan Eisenbarth, Logan Karney, Logun DeLeon
 * @version  4/11/17
 * GUI for the CIS 163 Food Court Project
 */

public class DataGUI extends JFrame implements ActionListener {

	//Variable to create the food court
	private FoodCourtLogic booth;

	// input JPanel information
	private JPanel input;

	//input info
	private JLabel inputInfo;
	private JLabel line;
	private JLabel secToNextPerson;
	private JLabel avgSecondsCashier;
	private JLabel totalTime;
	private JLabel avgSecondsEatery;
	private JLabel secBeforePersonLeaves;
	private JLabel numOfEateries;

	//input text fields
	private JTextField secToNextPersonLabel;
	private JTextField avgSecondsCashierLabel;
	private JTextField totalTimeLabel;
	private JTextField avgSecondsEateryLabel;
	private JTextField secBeforePersonLeavesLabel;
	private JTextField numOfEateriesLabel;

	//start and stop button
	private JButton start;
	private JButton stop;

	//stats labels
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

	//menu labels
	private JMenuBar menu;
	private JMenu fileMenu;
	private JMenuItem clearItem;
	private JMenuItem quitItem;

	//stats labels
	private JLabel empty;
	private JLabel specialNeeds;
	private JLabel limitedTime;
	private JLabel regular;
	private JLabel snStats;
	private JLabel ltStats;
	private JLabel regStats;

	/*
	 * GUI constructor
	 */
	public DataGUI() {

		// BorderLayout used to organize the JPanels in the JFrame
		this.setLayout(new BorderLayout());

		//create JPanel and layout
		input = new JPanel();
		input.setLayout(new GridLayout(20, 2));

		//create all of the labels and their text
		inputInfo = new JLabel("Input Information");
		line = new JLabel("----------------------------------------------");
		secToNextPerson = new JLabel("Seconds to the Next Person");
		avgSecondsCashier = new JLabel("Average Seconds per cashier");
		totalTime = new JLabel("Seconds Before Person Leaves");
		avgSecondsEatery = new JLabel("Average Seconds per Eatery");
		secBeforePersonLeaves = new JLabel("Total Time in Seconds");
		numOfEateries = new JLabel("Number of Eateries");

		//creates the top JTextFields
		secToNextPersonLabel = new JTextField();
		avgSecondsCashierLabel = new JTextField();
		totalTimeLabel = new JTextField();
		avgSecondsEateryLabel = new JTextField();
		secBeforePersonLeavesLabel = new JTextField();
		numOfEateriesLabel = new JTextField();

		//creates all of the output labels
		outputInfo = new JLabel("Output Information");
		line2 = new JLabel("----------------------------------------------");
		throughput = new JLabel("Throughput ");
		throughput2 = new JLabel("");
		avgTimePerson = new JLabel("Average time for a Person from start to finish: ");
		avgTimePerson2 = new JLabel("");
		numPeopleInLine = new JLabel("Number of people left in line");
		numPeopleInLine2 = new JLabel("");
		maxQLengthCashier = new JLabel("Max Q length cashier line ");
		maxQLengthCashier2 = new JLabel("");

		//creates start and stop method
		start = new JButton("Start Simulation");
		start.addActionListener(this);

		stop = new JButton("Quit Simulation");
		stop.addActionListener(this);

		//creates the special stats labels
		specialNeeds = new JLabel("Special Needs Person Average Time:");
		limitedTime = new JLabel("Limited Time Person Average Time:");
		regular      = new JLabel("Regular Person Average Time:");
		snStats      = new JLabel("");
		ltStats      = new JLabel("");
		regStats     = new JLabel("");

		//adds input panel
		this.add(input, BorderLayout.NORTH);

		//adds everything to the panel
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
		input.add(specialNeeds);
		input.add(snStats);
		input.add(limitedTime);
		input.add(ltStats);
		input.add(regular);
		input.add(regStats);
		input.add(numPeopleInLine);
		input.add(numPeopleInLine2);
		input.add(maxQLengthCashier);
		input.add(maxQLengthCashier2);

		//creates JMenu items
		menu = new JMenuBar();
		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		clearItem = new JMenuItem("Clear");

		// adds action listeners
		clearItem.addActionListener(this);
		quitItem.addActionListener(this);

		// adds items to menu
		fileMenu.add(clearItem);
		fileMenu.add(quitItem);

		//adds JMenu
		menu.add(fileMenu);
		setJMenuBar(menu);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 *Listens to actions performed by user
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @exception Exception , EmptyQException
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {

			int nextPersonTime;
			int cashierTime;
			int totalTime;
			int avgEateryTime;
			int secsBeforePersonLeaves;
			int numEateries;

			try {
				//Checks if text is a number
				nextPersonTime = Integer.parseInt(secToNextPersonLabel.getText());
				cashierTime = Integer.parseInt(avgSecondsCashierLabel.getText());
				totalTime = Integer.parseInt(totalTimeLabel.getText());
				avgEateryTime = Integer.parseInt(avgSecondsEateryLabel.getText());
				secsBeforePersonLeaves = Integer.parseInt(secBeforePersonLeavesLabel.getText());
				numEateries = Integer.parseInt(numOfEateriesLabel.getText());


			}
			catch (Exception f) {
				JOptionPane.showMessageDialog(input, "Put in valid integers");
				return;
			}

			booth = new FoodCourtLogic(nextPersonTime, avgEateryTime, cashierTime, secsBeforePersonLeaves);
			booth.addCheckOut();
			booth.addCheckOut();
			for(int i = 0; i < numEateries; i++){
				booth.addEatery();
			}
			try{
				booth.run(totalTime);
			}
			catch(EmptyQException ez){
				JOptionPane.showMessageDialog(input, "Error, Tried to remove from Empty Que");
			}

			throughput2.setText(booth.getThroughput() + " people");
			avgTimePerson2.setText(String.format("%.3f", booth.averageTime()) + " seconds");
			numPeopleInLine2.setText(booth.findPeopleLeft() + " people");
			maxQLengthCashier2.setText(booth.findMaxEateryLine() + " people");
			snStats.setText(String.format("%.3f", booth.getSNAvgTime()) + " seconds");
			ltStats.setText(String.format("%.3f", booth.getLTAvgTime()) + " seconds");
			regStats.setText(String.format("%.3f", booth.getRAvgTime()) + " seconds");
			this.booth = new FoodCourtLogic(0,0,0,0);

		}
		//clears the stats
		else if (e.getSource() == stop) {
			throughput2.setText("people");
			avgTimePerson2.setText("seconds");
			numPeopleInLine2.setText(" people");
			maxQLengthCashier2.setText(" people");
			snStats.setText("seconds");
			ltStats.setText("seconds");
			regStats.setText("seconds");

		}
		//clears the screen
		else if (e.getSource() == clearItem) {
			secToNextPersonLabel.setText("");
			avgSecondsCashierLabel.setText("");
			totalTimeLabel.setText("");
			avgSecondsEateryLabel.setText("");
			secBeforePersonLeavesLabel.setText("");
			numOfEateriesLabel.setText("");
			throughput2.setText("");
			avgTimePerson2.setText("");
			numPeopleInLine2.setText("");
			maxQLengthCashier2.setText("");
			snStats.setText("");
			ltStats.setText("");
			regStats.setText("");

		} else if (e.getSource() == quitItem){
			System.exit(1);
		}

	}

	public static void main(String[] args) {
		DataGUI frame = new DataGUI();
		frame.setTitle("Food Court Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 500));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
