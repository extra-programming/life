
//Stolen from the internet. Many thanks to zibadian for posting this utility class here for all to use:
//http://www.programmersheaven.com/mb/java/357630/357630/how-can-i-do-a-simple-line-break/

//Here is an example on how to use the "lineSeperator" util:
//JLabel l = new JLabel("hello"+UsefulStrings.getLineSeparator()+"world");

public final class UsefulStrings {

  private static String lineSeparator = (String) java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

  public static String getLineSeparator() {
     return lineSeparator;
  }

  public static String nextIntegerValueAsString(int value) {
     return Integer.toString(value+1);
     // Example of a general task, if you need that in a lot of projects.
  }
}
