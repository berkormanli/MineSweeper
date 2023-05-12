package com.example.minesweeper;

import java.util.Random;
import java.util.Scanner;

class MineSweeper {

    int rows;
    int column;
    int bombCount;
    Cell [][] board;
    Scanner input = new Scanner(System.in);
    Random random = new Random();

    void createBoard () {
        bombCount = (rows * column) / 4;
        board = new Cell[rows][column];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board = new Cell[i][j];
            }
        }

        while (bombCount > 0) {
            int i = random.nextInt(0, rows);
            int j = random.nextInt(0, column);

            if (!board[i][j].isHasBomb()) {
                board[i][j].setHasBomb(true);
                bombCount--;

            }
        }
        printMap();

        playGame();
    }

    boolean checkWin(){
        int unOpenedCells = 0;
        int bombNumber = 0;

        for(int i =0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if( board[i][j].getContent() == "-"){
                    unOpenedCells++;
                }
                if(board[i][j].isHasBomb()){
                    bombNumber++;
                }
            }
        }

        return unOpenedCells == bombNumber;
    }

    void userInput(){

        System.out.println("Welcome to the mine sweeper!");

        System.out.print("Please enter a rows number : ");
        rows = input.nextInt();

        System.out.print("Please enter a column number : ");
        column = input.nextInt();
        createBoard();
    }
    void playGame(){
        boolean finish = false;

        while (!finish){
            System.out.print("Please enter a rows :");
            int newRows = input.nextInt();

            System.out.print("Please enter a column : ");
            int newColumn = input.nextInt();

            int bombCountOfCell = 0;
            if((newRows > rows || newColumn > column) && (newRows < 0 || newColumn < 0) ){
                System.out.println("Row or column choice is out of bounds. Try again.");
            } else if (board[newRows][newColumn].hasBomb) {
                System.out.println("Bomb exploded! You lose!");
                printMineMap();
                finish = true;
            } else if (board[newRows][newColumn].getContent() != "-") {
                System.out.println("This sell is already selected. Try another cell.");
            }
            else{
                for(int i = newRows-1; i < newRows +2; i++){
                    for (int j = newColumn -1; j < newColumn +2; j++ ){
                        if((i >= 0 && j >= 0) && (i < rows && j < column) && board[i][j].hasBomb ){
                            bombCountOfCell++;
                        }
                    }
                }
                board[newRows][newColumn].setContent(Integer.toString(bombCountOfCell));

                if (checkWin()){
                    System.out.println("You win!");
                    printMineMap();
                    finish = true;
                }
            }
        }
    }

    public void printMap(){
        System.out.println("1");
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                System.out.println(board[i][j].getContent() + " ");
            }
            System.out.println();
        }
        System.out.println("2");


    }

    public void printMineMap(){
        System.out.println(" ");
        for (int i = 0; i<board.length; i++){
            for(int j = 0; j< board.length; j++){
                if(board[i][j].hasBomb){
                    System.out.println("* ");
                }
                else {
                    System.out.println("- ");
                }
                System.out.println();
            }
            System.out.println("2");
        }

    }
}
public class Main {
    public void main(String[] args) {
        MineSweeper ms = new MineSweeper();
        ms.userInput();
    }
}
