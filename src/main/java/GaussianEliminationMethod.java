import java.util.List;

/**
 * Gaussian algorithm function that we access from main
 * @param matrix matrix from file
 * @param n number of unknowns
 * @param results list of doubles that stores results (if they exist)
 * @return boolean - true if there is solutions, false otherwise
 */
public class GaussianEliminationMethod {

    private static final double epsilon = 0.000001;

    public static boolean GaussianSolver(Matrix matrix, int n, List<Double> results) {

        for (int i = 0; i < n; i++) {
            results.add(0d);
        }

        for (int rowIndex = 0; rowIndex < n - 1; rowIndex++) {
            int ielMax = findBiggestInColumn(matrix, n, rowIndex);

            if (ielMax != rowIndex) {
                swapRows(matrix, rowIndex, ielMax);
            }
            eliminate(matrix, n, rowIndex);
        }

        if (CheckIfNotCorrect(matrix, n)) {
            return false;
        } else {
            results = fillResultsWhenPossible(matrix, n, results);
        }
        return true;
    }

    private static int findBiggestInColumn(Matrix matrix, int n, int rowIndex) {
        int ielMax = rowIndex;
        double elMax = Math.abs(matrix.getFieldValue(rowIndex, rowIndex));

        for (int nextRowIndex = rowIndex + 1; nextRowIndex < n; nextRowIndex++) {
            if (Math.abs(matrix.getFieldValue(nextRowIndex, rowIndex)) > elMax) {
                elMax = Math.abs(matrix.getFieldValue(nextRowIndex, rowIndex));
                ielMax = nextRowIndex;
            }
        }
        return ielMax;
    }

    private static void swapRows(Matrix matrix, int rowIndex, int ielMax) {
        List<Double> temp = matrix.getRow(rowIndex);
        matrix.setRow(rowIndex, matrix.getRow(ielMax));
        matrix.setRow(ielMax, temp);
    }

    /**
     * this function is used for eliminating subsequent elements in matrix
     * @param matrix matrix from file
     * @param n number of unknowns
     * @param rowIndex particular row index
     */
    private static void eliminate(Matrix matrix, int n, int rowIndex) {
        for (int i = rowIndex + 1; i < n; i++) {
            double m = matrix.getFieldValue(i, rowIndex) / matrix.getFieldValue(rowIndex, rowIndex);
            for (int j = rowIndex + 1; j <= n; j++) {
                double valueToSet = matrix.getFieldValue(i, j)
                        - m * matrix.getFieldValue(rowIndex, j);
                matrix.setFieldValue(i, j, valueToSet);
            }
        }
    }

    /**
     *
     * @param matrix matrix from file
     * @param n number of unknowns
     * @return boolean - true if there are solutions, false otherwise
     */
    private static boolean CheckIfNotCorrect(Matrix matrix, int n) {
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(matrix.getFieldValue(i, i)) < epsilon) {
                if (Math.abs(matrix.getFieldValue(i, i + 1)) < epsilon) {
                    System.out.println("uk??ad nieoznaczony");
                } else {
                    System.out.println("uk??ad sprzeczny");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * this function is called when checkIfNotCorrect returns false
     * it stores solutions in results list
     * @param matrix matrix matrix from file
     * @param n n number of unknowns
     * @param results list of doubles that stores results
     * @return results
     */
    private static List<Double> fillResultsWhenPossible(Matrix matrix, int n,
                                                        List<Double> results) {
        double valueToAdd = matrix.getFieldValue(n - 1, n) /
                matrix.getFieldValue(n - 1, n - 1);

        results.set(n - 1, valueToAdd);

        for (int i = n - 2; i >= 0; i--) {
            double s = 0;
            for (int j = i + 1; j < n; j++) {
                s += matrix.getFieldValue(i, j) * results.get(j);
            }
            double valueToSetToResults = (matrix.getFieldValue(i, n) - s) /
                    matrix.getFieldValue(i, i);
            results.set(i, valueToSetToResults);
        }
        return results;
    }
}
