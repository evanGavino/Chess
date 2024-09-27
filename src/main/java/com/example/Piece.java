package com.example;
import javafx.scene.image.Image;

public class Piece {

    Type pieceType;
    String color;
    int xPos;
    int yPos;
    boolean isAlive;
    Image picture;

    public Piece(Type pieceType, String color, int x, int y, boolean isAlive, Image picture) {
        this.pieceType = pieceType;
        this.color = color;
        this.xPos = x;
        this.yPos = y;
        this.isAlive = isAlive;
        this.picture = picture;

    }
    public Piece(Piece clonedPiece) {
        this.pieceType = clonedPiece.pieceType;
        this.color = clonedPiece.color;
        this.xPos = clonedPiece.xPos;
        this.yPos = clonedPiece.yPos;
        this.isAlive = clonedPiece.isAlive;
        this.picture = clonedPiece.picture;
    }

    public Type getType() {
        return this.pieceType;
    }
    public String getColor() {
        return this.color;
    }
    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
    public boolean getIsAlive() {
        return this.isAlive;
    }
    public Image getPicture() {
        return picture;
    }

    public void setType(Type newType) {
        this.pieceType = newType;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setX(int newX) {
        this.xPos = newX;
    }
    public void setY(int newY) {
        this.yPos = newY;
    }
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public void setPicture(Image newPic) {
        this.picture = newPic;
    }

    


    public void test() {
        if (this.pieceType == Type.NONE) {
            System.out.println("success");
        }
    }
}
