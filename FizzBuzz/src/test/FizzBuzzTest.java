package test;

import main.FizzBuzz;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class FizzBuzzTest {

    /**
     * Class under test
     */
    FizzBuzz sut;

    Thread threadBuzz;
    Thread threadFizz;
    Thread threadFizzBuzz;
    Thread threadNumber;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    private static final String EXPECTED_OUTPUT_FOR_INPUT_FIFTEEN = "1\n" +
            "2\n" +
            "Fizz\n" +
            "4\n" +
            "Buzz\n" +
            "Fizz\n" +
            "7\n" +
            "8\n" +
            "Fizz\n" +
            "Buzz\n" +
            "11\n" +
            "Fizz\n" +
            "13\n" +
            "14\n" +
            "FizzBuzz";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void when_createFizzBuzz_given_fifteenAsInput_then_correctFifteenExpectedOutputIsEquals() {

        final int input = 15;
        sut = new FizzBuzz(input);

        initAllFizzBuzzThreads();


        //Assert.assertEquals(EXPECTED_OUTPUT_FOR_INPUT_FIFTEEN, outputStreamCaptor.toString().trim());
    }

    private void initAllFizzBuzzThreads() {

        threadBuzz = new Thread(() -> {
            try {
                sut.buzz(() -> System.out.println("Buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadFizz = new Thread(() -> {
            try {
                sut.fizz(() -> System.out.println("Fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        threadFizzBuzz = new Thread(() -> {
            try {
                System.out.println("test");
                sut.fizzbuzz(() -> System.out.println("FizzBuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        threadNumber = new Thread(() -> {
            try {
                sut.number((n) -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        threadFizz.start();
        threadBuzz.start();
        threadFizzBuzz.start();
        threadNumber.start();

    }

}
