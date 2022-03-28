import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.javatuples.Pair;

/**
 * Input reader is used to read matrix and its number of unknowns
 * from examples directory
 */

public class InputReader {

    /**
     * @param filename
     * @return Pair with integer (number of unknowns) and double list (matrix)
     * @throws FileNotFoundException
     */
    public static Pair<Integer, List<Double>> read(String filename) throws FileNotFoundException {
        Pair<Integer, List<Double>> matrixAndN = null;
        int n = 0;
        List<Double> matrix = new ArrayList<>();
        File input = new File("examples" + File.separator + filename);
        Scanner scanner = new Scanner(input);

        for (int i = 0; ; i++) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String data = scanner.nextLine();
            String[] tokens = data.split("\\s+");
            if (i == 0) {
                n = Integer.parseInt(tokens[0]);
            } else {
                for (int j = 0; j < tokens.length; j++) {
                    double value = Double.parseDouble(tokens[j]);
                    matrix.add(value);
                }
            }
        }
        return new Pair<>(n, matrix);
    }
}
