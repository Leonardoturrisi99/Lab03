package it.polito.tdp.spellchecker.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	List<String>inglese=new ArrayList<String>();
	//List<String>italiano=new ArrayList<String>();
	List<RichWord>scorretto=new ArrayList<RichWord>();
	Map<String,RichWord>parole=new TreeMap<>();
	
	public void loadDictionary(String language) {
		String nomeFile=language;
		try{
			FileReader fr= new FileReader(nomeFile);
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					String array[]=word.split("\n");
					for(String s:array) {
						RichWord rw=new RichWord(s);
						parole.put(s,rw);
					}
				}br.close();
		}catch(IOException e) {
			System.out.println("Errore nella lettura del file");
		}
		}

	public List<RichWord>spellCheckText(String s){
		RichWord parole;
		s=s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'?()\\[\\]\"]","");
		String array[]=s.toLowerCase().split(" ");
		for(String ss:array) {
			if(!this.parole.containsKey(ss)) {
				parole=new RichWord(ss);
				parole.setCorretta(false);
				scorretto.add(parole);
			}
	}
		return scorretto;
	}
	
	
}

