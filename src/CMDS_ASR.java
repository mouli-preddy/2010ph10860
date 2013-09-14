/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_ASR extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {
    int imme = risc.getProperImm(3);
    int first = Main.register.get(risc.getArg2());
    risc.pushImmediate(risc.getArg1(), first >> imme);
  }
}
