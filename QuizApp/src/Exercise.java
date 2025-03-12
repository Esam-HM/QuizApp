package proje_Esam_Halimeh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Exercise implements Serializable{
	private static final long serialVersionUID = 1L;
	private String exerciseID;
	private int N,aMin,bMax;
	private ArrayList<Question> questions;
	private LinkedList<SkorTablo> skorlar;   // skor tablosunda gösterilecek bilgiler
	
	public Exercise(String exerciseID,int N,int aMin,int bMax) {
		this.exerciseID = exerciseID;
		this.N = N;
		this.aMin = aMin;
		this.bMax = bMax;
		questions = new ArrayList<>();
		skorlar = new LinkedList<>();
	}

	public String getExerciseID() {
		return exerciseID;
	}

	public int getN() {
		return N;
	}

	public int getaMin() {
		return aMin;
	}

	public int getbMax() {
		return bMax;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public LinkedList<SkorTablo> getSkorlar() {
		return skorlar;
	}

	public void initQsForExercise() {        // soruları random olarak initialize etme
		int i;
		for(i=0;i<getN();i++) {
			Question q = new Question();
			q.createQuestion(getaMin(),getbMax());
			q.setQuesNum(i+1);
			questions.add(q);
		}
	}
	
	public String printQuestions() {
		int i;
		String str = "";
		for(i=0;i<questions.size();i++) {
			str = str + questions.get(i) + ";";
		}
		return str;
	}
	
	public void addSkorToTable(String name,double skor1,double skor2) {      // yüksek skor tablosuna skor ekleme
		SkorTablo skor = new SkorTablo(name,skor1,skor2);
		skorlar.add(skor);
	}
	
	public void viewTablo() {          // bu alıştırmanın yüksek skor tablosu çağırma
		SkorTablo tablo = new SkorTablo();
		tablo.tablo(this);
	}
}
