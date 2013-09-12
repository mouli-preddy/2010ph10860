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
  public static void Initiate() {
    register = new int[16];
    for (int i = 0; i < register.length; i++) {
      register[i] = 0;
    }
    errorMessage = "";
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

  public int get(String regI) {
    int index = getIndex(regI);
    if (index >= 0) return register[index];
    else {
      Debug.write("The register >>" + regI + "<< is not valid");
      return 0;
    }
  }
}
