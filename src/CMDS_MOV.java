/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_MOV extends RISCInstruction {

  public void exeCmd(RISCInstruction risc) {
    if (risc.isImmediate()) {
      super.pushImmediate(risc.getArg1(), getInput(risc.getImmediate(), risc.getModifier()));
    } else {
      int immed = Main.register.get(risc.getArg2());
      super.pushImmediate(risc.getArg1(), getInput(immed, risc.getModifier()));
    }
    Debug.completed("mov");
  }

  private int getInput(int immediate, short mod) {
    int result = 0;
    switch (mod) {
      case 0:
        return immediate;
      case 1:
        if (immediate < 0) return (immediate - 0xffff0000);
        else return immediate;
      case 2:
        return immediate * 0x10000;
    }
    return result;
  }
}
