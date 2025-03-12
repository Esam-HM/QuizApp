package proje_Esam_Halimeh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Data {
	private static File file = new File("20011908_data.ser");
	
	
	public Data() {
	}
	
	public static void saveData(Admin admin) {       //program sonunda bütün bilgileri kaydetme
		try {
			if(file.exists()) {
				file.delete();
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream ios = new ObjectOutputStream(fos);
			ios.writeObject(admin);
			ios.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Admin loadData() {         // program başlarken dosyada data varsa okur.
		if(file.length()==0) {
			return null;
		}else {
			Admin admin = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream reader = new ObjectInputStream(fis);
				admin = (Admin) reader.readObject();
				reader.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return admin;
		}
	}
	
	
	
}