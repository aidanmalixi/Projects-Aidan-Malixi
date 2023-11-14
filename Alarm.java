

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Container;

import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Alarm implements Runnable, ActionListener{
	private static String sep = System.getProperty("file.separator");
	private static String ringTonPath = System.getProperty("user.dir") + sep + "src" + sep
			+ "resources" + sep + "Ring Tones" + sep + "time-alarm.wav";
	
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private int hoursAlarm = 0;
	private int minutesAlarm = 0;
	private int secondsAlarm = 0;
	
	private Clip clip;
	private Thread clockThread;

	private String timeToRing = "05:23:15";

	//Components GUI
	private JFrame frame;
		private JPanel timePanel = new JPanel();
			private JLabel timeLabel = new JLabel("", JLabel.CENTER);

		private JButton enterAlarm = new JButton();	
		private JTextField timeInput = new JTextField();
		private JButton stopAlarm = new JButton();
	
	
	/**
	 * Standard Constructor. Also initialise GUI
	 */
	public Alarm(){
		//intialising frame 
		frame = new JFrame("Alarm Clock");

		int frameWidth = 400;
		int frameHeight = 600;
		frame.setSize(frameWidth, frameHeight);

		//Applying close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Position of GUI-Window when opened
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - frame.getSize().width) / 2;
		int y = (d.height - frame.getSize().height) / 2;
		frame.setLocation(x, y);

		//window resizable
		frame.setResizable(false);

		//content Pane
		Container cp = frame.getContentPane();
		cp.setLayout(null);

		//Time-Panel
		timePanel.setOpaque(false);
		timePanel.setBounds(20, 80, 340, 100);
		timePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "hh:mm:ss"));
		cp.add(timePanel);
			timeLabel.setBounds(30, 100, 200, 100);
			timeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
			timeLabel.setText("TEXTTTTTTTT");
			
			timePanel.add(timeLabel);

		timeInput.requestFocus();
		timeInput.setBounds(50, 300, 300, 60);
		cp.add(timeInput);

		enterAlarm.setText("Add Alarm Time");
		enterAlarm.setBounds(105, 450, 170, 50);
		enterAlarm.addActionListener(this);
		cp.add(enterAlarm);
		
		stopAlarm.setText("Stop Alarm Ringtone");
		stopAlarm.setBounds(105, 380, 170, 50);
		stopAlarm.addActionListener(this);
		cp.add(stopAlarm);

		//starting Thread
		clockThread = new Thread(this);
		clockThread.start();

		
		frame.setVisible(true);
	}
	
	
	/**
	 * Method to Import a ringtone in project folder and play it
	 */
	public void playMusic(){
		File file;

		try {
			file = new File(ringTonPath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();

			clip.open(audioStream);
			clip.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Override
	public void run() {
		try {
			while(true){
				SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
	
				//Now Calender
				Calendar cal = Calendar.getInstance();
				hours = cal.get(Calendar.HOUR_OF_DAY);
				if(hours > 12){
					hours -= 12;
				}
				minutes = cal.get(Calendar.MINUTE);
				seconds = cal.get(Calendar.SECOND);
	
				//Ringing Time
				Calendar calenderRing = Calendar.getInstance();
				Date dateToRing = formatter.parse(timeToRing);
				calenderRing.setTime(dateToRing);
				hoursAlarm = calenderRing.get(Calendar.HOUR_OF_DAY);
				if(hoursAlarm > 12){
					hours -= 12;
				}
				minutesAlarm = calenderRing.get(Calendar.MINUTE);
				secondsAlarm = calenderRing.get(Calendar.SECOND);

				Date actualTime = cal.getTime();
				timeLabel.setText(formatter.format(actualTime));

				//if Time matches 
				if(hours == hoursAlarm && minutes == minutesAlarm && seconds == secondsAlarm){
					playMusic();
					JOptionPane.showMessageDialog(null, "Wake Up!");
				}

				//1 Second sleep time
				Thread.sleep(1000);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * ActionListener for Buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enterAlarm){
			timeToRing = timeInput.getText().trim();
			System.out.println("Time to Ring: " + timeToRing);
			JOptionPane.showMessageDialog(null, "Alarm added: " + timeToRing);
			timeInput.setText("");
			timeInput.requestFocus();
		}
		if(e.getSource() == stopAlarm){
			timeToRing = "00:00:00";
			clip.stop();
		}
		
	}

	
	public static void main(String[] args){
		new Alarm();
	}
}
