/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_BGT extends RISCInstruction {
  public static void exeCmd(RISCInstruction risc) {
    if (Main.register.GT()) {
      int spNew = (int) Main.labels.findLabel(risc.getLabel());
      Main.p.setPointer(spNew);
    }
  }
}
