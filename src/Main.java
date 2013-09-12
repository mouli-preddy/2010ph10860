/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */

public class Main {
  public static void main(String args[]) {
    String fileName = takeArguments(args);
    Program p = new Program(fileName);

    RISCInstruction rs = p.getNextCommand();
    if (rs.isLabel()) System.out.println(rs.getLabel());
    p.getNextCommand().exeCmd();
    p.getNextCommand().exeCmd();
    p.getNextCommand().exeCmd();
    p.getNextCommand().exeCmd();
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
