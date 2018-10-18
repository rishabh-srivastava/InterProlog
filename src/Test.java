import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.declarativa.interprolog.*;
import com.xsb.interprolog.*;

public class Test {
	public static void hello() {
		PrologEngine engine = new XSBSubprocessEngine("C:\\Program Files (x86)\\XSB");
		if(engine.deterministicGoal(
	 			"name('Hii',UL),append(\"Hello,\", UL, ML), name(Message,ML)",
	 			"[string(Message)]") != null)
		//if (engine.deterministicGoal("javaMessage('java.lang.System'-out,println(string('Hello from Prolog, Java world!') ))"))
			System.out.println("This goal succeeded");
		engine.shutdown();
	}
    public static void main(String[] args) {
    	int i=0;
    	 try {
    		 String encoding = "UTF-8";
    		 File textFile = new File("myFile.txt");
   	      	 OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(textFile), encoding);
    		 for(i=0x0028;i<=0xffff;i++)
    		 {
    		 char u = (char)i;
    		 String s=Character.toString(u);
    		 
    		 PrologEngine engine = new XSBSubprocessEngine("C:\\Program Files (x86)\\XSB");
    	 		engine.command("import append/3 from basics"); // Only for XSB Prolog
    	 		Object[] bindings = engine.deterministicGoal(
    	 			"name(User,UL),append(\"Hello,\", UL, ML), name(Message,ML)",
    	 			"[string(User)]",
    	 			new Object[]{s},
    	 			"[string(Message)]");
    	 		String message = (String)bindings[0];
    	 		int idx = message.indexOf(',');
    	 		String check = message.substring(idx+1);
    	 		System.out.println("\nMessage:"+message);
    	 		
    	 		// the above demonstrates object passing both ways; 
    	 		// since we may simply concatenate strings, an alternative coding would be:
    	 		/*bindings = engine.deterministicGoal(
    	 			"name('"+s+"',UL),append(\"Hello,\", UL, ML), name(Message,ML)",
    	 			"[string(Message)]");
    	 		// (notice the ' surrounding the user name, unnecessary in the first case)
    	 		System.out.println("Same:"+bindings[0]);*/
    	 		engine.shutdown();

    		 
    		 System.out.println( "\\u" + Integer.toHexString(u | 0x10000).substring(1) );  
	    	 writer.write(u);
	    	 writer.write("\t");
	    	 if(s.equals(check))
 	 		{
 	 			System.out.println("True");
 	 			writer.write("True");
 	 		}
 	 		else {
 	 			System.out.println("False");
 	 			writer.write("False");
 	 		}
	    	 writer.write('\n');
    		 }
    		 writer.close();
    	    } catch (Exception e) {
    	      System.out.println(e.toString());
    	    }
    	
    	 //hello();
    	 
    	 
    	
    }
}