import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.lang.model.element.Element;
import javax.swing.text.html.HTML.Tag;

public class Tester {
	private BufferedReader testSetReader;
	private BufferedReader positiveSentiProbReader;
	private BufferedReader negativeSentiProbReader;
	private HashMap<String, Integer> positiveSentiMap;
	private HashMap<String, Integer> negativeSentiMap;
	
	private int timesOfSuccess;
	private int timesOfFailure;
	
	public Tester() throws IOException{
		testSetReader = new BufferedReader(new FileReader("testing set.csv"));
		positiveSentiProbReader = new BufferedReader(new FileReader("positive probability.csv"));
		negativeSentiProbReader = new BufferedReader(new FileReader("negative probability.csv"));
		positiveSentiMap = new HashMap<String, Integer>();
		negativeSentiMap = new HashMap<String, Integer>();
		
		negativeSentiProbReader.readLine();
		positiveSentiProbReader.readLine();
		
		timesOfSuccess = 0;
		timesOfFailure = 0;
		initHashMap();
	}
	
	public void test() throws IOException{
		
		while(true) {
			String nextLine = testSetReader.readLine();
			if(nextLine==null||nextLine=="") {break;}
			
			String tag = nextLine.split(",")[1];
			nextLine = Trainer.wordFilter(nextLine);
			String[] wordsList = Trainer.wordSplit(nextLine);
			compareLikelihood(1, 1, wordsList, Integer.parseInt(tag));
		}
		
		double rate = (double)timesOfSuccess / (timesOfFailure + timesOfSuccess);
		System.out.println("success rate: "+rate);
	}
	
	private void compareLikelihood(double posiLikelihood, double negaLikelihood, 
			String[] wordsList, int tag) {
		
		for(String word: wordsList) {
			Object positiveCount = positiveSentiMap.get(word);
			if(positiveCount==null) {
				positiveCount = 1;
			}
			Object negativeCount = negativeSentiMap.get(word);
			if(negativeCount==null) {
				negativeCount = 1;
			}
			posiLikelihood *= (int)positiveCount;
			negaLikelihood *= (int)negativeCount;
			
			if(posiLikelihood>10000 || negaLikelihood>10000) {
				posiLikelihood /= 10000;
				negaLikelihood /= 10000;
			}
		}
		
		if(posiLikelihood>=negaLikelihood) {
			if(tag==1) {
				timesOfSuccess += 1;
			}else {
				timesOfFailure += 1;
			}
		}else {
			if(tag==0) {
				timesOfSuccess += 1;
			}else {
				timesOfFailure += 1;
			}
		}
		//System.out.println(posiLikelihood+":"+negaLikelihood);
	}
	
	private void initHashMap() throws IOException{
		while(true) {
			String nextLine = positiveSentiProbReader.readLine();
			if(nextLine == null) {break;}
			String[] strs = nextLine.split(",");
			positiveSentiMap.put(strs[0], Integer.parseInt(strs[1]));
		}
		while(true) {
			String nextLine = negativeSentiProbReader.readLine();
			if(nextLine == null) {break;}
			String[] strs = nextLine.split(",");
			negativeSentiMap.put(strs[0], Integer.parseInt(strs[1]));
		}
	}
}
