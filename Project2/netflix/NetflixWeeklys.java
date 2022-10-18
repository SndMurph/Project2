package netflix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;





public class NetflixWeeklys {

	private ArrayList<ShowWeek> showweeks;
	
	private String fileName;
	private Random r = new Random();
	public NetflixWeeklys() {
		// default constructor
		showweeks = new ArrayList<ShowWeek>();
		fileName = null;
		//name = "not set";
	}
	
	public NetflixWeeklys( String fn) {
		// constructor with given course name and input file
		this();
		fileName = fn;
		readFile();
		//name = n;
		//readFile();
	}	
	//adds a show
	public void addShowWeek(ShowWeek s) {
		showweeks.add(s);
	}
	//makes arraylist a string
	public String toString() {
		String toReturn = "";
		for (ShowWeek a : showweeks) {
			toReturn = toReturn+a.toString()+"\n";
		}
		System.out.println(toReturn);
		return toReturn;
	}
	public String toString2() {
		String toReturn = "";
		for (ShowWeek a : showweeks) {
			toReturn = toReturn+a.toString2()+"\n";
		}
		System.out.println(toReturn);
		return toReturn;
	}
	
	//purges the input shows
	public void purge(String p){
		for (ShowWeek showWeek : showweeks) {

			if(p.equals(showWeek.getTitle())){
				showWeek.setrights(false);
			}
		}
	}

	//unpurges the input shows
	public void unpurge(String u){
		for (ShowWeek showWeek : showweeks) {

			if(u.equals(showWeek.getTitle())){
				showWeek.setrights(true);
			}
		}
	}
	
	public Object [] getWeeks(){
		 String[] dates = new String[showweeks.size()/10];
		int x = 0;
		int y = 0;
		while(showweeks.size()>x) {
			dates[y]=showweeks.get(x).getWeek();
			x+=40;
			y++;
		}
		
		System.out.println(dates);
		return dates;
	}
	//recommends a random show
	public ShowWeek randomRec() {
		
		int x = r.nextInt(showweeks.size());
		while(showweeks.get(x).getrights()==false) {
			x = r.nextInt(showweeks.size());
		}
		return showweeks.get(x);
	}

	//returns array list of the given weeks top 10;
	public ArrayList<ShowWeek> getShows(String date) {
		ArrayList<ShowWeek> dateshows = new ArrayList<ShowWeek>();
		int ind = 0;
		boolean t = false;
		//String 
		while(t==false){
			if(showweeks.get(ind).getWeek().equals(date))
				t=true;
			ind++;
		}
		ind--;
		for(int i = 0;i<40;i++) {
			dateshows.add(showweeks.get(ind));
			ind++;
		}
		return dateshows;
	}
	
	//Recommends a random show from the same week.
	public ShowWeek randomdateRec(String date) {
		Random r = new Random();
		int x = r.nextInt(10);
		ArrayList<ShowWeek> daterec = new ArrayList<ShowWeek>();
		daterec = getShows(date);
		ShowWeek rec = daterec.get(x);
		while(rec.getrights()==false){
			x = r.nextInt(10);
			rec = daterec.get(x);
		}
		
		return rec;
	}
	
	
	
	public ArrayList<ShowWeek> recommend(String title, String date) {
		ArrayList<ShowWeek> rec = new ArrayList<ShowWeek>();
		ShowWeek t = randomRec();
		while(t.getTitle().equals(title)){
			t = randomRec();
		}
		rec.add(t);
		t = randomdateRec(date);
		while(t.getTitle().equals(title)) {
			t = randomdateRec(date);
		}
		rec.add(t);
		return rec;
	}



	//read and write from files
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while (( line = lineReader.readLine())!=null) {
				String week= line;
				String category= lineReader.readLine();
				String weeklyrank= lineReader.readLine();
				String title= lineReader.readLine();
				String season= lineReader.readLine();
				String hours= lineReader.readLine();
				String weekstotal= lineReader.readLine();

				addShowWeek(new ShowWeek(week,category,weeklyrank,title,season,hours,weekstotal));

			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String week = line;
					String category= lineReader.readLine();
					String weeklyrank= lineReader.readLine();
					String title= lineReader.readLine();
					String season= lineReader.readLine();
					String hours= lineReader.readLine();
					String weekstotal= lineReader.readLine();
					addShowWeek(new ShowWeek(week,category,weeklyrank,title,season,hours,weekstotal));

				}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	} // end of readFile method	


	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method

	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method

	private void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
		try
		{

			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			

			for (int i = 0; i < showweeks.size(); i++) {
				ShowWeek t = showweeks.get(i);
				myOutfile.write (t.getWeek()+"\n");
				myOutfile.write (t.getCategory()+"\n");
				myOutfile.write (t.getWeeklyrank()+"\n");
				myOutfile.write (t.getTitle()+"\n");
				myOutfile.write (t.getSeason()+"\n");
				myOutfile.write (t.getHours()+"\n");
				myOutfile.write (t.getWeekstotal()+"\n");
				myOutfile.write (t.getrights()+"\n");
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}
	




}





