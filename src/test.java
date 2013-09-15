/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class test {

  public static void main(String args[]) {
    String sample = "asdfasdfasdf /* commentf */";
    sample = sample.replaceAll("/\\*(.|[\\r\\n])*?\\*/", "");
    if (sample.indexOf("//") > 0) sample = sample.substring(0, sample.indexOf("//"));
    if (sample.indexOf("@") > 0) sample = sample.substring(0, sample.indexOf("@"));
    sample = sample.replaceAll("@", "");
    System.out.println("string after removal=" + sample + "<<");
  }
}
