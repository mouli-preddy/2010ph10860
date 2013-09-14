import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.Vector;

public class RISCInstruction_OLD_VERSION {

  private String cmd;
  private String args1;
  private String args2;
  private String args3;
  private String Instruction;
  private boolean isImmediate;
  private int immediate;
  private final String methodName = "exeCmd";
  private short modifier;
  private String label;

  private Vector<String> input;

  // Constructors.........
  public RISCInstruction_OLD_VERSION() {
  }

  public RISCInstruction_OLD_VERSION(String command) {
    StringTokenizer st = new StringTokenizer(command, " [],\n\t");
    input = new Vector<String>();
    while (st.hasMoreTokens()) {
      input.add(st.nextToken());
    }
    populateValues();


//    testing
//    System.out.println("/////////////////////////////////////////////String received = " + command);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>label=" + label);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>> instruction=" + Instruction);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=modifier=" + modifier);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args1=" + args1);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args2=" + args2);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>=args3=" + args3);
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>immedi=" + immediate);

  }

  private void populateValues() {
    int index = 0;
    while (input.size() > 0) {
      switch (index) {
        case 0:
          if (putLabel()) input.remove(0);
          index++;
          break;
        case 1:
          if (putInstruc()) input.remove(0);
          index++;
          break;
        case 2:
          if (getAddress()) input.remove(0);
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

  private boolean getAddress() {
    String sample = input.elementAt(0);
    if (sample.charAt(0) == 'r') {
      putArgument(sample);
    } else if (sample.length() > 1 && sample.substring(0, 2).equals("0x")) {
      putHexImmed(sample);
    } else {
      putDecImmed(sample);
    }
    return true;
  }

  private boolean putDecImmed(String sample) {
    immediate = Integer.parseInt(sample);
    isImmediate = true;
    return true;
  }

  private boolean putHexImmed(String sample) {
    sample = sample.substring(2, sample.length());
    isImmediate = true;
    immediate = Integer.parseInt(sample, 16);
    return true;
  }

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

  private boolean putInstruc() {
    String sample = input.elementAt(0);
    Instruction = putModifier(sample);
    Instruction = Instruction.replaceAll("\\.", "");
    return true;
  }

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

  private boolean putLabel() {
    String sample = input.elementAt(0);
    if (sample.contains(":")) {
      label = sample.substring(0, sample.length() - 1).trim();
      return true;
    }
    return false;
  }

  //public functions ......
  public void exeCommand() {
//    if (isLabel()) {
//      Debug.write("you are trying to compile a label instruction");
//    } else {
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
      Debug.forceQuit("NEVER REACHING:: instantiateion failed " + getClassName());
    } catch (InvocationTargetException e) {
      Debug.write("NEVER REACHING:: invocation failed " + getClassName());
    } catch (ClassNotFoundException e) {
      Debug.forceQuit("NEVER REACHING:: class is not found " + getClassName());
    }
  }

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

  protected int getProperImm(int pos) {
    int immediateRIGHT;
    if (isImmediate()) {
      immediateRIGHT = getModifiedImm(getImmediate());
    } else if (pos == 2) {
      immediateRIGHT = Main.register.get(getArg2());
    } else immediateRIGHT = Main.register.get(getArgs3());
    return immediateRIGHT;
  }

  //  public boolean isLabel() {
//    if (label.length() > 1) return true;
//    else return false;
//  }
  //private functions for internal use ........
//  private void populateValues() {
//    label = "";
//    if (!isLabel()) {
//      getInstruction();
//      assignModifier();
//      pullArgs1();
//      args2 = pullArgAfter2();
//      if (!isImmediate) {
//        args3 = pullArgAfter2();
//      } else {
//        args3 = "";
//      }
//    }
//  }

//    private void checkForLabel () {
//      cmd = cmd.trim();
//    }

//  private String pullArgAfter2() {
//    String result = "";
//    if (cmd.length() > 0) {
//      if (cmd.charAt(0) == 'r') {
//        isImmediate = false;
//        result = truncateAt(',');
//      } else if ((cmd.length() > 2) && cmd.substring(0, 2).equals("0x")) {
//        isImmediate = true;
//        cmd = cmd.substring(2, cmd.length()).replaceAll("\\s+", "");
//        immediate = Integer.parseInt(cmd, 16);
//      } else {
//        isImmediate = true;
//        immediate = Integer.parseInt(cmd);
//      }
//    }
//    return result;
//  }

//  private void pullArgs1() {
//    args1 = truncateAt(',');
//  }

//  private void getInstruction() {
//    cmd = cmd.trim();
//    Instruction = truncateAt(' ');
//    Instruction = Instruction.replaceAll("\\.", "");
//  }

//  private void assignModifier() {
//    switch (Instruction.charAt(Instruction.length() - 1)) {
//      case 'u':
//        modifier = 1;
//        Instruction = Instruction.substring(0, Instruction.length() - 1);
//        break;
//      case 'h':
//        modifier = 2;
//        Instruction = Instruction.substring(0, Instruction.length() - 1);
//        break;
//      case ':':
//        label = Instruction.substring(0, Instruction.length() - 1);
//        label = label.trim();
//      default:
//        modifier = 0;
//    }
//  }

//  private String truncateAt(char cha) {
//    if (cmd.length() > 1) {
//      int index = cmd.indexOf(cha);
//      String result;
//      if (index > 0) {
//        result = cmd.substring(0, index);
//        result = result.trim();
//        cmd = cmd.substring(index + 1, cmd.length());
//        cmd = cmd.trim();
//      } else {
//        result = cmd.trim();
//      }
//      return result;
//    } else return "";
//  }

//accessor functions
//  public short getModifier() {
//    return modifier;
//  }

  private String getClassName() {
    return "CMDS_" + Instruction.toUpperCase();
  }

  public String getArgs3() {
    return args3;
  }

  public String getArg1() {
    return args1;
  }

  public boolean isImmediate() {
    return isImmediate;
  }

  public int getImmediate() {
    return immediate;
  }

//  public String getLabel() {
//    if (isLabel()) return label;
//    else return "";
//  }

  public String getArg2() {
    return args2;
  }

  public void pushImmediate(String regI, int imm) {
    Main.register.set(regI, imm);
  }

  public void assign(String reg1, String reg2) {
    int valueAt_reg2 = Main.register.get(reg2);
    pushImmediate(reg1, valueAt_reg2);
  }
}
