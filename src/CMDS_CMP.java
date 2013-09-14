/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_CMP extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {
    int immediateLEFT = Main.register.get(risc.getArg1());
    int immediateRIGHT;
    immediateRIGHT = risc.getProperImm(2);
    if (immediateLEFT == immediateRIGHT) {
      Main.register.setEQ();
      Main.register.resetGT();
    } else if (immediateLEFT > immediateRIGHT) {
      Main.register.setGT();
      Main.register.resetEQ();
    } else {
      Main.register.resetEQ();
      Main.register.resetGT();
    }
  }
}
