package netflix;

public class ShowWeek {
	private String week;
	private String category;
	private String weeklyrank;
	private String title;
	private String season;
	private String hours;
	private String weekstotal;
	private boolean rights;
	public ShowWeek() {
		week = "not set";
		category = "not set";
		weeklyrank = "not set";
		title = "not set";
		season = "not set";
		hours = "not set";
		weekstotal = "not set";
		rights = true;
	}
	public ShowWeek(String a, String b, String c, String d, String e, String f, String g ){
		week = a;
		category = b;
		weeklyrank = c;
		title = d;
		season = e;
		hours = f;
		weekstotal = g;
		rights = true;
	}
	
	//Getters and Setters for the 7 private data
	public boolean getrights() {
		return rights;
	}
	public void setrights(boolean b) {
		this.rights = b;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWeeklyrank() {
		return weeklyrank;
	}
	public void setWeeklyrank(String weeklyrank) {
		this.weeklyrank = weeklyrank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getWeekstotal() {
		return weekstotal;
	}
	public void setWeekstotal(String weekstotal) {
		this.weekstotal = weekstotal;
	}
	
	//String representation of the show
	public String toString( ) {
		return week +"\n"+category+"\n"+weeklyrank+"\n"+title+"\n"+season+"\n"+hours+"\n"+weekstotal+"\n"+rights+"\n";
	}
	public String toString2( ) {
		return week+", "+category+ ", "+weeklyrank+", "+title+", "+season+", "+hours+", "+weekstotal+", "+rights;
	}  
	public boolean equals(Object o) {
		ShowWeek s = (ShowWeek)o;

		return (week + title).equals(s.getWeek()+s.getTitle());
	}
	
	
	
}
