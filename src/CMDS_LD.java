/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_LD extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {
    if (risc.isAddressMode()) {
      //get proper address ...
      int address = Main.register.get(risc.getArg2());
      address += risc.getImmediate();
      System.out.println("accessing this " + address);

      //get the value at the address
      int valueAtAddress = Main.stack.getValueAt(address);

      //args1 = value obtained
      risc.pushImmediate(risc.getArg1(), valueAtAddress);
    }
  }
}
