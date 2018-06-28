/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IR_Paraphrased;
import java.text.*;
import java.util.*;
import org.python.core.PyFunction;
import org.python.util.PythonInterpreter;
import org.python.*;
import org.python.core.PyObject;
/**
 *
 * @author Abeer
 */

public class test_aWN_pyth {
     public static void main(String[] args){
     
//        PythonInterpreter interpreter = new PythonInterpreter();
//interpreter.exec("import sys\nsys.path.append('pathToModiles if they're not there by default')\nimport yourModule");
////// execute a function that takes a string and returns a string
//PyObject someFunc = interpreter.get("funcName");
//PyObject result = someFunc.__call__(new PyString("Test!"));
//String realResult = (String) result.__tojava__(String.class);
//         
    //   PythonInterpreter interp = new PythonInterpreter();
//       
//       Properties props = new Properties();
//   props.setProperty("python.path", "/home/modules:scripts");
//   PythonInterpreter.initialize(System.getProperties(), props,new String[] {""});
  
       try
        {
      //     PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
//            System.out.println(System.getProperties());
            
//            Properties props = new Properties();
//props.setProperty("python.path", "/Python21/lib/");
//  PythonInterpreter.initialize(System.getProperties(), props,new String[] {""});
            PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
          PythonInterpreter interp = new PythonInterpreter();
          //  interp.execfile("AWNDatabaseManagement.py");
              interp.execfile("AWN.py");
            //PyFunction func = (PyFunction)interp.get("calculate",PyFunction.class);
              PyFunction func = (PyFunction)interp.get("test",PyFunction.class);
               func.__call__();
        //   PyObject tmpFunction = interp.get("test"); 
          
                      
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss");
System.out.println("======[" + sdf.format(new Date()) +
"]===========");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       
//interp.execfile("AWNDatabaseManagement.py");
//PyFunction func = (PyFunction)interp.get("calculate",PyFunction.class);
//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss");
//System.out.println("======[" + sdf.format(new Date()) +
//"]===========");
//for (int i=1 ; i<10000 ; ++i) {
//// Assuming calculate takes a float argument.
//func.__call__(new PyFloat(i));
////interp.eval("calculate(" + i + ")");
//}
//System.out.println("======[" + sdf.format(new Date()) +
//"]===========");  
    } 
}
