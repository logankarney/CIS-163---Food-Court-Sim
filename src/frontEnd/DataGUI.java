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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backEnd.Clock;
import backEnd.Eatery;
import backEnd.PersonProducer;

public class DataGUI extends JFrame implements ActionListener {

	JPanel stats;
	JPanel buttons;
	JPanel labels;

	JButton start;
	JButton stop;

	private JTextField secondsToNextPerson;
	private JTextField averageSecondsCashier;
	private JTextField totalTime;
	private JTextField averageSecondsEatery;
	private JTextField secondsBeforePersonLeaves;
	private JTextField numberOfEateries;
	
	
	Eatery booth;

	public DataGUI() {
		setLayout(new BorderLayout());
		
		
		Clock clk = new Clock();
		booth = new Eatery();

		// 		int numOfTicksNextPerson = 20 
		//      int averageBoothTime = 20
		
		PersonProducer produce = new PersonProducer(booth, 20, 18);	
		clk.add(produce);
		clk.add(booth);
		
		clk.run(10000);
		
		addInformation();
		// addButtons();
		addInfo();
	}

	public void addInformation() {
		Info inputInfo;
		Info outputInfo;

		stats = new JPanel();
		stats.setLayout(new BorderLayout());

		String[] inputText = { "Input Information", "Seconds to the Next Person", "Average Seconds per Cashier",
				"Total time in seconds", "Average Seconds per Eatery", "Seconds Before Person leaves",
				"Number of Eateries" };

		String[] outPutText = { "Output Information", "Throughput", "Average time for a Person from start to finish:",
				"Number of people left line", "Max Q length cashier line" };

		inputInfo = new Info(inputText, 5, 20, 30);
		outputInfo = new Info(outPutText, 5, 80, 30);

		inputInfo.setPreferredSize(new Dimension(100, 240));
		outputInfo.setPreferredSize(new Dimension(100, 250));

		stats.add(inputInfo, BorderLayout.NORTH);
		stats.add(outputInfo, BorderLayout.CENTER);

		stats.setPreferredSize(new Dimension(260, 550));
		add(stats, BorderLayout.WEST);

		labels = new JPanel();
		labels.setLayout(new BorderLayout());

		String[] outputText = {"------------------------------------------------------------...", booth.getThroughPut() + " people with Max = ___", "___ seconds", "__ people", "_" };
		
		
		outputInfo = new Info(outputText, 00, 40, 30);
		labels.add(outputInfo, BorderLayout.CENTER);
	}

	public void addInfo() {

		FieldInfo field;

		start = new JButton("Start Simulation");
		
		
		stop = new JButton("Stop Simulation");
		
		secondsToNextPerson = new JTextField(20);
		averageSecondsCashier = new JTextField(20);
		totalTime = new JTextField(20);
		averageSecondsEatery = new JTextField(20);
		secondsBeforePersonLeaves = new JTextField(20);
		numberOfEateries = new JTextField(20);

		JTextField[] labelArray = { secondsToNextPerson, averageSecondsCashier, totalTime, averageSecondsEatery,
				secondsBeforePersonLeaves, numberOfEateries };

		field = new FieldInfo(labelArray, start, stop);
		//field.setPreferredSize(new Dimension(0, 5));

		labels.add(field, BorderLayout.NORTH);
		//labels.setPreferredSize(new Dimension(300, 30));
		labels.setBorder(new EmptyBorder(37,0,0,0));

		add(labels, BorderLayout.EAST);
		
		revalidate();
	}

	public static void main(String[] args) {
		DataGUI g = new DataGUI();
		g.setSize(650, 550);
		// g.setResizable(false);
		g.setVisible(true);
		g.setTitle("Food Court Simulation");
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			System.out.println("Start");
		}
		
	}
}

class Info extends JPanel {
	private String[] data;

	private int x;

	private int y;

	private int increment;

	public Info(String[] data, int x, int y, int increment) {
		this.data = data;
		this.x = x;
		this.y = y;
		this.increment = increment;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < data.length; i++) {
			g.drawString(data[i], x, i * increment + y);
		}
	}
}

class FieldInfo extends JPanel {
	//private JButton start, stop;
	
	public FieldInfo(JTextField[] array, JButton start, JButton stop) {
		//setSize(100,550);
		GridLayout layout = new GridLayout(7, 1);

		layout.setVgap(10);

		setLayout(layout);
		
		
		for (JTextField t : array) {
			add(t);
		}
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		
		start = new JButton("Start Simulation");
		buttons.add(start);
	
		buttons.add(stop);
		
		add(buttons);
	}
}
