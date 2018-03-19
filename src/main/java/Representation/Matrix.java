package Representation;

import Exceptions.MatrixOperationException;
import Model.Coordinates;
import Model.Point;
import Utils.Randomizer;
//import org.geonames.BoundingBox;

import java.util.List;
import java.util.Random;

/**
 * Created by edoyle on 1/23/18.
 */
public class Matrix {
    private int rows;
    private int cols;
    private double entries[][];

    Matrix(List<Coordinates> values) {
        rows = values.size();
        cols = 2;
        entries = new double[rows][cols];
        for (int i = 0; i < values.size(); ++i) {
            Coordinates temp = values.get(i);
            entries[i][0] = temp.getLongitude();
            entries[i][1] = temp.getLatitude();
        }
    }

    private Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        entries = new double[rows][cols];
    }

    public int getNumRows() {return this.rows;}

    public Point getEntryAt(int row) {
        return new Point(entries[row][0], entries[row][1]);
    }


    protected void mutate(Double alpha) {
        if (Randomizer.randomProbability() < alpha) {
            int startRow;
            int endRow;
            int startCol;
            int endCol;
            startRow = Randomizer.randomInteger(0, rows - 1);
            endRow = Randomizer.randomInteger(startRow, rows - 1);
            startCol = Randomizer.randomInteger(0, cols - 1);
            endCol = Randomizer.randomInteger(startCol, cols - 1);
            for (int i = startRow; i <= endRow; ++i) {
                for (int j = startCol; j <= endCol; ++j) {
                    entries[i][j] = Randomizer.generateGaussianValue(entries[i][j]);
                }
            }
        }
    }

    protected Matrix generateMutatedCopy(Double alpha) {
        Matrix copy = new Matrix(rows, cols);
        RotationType type = RotationType.NONE;
        if (Randomizer.randomProbability() < alpha) {
            type = RotationType.values()[Randomizer.randomInteger(1,3)];
        }
        for (int i = 0; i < rows; ++i) {
            System.arraycopy(entries[i], 0, copy.entries[i], 0, cols);
        }
        type.rotate(copy.entries, rows, cols);
        copy.mutate(alpha);
        return copy;
    }

    private Matrix deepCopy() {
        Matrix copy = new Matrix(rows, cols);
        for (int i = 0; i < rows; ++i) {
            System.arraycopy(entries[i], 0, copy.entries[i], 0, cols);
        }
        return copy;
    }

    public Matrix difference(Matrix other) {
        Matrix result;
        if(rows == other.rows && cols == other.cols) {
            result = new Matrix(rows, cols);
            subtract(other, result);
        }
        else {
            result =  new Matrix( rows > other.rows ? rows : other.rows, cols);
            mismatchedSubtract(other, result);
        }
      return null;
    }

    private void mismatchedSubtract(Matrix other, Matrix result) {
    }

    private void subtract(Matrix other, Matrix result) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                result.entries[i][j] = entries[i][j] - other.entries[i][j];
            }
        }
    }

    public double trace() throws MatrixOperationException {
        if (rows != cols) {
            throw new MatrixOperationException(String.format("Invalid Matrix Dimensions %dx%d! Must be nxn", rows, cols));
        }
        double total = 0;
        for (int i = 0; i < rows; ++i) {
            total += entries[i][i];
        }
        return total;
    }

    public double distance(Matrix other) throws MatrixOperationException {
        Matrix normal = difference(other);
        Matrix transposed = normal.deepCopy();
        transposed.transpose();
        return Math.sqrt(normal.multiply(transposed).trace());
    }

    private Matrix multiply(Matrix other) throws MatrixOperationException {
        if (rows != other.cols || cols != other.rows) {
            throw new MatrixOperationException("Invalid Matrix Dimensions!");
        }
        Matrix product = new Matrix(rows, rows);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < other.cols; j++) {
                for(int k = 0; k < cols; k++) {
                    product.entries[i][j] += entries[i][k] * other.entries[k][j];
                }
            }
        }
        return product;
    }

    private void transpose() {
        int index;
        double temp [][] = new double[cols][rows];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                temp[j][i] = entries[i][j];
            }
        }
        index = cols;
        entries = temp;
        cols = rows;
        rows = index;
    }

//    public void normalize(BoundingBox boundary) {
//        double yRange = boundary.getNorth() - boundary.getSouth();
//        double xRange = boundary.getWest() - boundary.getEast();
//        for (int i = 0; i < rows; ++i) {
//            entries[i][0] = (entries[i][0] - boundary.getEast()) / xRange;
//            entries[i][1] = (entries[i][1] - boundary.getSouth()) / yRange;
//        }
//    }


    interface Rotation {
        void rotate (double [][] matrix, int rows, int cols);
    }

    enum RotationType implements  Rotation{
        NONE {
            @Override
            public void rotate(double[][] matrix, int rows, int cols) {
            }
        },
        SINGLE {
            @Override
            public void rotate(double[][] matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        temp = 1 - matrix[i][j + 1];
                        matrix[i][j + 1] = matrix[i][j];
                        matrix[i][j] = temp;
                    }
                }
            }
            },
        DOUBLE {
            @Override
            public void rotate(double[][] matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        matrix[i][j + 1] = 1 - matrix[i][j + 1];
                        matrix[i][j] = 1 - matrix[i][j];
                    }
                }
            }
            },
        TRIPLE {
            @Override
            public void rotate(double[][] matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        temp = 1 - matrix[i][j];
                        matrix[i][j] = matrix[i][j + 1];
                        matrix[i][j + 1] = temp;
                    }
                }
            }
            },
    }

}
