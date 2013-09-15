/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_ST extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {
    if (risc.isAddressMode()) {
      //get the address to store value
      int address = Main.register.get(risc.getArg2());
      address += risc.getImmediate();

      //get the value t be stored
      int valueToStore = Main.register.get(risc.getArg1());

      //store value int the given address.
      Main.stack.setValueAt(address, valueToStore);
    }
  }
}
