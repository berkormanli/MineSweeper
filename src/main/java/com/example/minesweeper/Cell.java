package com.example.minesweeper;

import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class Cell {
    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    boolean hasBomb;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

    public Cell(){
        this.hasBomb = false;
        this.content = "-";
    }
}








    //attributes-content String- hasBomb Boolean
    //default constructor (content = "-", hasBomb = false
    //get content methodu
    //get hasBomb
    //set content (bombaları gösterecek)
    //set hasBomb




