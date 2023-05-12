package com.example.minesweeper;

import java.util.Random;
import java.util.Scanner;

class MineSweeper {
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    int rows;
    int columns;
    int bombCount;
    Cell [][]board;

    void createBoard() {
        bombCount = (rows * columns) / 4;
        board = new Cell[rows][columns];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }

        while (bombCount > 0) {

            int i = random.nextInt(0, rows);
            int j = random.nextInt(0, columns);

            if (!board[i][j].isHasBomb()) {

                board[i][j].setHasBomb(true);
                bombCount--;
            }
        }

        printMap();

        playGame();
    }

    boolean checkWin() {
        int unopenedCells = 0;
        int bombNumber = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j].getContent() == "-") {
                    unopenedCells++;
                }
                if (board[i][j].isHasBomb()) {
                    bombNumber++;
                }

            }

        }
        return bombNumber == unopenedCells;
    }

    void userInput() {
        System.out.println("Welcome to Mine Sweeper!");

        System.out.print("Please enter a rows number :");
        rows = input.nextInt();
        System.out.print("Please enter a column number :");
        columns = input.nextInt();
        createBoard();
    }

    void playGame(){
        boolean finish = false;
        while (!finish) {
            System.out.print("Enter a row: ");
            int rowInput=input.nextInt()-1;

            System.out.print("Enter a column: ");
            int columnInput=input.nextInt()-1;

            int bombCountOfCell = 0;
            if ((rowInput >= rows || columnInput >= columns) || (rowInput < 0 || columnInput < 0))
                System.out.println("Row or column choice is out of bounds. Try again.");
            else if (board[rowInput][columnInput].isHasBomb()) {
                System.out.println("Bomb exploded! You lose!");
                printMineMap();
                finish = true;
            }
            else if (board[rowInput][columnInput].getContent() != "-") {
                System.out.println("This sell is already selected. Try another cell.");
            }
            else {
                //if (board[rowInput][columnInput].getContent() == "-" && !board[rowInput][columnInput].isHasBomb()) {
                for (int i = rowInput - 1; i < rowInput + 2; i++) {
                    for (int j = columnInput - 1; j < columnInput + 2; j++) {

                        if (i >= 0 && j >= 0 && i < rows && j < columns && board[i][j].isHasBomb()) {
                            bombCountOfCell++;
                            //board[rowInput][columnInput].setContent(Integer.toString(bombCountOfCell));
                        }
                        //else {
                        //board[rowInput][columnInput].setContent(Integer.toString(bombCountOfCell));
                        //}
                    }
                }
                board[rowInput][columnInput].setContent(Integer.toString(bombCountOfCell));
                printMap();

                if (checkWin()) {
                    System.out.println("You win!");
                    printMineMap();
                    finish = true;
                }
                //}
            }
        }
    }

    void printMap() {
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                System.out.print(board[i][j].getContent() + " ");
            }
            System.out.println();
        }
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
    }

    void printMineMap() {
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (board[i][j].isHasBomb()){
                    System.out.print("* ");
                }
                else {
                    System.out.print(board[i][j].getContent()+" ");
                }
            }
            System.out.println();
        }
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
    }
}
public class Main {
    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper();
        ms.userInput();
    }
}
