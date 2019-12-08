import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class Main{
	public static BufferedReader reader;
	public static FileWriter positiveWriter;
	public static FileWriter negativeWriter;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new FileReader("training set.csv"));
		positiveWriter = new FileWriter("positive sentiment.csv");
		negativeWriter = new FileWriter("negative sentiment.csv");
		
		reader.readLine();
		while(true) {
			String nextLine = reader.readLine();
			if(nextLine==null) {break;}
			//System.out.print(nextLine);
			String secondElement = nextLine.split(",")[1];
			if(secondElement.equalsIgnoreCase("1")) {
				positiveWriter.write(nextLine+'\n');
				positiveWriter.flush();
			}
			else if(secondElement.equalsIgnoreCase("0")) {
				negativeWriter.write(nextLine+'\n');
				negativeWriter.flush();
			}
			else {
				String firstElement = nextLine.split(",")[0];
				System.out.println("Error found. At Line: "+firstElement);
			}
		}
	}
}
