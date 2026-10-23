public class Main {
    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        TextMessage original = new SimpleTextMessage("Alert: 5 < 10 & 2 > 1");
        
        TextMessage escaped = new HtmlEscapingDecorator(original);
        TextMessage watermarked = new WatermarkDecorator(escaped, studentId);

        System.out.println("Original:  " + original.getContent());
        System.out.println("Decorated: " + watermarked.getContent());
    }
}
