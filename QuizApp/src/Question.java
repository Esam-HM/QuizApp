package proje_Esam_Halimeh;

import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable{
	private static final long serialVersionUID = 1L;
	private int QuesNum;
	private int aNum,bNum,result;
	
	public Question() {
		super();
	}

	public int getQuesNum() {
		return QuesNum;
	}

	public void setQuesNum(int quesNum) {
		QuesNum = quesNum;
	}

	public int getaNum() {
		return aNum;
	}

	public void setaNum(int aNum) {
		this.aNum = aNum;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public  void createQuestion(int min,int max) {        // soruyu rastgele sayılar ile oluşturma
		Random rand = new Random();
		setaNum(rand.nextInt(min, max+1));
		setbNum(rand.nextInt(min, max+1));
		setResult(getbNum()*getaNum());
	}
	
	@Override
	public String toString() {
		return  QuesNum + " ) "  + aNum + " * " + bNum + " = ";
	}
	
}