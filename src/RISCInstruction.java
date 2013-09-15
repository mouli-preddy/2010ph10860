import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.Vector;

public class RISCInstruction {

  private String args1;
  private String args2;
  private String args3;
  private String Instruction;
  private boolean isImmediate;
  private int immediate;
  private final String methodName = "exeCmd";
  private short modifier;
  private boolean hasLabel;
  private String label;

  private Vector<String> input;

  // Constructors and other functions of use ...

  /**
   * Dummy constructor to satisfy compiler
   * This is never supposed to be reached.....
   */
  public RISCInstruction() {
  }

  /**
   * The string param is used to populate the values of
   * label, Instruction, args[1,2,3], immediate(16 bit).
   *
   * @param command = The line of RISC code that is executable.
   */
  public RISCInstruction(String command) {
    StringTokenizer st = new StringTokenizer(command, " [],\n\t");
    input = new Vector<String>();
    while (st.hasMoreTokens()) {
      input.add(st.nextToken());
    }
    populateValues();
  }

  /**
   * The main function to push the immediate into args.
   *
   * @param regI the register args that is populated
   * @param imm  the immediate that is pushed into @param regI
   */
  public void pushImmediate(String regI, int imm) {
    Main.register.set(regI, imm);
  }

  /**
   * assings reg2 to reg1.
   *
   * @param reg1 the args that is populated
   * @param reg2 the args that is read to assign to other args...
   */
  public void assign(String reg1, String reg2) {
    int valueAt_reg2 = Main.register.get(reg2);
    pushImmediate(reg1, valueAt_reg2);
  }

  /**
   * The function that you should call to execute the command that is contains.
   * This execution means the invocation of class of name
   * CMDS_<instruction> in uppercase.
   * quits the program when any kind of error is encountered ...
   */
  public void exeCommand() {
    if (Instruction != null) {
      try {
        Class<?> cls = Class.forName(getClassName());
        Method meth = cls.getMethod(methodName, this.getClass());
        meth.invoke(cls.newInstance(), this);
        Debug.completed(Instruction);
      } catch (NoSuchMethodException e) {
        Debug.forceQuit("NEVER REACHING:: method not found" + getClassName());
      } catch (IllegalAccessException e) {
        Debug.forceQuit("NEVER REACHING:: class illegally reached " + getClassName());
      } catch (InstantiationException e) {
        Debug.forceQuit("NEVER REACHING:: instantiation failed " + getClassName());
      } catch (InvocationTargetException e) {
        Debug.write("NEVER REACHING:: invocation failed " + getClassName());
      } catch (ClassNotFoundException e) {
        Debug.forceQuit("NEVER REACHING:: class is not found " + getClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * returns the immediate appropriate based on the preloaded modifier
   *
   * @param imme
   * @return
   */
  protected int getModifiedImm(int imme) {
    short immediate = (short) imme;
    int result = 0;
    switch (modifier) {
      case 0:
        result = immediate;
        break;
      case 1:
        if (immediate < 0) result = (immediate - 0xffff0000);
        else result = immediate;
        break;
      case 2:
        result = immediate * 0x10000;
    }
    return result;
  }

  /**
   * Returns the requested args as an immediate.
   * defaults to args3 and starts with immediate if one exists.
   * pos = 2 indicates it to go to immediate pointed by args2.
   *
   * @param pos 2 for args2 and defaults.
   * @return a second argument of calculation that the instruction is expeted to have.
   */
  protected int getProperImm(int pos) {
    int imm;
    if (isImmediate()) {
      imm = getModifiedImm(getImmediate());
    } else if (pos == 2) {
      imm = Main.register.get(getArg2());
    } else imm = Main.register.get(getArgs3());
    return imm;
  }

  //parsing helper functions ...

  /**
   * The first part of parser that actually tokenizes the string and
   * populates the values that are represented by the RISC command ...
   */
  private void populateValues() {
    int index = 0;
    //this loop runs till the tokens are exhausted or till 3 args are read...
    while (input.size() > 0) {
      switch (index) {
        case 0:
          hasLabel = false;
          if (putLabel(true)) input.remove(0);
          index++;
          break;
        case 1:
          if (putInstruc()) input.remove(0);
          index++;
          break;
        case 2:
          if (isBranchOp() || getAddress()) input.remove(0);
          index++;
          break;
        case 3:
          if (getAddress()) input.remove(0);
          index++;
          break;
        case 4:
          if (getAddress()) input.remove(0);
          index++;
          break;
        default:
          return;
      }
    }
  }

  private boolean isBranchOp() {
    String sample = input.elementAt(0);
    boolean isRegister = sample.replaceAll("\\d*$", "").equals("r");
    if (!isRegister) {
      putLabel(false);
      return true;
    }
    return false;
  }

  /**
   * @return true if the address is read from
   */
  private boolean getAddress() {
    String sample = input.elementAt(0);
    // if the argument is of the form rd
    if (sample.charAt(0) == 'r') {
      putArgument(sample);

      //    If the argument is of the form 0xnnnn
    } else if (sample.length() > 1 && sample.substring(0, 2).equals("0x")) {
      putHexImmed(sample);

      //    If nothing else works go for dec format numbers...
    } else if (sample.charAt(0) != '.') {
      putDecImmed(sample);
    }
    return true;
  }

  /**
   * Reads a string in dec format and
   *
   * @param sample string containing the dec format number for parsing
   * @return true if dec format number has been read
   */
  private boolean putDecImmed(String sample) {
    try {
      immediate = Integer.parseInt(sample);
      isImmediate = true;
    } catch (NumberFormatException e) {
      Debug.write("number format exception reached for" + sample);
      return false;
    }
    return true;
  }

  /**
   * Reads a Hex format string to populate immediate
   * Takes care of extra spacing between 0x and suffix and also
   * space between two bytes...
   *
   * @param sample the string containing the int in hex format
   * @return
   */
  private boolean putHexImmed(String sample) {
    try {
      int i = 1;
      while (sample.length() < 6) {
        sample += input.elementAt(i);
        i++;
      }
      sample = sample.substring(2, sample.length());
      immediate = Integer.parseInt(sample, 16);
      isImmediate = true;
    } catch (Exception e) {
      Debug.write("Hex format string not found but trying to read ...");
      return false;
    }
    return true;
  }

  /**
   * Checks for the first argument among args[1,2,3] thats not null
   * ans inserts the arguments string received at that position ...
   *
   * @param sample the argument string to be inserted
   * @return true if the insertion is successful ...
   */
  private boolean putArgument(String sample) {
    if (args1 == null) {
      args1 = sample;
      return true;
    } else if (args2 == null) {
      args2 = sample;
      return true;
    } else if (args3 == null) {
      args3 = sample;
      return true;
    }
    return false;
  }

  /**
   * Inserts the instruction into the private string variable.
   * removes all . that occur in the string input
   *
   * @return true always...
   */
  private boolean putInstruc() {
    String sample = input.elementAt(0);
    Instruction = putModifier(sample);
    Instruction = Instruction.replaceAll("\\.", "");
    return true;
  }

  /**
   * decides and assigns modifier with corresponding values...
   *
   * @param sample the instruction thats being read
   * @return the instruction after proper modifier is assigned.
   */
  private String putModifier(String sample) {
    switch (sample.charAt(sample.length() - 1)) {
      case 'u':
        modifier = 1;
        return sample.substring(0, sample.length() - 1);
      case 'h':
        modifier = 2;
        return sample.substring(0, sample.length() - 1);
      default:
        modifier = 0;
        return sample;
    }
  }

  /**
   * Populates label if a label is found of the format
   * <labelName>:
   *
   * @return true of label is found ...
   */
  private boolean putLabel(boolean hasColon) {
    String sample = input.elementAt(0);
    if (hasColon && sample.contains(":")) {
      label = sample.substring(0, sample.length() - 1).trim();
      hasLabel = true;
      return true;
    } else if (!hasColon) {
      label = sample.trim();
      hasLabel = false;
      return true;
    }
    return false;
  }

  /**
   * generates a class name of type CMDS_<instruction>
   *
   * @return the class name
   */
  private String getClassName() {
    return "CMDS_" + Instruction.toUpperCase();
  }

  //getter functions for all private variables.

  /**
   * getter for args3.
   */
  public String getArgs3() {
    return args3;
  }

  /**
   * getter for args1
   */
  public String getArg1() {
    return args1;
  }

  /**
   * verifier for immediate ...
   */
  public boolean isImmediate() {
    return isImmediate;
  }

  /**
   * getter for immediate
   * return 0 if the immediate is not assigned previously...
   */
  public int getImmediate() {
    return immediate;
  }

  /**
   * getter for args2...
   */
  public String getArg2() {
    return args2;
  }

  public boolean isHasLabel() {
    return hasLabel;
  }

  /**
   * returns the label of this command if the label is not populated
   * this returns a null;;;
   */
  public String getLabel() {
    return label;
  }
}
