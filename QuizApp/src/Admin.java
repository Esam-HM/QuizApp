package proje_Esam_Halimeh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private LinkedList<Child> children;
	private ArrayList<Exercise> exercises;
	
	public Admin(String name) {
		this.name=name;
		children=new LinkedList<>();
		exercises = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public LinkedList<Child> getChildren() {
		return children;
	}

	public ArrayList<Exercise> getExercises() {
		return exercises;
	}
	
	public void addChild(Child ch) {
		children.add(ch);
	}
	
	public Child getChild(String name) {
		for(Child child : children) {
			if(child.getName().compareTo(name)==0) {
				return child;
			}
		}
		return null;
	}
	
	public Exercise getExercise(int index) {
		return exercises.get(index);
	}
	
	public Exercise getExercise(String ID) {
		for(Exercise ex : exercises) {
			if(ex.getExerciseID()==ID) {
				return ex;
			}
		}
		return null;
	}
	
	public void initExercise(String id,String nStr,String aStr,String bStr) {      // alıştırmayı tanımlama
		int a = Integer.parseInt(nStr);
		int b = Integer.parseInt(aStr);
		int c = Integer.parseInt(bStr);
		Exercise ex = new Exercise(id,a,b,c);
		ex.initQsForExercise();
		exercises.add(ex);
	}
	
	public boolean adminLogin(String isim) {            // admin hesaba girişi
		
		if(isim.compareTo(getName())!=0 || isim.compareTo("")==0) {
			return false;
		}
		return true;
	}
	
	public boolean childLogin(String isim) {
		for(Child ch : children) {
			if(isim.compareTo(ch.getName())==0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean writeToExcel() {          // excel dosyasına yazma , rapor oluşturma
		try {
			int i;
			File file = new File("20011908_Rapor.csv");
			if(file.exists()) {
				file.delete();
			}
			FileWriter fw = new FileWriter("20011908_Rapor.csv");
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write("Child name,Exercise ID,Start Time,Duration(sn),Questions,Questions Duration(sn),Corrects,Wrongs,Answers,test Skor,hiz skor");
			writer.newLine();
			for(Child ch : getChildren()) {
				if(ch.getPractices().size()!=0) {
					for(i=0;i<ch.getPractices().size();i++) {
						writer.write(ch.toString(i));
						writer.newLine();
					}
				}
			}
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
