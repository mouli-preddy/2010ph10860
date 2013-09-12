/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_MOV extends RISCInstruction {

  public void exeCmd(RISCInstruction risc) {
    System.out.println("I am printing in all the values that I received--------------------------------------------");
    System.out.println("command is >>>>>>>>>>>>>>>>>>>>>>>>>>MOV<<<<<<");
    System.out.println("arg1 is  >>>>>>>>>>>>>>>>>>>>>>>>>>>>" + risc.getArg1() + "<<<<<");
    if (risc.isImmediate())
      System.out.println("arg2 is immediate with >>>>>>>>>>>>>>" + risc.getImmediate() + "<<<<<<");
    else System.out.println("arg2 is register with >>>>>>>>>>>>>>" + risc.getArg2() + "<<<<<<");
  }
}
