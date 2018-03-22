package socket_connection;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;


public class Gui_Bulder {
	static String slots []={"slot 1","slot 2","slot 3","slot 4","slot 5","slot 6","slot 7","slot 8"};
	static ArrayList<String> drinks = new ArrayList();
	
	static File drinksFile = new File("Drinks");
	static File mixerFile = new File("DrinkMixer");
	
	//GUI Init()
	static JTextArea slot1 =new JTextArea();
	static JTextArea slot2 =new JTextArea();
	static JTextArea slot3 =new JTextArea();
	static JTextArea slot4 =new JTextArea();
	static JTextArea slot5 =new JTextArea();
	static JTextArea slot6 =new JTextArea();
	static JTextArea slot7 =new JTextArea();
	static JTextArea slot8 =new JTextArea();
	
	//varios command buttons 
	static JButton pauseBttn = new JButton("PAUSE");
	static JButton shutDownBttn = new JButton("SHUT DOWN");
	static JButton viewQue = new JButton("VIEW QUE");
	static JButton readyButton = new JButton("READY FOR NEXT DRINK");
	
	//set up gui here
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel(new GridLayout(1,3));
	static JPanel slotPanel = new JPanel((new GridLayout(16,1)));
	static JPanel middlePanel = new JPanel(new GridLayout(8,1));
	static JPanel rightPanel = new JPanel(new GridLayout(8,1));
	
	
	//adds change buttons for each slot action listers bring new window up
	static JButton changeBttn1 = new JButton("change1");
	static JButton changeBttn2 = new JButton("change2");
	static JButton changeBttn3 = new JButton("change3");
	static JButton changeBttn4 = new JButton("change4");
	static JButton changeBttn5 = new JButton("change5");
	static JButton changeBttn6 = new JButton("change6");
	static JButton changeBttn7 = new JButton("change7");
	static JButton changeBttn8 = new JButton("change8");
	
	
	//change window
	static JTextArea current = new JTextArea();
	
	public static void main(String[] args) {
			ServerThread server = new ServerThread();
			server.start(); // starts server thread			
			try {
				getUpdatedSlots();
				getDrinksList();
			} catch (IOException e) {
				e.printStackTrace();
			}

			setUpGui();
	}
	public static void getUpdatedSlots()throws IOException{
		FileInputStream in = null;
		BufferedReader buff = null;
		try{
			in = new FileInputStream(mixerFile);
			buff = new BufferedReader(new InputStreamReader(in));
			String line =null;
			int i = 0;
			while((line = buff.readLine()) != null){
					slots[i] =line;
					System.out.println(line);
					i++;	
			}
		}finally{
			buff.close();
		}
	}
	public static void getDrinksList() throws IOException{
		FileInputStream in = null;
		BufferedReader buff = null;
		try{
			in = new FileInputStream(drinksFile);
			buff = new BufferedReader(new InputStreamReader(in));
			String line =null;
			int i = 0;
			while((line =buff.readLine())!=null){
				drinks.add(line);
				i++;	
			}
			
		}finally{
			buff.close();
		}
	}
	public static void setUpGui(){

		frame.setPreferredSize(new Dimension(500,400));
		frame.setVisible(true);
		frame.setTitle("Drink Mixer Main Menu");
		
		//initializes slots and reads from slots to see what is in each slot
		slot1.setText(slots[0]);
		slot1.setEditable(false);
		slot2.setText(slots[1]);
		slot2.setEditable(false);
		slot3.setText(slots[2]);
		slot3.setEditable(false);
		slot4.setText(slots[3]);
		slot4.setEditable(false);
		slot5.setText(slots[4]);
		slot5.setEditable(false);
		slot6.setText(slots[5]);
		slot6.setEditable(false);
		slot7.setText(slots[6]);
		slot7.setEditable(false);
		slot8.setText(slots[7]);
		slot8.setEditable(false);
		
		//makes labels for each slot
		JLabel slot_lable_1 = new JLabel();
		slot_lable_1.setText("slot 1");
		JLabel slot_lable_2 = new JLabel();
		slot_lable_2.setText("slot 2");
		JLabel slot_lable_3 = new JLabel();
		slot_lable_3.setText("slot 3");
		JLabel slot_lable_4 = new JLabel();
		slot_lable_4.setText("slot 4");
		JLabel slot_lable_5 = new JLabel();
		slot_lable_5.setText("slot 5");
		JLabel slot_lable_6 = new JLabel();
		slot_lable_6.setText("slot 6");
		JLabel slot_lable_7 = new JLabel();
		slot_lable_7.setText("slot 7");
		JLabel slot_lable_8 = new JLabel();
		slot_lable_8.setText("slot 8");
		
		
		
		changeBttn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					Socket socket = new Socket("localhost",6166);
					/*Socket socketListner = new Socket("localhost",6167);
					
					BufferedReader input = new BufferedReader(new InputStreamReader(socketListner.getInputStream()));
					
					
					String answer =input.readLine();
					System.out.println(answer);*/
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					out.println("PAUSE");
					changeEventListner(0);
					out.close();
					socket.close();
					
				}catch(Exception e){
					
				}
				
			}
			});
		changeBttn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(1);
			}
			});
		changeBttn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(2);
			}
			});
		changeBttn4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(3);
			}
			});
		changeBttn5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(4);
			}
			});
		changeBttn6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(5);
			}
			});
		changeBttn7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(6);
			}
			});
		changeBttn8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changeEventListner(7);
			}
			});

		
		//add to main JFrame
		slotPanel.add(slot_lable_1);
		slotPanel.add(slot1);
		slotPanel.add(slot_lable_2);
		slotPanel.add(slot2);
		slotPanel.add(slot_lable_3);
		slotPanel.add(slot3);
		slotPanel.add(slot_lable_4);
		slotPanel.add(slot4);
		slotPanel.add(slot_lable_5);
		slotPanel.add(slot5);
		slotPanel.add(slot_lable_6);
		slotPanel.add(slot6);
		slotPanel.add(slot_lable_7);
		slotPanel.add(slot7);
		slotPanel.add(slot_lable_8);
		slotPanel.add(slot8);
		
		middlePanel.add(changeBttn1);
		middlePanel.add(changeBttn2);
		middlePanel.add(changeBttn3);
		middlePanel.add(changeBttn4);
		middlePanel.add(changeBttn5);
		middlePanel.add(changeBttn6);
		middlePanel.add(changeBttn7);
		middlePanel.add(changeBttn8);
		
		rightPanel.add(pauseBttn);
		rightPanel.add(shutDownBttn);
		rightPanel.add(readyButton);
		rightPanel.add(viewQue);
		
		panel.add(slotPanel);
		panel.add(middlePanel);
		panel.add(rightPanel);
		
		frame.add(panel);
	}
	static void changeEventListner(int slotNumber){
		JFrame frame = new JFrame();
		JPanel mainPanel = new JPanel(new GridLayout(2,1));
		JPanel upperPanel = new JPanel(new GridLayout(9,2));
		JPanel lowerPanel = new JPanel(new GridLayout(6,3));
		
		JLabel empty = new JLabel(" ");
		JLabel current_label = new JLabel("Current");
		current.setText(slots[slotNumber]);
		current.setEditable(false);
		JLabel newLabel = new JLabel("changing to");
		JComboBox drinksList = new JComboBox();
		JButton submit = new JButton("SUBMIT");
		for(int i = 0; i < drinks.size(); i++){
			drinksList.addItem(drinks.get(i));
		}
		//needs work!
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileWriter fw = null;
				BufferedWriter out = null;
				slots[slotNumber] = (String) drinksList.getSelectedItem();
				String fileContext =slots[0];
				for(int i =1;i<8;i++){
					fileContext = fileContext +"\n"+ slots[i];
				}
				try {
					fw = new FileWriter(mixerFile);
					out = new BufferedWriter(fw);
					out.write(fileContext);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(out != null){
						try {
							out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(fw != null){
						try {
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				switch(slotNumber+1){
				case 1: Thread thread1 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot1.setText(slots[slotNumber]);
							slot1.revalidate();}});
						thread1.start();
						break;
				case 2: Thread thread2 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot2.setText(slots[slotNumber]);
							slot2.revalidate();}});
						thread2.start();
						break;
				case 3: Thread thread3 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot3.setText(slots[slotNumber]);
							slot3.revalidate();}});
						thread3.start();
						break;		
				case 4:  Thread thread4 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot4.setText(slots[slotNumber]);
							slot4.revalidate();}});
						thread4.start();
						break;
				case 5: Thread thread5 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot5.setText(slots[slotNumber]);
							slot5.revalidate();}});
						thread5.start();
						break;
				case 6: Thread thread6 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot6.setText(slots[slotNumber]);
							slot6.revalidate();}});
						thread6.start();
						break;
				case 7: Thread thread7 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot7.setText(slots[slotNumber]);
							slot7.revalidate();}});
						thread7.start();
						break;
				case 8: Thread thread8 = new Thread(new Runnable(){public void run(){
							current.setText(slots[slotNumber]);
							current.revalidate();
							slot8.setText(slots[slotNumber]);
							slot8.revalidate();}});
						thread8.start();
						break;
				
				default: System.out.println(slotNumber);
			}
			}
			});
		
		upperPanel.add(current_label);
		upperPanel.add(current);
		upperPanel.add(newLabel);
		upperPanel.add(drinksList);
		lowerPanel.add(submit);
		mainPanel.add(upperPanel);
		mainPanel.add(lowerPanel);
		frame.add(mainPanel);
		frame.setSize(900,900);
		frame.setVisible(true);
	}
}
