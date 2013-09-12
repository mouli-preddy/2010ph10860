/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class test {
  public static void main(String ar[]) {
    String test = "0x123f";
    test = test.substring(2, test.length()).trim();
    int temp = Integer.parseInt(test, 16);
    System.out.println("The int we have is >>" + test + "<< and after parsing its " + temp);
  }
}

//    int temp = Integer.parseInt(test);
//NumberFormatException

//ox is to be removed.and code is
//  int temp = Integer.parseInt(test, 16);

