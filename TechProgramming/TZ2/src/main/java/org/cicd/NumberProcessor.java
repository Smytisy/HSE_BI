package org.cicd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cicd.*;
public class NumberProcessor {

    public static List<BigInteger> readNumbersFromFile(String filename) throws IOException {
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

    public static BigInteger _min(List<BigInteger> numbers) {
        return Collections.min(numbers);
    }

    public static BigInteger _max(List<BigInteger> numbers) {
        return Collections.max(numbers);
    }

    public static BigInteger _sum(List<BigInteger> numbers) {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : numbers) {
            sum = sum.add(number);
        }
        return sum;
    }

    public static BigInteger _mult(List<BigInteger> numbers) {
        BigInteger product = BigInteger.ONE;
        for (BigInteger number : numbers) {
            product = product.multiply(number);
        }
        return product;
    }

    public static void main(String[] args) {
        try {
            List<BigInteger> numbers = readNumbersFromFile("./src/main/java/org/cicd/numbers.txt");
            System.out.println("Min: " + _min(numbers));
            System.out.println("Max: " + _max(numbers));
            System.out.println("Sum: " + _sum(numbers));
            System.out.println("Multi: " + _mult(numbers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}