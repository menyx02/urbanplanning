package Representation;

import Model.Coordinates;
import Utils.Randomizer;
import org.geonames.BoundingBox;

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

    public Matrix difference(Matrix other) {
        Matrix result =  new Matrix(rows, cols);
      /*  for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j)
        }*/
      // todo: complete
      return null;
    }

    public double distance(Matrix other) {
        //todo: complete method
        return 0;
    }

    //private void transpose()

    public void normalize(BoundingBox boundary) {
        double yRange = boundary.getNorth() - boundary.getSouth();
        double xRange = boundary.getWest() - boundary.getEast();
        for (int i = 0; i < rows; ++i) {
            entries[i][0] = (entries[i][0] - boundary.getEast()) / xRange;
            entries[i][1] = (entries[i][1] - boundary.getSouth()) / yRange;
        }
    }


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
