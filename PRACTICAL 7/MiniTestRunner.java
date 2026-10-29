import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class SampleTests {
    @Run
    public void testAddition() {
        System.out.println("Addition test executed");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Subtraction test executed");
    }

    public void normalMethod() {
        System.out.println("This method will not run");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        SampleTests tests = new SampleTests();
        int count = 0;

        for (Method method : tests.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                method.invoke(tests);
                count++;
            }
        }

        System.out.println("Total tests run: " + count);
    }
}