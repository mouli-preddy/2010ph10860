/**
 * User: moulikrishna.
 * Institute: IIT Delhi.
 * purpose: As part of course requirements CSL211 Computer Architecture.
 * Submission date: 15th September, 2013
 */
public class Debug {
  private static boolean _debuggingMode = false;

  public static void startDebugging(String permission) {
    if (permission.equalsIgnoreCase("debug")) _debuggingMode = true;
    else _debuggingMode = false;
  }

  public static void write(String message) {
    if (_debuggingMode) forceWrite(message);
  }

  public static void forceWrite(String message) {
    System.out.println(message);
  }

  public static void forceQuit(String message) {
    forceWrite(message);
    System.exit(1);
  }

  public static void completed(String name) {
    write("                                                         Completed>>" + name);
  }

  public static void command(String commandRead) {
    if (commandRead != null) {
      write("                                                         Reading>>" + commandRead);
    }
  }

  public static void forceWrite(int i) {
    forceWrite(Integer.toString(i));
  }
}
