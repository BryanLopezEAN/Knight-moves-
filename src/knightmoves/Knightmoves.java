/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightmoves;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Bryan Lopez Avila
 */
public class Knightmoves {

    static final Position[][] chessboard = new Position[8][8];
    static Queue<Position> q = new LinkedList<Position>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        populateChessBoard();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ingrese la cantidad de tests:");
        int nTest = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nTest; i++) {
            System.out.println("Ingrese los puntos de partida y los de llegada separados por espacios");
            String positions = sc.nextLine();
            String[] data = positions.split(" ");
            
            if (Integer.parseInt(data[0]) != Integer.parseInt(data[2]) && Integer.parseInt(data[1]) != Integer.parseInt(data[3])) {
                Position start = new Position(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 0);
                Position end = new Position(Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.MAX_VALUE);

                chessboard[0][1] = new Position(start.x, start.y, 0);
                q.add(start);

                while (q.size() != 0)
                {
                    Position pos = q.poll();
                    if (end.equals(pos)) {
                        Iterable<Position> path = getShortestPath(start, end);
                        System.out.println("Minimo de movimientos: " + pos.depth);
                    } else {
                        bfs(pos, ++pos.depth);
                    }
                }
            }else{
                System.out.println("Minimo de movimientos: 0");
            }
        }
    }

    private static void bfs(Position current, int depth) {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                Position next = new Position(current.x + i, current.y + j, depth);
                if (inRange(next.x, next.y)) {
                    if (current.equals(next)) {
                        continue;
                    }
                    if (isValid(current, next)) {
                        Position position = chessboard[next.x][next.y];
                        if (position.depth > depth) {
                            chessboard[current.x + i][current.y + j] = new Position(current.x, current.y, depth);
                            q.add(next);
                        }
                    }
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    public static boolean isValid(Position current, Position next) {
        int deltaR = next.x - current.x;
        int deltaC = next.y - current.y;
        return 5 == deltaR * deltaR + deltaC * deltaC;
    }

    private static void populateChessBoard() {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length; j++) {
                chessboard[i][j] = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
    }

    private static Iterable getShortestPath(Position start, Position end) {
        Stack<Position> path = new Stack<Position>();
        Position current = chessboard[end.x][end.y];
        while (!current.equals(start)) {
            path.add(current);
            current = chessboard[current.x][current.y];
        }
        path.add(new Position(start.x, start.y, 0));
        return path;
    }
}
