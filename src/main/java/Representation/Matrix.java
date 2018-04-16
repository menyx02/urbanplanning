package Representation;

import Exceptions.MatrixOperationException;
import Model.Coordinates;
import Model.Point;
import Utils.Randomizer;
import javafx.util.Pair;
import org.geonames.BoundingBox;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by edoyle on 1/23/18.
 */
public class Matrix {
    private List<List<Double>> entries;

    Matrix(List<Coordinates> values) {
        entries = new ArrayList<List<Double>>(values.size());
        for (int i = 0; i < values.size(); ++i) {
            final Coordinates temp = values.get(i);
            entries.add(new ArrayList<Double>(){{add(temp.getLongitude()); add(temp.getLatitude());}});
        }
    }

    public int rows() {
        return entries.size();
    }

    public int cols() {
        if(rows() == 0 || entries.get(0) == null){
            return 0;
        }
        return entries.get(0).size();
    }

    private Matrix(int rows) {
        entries = new ArrayList<List<Double>>(rows);
    }

    public Matrix(final Coordinates coordinate) {
        entries = new ArrayList<List<Double>>(2);
        entries.add(new ArrayList<Double>(){{add(coordinate.getLongitude()); add(coordinate.getLatitude());}});
    }


    protected void mutate(Double alpha) {
        if (Randomizer.randomProbability() < alpha) {
            int startRow;
            int endRow;
            int startCol;
            int endCol;
            if(rows() > 0) {
                startRow = rows() != 1 ? Randomizer.randomInteger(0, rows() - 1) : 0;
                endRow = rows() != 1 ? Randomizer.randomInteger(startRow, rows() - 1) : 0;
                startCol = Randomizer.randomInteger(0, cols() - 1);
                endCol = Randomizer.randomInteger(startCol, cols() - 1);
                for (int i = startRow; i <= endRow; ++i) {
                    for (int j = startCol; j <= endCol; ++j) {
                        entries.get(i).set(j, Randomizer.generateGaussianValue(entries.get(i).get(j)));
                    }
                }
            }
            else {
                System.out.println("No rows");
            }
        }
    }

    protected Matrix generateMutatedCopy(Double alpha) {
        Matrix copy = new Matrix(rows());
        RotationType type = RotationType.NONE;
        if (Randomizer.randomProbability() < alpha) {
            type = RotationType.values()[Randomizer.randomInteger(1,3)];
        }
        for (int i = 0; i < rows(); ++i) {
            copy.entries.add(entries.get(i));
        }
        type.rotate(copy.entries, rows(), cols());
        copy.mutate(alpha);
        return copy;
    }

    private Matrix deepCopy() {
        Matrix copy = new Matrix(rows());
        for (int i = 0; i < rows(); ++i) {
            entries.set(i, copy.entries.get(i));
        }
        return copy;
    }

    public Matrix difference(Matrix other) {
        Matrix result;
        if(rows() == other.rows() && cols() == other.cols()) {
            result = new Matrix(rows());
            subtract(other, result);
        }
        else {
            result =  new Matrix( rows() > other.rows() ? rows() : other.rows());
            mismatchedSubtract(other, result);
        }
      return null;
    }

    private void mismatchedSubtract(Matrix other, Matrix result) {
    }

    private void subtract(Matrix other, Matrix result) {
       for (int i = 0; i < rows(); ++i) {
            for (int j = 0; j < cols(); ++j) {
                result.entries.get(i).set(j, entries.get(i).get(j) - other.entries.get(i).get(j));
            }
       }
    }

    public double trace() throws MatrixOperationException {
        if (entries.size() != entries.get(0).size()) {
            throw new MatrixOperationException(String.format("Invalid Matrix Dimensions %dx%d! Must be nxn", rows(), cols()));
        }
        double total = 0;
        for (int i = 0; i < rows(); ++i) {
            total += entries.get(i).get(i);
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
       if (rows() != other.cols() || cols()!= other.rows()) {
            throw new MatrixOperationException("Invalid Matrix Dimensions!");
        }
        Matrix product = new Matrix(rows());
        for(int i = 0; i < rows(); i++) {
            for(int j = 0; j < other.cols(); j++) {
                for(int k = 0; k < cols(); k++) {
                    product.entries.get(i).set(j, product.entries.get(i).get(j)
                            + entries.get(i).get(k) * other.entries.get(k).get(j));
                }
            }
        }
        return product;
    }

    private void transpose() {
        List<List<Double>> temp = new ArrayList<List<Double>>(cols());
        for (int i = 0; i < rows(); ++i) {
            for (int j = 0; j < cols(); ++j) {
                temp.get(j).set(i, entries.get(i).get(j));
            }
        }
        entries = temp;
    }

   public void normalize(BoundingBox boundary) {
        double yRange = boundary.getNorth() - boundary.getSouth();
        double xRange = boundary.getWest() - boundary.getEast();
        for (int i = 0; i < rows(); ++i) {
            entries.get(i).set(0, (entries.get(i).get(0) - boundary.getEast()) / xRange);
            entries.get(i).set(1, (entries.get(i).get(1) - boundary.getSouth()) / yRange);
        }
    }

    public Point getEntryAt(int j) {
        return new Point(entries.get(j).get(0), entries.get(j).get(1));
    }

    public void addEntry(final Coordinates coordinates) {
        entries.add(new ArrayList<Double>(){{add(coordinates.getLongitude()); add(coordinates.getLatitude());}});
    }


    interface Rotation {
        void rotate (List<List<Double>> matrix, int rows, int cols);
    }

    enum RotationType implements  Rotation{
        NONE {
            @Override
            public void rotate(List<List<Double>> matrix, int rows, int cols) {
            }
        },
        SINGLE {
            @Override
            public void rotate(List<List<Double>> matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        temp = 1d - matrix.get(i).get(j + 1);
                        matrix.get(i).set(j + 1, matrix.get(i).get(j));
                        matrix.get(i).set(j, temp);
                    }
                }
            }
            },
        DOUBLE {
            @Override
            public void rotate(List<List<Double>> matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        matrix.get(i).set(j + 1, 1d - matrix.get(i).get(j + 1));
                        matrix.get(i).set(j, 1d - matrix.get(i).get(j));
                    }
                }
            }
            },
        TRIPLE {
            @Override
            public void rotate(List<List<Double>> matrix, int rows, int cols) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < (cols - 1); ++j) {
                        double temp;
                        temp = 1d - matrix.get(i).get(j);
                        matrix.get(i).set(j, matrix.get(i).get(j + 1));
                        matrix.get(i).set(j + 1, temp);
                    }
                }
            }
            },
    }

}
