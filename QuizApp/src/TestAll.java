package proje_Esam_Halimeh;

import junit.framework.TestSuite;

public class TestAll {
	public static TestSuite suite() {
		TestSuite suite = new TestSuite("Butun Textler");
		suite.addTestSuite(Test.class);
		return suite;
	}
}
