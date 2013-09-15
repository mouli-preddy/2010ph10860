import java.io.*;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */

public class Program {

  private RandomAccessFile fileSc;
  private long pointer;

  public Program(String fileName) {
    try {
      fileSc = new RandomAccessFile(fileName, "r");
    } catch (FileNotFoundException e) {
      Debug.forceQuit("The file you gave me is not found");
    }
  }

  private String nextCommandStr() {
    String result = "";
    while (result.length() == 0) {
      try {
        result = nextLine();
      } catch (Exception e) {
        Debug.write("-----------------------------EOF reached in the given file------------------------------");
        return null;
      }
    }
    return result;
  }

  private String nextLine() throws IOException {
    String result = null;
    try {
      pointer = fileSc.getFilePointer();
    } catch (Exception e) {
      Debug.forceQuit("NEVER REACHING:: the file pointer is not correct");
    }
    result = fileSc.readLine();
    result = result.trim();
    return result;
  }

  public RISCInstruction getNextCommand() {
    String newLine = nextCommandStr();
    Debug.command(newLine);
    if (newLine == null) return null;
    else {
      RISCInstruction cmd = new RISCInstruction(newLine);
      return cmd;
    }
  }

  public long getFilePointer() {
    return pointer;
  }

  public void setPointer(long pointer) {
    try {
      fileSc.seek(pointer);
    } catch (Exception e) {
      Debug.forceQuit("Your stack pointer is corrupted");
    }
  }
}
