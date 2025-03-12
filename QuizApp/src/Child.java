package proje_Esam_Halimeh;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Child implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Exercise> practices;
	private LinkedList<ExerciseResult> listOfResults;
	private Admin admin;
	
	
	public Child(String name,Admin admin) {
		this.name=name;
		this.admin=admin;
		practices= new ArrayList<>();
		listOfResults = new LinkedList<>();
	}


	public String getName() {
		return name;
	}


	public ArrayList<Exercise> getPractices() {
		return practices;
	}


	public LinkedList<ExerciseResult> getListOfResults() {
		return listOfResults;
	}


	public Admin getAdmin() {
		return admin;
	}
	
	public Exercise addExerciseToChild(Exercise ex) {          // çocuğun yaptığı alıştırmalar listesine ekle
		String str;
		ExerciseResult result = new ExerciseResult();
		practices.add(ex);
		LocalDateTime ll = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
		str = dtf.format(ll);
		result.setStartingTime(str);
		listOfResults.add(result);
		return ex;
	}
	
	
	
	public void kaydetQ(Exercise exercise,int sec,int min,int indexOfQuestion,String answer){   // çocuk çözdüğü her sorunun ExerciseResult sınıfında bilgileri kaydetme
		int a = Integer.parseInt(answer);
		int i = practices.lastIndexOf(exercise);
		ExerciseResult result = listOfResults.get(i);
		Question q = exercise.getQuestions().get(indexOfQuestion);
		if(a == q.getResult()) {
			result.setCorrectAnswer(q);
		}else {
			result.setWrongAnswer(q);
		}
		result.setQuestionDuration((min*60 +sec)-(result.sumOfDurations()));
		result.setQuestionAnswer(a);
	}
	
	public void endOfExercise(Exercise ex , int min ,int sec) {      // alıştırma sonunda puan ve süre atama
		int i = practices.size()-1;
		ExerciseResult result = listOfResults.get(i);
		double  a = result.getCorrectAns().size();
		double b = ex.getQuestions().size();
		double c = min*60 + sec;
		String str = String.format("%02d:%02d",min,sec);
		result.setTestSkor((a/b)*100);
		result.setTestDuration(str);
		result.setHizSkor((b/c)*100);       // hiz skor soru sayisi bölü alıştırma süresi * 100 olarak yaptım. hiz skor yüksek olması çocuk hızlı yapmış demek
		ex.addSkorToTable(getName(),result.getTestSkor(),result.getHizSkor());
	}
	
	public String viewExerciseResult(int indexOfResult) {          // alıştırma sonunda çocuğa gösterilecek bilgiler
		int i;
		ExerciseResult result = listOfResults.get(indexOfResult);
		ArrayList<Question> q = practices.get(indexOfResult).getQuestions();
		String str = "isminiz: " + name + "\nAlıştırma ID: " + practices.get(indexOfResult).getExerciseID() + "\n";
		str = str + result.toString() + "\n>> Sorular:\n";
		for(i=0;i<result.getqAns().size();i++) {
			str = str + "\n >> Soru " + q.get(i) + result.getqAns().get(i);
			if(result.getqAns().get(i) == q.get(i).getResult()) {
				str = str  + " >>> Doğru yaptınız\n";
			}else {
				str = str + " >>> XX .Doğru cevap: " + q.get(i).getResult() + "\n";
			}
			 
		}
		return str;
	}
	
	public String toString(int indexOfExercise) {
		Exercise ex = practices.get(indexOfExercise);
		ExerciseResult result = listOfResults.get(indexOfExercise);
		String str ;
		str = getName() + "," +ex.getExerciseID()+ "," +result.getStartingTime()+ "," +result.getTestDuration()+ ",";
		str = str + ex.printQuestions()+ ","+ result.printQuestionDurations() + "," +result.printCorrectAns()+ ",";
		str = str + result.printWrongAns()+ "," + result.printAnswers() + "," + result.getTestSkor() + "," +result.getHizSkor();
		
		return str;
	}
}
