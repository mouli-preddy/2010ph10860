/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_MOV extends RISCInstruction {

  public void exeCmd(RISCInstruction risc) {
    if (risc.isImmediate()) {
      int immed = risc.getModifiedImm(risc.getImmediate(), risc.getModifier());
      super.pushImmediate(risc.getArg1(), immed);
    } else {
      int immed = Main.register.get(risc.getArg2());
      immed = super.getModifiedImm(immed, risc.getModifier());
      super.pushImmediate(risc.getArg1(), immed);
    }
  }
}
