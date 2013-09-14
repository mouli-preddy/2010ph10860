import java.util.StringTokenizer;
import java.util.Vector;

/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class test {

  private static String cmd;
  private static String args1;
  private static String args2;
  private static String args3;
  private static String Instruction;
  private static boolean isImmediate;
  private static int immediate;
  private static short modifier;
  private static String label;

  private static Vector<String> input;


  public static void main(String[] a) {
    String str = "   sample:    movh        r2 , 543[r5], ";
    args1 = null;
    args2 = null;
    args3 = null;
    System.out.println("String received = " + str);
    StringTokenizer st = new StringTokenizer(str, " [],\n\t");
    input = new Vector<String>();
    while (st.hasMoreTokens()) {
      input.add(st.nextToken());
    }
    populateValues();
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>label=" + label);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>> instruction=" + Instruction);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args1=" + args1);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args2=" + args2);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args3=" + args3);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>immedi=" + immediate);
  }

  private static void populateValues() {
    int index = 0;
    System.out.println("tokens remaining " + input.size());
    while (input.size() > 0) {
      switch (index) {
        case 0:
          if (putLabel()) input.remove(0);
          index++;
          break;
        case 1:
          if (putInstruc()) input.remove(0);
          index++;
          break;
        case 2:
          if (getAddress()) input.remove(0);
          index++;
          break;
        case 3:
          if (getAddress()) input.remove(0);
          index++;
          break;
        case 4:
          if (getAddress()) input.remove(0);
          index++;
          break;
        default:
          return;
      }
      System.out.println("tokens remaining " + input.size());
    }
  }

  private static boolean getAddress() {
    String sample = input.elementAt(0);
    if (sample.charAt(0) == 'r') {
      putArgument(sample);
    } else if (sample.substring(0, 1).equals("0x")) {
      putHexImmed(sample);
    } else {
      putDecImmed(sample);
    }
    return true;
  }

  private static boolean putDecImmed(String sample) {
    immediate = Integer.parseInt(sample);
    System.out.println("A dec immediate is obtained is ............................." + immediate);
    isImmediate = true;
    return true;
  }

  private static boolean putHexImmed(String sample) {
    sample = sample.substring(2, sample.length() - 1);
    isImmediate = true;
    immediate = Integer.parseInt(sample, 16);
    return true;
  }

  private static boolean putArgument(String sample) {
    if (args1 == null) {
      args1 = sample;
      return true;
    } else if (args2 == null) {
      args2 = sample;
      return true;
    } else if (args3 == null) {
      args3 = sample;
      return true;
    }
    return false;
  }

  private static boolean putInstruc() {
    String sample = input.elementAt(0);
    Instruction = putModifier(sample);
    return true;
  }

  private static String putModifier(String sample) {
    switch (sample.charAt(sample.length() - 1)) {
      case 'u':
        modifier = 1;
        return sample.substring(0, sample.length() - 1);
      case 'h':
        modifier = 2;
        return sample.substring(0, sample.length() - 1);
      default:
        modifier = 0;
        return sample;
    }
  }

  private static boolean putLabel() {
    String sample = input.elementAt(0);
    if (sample.contains(":")) {
      label = sample.substring(0, sample.length() - 1).trim();
      return true;
    }
    return false;
  }
}
