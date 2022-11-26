/*
 * 
 */
package computer;

import java.util.ArrayList;
import piece.Board;
import piece.EnumPlayer;
import piece.Piece;
import screen.JChess;

/**
 *
 * @author tiago
 */

public class Computer {
    
    private final EnumPlayer computerPlayer;

    public Computer(EnumPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    
    public void playComputer(Board board) {
        
        if (board.getPlayer() == this.computerPlayer) {
            int[] move = minimax4(board);
            board.movePiece(board.getPiece(move[1], move[2]), move[3], move[4]);
            JChess.print = true;
        }
    }
    
    private int evaluationFunction(Board board) {
        int e = 0;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board.getPiece(i, j) != null && board.getPiece(i, j).getPlayer() == this.getComputerPlayer()) {
                    e += board.getPiece(i, j).getValuePiece();
                }
                
                else if (board.getPiece(i, j) != null && board.getPiece(i, j).getPlayer() != this.getComputerPlayer()) {
                    e -= board.getPiece(i, j).getValuePiece();
                }
            }
        }
        
        return e;
    }
    
    private int[] findBestMove (Board board) {
        Board boardClone = new Board(board);
        int[] bestMoveData = new int[5];
        int row, column, evaluation, score = -500;
        
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluation = board.getPlayer() == this.computerPlayer? this.evaluationFunction(this.movePieceClone(board, piece, row, column)) : -this.evaluationFunction(this.movePieceClone(board, piece, row, column));
                                if (evaluation > score) {
                                    score = evaluation;
                                    bestMoveData[0] = score;
                                    bestMoveData[1] = i;
                                    bestMoveData[2] = j;
                                    bestMoveData[3] = row;
                                    bestMoveData[4] = column;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return bestMoveData;
    }
    
    private int[] findBestMove(ArrayList<int[]> listMoves, Board board) {
        int[] bestMoveData = new int[5];
        int evaluation, score = -500;
        
        for(int[] move : listMoves) {
            evaluation = this.evaluationFunction(this.movePieceClone(board, board.getPiece(move[1], move[2]), move[3], move[4]));
            if (evaluation > score) {
                score = evaluation;
                bestMoveData[0] = score;
                bestMoveData[1] = move[1];
                bestMoveData[2] = move[2];
                bestMoveData[3] = move[3];
                bestMoveData[4] = move[4];
            }
        }
        return bestMoveData;
    }
    /*
    private int[] minimaxLastMove(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, findBestMove;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                findBestMove = findBestMove(movePieceClone(boardClone, piece, row, column))[0];
                                //System.out.print("Print da medida ");
                                //System.out.println(findBestMove);
                                int[] result = new int[5];
                                result[0] = findBestMove;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        resultList = findMin(resultList);
        
        return this.findBestMove(resultList, board);
    }
    
    */
    
    private int[] minimax(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = evaluationFunction(movePieceClone(boardClone, piece, row, column));
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMin(resultList);
    }  
    
    private int[] minimax2(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = minimax(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMax(resultList);
    } 

    private int[] minimax3(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = minimax2(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMin(resultList);
    }     

    private int[] minimax4(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = minimax3(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMax(resultList);
    }     
    
    private int[] minimax5(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = minimax4(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMin(resultList);
    } 

    private int[] minimax6(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                evaluate = minimax5(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        
        return findMax(resultList);
    }     
    
    private int[] findMin(ArrayList<int[]> list) {
        int min = 500, ind = 0;
        
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] < min) {
                min = list.get(i)[0];
                ind = i;
            }
        }
        if (list.isEmpty()) {
            int [] result = new int[5];
            result[0] = -1000;
            return result;
        }
        return list.get(ind);
    }
   
    private int[] findMax(ArrayList<int[]> list) {
        int max = -500, ind = 0;
        
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] > max) {
                max = list.get(i)[0];
                ind = i;
            }
        }
        
        if (list.isEmpty()) {
            int [] result = new int[5];
            result[0] = 1000;
            return result;
        }
        return list.get(ind);
    }
    
    private Board boardClone (Board board) {
        return new Board(board);
    }
    
    private Board movePieceClone(Board board, Piece piece, int row, int column) {
        Board boardClone = new Board(board);
        boardClone.movePiece(piece.clone(), row, column);
        return boardClone;
    }
    //Getter and Setters 
    public EnumPlayer getComputerPlayer() {
        return computerPlayer;
    }
    
    public EnumPlayer getPlayer() {
        return computerPlayer;
    }
    
    
 
}
