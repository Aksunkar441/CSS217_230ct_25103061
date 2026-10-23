interface ModernConsole {
    void logMessage(String tag, String body);
}

interface LegacyPrinter {
    void printLine(String combinedText);
}

public class TwoWayLogAdapter implements ModernConsole, LegacyPrinter {

    @Override
    public void logMessage(String tag, String body) {
        System.out.println("[" + tag + "] " + body);
    }

    @Override
    public void printLine(String combinedText) {
        if (combinedText.startsWith("[") && combinedText.contains("] ")) {
            int endTagIdx = combinedText.indexOf("] ");
            String tag = combinedText.substring(1, endTagIdx);
            String body = combinedText.substring(endTagIdx + 2);

            logMessage(tag, body);
        } else {
            System.out.println(combinedText);
        }
    }

    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        TwoWayLogAdapter adapter = new TwoWayLogAdapter();

        System.out.println("\n--- ModernConsole Call ---");
        adapter.logMessage(studentId, "This is a message from the modern system.");

        System.out.println("\n--- LegacyPrinter Call ---");
        adapter.printLine("[" + studentId + "] This is a message from the legacy system.");
    }
}