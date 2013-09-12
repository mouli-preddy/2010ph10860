/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */

public class Main {

  public static Register register;

  public static void main(String args[]) {
    String fileName = takeArguments(args);
    register = new Register();
    Program p = new Program(fileName);

    RISCInstruction rs = p.getNextCommand();
    while (rs != null) {
      rs.exeCmd();
      rs = p.getNextCommand();
    }
  }

  private static String takeArguments(String[] args) {
    //assuming the fileName is written in args[0];
    if (args.length > 0) {
      if (args.length > 1) {
        Debug.startDebugging(args[1]);
      }
      return args[0];
    } else {
      Debug.forceQuit("The File name is missing we need atleast one file name to start with");
    }
    return "";
  }
}


// pattern for CMDS_**** instucrion executer...
//
//public class CMDS_PRINT extends RISCInstruction {
//  public static void exeCmd(RISCInstruction risc) {
//
//  }
//}
