/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_RET extends RISCInstruction {
  public static void exeCmd(RISCInstruction risc) {
    int returnAdd = Main.register.get("ra");
    Main.p.setPointer(returnAdd);
  }
}
