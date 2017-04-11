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
import backEnd.PersonProducer;

public class DataGUI extends JFrame implements ActionListener {

	private Clock clk;
	private Eatery booth;

	// input JPanel information
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

	private JMenuBar menu;
	private JMenu fileMenu;
	private JMenuItem clearItem;
	private JMenuItem quitItem;

	private JLabel empty;
	private JLabel specialNeeds;
	private JLabel limitedTime;
	private JLabel regular;
	private JLabel snStats;
	private JLabel ltStats;
	private JLabel regStats;

	public DataGUI() {

		/*
		 * Stuff added from the sim class that was given
		 */
		clk = new Clock();
		booth = new Eatery();

		// int numOfTicksNextPerson = 20
		// int averageBoothTime = 20

		// PersonProducer produce = new PersonProducer(booth, 20, 18, 18);
		// clk.add(produce);
		// clk.add(booth);

		// clk.run(10000);

		// BorderLayout used to organize the JPanels in the JFrame
		this.setLayout(new BorderLayout());

		// Creating the input panel

		input = new JPanel();
		input.setLayout(new GridLayout(20, 2));

		inputInfo = new JLabel("Input Information");
		line = new JLabel("----------------------------------------------");
		secToNextPerson = new JLabel("Seconds to the Next Person");
		avgSecondsCashier = new JLabel("Average Seconds per cashier");
		totalTime = new JLabel("Total time in seconds");
		avgSecondsEatery = new JLabel("Average Seconds per Eatery");
		secBeforePersonLeaves = new JLabel("Seconds Before Person Leaves");
		numOfEateries = new JLabel("Number of Eateries");

		secToNextPersonLabel = new JTextField();
		avgSecondsCashierLabel = new JTextField();
		totalTimeLabel = new JTextField();
		avgSecondsEateryLabel = new JTextField();
		secBeforePersonLeavesLabel = new JTextField();
		numOfEateriesLabel = new JTextField();

		outputInfo = new JLabel("Output Information");
		line2 = new JLabel("----------------------------------------------");
		throughput = new JLabel("Throughput ");

		// throughput2 = new JLabel(booth.getThroughPut() + " people with Max =
		// 500");
		throughput2 = new JLabel("");

		avgTimePerson = new JLabel("Average time for a Person from start to finish: ");

		// avgTimePerson2 = new JLabel( "ADD IN AVG SEC METHOD people");
		avgTimePerson2 = new JLabel("");

		numPeopleInLine = new JLabel("Number of people left in line");

		// numPeopleInLine2 = new JLabel("ADD IN PEOPLE LEFT IN LINE METHOD" + "
		// people");
		numPeopleInLine2 = new JLabel("");

		maxQLengthCashier = new JLabel("Max Q length cashier line ");

		// maxQLengthCashier2 = new JLabel(booth.getMaxQlength() + "");
		maxQLengthCashier2 = new JLabel("");

		start = new JButton("Start Simulation");
		start.addActionListener(this);

		stop = new JButton("Quit Simulation");
		stop.addActionListener(this);

		specialNeeds = new JLabel("Special Needs Person Time:");
		limitedTime = new JLabel("Limited Time Person Time:");
		regular      = new JLabel("Regular Person Time:");
		snStats      = new JLabel("");
		ltStats      = new JLabel("");
		regStats     = new JLabel("");

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

		menu.add(fileMenu);
		setJMenuBar(menu);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {

			int nextPersonTime;
			int cashierTime;
			int totalTime;
			int avgEateryTime;
			int secsBeforePersonLeaves;
			int numEateries;

				try {

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
				while(true){
					
					clk.run(totalTime);
					for(int i = 0; i < numEateries; i++){
						
					}
					// PUT FOODCOURTLOGIC CLASS HERE
					
					//public int getLeft() {
					//	return Q.size();
					//}
					
					//public int getMaxQlength() {
					//	return maxQlength;
					//}

					//public int getThroughPut() {
					//	return completed;
					//}
					
					throughput2.setText("Total Time: " + booth.getThroughPut() + " seconds");
					avgTimePerson2.setText("seconds");
					numPeopleInLine2.setText(booth.getLeft() + " people");
					maxQLengthCashier2.setText(booth.getMaxQlength() + " people");
					snStats.setText("seconds");
					ltStats.setText("seconds");
					regStats.setText("seconds");
					if(clk.getHasEnded())
						break;
			}
		} else if (e.getSource() == stop) {
			throughput2.setText("Total Time: " + " seconds");
			avgTimePerson2.setText("seconds");
			numPeopleInLine2.setText(" people");
			maxQLengthCashier2.setText(" people");
			snStats.setText("seconds");
			ltStats.setText("seconds");
			regStats.setText("seconds");
			// FOODCOURTLOGIC OBJECT = NULL;
			
		} else if (e.getSource() == clearItem) {
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
