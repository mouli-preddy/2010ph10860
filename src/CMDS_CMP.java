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
    immediateRIGHT = getProperImm(risc);
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

  private static int getProperImm(RISCInstruction risc) {
    int immediateRIGHT;
    if (risc.isImmediate()) {
      immediateRIGHT = risc.getModifiedImm(risc.getImmediate());
    } else {
      immediateRIGHT = Main.register.get(risc.getArg2());
    }
    return immediateRIGHT;
  }
}
