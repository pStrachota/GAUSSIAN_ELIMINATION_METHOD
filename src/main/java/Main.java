import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.javatuples.Pair;

public class Main {
    public static void main(String[] args) {

        Pair<Integer, List<Double>> integerListPair = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe układów równań" +
                "których chcesz otrzymać rozwiązanie");

        //we have five additional files, that's why from 1 to 15

        int numberOfEquations = 0;
        System.out.println("Mozesz wybrac liczbe z przedzialu od 1 do 15");
        do {
            String userInput = scanner.nextLine();
            numberOfEquations = Integer.parseInt(userInput);
        } while (numberOfEquations > 15 || numberOfEquations <= 0);

        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");

        for (int i = 1; i <= numberOfEquations; i++) {
            try {
                integerListPair = InputReader.read("example_" + i + ".txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Integer n = integerListPair.getValue0();
            List<Double> doubles = integerListPair.getValue1();
            List<Double> results = new ArrayList<>();
            Matrix matrix = new Matrix(n, doubles);

            if (GaussianEliminationMethod.GaussianSolver(matrix, n, results)) {
                results.forEach(System.out::println);
            }
            System.out.println("------------------------------------------------");
            System.out.println("------------------------------------------------");
        }
    }
}
