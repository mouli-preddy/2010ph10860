/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_NOT extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {
    int imme = risc.getProperImm(3);
    risc.pushImmediate(risc.getArg1(), ~imme);
  }
}
