import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Main{
	static BufferedReader positiveReader;
	static BufferedReader negativeReader;
	static FileWriter positiveFileWriter;
	static FileWriter negativeFileWriter;
	static HashMap<String, Integer> wordCountMap;
	
	static int POSITIVE_SENTIMENT = 0;
	static int NEGATIVE_SENTIMENT = 1;
	
	public static void main(String[] args) throws IOException {
		positiveReader = new BufferedReader(new FileReader("positive sentiment.csv"));
		negativeReader = new BufferedReader(new FileReader("negative sentiment.csv"));
		positiveFileWriter = new FileWriter("positive probability.csv");
		negativeFileWriter = new FileWriter("negative probability.csv");
		
		wordCountMap = new HashMap<String, Integer>();
		
		while(true) {
			String nextLine = positiveReader.readLine();
			if(nextLine==null) {break;}
			nextLine = wordFilter(nextLine);
			String[] wordsList = wordSplit(nextLine);
			for(String element: wordsList) {
				Object o = wordCountMap.get(element);
				if(o==null) {
					wordCountMap.put(element, 1);
				} else {
					int i = (int)o + 1;
					wordCountMap.put(element, i);
				}
			}
		}
		ArrayList<HashMap.Entry<String, Integer>> wordCountList = sortHashMap(wordCountMap);
		printProbility(wordCountList, POSITIVE_SENTIMENT);
		
		wordCountMap.clear();
		while(true) {
			String nextLine = negativeReader.readLine();
			if(nextLine==null) {break;}
			nextLine = wordFilter(nextLine);
			String[] wordsList = wordSplit(nextLine);
			for(String element: wordsList) {
				Object o = wordCountMap.get(element);
				if(o==null) {
					wordCountMap.put(element, 1);
				} else {
					int i = (int)o + 1;
					wordCountMap.put(element, i);
				}
			}
		}
		wordCountList = sortHashMap(wordCountMap);
		printProbility(wordCountList, NEGATIVE_SENTIMENT);
		
		Tester tester = new Tester();
		tester.test();
	}
	
	static String wordFilter(String str) {
		String step1 = str.split(",")[3];
		String step2 = step1.toLowerCase();
		String step3 = step2.replaceAll("[^a-zA-Z\\s]", " ");
		String step4 = step3.replaceAll("\\s{2,}", " ");
		if(step4.startsWith(" ")) {
			step4 = step4.substring(1);
		}
		return step4;
	}
	
	static String[] wordSplit(String str) {
		String[] strs = str.split("\\s");
		return strs;
	}
	
	static ArrayList sortHashMap(HashMap<String, Integer> hashmap) {
		ArrayList<HashMap.Entry<String, Integer>> list = new ArrayList
				<HashMap.Entry<String, Integer>>(hashmap.entrySet());  
		  
        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {  
            public int compare(HashMap.Entry<String, Integer> o1,  
                    HashMap.Entry<String, Integer> o2) {   
                return (o1.getKey()).toString().compareTo(o2.getKey().toString());  
            }  
        });
        return list;
	}
	
	static void printProbility(ArrayList<HashMap.Entry<String, Integer>> list, int mode) 
			throws IOException{
		
		int totalNumber = 0;
		for(HashMap.Entry<String, Integer> anEntry: list) {
			totalNumber += anEntry.getValue();
		}
		
		if(mode == POSITIVE_SENTIMENT) {
			positiveFileWriter.write("TotalNum,"+totalNumber+'\n');
		}
		else if (mode == NEGATIVE_SENTIMENT) {
			negativeFileWriter.write("TotalNum,"+totalNumber+'\n');
		}
		
		for(int i=0;i < list.size();i++) {
			HashMap.Entry<String, Integer> anEntry = list.get(i);
			String key = anEntry.getKey();
			int value = anEntry.getValue();
			
			if(mode == POSITIVE_SENTIMENT) {
				positiveFileWriter.write(key+','+value+'\n');
			}
			else if (mode == NEGATIVE_SENTIMENT) {
				negativeFileWriter.write(key+','+value+'\n');
			}
		}
		
		if(mode == POSITIVE_SENTIMENT) {
			positiveFileWriter.close();
		}
		else if (mode == NEGATIVE_SENTIMENT) {
			negativeFileWriter.close();
		}
	}
}
