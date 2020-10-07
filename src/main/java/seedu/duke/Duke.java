package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    static Ui ui = new Ui();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);

        System.out.println("Hello " + in.nextLine());
        while (true) {
            new Parser().parser(ui);
        }
    }
}