package helpers;

public class MatrixHelper {

    private int rows;
    private int cols;
    private double[][] data;

    public MatrixHelper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public MatrixHelper(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public double get(int r, int c) {
        return data[r][c];
    }

    public void set(int r, int c, double value) {
        data[r][c] = value;
    }

    public void setSquare(int x1, int x2, int y1, int y2, double value){
        for(var i = y1; i <= y2; i++){
            for(var j = x1; j <= x2; j++){
                set(i, j, value);
            }
        }
    }

    public void toggleSquare(int x1, int x2, int y1, int y2){
        for(var i = y1; i <= y2; i++){
            for(var j = x1; j <= x2; j++){
                var value = get(i, j) * -1;
                set(i, j, value);
            }
        }
    }

    public int countCellsWithValue(double value){
        var counter = 0;
        for(var i = 0; i < rows; i++){
            for(var j = 0; j < cols; j++){
                if(data[i][j] == value){
                    counter ++;
                }
            }
        }

        return counter;
    }

    public double[] getRow(int r) {
        return data[r];
    }

    public double[] getColumn(int c) {
        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = data[i][c];
        }
        return column;
    }

    public MatrixHelper transpose() {
        MatrixHelper result = new MatrixHelper(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(j, i, data[i][j]);
            }
        }

        return result;
    }

    public MatrixHelper add(MatrixHelper other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match");
        }

        MatrixHelper result = new MatrixHelper(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, data[i][j] + other.data[i][j]);
            }
        }

        return result;
    }

    public MatrixHelper multiply(MatrixHelper other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Invalid matrix dimensions for multiplication");
        }

        MatrixHelper result = new MatrixHelper(rows, other.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += data[i][k] * other.data[k][j];
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    public void print() {
        for (double[] row : data) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
