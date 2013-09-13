import java.util.Scanner;

/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class test {
  public static void main(String[] a) {
    String integer = "8000";
    int testSamplef = (short) Integer.parseInt(integer, 16);
    System.out.println("The string parsing is " + Integer.toHexString(testSamplef));
    int value = 0;
    while (value < 3) {
      switch (value) {
        case 0:
          put(testSamplef);
          break;
        case 1:
          if (testSamplef < 0) put(testSamplef - 0xffff0000);
          else put(testSamplef);
          break;
        case 2:
          put(testSamplef * 0x10000);
          break;
      }
      value++;
    }
  }

  private static void put(int testSamplef) {
    System.out.println(Integer.toHexString(testSamplef));
  }
}
