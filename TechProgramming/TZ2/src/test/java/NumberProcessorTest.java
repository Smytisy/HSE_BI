
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.cicd.NumberProcessor;
import java.util.*;
import java.math.BigInteger;


public class NumberProcessorTest {

    // Testing correct working function
    @Test
    public void testMin() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("1"), NumberProcessor._min(numbers));
    }

    @Test
    public void testMax() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("4"), NumberProcessor._max(numbers));
    }

    @Test
    public void testSum() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("10"), NumberProcessor._sum(numbers));
    }

    @Test
    public void testMult() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("24"), NumberProcessor._mult(numbers));
    }


    // Test check time working
    @Test
    public void testPerformance() {
        List<BigInteger> numbers = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            numbers.add(new BigInteger("1"));
        }

        long startTime = System.currentTimeMillis();
        NumberProcessor._min(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Min performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor._max(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Max performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor._sum(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Sum performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor._mult(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Mult performance: " + (endTime - startTime) + " ms");
    }

    // Any other test
    @Test
    public void testEdgeCase() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("0"), new BigInteger("-1"), new BigInteger("1"));
        assertEquals(new BigInteger("-1"), NumberProcessor._min(numbers));
        assertEquals(new BigInteger("1"), NumberProcessor._max(numbers));
        assertEquals(new BigInteger("0"), NumberProcessor._sum(numbers));
        assertEquals(new BigInteger("1"), NumberProcessor._mult(numbers));
    }
}