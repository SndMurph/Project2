package netflix;
//Ryan Murphey
//CSCI 3381 OO with Java Project 1
//
//
//
public class Main {

	public static void main(String[] args) {
		 
		ShowWeek w1 = new ShowWeek();
		System.out.println(w1);
		ShowWeek w2 = new ShowWeek("9-25-2022","Films (English)","1","Greatest show","season 69","420000","9001");
		System.out.println(w2);

		NetflixWeeklys test = new NetflixWeeklys("./netflix/netflixdata.txt");
		test.getWeeks();
		//NetflixWeeklys test = new NetflixWeeklys();
		test.addShowWeek(w2);
		test.addShowWeek(w1);
		test.purge("Greatest show");
		test.unpurge("Greatest show");
		System.out.println(w2.toString());
		
	//	test.toString();
	
		//System.out.println(test.randomRec());
		System.out.println( test.recommend("HIT: The First Case","2022-09-04"));
		//test.getShows("2021-07-04");
		

		

		w1.setWeek("9-25-2022");
		w1.setCategory("Films (English)");
		w1.setWeeklyrank("1");
		w1.setTitle("Greatest show");
		w1.setSeason("season 69");
		w1.setHours("4200000");
		w1.setWeekstotal("9001");
		w1.setrights(false);
		System.out.println(w1.equals(w2));
		System.out.println(w1.getWeek());
		System.out.println(w1.getCategory());
		System.out.println(w1.getWeeklyrank());
		System.out.println(w1.getTitle());
		System.out.println(w1.getSeason());
		System.out.println(w1.getHours());
		System.out.println(w1.getWeekstotal());
		System.out.println(w1.getrights());
		System.out.println(w1.equals(w2));
		
		
		
		//test.addShowWeek(w1);
		//test.addShowWeek(w2);
		//System.out.println(test);
		
		test.writeFile("./netflix/dataWriteTest.txt");
		//System.out.println(test);
		
	}

}
