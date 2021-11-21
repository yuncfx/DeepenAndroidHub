package kot.relative;

import java.io.IOException;

public class MyException {
    public void myMethod() throws IOException {
        throw new IOException("io exception");
    }
}
