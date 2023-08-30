package org.example.aop.dsa;

/**
 * @author = mbalaji on 30-08-2023
 * @project = spring-aop
 */

import java.util.Scanner;

/**
 * A robot is moving on a grid of size n*m. In one move, it moves one step
 * right or one step down. Some cells of the grid have landmines, so the
 * robot has to avoid them. The grid is given as an array of size n*m, grid[n]
 * [m], where grid[i][j] = 0 means that the cell has a landmine, and grid[i][j]
 * Question - 28
 * Avoiding Landmines
 * SCORE: 100 points
 * Dynamic Programming Hard Algorithms Data Structures Problem Solving
 * Theme: Automotive Interviewer Guidelines
 * 9/16
 * = 1 means the cell is safe to step upon. Find the number of ways in
 * which the robot can reach the bottom-right most cell, grid[n-1][m-
 * 1], starting from the top-left most cell, grid[0][0]. The number can be
 * large, so return the value modulo (10 +7).
 * Example
 * grid = [1, 1, 1], [1, 0, 1], [1, 1, 1]
 * The grid has a landmine at grid[1][1] = 0. There are two possible safe
 * ways to go from grid[0][0] to grid[2][2], as shown in the figure.
 * <p>
 * 1 1 1    ||  1  1    || || ||
 * 1 0 1    ||  0  1    1   0 ||
 * 1 1 1    || || ||    1   0 ||
 * <p>
 * Therefore, return 2 modulo (10 +7) = 2 as the answer
 * <p>
 * Constraints
 * 1 ≤ n, m ≤ 1000
 * Each cell in the grid contains either a 0 or a 1.
 * <p>
 * The first line contains an integer n, the number of rows in the matrix
 * warehouse.
 * The next line contains an integer m, the number of columns in the
 * matrix warehouse.
 * The next n lines each contain a string grid[i] where 0 ≤ i < n and
 * |grid[i]| = m.
 */
public class AvoidLandmines {

    public static void main(String[] args) {
        /*Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter no of rows");
        int rows = sc1.nextInt();
        sc1 = new Scanner(System.in);
        System.out.println("Enter no of columns");
        int columns = sc1.nextInt();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sc1 = new Scanner(System.in);
                System.out.println("Enter the value for " + i + " " + j);
                matrix[i][j] = sc1.nextInt();
            }
        }*/
        int rows = 3;
        int columns = 4;
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        System.out.println(matrix.length);
        solveMaze(matrix, rows, columns);

    }

    /**
     *
     * @param mat
     * @param i
     * @param j
     * @param row
     * @param column
     * @return
     */
    static boolean isSafe(int[][] mat, int i, int j, int row, int column) {
        return i >= 0 && i < row
                && j >= 0 && j < column
                && mat[i][j] == 1;
    }

    static void printSolution(int sol[][])
    {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol.length; j++)
                System.out.print(
                        " " + sol[i][j] + " ");
            System.out.println();
        }
    }

    static boolean solveMaze(int[][] maze, int x, int y) {
        int[][] result = new int[x][y];
        if(solveMazeUtil(maze, 0, 0, result) == false) {
            System.out.println("Solution doesn't exist");
            return false;
        }
        printSolution(result);
        return true;
    }

    /**
     * if mat[x][y] is goal return true
     *
     * @param mat
     * @param x
     * @param y
     * @param result
     * @return
     */
    static boolean solveMazeUtil(int[][] mat, int x, int y, int[][] result) {
        /**
         * Goal
         */
        if(x == mat.length - 1 && y == mat.length - 1 && mat[x][y] == 1 && result[x][y] == 1) {
            return true;
        }

        if(isSafe(mat, x, y, mat.length, mat.length)) {

            /**
             * Path is already in solution
             */
            if (result[x][y] == 1) {
                return false;
            }

            result[x][y] = 1;

            /**
             * Move horizontally
             */
            if(x < mat.length-1) {
                if (solveMazeUtil(mat, x + 1, y, result)) {
                    return true;
                }
            }
            /**
             * Move Vertically
             */
            if(y < mat.length-1) {
                if (solveMazeUtil(mat, x, y + 1, result)) {
                    return true;
                }
            }

        /* If none of the above movements works then
            BACKTRACK: unmark x, y as part of solution
            path */
            if(x != mat.length -1 && y != mat.length -1 ) {
                result[x][y] = 0;
                return false;
            }
        }
        return true;
    }


}
