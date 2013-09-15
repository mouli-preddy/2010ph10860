/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_CALL extends RISCInstruction {
  public static void exeCmd(RISCInstruction risc) {
    //Accessing the next command in line and stores in ra
    Main.p.getNextCommand();
    int ra = (int) Main.p.getFilePointer();
    risc.pushImmediate("ra", ra);

    //Changing stack pointer to the address of label in file...
    int spNew = (int) Main.labels.findLabel(risc.getLabel());
    Main.p.setPointer(spNew);
  }
}
