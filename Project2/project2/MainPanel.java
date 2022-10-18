package project2;
import netflix.ShowWeek;
import netflix.NetflixWeeklys; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//Ryan Murphey
//CSCI 3381 OO with Java Project 2
//
//
//
import javax.swing.*;
public class MainPanel extends JPanel {
	private final int WIDTH = 800, HEIGHT = 500;

	private ImageIcon image;
	private JPanel controlPanel;
	private JTextArea textArea_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();



	public MainPanel()
	{
		super(new BorderLayout());
		NetflixWeeklys allData = new NetflixWeeklys("./netflix/netflixdata.txt");


		controlPanel = new JPanel();
		controlPanel.setPreferredSize (new Dimension(WIDTH , HEIGHT));
		controlPanel.setBackground (Color.DARK_GRAY);
		controlPanel.setLayout(null);



		setPreferredSize (new Dimension(600, 500));
		setBackground (Color.LIGHT_GRAY);
		add(controlPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 79, 180, 343);
		controlPanel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(allData.toString2());
		textArea.setBackground(Color.LIGHT_GRAY);

		JComboBox weekdates = new JComboBox();
		weekdates.setBackground(new Color(128, 128, 128));
		weekdates.setModel(new DefaultComboBoxModel(allData.getWeeks()));
		weekdates.setBounds(10, 127, 123, 23);
		controlPanel.add(weekdates);


		JComboBox weekshows = new JComboBox();
		weekshows.setBackground(new Color(128, 128, 128));
		weekshows.setBounds(10, 214, 274, 23);
		controlPanel.add(weekshows);


		JLabel lblNewLabel = new JLabel("Choose week of Shows");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 110, 154, 19);
		controlPanel.add(lblNewLabel);

		JButton getshows = new JButton("Get Shows");
		getshows.setBackground(new Color(128, 128, 128));
		getshows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] dateShows = new String[40];
				ArrayList<ShowWeek> movies = allData.getShows( weekdates.getSelectedItem().toString());
				for(int i=0;i<40;i++) {
					dateShows[i] = movies.get(i).toString2();
				}
				weekshows.setModel(new DefaultComboBoxModel(dateShows));

			}
		});

		getshows.setBounds(141, 127, 102, 23);
		controlPanel.add(getshows);

		JRadioButton AddNew = new JRadioButton("Add a Show");
		AddNew.setBackground(new Color(128, 128, 128));
		buttonGroup.add(AddNew);
		AddNew.setBounds(10, 305, 111, 23);
		controlPanel.add(AddNew);

		JRadioButton Recommend = new JRadioButton("Recommend");
		Recommend.setBackground(new Color(128, 128, 128));
		buttonGroup.add(Recommend);
		Recommend.setBounds(10, 268, 111, 23);
		controlPanel.add(Recommend);

		TextField Added = new TextField();
		Added.setEditable(false);
		Added.setBackground(new Color(128, 128, 128));
		Added.setBounds(10, 390, 274, 32);
		controlPanel.add(Added);

		
		JButton Submit = new JButton("Submit");
		Submit.setBackground(new Color(128, 128, 128));
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AddNew.isSelected()) {
				    ShowWeek toadd = new ShowWeek();
				    JFrame frame = new JFrame();
				    Object week = JOptionPane.showInputDialog(frame, "Enter Week to add to data.");
				    Object category = JOptionPane.showInputDialog(frame, "Enter Category to add to data.");
				    Object weeklyrank = JOptionPane.showInputDialog(frame, "Enter Weekly rank to add data.");
				    Object title = JOptionPane.showInputDialog(frame, "Enter title to data to add.");
				    Object season = JOptionPane.showInputDialog(frame, "Enter season to data to add.");
				    Object hour = JOptionPane.showInputDialog(frame, "Enter hours to add to data.");
				    Object weekstotal = JOptionPane.showInputDialog(frame, "Enter Weekstotal to add to data.");
				    Object rights = JOptionPane.showInputDialog(frame, "Does netflix have the rights yes or no.");

				    String a = week.toString();
				    toadd.setWeek(a); 
				    a = category.toString();
				    toadd.setCategory(a);
				   a = weeklyrank.toString();
				   toadd.setWeeklyrank(a);
				   a = title.toString();
				   toadd.setTitle(a);
				   a = season.toString();
				   toadd.setSeason(a);
				   a = hour.toString();
				   toadd.setHours(a);
				   a = weekstotal.toString();
				   toadd.setWeekstotal(a);
				   a = rights.toString();
				   if(a.equals("yes")) {
					   toadd.setrights(true);
				   }else {
					   toadd.setrights(false);
				   } 
				   Added.setText(toadd.toString2());
				    allData.addShowWeek(toadd);
				    
				} if(Recommend.isSelected()) {
					String str = weekshows.getSelectedItem().toString();
					String[] cut = str.split("[,]",0);
					ArrayList<ShowWeek> recom = new ArrayList<ShowWeek>();
					recom = allData.recommend(cut[3], cut[0]);
					ShowWeek reco = recom.get(0);
					ShowWeek recomen = recom.get(1);
					String recommenda = "A Random Recommendation:"+ "\n" + reco.toString2() + "\n\n"+ "Check out something from the same week: " + "\n" +recomen.toString2();
				
					infoBox(recommenda,"Recommendations");
					//Added.setText(cut[0] + cut[3]);
				}
			}
		});
		
		Submit.setBounds(141, 281, 83, 23);
		controlPanel.add(Submit);
		
		JButton Save = new JButton("Save");
		Save.setBackground(new Color(128, 128, 128));
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allData.writeFile("./netflix/netflixdata.txt");
			}
		});
		Save.setBounds(290, 390, 89, 23);
		controlPanel.add(Save);
		
		JLabel lblNewLabel_1 = new JLabel("Shows to be added");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 375, 123, 14);
		controlPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Shows in a week");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 199, 111, 14);
		controlPanel.add(lblNewLabel_2);
		
		JLabel Party1 = new JLabel("");
		Party1.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Image1.gif")));
		Party1.setBounds(0, 124, 301, 365);
		Party1.setVisible(false);
		controlPanel.add(Party1);
		
		JLabel Party2 = new JLabel("New label");
		Party2.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/giphy.gif")));
		Party2.setBounds(333, 211, 267, 232);
		Party2.setVisible(false);
		controlPanel.add(Party2);
		
		JLabel Netflixtop10 = new JLabel("New label");
		Netflixtop10.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Netflixtop10.PNG")));
		Netflixtop10.setBounds(0, 0, 192, 46);
		controlPanel.add(Netflixtop10);
		
		JLabel Party3 = new JLabel("New label");
		Party3.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Image3.gif")));
		Party3.setBounds(83, 0, 380, 218);
		Party3.setVisible(false);
		controlPanel.add(Party3);
		
		JLabel Party4 = new JLabel("New label");
		Party4.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Image4.gif")));
		Party4.setBounds(119, 110, 354, 365);
		Party4.setVisible(false);
		controlPanel.add(Party4);

		JButton Party = new JButton("Party?");
		Party.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Party1.isVisible()){
					Party1.setVisible(false);
					Party2.setVisible(false);
					Party3.setVisible(false);
					Party4.setVisible(false);
					Netflixtop10.setVisible(true);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_2.setVisible(true);
					Save.setVisible(true);
					Submit.setVisible(true);
					Added.setVisible(true);
					Recommend.setVisible(true);
					AddNew.setVisible(true);
					textArea.setVisible(true);
					weekshows.setVisible(true);
					getshows.setVisible(true);
					weekdates.setVisible(true);
					lblNewLabel.setVisible(true);
					scrollPane.setVisible(true);
				}else {
					Party1.setVisible(true);
					Party2.setVisible(true);
					Party3.setVisible(true);
					Party4.setVisible(true);
					lblNewLabel.setVisible(false);
					Netflixtop10.setVisible(false);
					lblNewLabel_1.setVisible(false);
					lblNewLabel_2.setVisible(false);
					Save.setVisible(false);
					Submit.setVisible(false);
					Added.setVisible(false);
					Recommend.setVisible(false);
					AddNew.setVisible(false);
					textArea.setVisible(false);
					weekshows.setVisible(false);
					getshows.setVisible(false);
					weekdates.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
		});
		Party.setBackground(new Color(255, 0, 255));
		Party.setBounds(470, 454, 89, 23);
		controlPanel.add(Party);
		
		

	}
	   public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	  
}


