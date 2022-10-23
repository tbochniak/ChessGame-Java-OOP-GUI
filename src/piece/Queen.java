/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Queen extends Piece{
    
    public Queen(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wQueen.png" : "src/figs/bQueen.png"));
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        return true;
    }

    @Override
    public ArrayList<Integer[]> possibleMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        return moves;
    }
 
}