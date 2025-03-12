package proje_Esam_Halimeh;

import java.io.Serializable;
import java.util.ArrayList;

public class ExerciseResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private double testSkor,hizSkor;
	private String startingTime;
	private String testDuration;             // test süresi
	private ArrayList<Question> correctAns;   // doğru yapılan sorular
	private ArrayList<Question> wrongAns;     // yanlış yapılan sorular
	private ArrayList<Integer> qAns;           // çocuğun soru cevapları
	private ArrayList<Integer> qDuration;      // her sorunun süresi kaç sn
	
	public ExerciseResult() {
		correctAns = new ArrayList<>();
		wrongAns = new ArrayList<>();
		qAns = new ArrayList<>();
		qDuration = new ArrayList<>();
	}

	public double getTestSkor() {
		return testSkor;
	}

	public void setTestSkor(double testSkor) {
		this.testSkor = testSkor;
	}

	public double getHizSkor() {
		return hizSkor;
	}

	public void setHizSkor(double hizSkor) {
		this.hizSkor = hizSkor;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(String testDuration) {
		this.testDuration = testDuration;
	}

	public ArrayList<Question> getCorrectAns() {
		return correctAns;
	}

	public ArrayList<Question> getWrongAns() {
		return wrongAns;
	}

	public ArrayList<Integer> getqAns() {
		return qAns;
	}

	public ArrayList<Integer> getqDuration() {
		return qDuration;
	}
	
	public void setQuestionDuration(int time) {
		qDuration.add(time);
	}
	
	public void setWrongAnswer(Question q) {
		wrongAns.add(q);
	}
	
	public void setCorrectAnswer(Question q) {
		correctAns.add(q);
	}
	
	public void setQuestionAnswer(int ans) {
		qAns.add(ans);
	}
	
	public int sumOfDurations() {
		int i;
		int sum = 0;
		for(i=0;i <qDuration.size();i++) {
			sum += qDuration.get(i);
		}
		return sum;
	}
	
	public String printCorrectAns() {
		String str ="";
		int i;
		for(i=0;i<correctAns.size();i++) {
			str = str + correctAns.get(i) + ";";
		}
		return str;
	}
	
	public String printWrongAns() {
		String str ="";
		int i;
		for(i=0;i<wrongAns.size();i++) {
			str = str + wrongAns.get(i) + ";";
		}
		return str;
	}
	
	public String printQuestionDurations() {
		String str ="";
		int i;
		for(i=0;i<qDuration.size();i++) {
			str = str + qDuration.get(i) + ";";
		}
		return str;
	}
	
	public String printAnswers() {
		String str ="";
		int i;
		for(i=0;i<qAns.size();i++) {
			str = str + qAns.get(i) + ";";
		}
		return str;
	}

	@Override
	public String toString() {
		return "Alıştırma sonucu:\n > test skor= " + testSkor + "\n > hiz skor= " + hizSkor + "\n > test Suresi= " + testDuration;
	}
}
