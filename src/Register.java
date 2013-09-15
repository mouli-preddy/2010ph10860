/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class Register {
  private static int[] register;
  public static boolean e;
  public static boolean gt;
  public static String errorMessage;

  //public classes
  public Register() {
    register = new int[16];
    for (int i = 0; i < register.length; i++) {
      register[i] = 0;
    }
    errorMessage = "";
    set("sp", 0xfff);
  }

  //private classes
  private int getIndex(String RegI) {
    String inte = RegI.trim().substring(1, RegI.length());
    //checking for StackPointer and ReturnAddress...
    if (RegI.equals("ra")) return 15;
    else if (RegI.equals("sp")) return 14;

    //Trying to parse the integer value as in r<int>...
    int result = -1;
    try {
      result = Integer.parseInt(inte);
    } catch (NumberFormatException e) {
      Debug.write("There is a number format error occuring here");
    }
    return result;
  }

  public boolean set(String regI, int value) {
    int index = getIndex(regI);
    if (index >= 0) {
      register[index] = value;
      return true;
    } else return false;
  }

  public void setEQ() {
    e = true;
  }

  public void resetEQ() {
    e = false;
  }

  public void setGT() {
    gt = true;
  }

  public void resetGT() {
    gt = false;
  }

  public boolean E() {
    return e;
  }

  public boolean GT() {
    return gt;
  }

  public int get(String regI) {
    if (regI.equals("sp")) return register[14];
    else if (regI.equals("ra")) return register[15];
    else {
      int index = getIndex(regI);
      if (index >= 0 && index < 16) return register[index];
      else {
        Debug.write("The register >>" + regI + "<< is not valid");
        return 0;
      }
    }
  }
}
