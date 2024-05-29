package org.cicd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberProcessor {

    public static List<BigInteger> readNumbersFromFile(String filename) throws IOException {        // Realization function read from file and find min, max, sum, multi
        List<BigInteger> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        if (line != null) {
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                numbers.add(new BigInteger(token));
            }
        }
        reader.close();
        return numbers;
    }

    public static BigInteger min(List<BigInteger> numbers) {
        return Collections.min(numbers);
    }

    public static BigInteger max(List<BigInteger> numbers) {
        return Collections.max(numbers);
    }

    public static BigInteger sum(List<BigInteger> numbers) {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : numbers) {
            sum = sum.add(number);
        }
        return sum;
    }

    public static BigInteger mult(List<BigInteger> numbers) {
        BigInteger product = BigInteger.ONE;
        for (BigInteger number : numbers) {
            product = product.multiply(number);
        }
        return product;
    }

    public static void main(String[] args) {
        try {
            List<BigInteger> numbers = readNumbersFromFile("./src/main/java/org/cicd/numbers.txt");
            System.out.println("Min: " + min(numbers));
            System.out.println("Max: " + max(numbers));
            System.out.println("Sum: " + sum(numbers));
            System.out.println("Multi: " + mult(numbers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}