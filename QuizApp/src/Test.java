package proje_Esam_Halimeh;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test extends TestCase {

	private Admin admin;
	private Child child;
	private Exercise exercise;
	private Question question;
	private ExerciseResult result;
	
	protected void setUp() {
		admin = new Admin("Esam");
		child = new Child("Ahmet",admin);
		exercise = new Exercise("S1",6,1,8);
		question = new Question();
		result = new ExerciseResult();
	}

	public void testAddChild() {        // çocuğa ekleme metodu test etme. eğer doğru çalışırsa çocuğun ismi döndürür.
		admin.addChild(child);
		Child ch = admin.getChild("Ahmet");
		String sonuc = ch.getName();
		assertEquals(sonuc,"Ahmet");
	}
	
	public void testIinlQuestions() {     // alıştırmanın soru listesi initialize etme. eğer oluşmuşsa null değil demek
		exercise.initQsForExercise();
		ArrayList<Question> sonuc = exercise.getQuestions();
		assertNotNull(sonuc);
	}
	
	
	public void testAddExercise() {       // admin alıştırma tanımladıktan sonra alıştırma listesine ekleme
		String a,b,c;
		int size;
		a = Integer.toString(exercise.getN());
		b = Integer.toString(exercise.getaMin());
		c = Integer.toString(exercise.getbMax());
		admin.initExercise(exercise.getExerciseID(),a,b,c);
		size = admin.getExercises().size()-1;
		assertEquals(admin.getExercises().get(size).getExerciseID(),"S1");
		assertEquals(admin.getExercises().get(size).getN(),6);
		assertEquals(admin.getExercises().get(size).getaMin(),1);
		assertEquals(admin.getExercises().get(size).getbMax(),8);
	}
	
	public void testAddExerciseToChild() {          // bir çocuk seçtiği alıştırmayı kendi alıştırma listesine ekleme
		int index;
		child.addExerciseToChild(exercise);
		index = child.getPractices().size()-1;
		String sonuc = child.getPractices().get(index).getExerciseID();
		assertEquals(sonuc,"S1");
	}

}
