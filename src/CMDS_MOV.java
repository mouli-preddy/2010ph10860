/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_MOV extends RISCInstruction {

  public void exeCmd(RISCInstruction risc) {
    if (risc.isImmediate()) super.pushImmediate(risc.getArg1(), risc.getImmediate());
    else {
      super.assign(risc.getArg1(), risc.getArg2());
    }
    Debug.completed("mov");
  }
}
