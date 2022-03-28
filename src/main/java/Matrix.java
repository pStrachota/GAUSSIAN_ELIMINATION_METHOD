import java.util.ArrayList;
import java.util.List;


public class Matrix {

    private int matrixSize;
    private List<Double> matrix;

    public Matrix(int matrixSize, List<Double> matrix) {
        this.matrixSize = matrixSize;
        this.matrix = matrix;
    }

    public double getFieldValue(int row, int column) {
        return this.matrix.get(row * matrixSize + column + row);
    }

    public void setFieldValue(int row, int column, double value) {
        this.matrix.set(row * matrixSize + column + row, value);
    }

    public List<Double> getRow(int index) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i <= matrixSize; i++) {
            result.add(matrix.get(index * matrixSize + index + i));
        }
        return result;
    }

    public void setRow(int index, List<Double> rowToSet) {
        for (int i = 0; i <= matrixSize; i++) {
            this.matrix.set(index * matrixSize + index + i, rowToSet.get(i));
        }
    }
}
