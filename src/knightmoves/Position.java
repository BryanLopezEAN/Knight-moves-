/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightmoves;

/**
 *
 * @author Bryan Lopez Avila
 */
public class Position {

    public int x;
    public int y;
    public int depth;

    Position(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    public boolean equals(Position that) {
        return this.x == that.x && this.y == that.y;
    }
}
