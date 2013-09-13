/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_PRINT extends RISCInstruction {
  public static void exeCmd(RISCInstruction risc) {
    int valueObtained = Main.register.get(risc.getArg1());
    Debug.forceWrite(valueObtained);
    Debug.completed("print");
  }
}
