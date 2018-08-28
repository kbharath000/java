package TestProject.TestProject;

import java.time.Clock;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Veriy Error is present in last ten seconds
 * @author bharathkumar
 *
 */
public class CheckErrorOccurIn10Seconds {
 
  HashMap<String,Long> persist = new HashMap<>();
  public void checkErrrorOccurInTenSeconds(String error) throws Exception{    
    if(!persist.containsKey(error)){
      persist.put(error , Clock.systemDefaultZone().millis());
      System.out.println("Given Error Not Present:  " + error);
    }else{
      Long instant = persist.get(error);
      if(instant >= Clock.systemDefaultZone().millis() - 10000){
        System.out.println("Already Given Error Present: " + error );
      }else{
        persist.put(error , Clock.systemDefaultZone().millis());
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    CheckErrorOccurIn10Seconds ce = new CheckErrorOccurIn10Seconds();
    ce.checkErrrorOccurInTenSeconds("ArithematicException");
    ce.checkErrrorOccurInTenSeconds("ArrayIndexOutOfBoundException");
    TimeUnit.SECONDS.sleep(5);
    ce.checkErrrorOccurInTenSeconds("ArithematicException");
    TimeUnit.SECONDS.sleep(1);
    ce.checkErrrorOccurInTenSeconds("ArrayIndexOutOfBoundException");
  }

}
