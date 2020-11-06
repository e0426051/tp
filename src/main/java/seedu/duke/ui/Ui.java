package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi that handles user interaction.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    /**
     * Prints welcome message.
     */
    public static void showWelcomeScreen() {
        showToUserLn(Messages.MESSAGE_LOGO);
        showToUserLn(Messages.MESSAGE_WELCOME);
    }

    /**
     * Accepts input from user via input stream.
     *
     * @return Input from user
     */
    public static String getUserCommand() {
        showToUser(Messages.MESSAGE_PROMPT_INPUT.trim());
        String input = in.nextLine().trim();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        return input.strip();
    }

    /**
     * Gets raw user input from user via input stream.
     * This method is different from getUserCommand():
     * It does not print "command>" and does not perform loop until it gets a non-empty trimmed string.
     * 
     * @return Raw input from user
     */
    public static String getUserInput() {
        return in.nextLine().trim();
    }

    /**
     * Print messages to user.
     *
     * @param messages Specify messages to print
     */
    public static void showToUser(String... messages) {
        for (String message : messages) {
            out.print(message);
        }
    }

    /**
     * Print messages to user in new line.
     *
     * @param messages Specify messages to print
     */

    public static void showToUserLn(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

    /**
     * Print error messages to the user.
     *
     * @param messages Specify error to be printed
     */

    public static void showError(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

    public static void setInStream(InputStream inStream) {
        in = new Scanner(inStream);
    }
    
    public static void setOutStream(PrintStream outStream) {
        out = outStream;
    }
}
