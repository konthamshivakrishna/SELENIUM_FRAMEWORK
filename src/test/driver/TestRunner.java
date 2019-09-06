package test.driver;

import test.resources.generic.Dlib;
import test.resources.generic.GlobalVariables;

public class TestRunner 
{
	public static void main(String []args) 
	{
		Dlib.onExecutionStart();
		String SelectedTestCases = Dlib.getTestCases();
 	    for (String TestCase: SelectedTestCases.split(";;"))
	    {
	    	 if(TestCase.length()==0) continue;
	    	 GlobalVariables.CurrentTestCase = TestCase;
	    	 Dlib.onTestCaseStart();
	    	 Dlib.execute(GlobalVariables.ClassPath);
	    	 Dlib.onTestCaseFinish();
	    }
 	   Dlib.onExecutionFinish();
	}
}
