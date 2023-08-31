package org.example.aop.dsa;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Count no of possible ways to reach from 0,0 to 3,3
 */
public class Maze {
    public static void main(String[] args) {
//        int count = count(3, 3);
//        System.out.println(count);
//        path("", 3,3);
        /*List<String> path = path("", 3, 3);
        path.stream().forEach(System.out::println);*/
        int[][] maze = {
                {1,1,1},
                {1,0,1},
                {1,1,1},
        };
        int i = countWithObstacles(maze, maze.length, maze[0].length, 0);
        System.out.println(i);
        List<String> strings = pathWithObstacles(maze, "", maze.length, maze[0].length);
        strings.forEach(System.out::println);
    }

    static int count(int r, int c) {
        if (r == 1 || c == 1) {
            return 1;
        }
        int left = count(r - 1, c);
        int right = count(r, c - 1);
        return left + right;
    }

    static void printPath(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);
            return;
        }
        if (r > 1) {
            path(p + "D", r - 1, c);
        }
        if (c > 1) {
            path(p + "R", r, c - 1);
        }
    }

    static List<String> path(String p, int r, int c) {
        List<String> paths = new ArrayList<>();
        if (r == 1 && c == 1) {
            paths.add(p);
            return paths;
        }
        if (r > 1) {
            paths.addAll(path(p + "D", r - 1, c));
        }
        if (c > 1) {
            paths.addAll(path(p + "R", r, c - 1));
        }
        return paths;
    }

    static int countWithObstacles(int[][] maze, int r, int c, int count) {
        if (r == 1 && c == 1) {
            return count + 1;
        }
        if (maze[r - 1][c - 1] == 1) {
            if (r > 1) {
                count = countWithObstacles(maze, r - 1, c, count);
            }
            if (c > 1) {
                count = countWithObstacles(maze, r, c - 1, count);
            }
        }
        return count;
    }

    static List<String> pathWithObstacles(int[][] maze, String p, int r, int c) {
        List<String> paths = new ArrayList<>();
        if (r == 1 && c == 1) {
            paths.add(p);
            return paths;
        }
        if (maze[r - 1][c - 1] == 1) {
            if (r > 1) {
                paths.addAll(pathWithObstacles(maze, p + "D", r - 1, c));
            }
            if (c > 1) {
                paths.addAll(pathWithObstacles(maze, p + "R", r, c - 1));
            }
        }
        return paths;
    }
}
