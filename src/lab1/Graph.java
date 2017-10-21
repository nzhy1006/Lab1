package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;

public class Graph {
	int wordsid = 0; 
	Vector<HashMap<Integer, Integer>> vec = new Vector<HashMap<Integer, Integer>>();  //heads 
	HashMap<String, Integer> words = new HashMap<String, Integer>();  // trans words to int 
	Vector<String> wordlist = new Vector<String>(); 
	boolean[] mark;
	String line(String str) { 
		str = str.toLowerCase(); 
		String ss = "";
		boolean flag = false; 
		for(int i = 0; i < str.length(); i++) { 
			if((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) { 
				ss = ss + str.charAt(i); 
				flag = true; 
			} else { 
				if(flag == true) {
					ss = ss + ' '; 
					flag = false; 
				} 
			} 
		} 
		return ss;
	} 
	void readFile(String filePath) {
		wordsid = 0; 
		vec = new Vector<HashMap<Integer, Integer>>();  //heads 
		words = new HashMap<String, Integer>();  // trans words to int 
		wordlist = new Vector<String>();
		Vector<HashMap<Integer, Boolean>> mark = new Vector<HashMap<Integer, Boolean>>();
		try { 
            String encoding="GBK"; 
            File file=new File(filePath); 
            if(file.isFile() && file.exists()){ //判断文件是否存在 
                InputStreamReader read = new InputStreamReader( 
                new FileInputStream(file),encoding);//考虑到编码格式 
                BufferedReader bufferedReader = new BufferedReader(read); 
                String lineTxt = null; 
                String end = null;
                while((lineTxt = bufferedReader.readLine()) != null){ 
                	String str = line(lineTxt); 
                	if(str != ""){
                		String[] spl = str.split(" ");
                		if(end != null) {
                			addE(end, spl[0]);
                		}
                		for(int i = 0; i < spl.length - 1; i++) {
                            addE(spl[i], spl[i + 1]);
                		}
                		end = spl[spl.length - 1];
                	}
                } 
                read.close(); 
            } else { 
                System.out.println("找不到指定的文件"); 
            } 
        } catch (Exception e) { 
            System.out.println("读取文件内容出错"); 
            e.printStackTrace(); 
        } 
	}
	void addN(String x) {
		if (words.containsKey(x) == false) {
			words.put(x, wordsid);
			wordlist.add(x);
			HashMap<Integer, Integer> tpm = new HashMap<Integer, Integer>();
			vec.addElement(tpm);
			wordsid = wordsid + 1;
		}
	}
	void addE(String u, String v) {
		addN(u);
		addN(v);
		int uid = words.get(u);
		int vid = words.get(v);
		int Evalue = 1;
		if (vec.elementAt(uid).containsKey(vid) == true) {
			Evalue = (vec.elementAt(uid).get(vid))+ 1;
		} 
		vec.elementAt(uid).put(vid, Evalue);
	}
	HashMap<Integer, Integer> showEs(String x) {
		if (words.containsKey(x) == false) {
			System.out.println(x + "dose not exist!");
			return new HashMap<Integer, Integer>();
		}
		int xid = words.get(x);
		HashMap<Integer, Integer> tpm = vec.elementAt(xid);	
		return tpm;
	}
} 


//change something no.1
//change something no.1
//b2
//11