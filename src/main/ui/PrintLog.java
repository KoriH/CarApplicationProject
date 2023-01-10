package ui;

import model.Event;
import model.EventLog;

public class PrintLog implements LogPrinter {

    public PrintLog() {

    }

    @Override
    public void printLog(EventLog el) {

        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }
}
