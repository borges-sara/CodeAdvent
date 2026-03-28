package helpers;

import java.util.Objects;
import java.util.function.BinaryOperator;

@SuppressWarnings("unchecked")
public class GenericMatrixHelper<T> {

    private int rows;
    private int cols;
    private Object[][] data;

    public GenericMatrixHelper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Object[rows][cols];
    }

    public GenericMatrixHelper(T[][] input) {
        this.rows = input.length;
        this.cols = input[0].length;
        this.data = new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(input[i], 0, this.data[i], 0, cols);
        }
    }

    public T get(int r, int c) {
        return (T) data[r][c];
    }

    public void set(int r, int c, T value) {
        data[r][c] = value;
    }

    public void setSquare(int x1, int x2, int y1, int y2, T value) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                set(i, j, value);
            }
        }
    }

    public int countCellsWithValue(T value) {
        int counter = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Objects.equals(data[i][j], value)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    public T[] getRow(int r) {
        return (T[]) data[r];
    }

    public T[] getColumn(int c) {
        T[] column = (T[]) new Object[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = (T) data[i][c];
        }
        return column;
    }

    public GenericMatrixHelper<T> transpose() {
        GenericMatrixHelper<T> result = new GenericMatrixHelper<>(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(j, i, get(i, j));
            }
        }

        return result;
    }

    // Generic ADD using lambda
    public GenericMatrixHelper<T> add(GenericMatrixHelper<T> other, BinaryOperator<T> op) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match");
        }

        GenericMatrixHelper<T> result = new GenericMatrixHelper<>(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, op.apply(get(i, j), other.get(i, j)));
            }
        }

        return result;
    }

    // Generic MULTIPLY using lambda
    public GenericMatrixHelper<T> multiply(
            GenericMatrixHelper<T> other,
            BinaryOperator<T> multiplyOp,
            BinaryOperator<T> addOp,
            T zero) {

        if (cols != other.rows) {
            throw new IllegalArgumentException("Invalid matrix dimensions for multiplication");
        }

        GenericMatrixHelper<T> result = new GenericMatrixHelper<>(rows, other.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {

                T sum = zero;

                for (int k = 0; k < cols; k++) {
                    T product = multiplyOp.apply(get(i, k), other.get(k, j));
                    sum = addOp.apply(sum, product);
                }

                result.set(i, j, sum);
            }
        }

        return result;
    }

    public void print() {
        for (Object[] row : data) {
            for (Object value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}