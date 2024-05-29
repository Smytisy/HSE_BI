
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.cicd.NumberProcessor;

import java.io.IOException;
import java.util.*;
import java.math.BigInteger;


public class NumberProcessorTest {

    // Testing correct working function
    @Test
    public void testMin() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("1"), NumberProcessor.min(numbers));
    }

    @Test
    public void testMax() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("4"), NumberProcessor.max(numbers));
    }

    @Test
    public void testSum() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("10"), NumberProcessor.sum(numbers));
    }

    @Test
    public void testMult() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("4"), new BigInteger("2"), new BigInteger("3"));
        assertEquals(new BigInteger("24"), NumberProcessor.mult(numbers));
    }


    // Test check time working
    @Test
    @Timeout(5)
    @DisplayName("Test check time working")
    public void testPerformance() {
        List<BigInteger> numbers = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            numbers.add(new BigInteger("1"));
        }

        long startTime = System.currentTimeMillis();
        NumberProcessor.min(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Min performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor.max(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Max performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor.sum(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Sum performance: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        NumberProcessor.mult(numbers);
        endTime = System.currentTimeMillis();
        System.out.println("Mult performance: " + (endTime - startTime) + " ms");
    }

    // Any other test
    @Test
    public void testEdgeCase() {
        List<BigInteger> numbers = Arrays.asList(new BigInteger("0"), new BigInteger("-1"), new BigInteger("1"));
        assertEquals(new BigInteger("-1"), NumberProcessor.min(numbers));
        assertEquals(new BigInteger("1"), NumberProcessor.max(numbers));
        assertEquals(new BigInteger("0"), NumberProcessor.sum(numbers));
        assertEquals(new BigInteger("0"), NumberProcessor.mult(numbers), "should be 0");
    }

    @Nested
    class forYou {

        private List<BigInteger> numbers;

        @BeforeEach
        void setUp() {
            numbers = Arrays.asList(
                    new BigInteger("1"),
                    new BigInteger("4"),
                    new BigInteger("2"),
                    new BigInteger("3")
            );
        }

        @Test
        void testMin() {
            // given
            List<BigInteger> testNumbers = new ArrayList<>(numbers);

            // when
            BigInteger result = NumberProcessor.min(testNumbers);

            // then
            assertEquals(new BigInteger("1"), result);
        }

        @Test
        void testMax() {
            // given
            List<BigInteger> testNumbers = new ArrayList<>(numbers);

            // when
            BigInteger result = NumberProcessor.max(testNumbers);

            // then
            assertEquals(new BigInteger("4"), result);
        }

        @Test
        void testSum() {
            // given
            List<BigInteger> testNumbers = new ArrayList<>(numbers);

            // when
            BigInteger result = NumberProcessor.sum(testNumbers);

            // then
            assertEquals(new BigInteger("10"), result);
        }

        @Test
        void testMult() {
            // given
            List<BigInteger> testNumbers = new ArrayList<>(numbers);

            // when
            BigInteger result = NumberProcessor.mult(testNumbers);

            // then
            assertEquals(new BigInteger("24"), result);
        }

        @Test
        void testReadNumbersFromFileException() {
            // given
            String invalidFilename = "./invalid/path/numbers.txt";

            // when & then
            assertThrows(IOException.class, () -> {
                NumberProcessor.readNumbersFromFile(invalidFilename);
            });
        }
    }
}