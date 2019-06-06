package coll.Matrix;

import java.util.*;

public class Matrix<T> implements Iterable {

	/**
	 * Construct a Matrix object.
	 *
	 * @param rows. An int that specifies the number of rows.
	 * @param columns. An int that specifies the number of columns.
	 */
	private T[][] matrix;
	//ArrayList<T>[][] grid; //I couldn't decide whether to use T matrix[][] or ArrayList.

	int rows, columns;

	public Matrix(int rows, int columns) {
		this.rows =  rows;
		this.columns = columns;

		matrix = (T[][]) new Object[rows][columns];


	}

	/**
	 * Assigns a value to a given cell, specified by its row, column coordinates.
	 *
	 * @param row. An int for the row index with 0-based indexing.
	 * @param column. An int for the column index with 0-based indexing.
	 * @param value. A generic value to be assigned to the given cell.
	 */
	private T value;
	public void insert(int row, int column, T value) {
		this.value = value;

		matrix[row][column] = value;

	}

	/**
	 * Gets the value at a given cell, specified by its row, column coordinates.
	 * @param row. An int for the row index with 0-based indexing.
	 * @param column. An int for the column index with 0-based indexing.
	 * @return value. A generic value located at the given cell.
	 */
	public T get(int row, int column) {
		return matrix[row][column];
	}

	/**
	 * Gets the total number of cells in the matrix.
	 * @return an int equal to the total number of cells in the matrix.
	 */
	public int size() {


		return rows * columns;

	}

	/**
	 * Converts the matrix to String format.
	 * @return a String representation of the matrix.
	 */
	public String toString() {
        StringBuilder builder = new StringBuilder();

       /* for (T[] columns : matrix)
            builder.append(Arrays.toString(columns)+ "\t").append("\n");

        return builder.toString().trim();*/

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                builder.append(get(i , j));
                if (j + 1 != columns)
                    builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
	}


	/**
	 * Returns an Iterator object for the matrix. The Iterator should follow the
	 * order of row by row. Within each row the order is left to right.
	 * @return an Iterator object for the matrix.
	 */
	public Iterator iterator() {


        return new Iterator<T>() {
            private int iRow = 0;
            private int iColumn = 0;

            @Override
            public boolean hasNext() {
                return !(iRow == rows - 1 && iColumn == columns);
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                if (iColumn == columns) {
                    iRow++;
                    iColumn = 0;
                }

                return matrix[iRow][iColumn++];
            }

        };




	}

}
