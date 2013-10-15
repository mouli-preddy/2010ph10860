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

  private int getPointer(int jump) {
    return jump;
  }

  public int getValueAt(int address) {
    address = getPointer(address);
    return stack[address];
  }

  public int setValueAt(int address, int value) {
    address = getPointer(address);
    return stack[address] = value;
  }
}
