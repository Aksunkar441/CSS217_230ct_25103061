import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64ReaderAdapter extends Reader {
    private final InputStreamReader reader;

    public Base64ReaderAdapter(InputStream inputStream) {
        this.reader = new InputStreamReader(
            Base64.getDecoder().wrap(inputStream), 
            StandardCharsets.UTF_8
        );
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        byte[] encodedBytes = Base64.getEncoder().encode(studentId.getBytes(StandardCharsets.UTF_8));
        InputStream bais = new ByteArrayInputStream(encodedBytes);

        Reader adapter = new Base64ReaderAdapter(bais);
        BufferedReader bufferedReader = new BufferedReader(adapter);

        String decoded = bufferedReader.readLine();
        System.out.println("Decoded string: " + decoded);
        
        if (studentId.equals(decoded)) {
            System.out.println("Assertion passed: decoded string == Student ID");
        } else {
            System.out.println("Assertion failed!");
        }
        
        bufferedReader.close();
    }
}
