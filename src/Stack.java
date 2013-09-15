/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class Stack {

  private int[] stack;
  private int stackSize = 4100;
  private int pointer;

  public Stack() {
    stack = new int[stackSize];
    pointer = 0xFFF;
  }

  public int getPointer(int jump) {
    int value = jump / 4;
    if (value > 0) {
      return value;
    } else return 1;
  }
}
