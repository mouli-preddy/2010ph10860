import java.math.BigInteger;

/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class CMDS_READ extends RISCInstruction {

  public static void exeCmd(RISCInstruction risc) {


    String in1 = Main.readNextLine().replace("0x", "").replace(" ", "");
    String in2 = Main.readNextLine().replace("0x", "").replace(" ", "");
    BigInteger bg1 = new BigInteger(in1, 16);
    BigInteger bg2 = new BigInteger(in2, 16);
    bg2 = bg1.multiply(bg2);
    String sample = bg2.toString(16);
    while (sample.length() != 128) {
      sample = "0" + sample;
    }
    sample = "0x" + sample;


    StringBuilder str = new StringBuilder(sample);
    int idx = str.length() - 2;

    while (idx > 0) {
      str.insert(idx, " ");
      idx = idx - 2;
    }


    System.out.println(str.toString().toUpperCase());

//    String input1 = Main.readNextLine().replace("0x", "");
//    String input2 = Main.readNextLine().replace("0x", "");
//    BigInteger bg1 = new BigInteger(input1, 16);
//    BigInteger bg2 = new BigInteger(input2, 16);
//    System.out.println("bg1>>>>>>>>>>>" + bg1);
//    System.out.println("bg1 hex>>>>>>>"+bg1.toString(16));

//    //Create a string tokenizer with the input from Main.getNextLine();
//    String input = Main.readNextLine();
//    input = input.replace("0x", "");
//    StringTokenizer st = new StringTokenizer(input, " ");
//
//
//    //iterate through st and populate all the values.
//    int index = 0;
//    int address = Main.register.get(risc.getArg2());
//    while (st.hasMoreTokens()) {
//      //get the address to store value sp -
//      address--;
//
//      //get the value t be stored
//      String nextToken = st.nextToken();
//      int valueToStore = Integer.parseInt(nextToken, 16);
//
//      //store value int the given address.
//      Main.stack.setValueAt(address, valueToStore);
//      index++;
//    }
//
//    //setting the register sent as args2 as index
//    risc.pushImmediate(risc.getArg1(), index);
  }
}
