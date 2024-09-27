package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;



import java.io.FileNotFoundException;

import javafx.event.*;

import java.util.ArrayList;
import java.util.Optional;

public class App extends Application {

    Scene scene;


    private boolean whiteTurn;
    private TilePane tilePane;
    private Button[][] buttonArray;
    private ImageView[][] viewArray;
    private Piece[][] pieceArray;
    private Image blackPawn;
    private Image blackRook;
    private Image blackBishop;
    private Image blackKnight;
    private Image blackQueen;
    private Image blackKing;
    private Image whitePawn;
    private Image whiteRook;
    private Image whiteBishop;
    private Image whiteKnight;
    private Image whiteQueen;
    private Image whiteKing;
    private final Piece none;
    private Piece currentlyClicked;
    private Stage primaryStage;
    private ArrayList<Integer> legalMoves;

    private boolean whiteWins;
    

    public App() {
        
        blackPawn = new Image("https://upload.wikimedia.org/wikipedia/commons/c/cd/Chess_pdt60.png", 60, 60, true, true);
        blackRook = new Image("https://upload.wikimedia.org/wikipedia/commons/a/a0/Chess_rdt60.png", 60, 60, true, true);
        blackBishop = new Image("https://upload.wikimedia.org/wikipedia/commons/8/81/Chess_bdt60.png", 60, 60, true, true);
        blackKnight = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Chess_ndt60.png", 60, 60, true, true);
        blackQueen = new Image("https://upload.wikimedia.org/wikipedia/commons/a/af/Chess_qdt60.png", 60, 60, true, true);
        blackKing = new Image("https://upload.wikimedia.org/wikipedia/commons/e/e3/Chess_kdt60.png", 60, 60, true, true);
        whitePawn = new Image("https://upload.wikimedia.org/wikipedia/commons/0/04/Chess_plt60.png", 60, 60, true, true);
        whiteRook = new Image("https://upload.wikimedia.org/wikipedia/commons/5/5c/Chess_rlt60.png", 60, 60, true, true);
        whiteBishop = new Image("https://upload.wikimedia.org/wikipedia/commons/9/9b/Chess_blt60.png", 60, 60, true, true);
        whiteKnight = new Image("https://upload.wikimedia.org/wikipedia/commons/2/28/Chess_nlt60.png", 60, 60, true, true);
        whiteQueen = new Image("https://upload.wikimedia.org/wikipedia/commons/4/49/Chess_qlt60.png", 60, 60, true, true);
        whiteKing = new Image("https://upload.wikimedia.org/wikipedia/commons/3/3b/Chess_klt60.png", 60, 60, true, true);
        none = new Piece(Type.NONE, "none", 5, 0, false, null);
        tilePane = new TilePane(0,0);

        whiteTurn = true;
        
        
        buttonArray = new Button[8][8];
        viewArray = new ImageView[8][8];
        
        
              
        pieceArray = new Piece[][] {{new Piece(Type.ROOK, "black", 0, 0, true, blackRook),new Piece(Type.KNIGHT, "black", 0, 1, true, blackKnight),new Piece(Type.BISHOP, "black", 0, 2, true, blackBishop),new Piece(Type.QUEEN, "black", 0, 3, true, blackQueen),new Piece(Type.KING, "black", 0, 4, true, blackKing),new Piece(Type.BISHOP, "black", 0, 5, true, blackBishop),new Piece(Type.KNIGHT, "black", 0, 1, true, blackKnight),new Piece(Type.ROOK, "black", 0, 0, true, blackRook)},
                                    {new Piece(Type.PAWN, "black", 1, 0, true, blackPawn),new Piece(Type.PAWN, "black", 1, 1, true, blackPawn),new Piece(Type.PAWN, "black", 1, 2, true, blackPawn),new Piece(Type.PAWN, "black", 1, 3, true, blackPawn),new Piece(Type.PAWN, "black", 1, 4, true, blackPawn),new Piece(Type.PAWN, "black", 1, 5, true, blackPawn),new Piece(Type.PAWN, "black", 1, 6, true, blackPawn),new Piece(Type.PAWN, "black", 1, 7, true, blackPawn)},
                                    {new Piece(Type.NONE, "none", 2, 0, false, null),new Piece(Type.NONE, "none", 2, 1, false, null),new Piece(Type.NONE, "none", 2, 2, false, null),new Piece(Type.NONE, "none", 2, 3, false, null),new Piece(Type.NONE, "none", 2, 4, false, null),new Piece(Type.NONE, "none", 2, 5, false, null),new Piece(Type.NONE, "none", 2, 6, false, null),new Piece(Type.NONE, "none", 2, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 3, 0, false, null),new Piece(Type.NONE, "none", 3, 1, false, null),new Piece(Type.NONE, "none", 3, 2, false, null),new Piece(Type.NONE, "none", 3, 3, false, null),new Piece(Type.NONE, "none", 3, 4, false, null),new Piece(Type.NONE, "none", 3, 5, false, null),new Piece(Type.NONE, "none", 3, 6, false, null),new Piece(Type.NONE, "none", 3, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 4, 0, false, null),new Piece(Type.NONE, "none", 4, 1, false, null),new Piece(Type.NONE, "none", 4, 2, false, null),new Piece(Type.NONE, "none", 4, 3, false, null),new Piece(Type.NONE, "none", 4, 4, false, null),new Piece(Type.NONE, "none", 4, 5, false, null),new Piece(Type.NONE, "none", 4, 6, false, null),new Piece(Type.NONE, "none", 4, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 5, 0, false, null),new Piece(Type.NONE, "none", 5, 1, false, null),new Piece(Type.NONE, "none", 5, 2, false, null),new Piece(Type.NONE, "none", 5, 3, false, null),new Piece(Type.NONE, "none", 5, 4, false, null),new Piece(Type.NONE, "none", 5, 5, false, null),new Piece(Type.NONE, "none", 5, 6, false, null),new Piece(Type.NONE, "none", 5, 7, false, null)},
                                    {new Piece(Type.PAWN, "white", 6, 0, true, whitePawn),new Piece(Type.PAWN, "white", 6, 1, true, whitePawn),new Piece(Type.PAWN, "white", 6, 2, true, whitePawn),new Piece(Type.PAWN, "white", 6, 3, true, whitePawn),new Piece(Type.PAWN, "white", 6, 4, true, whitePawn),new Piece(Type.PAWN, "white", 6, 5, true, whitePawn),new Piece(Type.PAWN, "white", 6, 6, true, whitePawn),new Piece(Type.PAWN, "white", 6, 7, true, whitePawn)},
                                    {new Piece(Type.ROOK, "white", 7, 0, true, whiteRook),new Piece(Type.KNIGHT, "white", 7, 1, true, whiteKnight),new Piece(Type.BISHOP, "white", 7, 2, true, whiteBishop),new Piece(Type.QUEEN, "white", 7, 3, true, whiteQueen),new Piece(Type.KING, "white", 7, 4, true, whiteKing),new Piece(Type.BISHOP, "white", 7, 5, true, whiteBishop),new Piece(Type.KNIGHT, "white", 7, 6, true, whiteKnight),new Piece(Type.ROOK, "white", 7, 7, true, whiteRook)}};
     
        
        
        currentlyClicked = null;
    }

    private void restartHelper() {
        whiteTurn = true;
        pieceArray = null;
        pieceArray = new Piece[][] {{new Piece(Type.ROOK, "black", 0, 0, true, blackRook),new Piece(Type.KNIGHT, "black", 0, 1, true, blackKnight),new Piece(Type.BISHOP, "black", 0, 2, true, blackBishop),new Piece(Type.QUEEN, "black", 0, 3, true, blackQueen),new Piece(Type.KING, "black", 0, 4, true, blackKing),new Piece(Type.BISHOP, "black", 0, 5, true, blackBishop),new Piece(Type.KNIGHT, "black", 0, 1, true, blackKnight),new Piece(Type.ROOK, "black", 0, 0, true, blackRook)},
                                    {new Piece(Type.PAWN, "black", 1, 0, true, blackPawn),new Piece(Type.PAWN, "black", 1, 1, true, blackPawn),new Piece(Type.PAWN, "black", 1, 2, true, blackPawn),new Piece(Type.PAWN, "black", 1, 3, true, blackPawn),new Piece(Type.PAWN, "black", 1, 4, true, blackPawn),new Piece(Type.PAWN, "black", 1, 5, true, blackPawn),new Piece(Type.PAWN, "black", 1, 6, true, blackPawn),new Piece(Type.PAWN, "black", 1, 7, true, blackPawn)},
                                    {new Piece(Type.NONE, "none", 2, 0, false, null),new Piece(Type.NONE, "none", 2, 1, false, null),new Piece(Type.NONE, "none", 2, 2, false, null),new Piece(Type.NONE, "none", 2, 3, false, null),new Piece(Type.NONE, "none", 2, 4, false, null),new Piece(Type.NONE, "none", 2, 5, false, null),new Piece(Type.NONE, "none", 2, 6, false, null),new Piece(Type.NONE, "none", 2, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 3, 0, false, null),new Piece(Type.NONE, "none", 3, 1, false, null),new Piece(Type.NONE, "none", 3, 2, false, null),new Piece(Type.NONE, "none", 3, 3, false, null),new Piece(Type.NONE, "none", 3, 4, false, null),new Piece(Type.NONE, "none", 3, 5, false, null),new Piece(Type.NONE, "none", 3, 6, false, null),new Piece(Type.NONE, "none", 3, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 4, 0, false, null),new Piece(Type.NONE, "none", 4, 1, false, null),new Piece(Type.NONE, "none", 4, 2, false, null),new Piece(Type.NONE, "none", 4, 3, false, null),new Piece(Type.NONE, "none", 4, 4, false, null),new Piece(Type.NONE, "none", 4, 5, false, null),new Piece(Type.NONE, "none", 4, 6, false, null),new Piece(Type.NONE, "none", 4, 7, false, null)},
                                    {new Piece(Type.NONE, "none", 5, 0, false, null),new Piece(Type.NONE, "none", 5, 1, false, null),new Piece(Type.NONE, "none", 5, 2, false, null),new Piece(Type.NONE, "none", 5, 3, false, null),new Piece(Type.NONE, "none", 5, 4, false, null),new Piece(Type.NONE, "none", 5, 5, false, null),new Piece(Type.NONE, "none", 5, 6, false, null),new Piece(Type.NONE, "none", 5, 7, false, null)},
                                    {new Piece(Type.PAWN, "white", 6, 0, true, whitePawn),new Piece(Type.PAWN, "white", 6, 1, true, whitePawn),new Piece(Type.PAWN, "white", 6, 2, true, whitePawn),new Piece(Type.PAWN, "white", 6, 3, true, whitePawn),new Piece(Type.PAWN, "white", 6, 4, true, whitePawn),new Piece(Type.PAWN, "white", 6, 5, true, whitePawn),new Piece(Type.PAWN, "white", 6, 6, true, whitePawn),new Piece(Type.PAWN, "white", 6, 7, true, whitePawn)},
                                    {new Piece(Type.ROOK, "white", 7, 0, true, whiteRook),new Piece(Type.KNIGHT, "white", 7, 1, true, whiteKnight),new Piece(Type.BISHOP, "white", 7, 2, true, whiteBishop),new Piece(Type.QUEEN, "white", 7, 3, true, whiteQueen),new Piece(Type.KING, "white", 7, 4, true, whiteKing),new Piece(Type.BISHOP, "white", 7, 5, true, whiteBishop),new Piece(Type.KNIGHT, "white", 7, 6, true, whiteKnight),new Piece(Type.ROOK, "white", 7, 7, true, whiteRook)}};
        
        
       

        
        for (int i = 0; i < 8; i++) {            
            for (int j = 0; j < 8; j++) {                              
                buttonArray[i][j].setGraphic(new ImageView(pieceArray[i][j].getPicture()));             
                buttonArray[i][j].setDisable(false);
            }
        }

        
        
    }

    @Override
    public void init() throws FileNotFoundException{
        
        
        EventHandler<ActionEvent> clickEvent = (ActionEvent e) -> {

            buttonHelper(e);
        };

        tilePane.setPrefColumns(8);
        tilePane.setPrefRows(8);
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                
                Button button = new Button();
                button.setPrefSize(76,76);
                
                
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        button.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0;");
                    }
                    else {
                        button.setStyle("-fx-background-color: #325e33; -fx-background-radius: 0; -fx-border-width: 0;");
                    }
                } else {
                    if (j % 2 == 0) {
                        button.setStyle("-fx-background-color: #325e33; -fx-background-radius: 0; -fx-border-width: 0;");
                    } else {
                        button.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0;");
                    }
                }
                
                tilePane.getChildren().add(button);
                buttonArray[i][j] = button;
                button.setGraphic(new ImageView(pieceArray[i][j].getPicture()));

                

                buttonArray[i][j].setOnAction(clickEvent);
            }
        }

        tilePane.setAlignment(Pos.CENTER);
        


        
    }

    private void buttonHelper(ActionEvent e) {
        boolean isGameOver = false;
        boolean buttonFound = false;
        Button source = (Button) e.getSource();
        for (int i = 0; i < buttonArray.length && !buttonFound;i++) {
            
            for (int j = 0; j < buttonArray[i].length && !buttonFound;j++) {       
                if (source == buttonArray[i][j]) {
                    source = buttonArray[i][j];
                    buttonFound = true;
                    System.out.println(pieceArray[i][j].getType());
                    if (currentlyClicked == null){
                        if (whiteTurn) {
                            if (pieceArray[i][j].getType() != Type.NONE && !pieceArray[i][j].getColor().equals("black")) {
                                currentlyClicked = pieceArray[i][j];
                                legalMoves = checkLegalMoves(currentlyClicked.getType(), currentlyClicked.getColor(), i, j);
                                System.out.println(legalMoves);
                            }    
                        } else {
                            if (pieceArray[i][j].getType() != Type.NONE && !pieceArray[i][j].getColor().equals("white")) {
                                currentlyClicked = pieceArray[i][j];
                                legalMoves = checkLegalMoves(currentlyClicked.getType(), currentlyClicked.getColor(), i, j);
                                System.out.println(legalMoves);
                            }
                        
                        }

                    } else {
                        //System.out.println(currentlyClicked.getX() + ", " + currentlyClicked.getY());
                        if (currentlyClicked.getX() % 2 == 0) {
                            if (currentlyClicked.getY() % 2 == 0) {
                                buttonArray[currentlyClicked.getX()][currentlyClicked.getY()].setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0;");
                            }
                            else {
                                buttonArray[currentlyClicked.getX()][currentlyClicked.getY()].setStyle("-fx-background-color: #325e33; -fx-background-radius: 0; -fx-border-width: 0;");
                            }
                        } else {
                            if (currentlyClicked.getY() % 2 == 0) {
                                buttonArray[currentlyClicked.getX()][currentlyClicked.getY()].setStyle("-fx-background-color: #325e33; -fx-background-radius: 0; -fx-border-width: 0;");
                            } else {
                                buttonArray[currentlyClicked.getX()][currentlyClicked.getY()].setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0;");
                            }
                        }
                        if (currentlyClicked != pieceArray[i][j]) {
                            boolean isMoveLegal = false;
                            int counter = 0;
                            while (counter < legalMoves.size() && !isMoveLegal) {
                                if (legalMoves.get(counter) == -1) {
                                    counter++;
                                } else if (i == legalMoves.get(counter) && j == legalMoves.get(counter+1)){
                                    isMoveLegal = true;
                                } else {
                                    counter +=2;
                                }
                                
                                
                            }
                            if (isMoveLegal) {
                                if (pieceArray[i][j].getType() == Type.KING) {
                                    isGameOver = true;
                                }
                                pieceArray[i][j] = currentlyClicked;
                                pieceArray[currentlyClicked.getX()][currentlyClicked.getY()] = none;
                            
                                buttonArray[i][j].setGraphic(new ImageView(pieceArray[i][j].getPicture()));
                                buttonArray[currentlyClicked.getX()][currentlyClicked.getY()].setGraphic(new ImageView(pieceArray[currentlyClicked.getX()][currentlyClicked.getY()].getPicture()));
                                currentlyClicked.setX(i);
                                currentlyClicked.setY(j);
                                whiteTurn = !whiteTurn;
                                
                            }
                            if(isGameOver) {
                                this.gameOver();
                            }
                            
                        }
                        currentlyClicked = null;



                        
                        
                        
                    }        
                       
                }
                
            }
        }
    }

    /* 
    private ArrayList<Integer> checkPawnAttackSquares(Type type, String color, int x, int y) {
        ArrayList<Integer> attackSquares = new ArrayList<Integer>();
        if (x != 0 && x != 7) {
            if (color.equals("white")) {
                if (y > 0) {
                    attackSquares.add(x-1);
                    attackSquares.add(y-1);
                }
                if (y < 7) {
                    attackSquares.add(x-1);
                    attackSquares.add(y+1);
                }
            }
        }
        if (color.equals("black")) {
            if (y > 0) {
                attackSquares.add(x+1);
                attackSquares.add(y-1);
            }
            if (y < 7) {
                attackSquares.add(x+1);
                attackSquares.add(y+1);
            }
        }
        return attackSquares;
    }
    */ 
    


    private ArrayList<Integer> checkLegalMoves(Type type, String color, int x, int y) {
        ArrayList<Integer> legalMoveList = new ArrayList<Integer>();
        switch(type) {
            case PAWN:
                if (x != 0 && x != 7) {
                    if (color.equals("white")) {
                        
                        if (pieceArray[x-1][y].getType() == Type.NONE) {
                            legalMoveList.add(x-1);
                            legalMoveList.add(y);
                            if (x == 6 && pieceArray[x-2][y].getType() == Type.NONE) {
                                legalMoveList.add(x-2);
                                legalMoveList.add(y);
                            }
                        }
                    }
                    if (color.equals("black")) {
                        
                        if (pieceArray[x+1][y].getType() == Type.NONE) {
                            legalMoveList.add(x+1);
                            legalMoveList.add(y);
                            if (x == 1 && pieceArray[x+2][y].getType() == Type.NONE) {
                                legalMoveList.add(x+2);
                                legalMoveList.add(y);
                            }
                        }
                    }
                    if (x != 0 && x != 7) {
                        if (color.equals("white")) {
                            if (y > 0 && pieceArray[x-1][y-1].getColor().equals("black")) {
                                legalMoveList.add(x-1);
                                legalMoveList.add(y-1);
                            }
                            if (y < 7 && pieceArray[x-1][y+1].getColor().equals("black")) {
                                legalMoveList.add(x-1);
                                legalMoveList.add(y+1);
                            }
                        }
                    }
                    if (color.equals("black")) {
                        if (y > 0 && pieceArray[x+1][y-1].getColor().equals("white")) {
                            legalMoveList.add(x+1);
                            legalMoveList.add(y-1);
                        }
                        if (y < 7 && pieceArray[x+1][y+1].getColor().equals("white")) {
                            legalMoveList.add(x+1);
                            legalMoveList.add(y+1);
                        }
                    }
                    
                }
                
                break;
            case BISHOP:
                System.out.println(type + ", " + color + ", " + x + ", " + y);
                if (color.equals("black")) {
                    
                    if (x < 7) {
                        
                        
                        //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                        boolean nextPieceWhite = false;
                        boolean nextPieceBlack = false;
                        for (int i = x+1; i < 8 && (y + (i-x)) < 8 && (pieceArray[i][y + (i-x)].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i++) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            } else if (pieceArray[i][y+(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1);
                        nextPieceWhite = false;
                        nextPieceBlack = false;
                        for (int i = x+1; i < 8 && (y - (i-x)) >= 0 && (pieceArray[i][y - (i-x)].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i++) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y-(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y-(i-x));
                            } else if (pieceArray[i][y-(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y-(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1);
                    }
                    if (x > 0) {
                        
                        
                        //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                        boolean nextPieceWhite = false;
                        boolean nextPieceBlack = false;
                        for (int i = x-1; i > -1 && (y + (i-x)) > -1 && (pieceArray[i][y + (i-x)].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i--) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            } else if (pieceArray[i][y+(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1); 
                        nextPieceWhite = false;
                        nextPieceBlack = false;
                        for (int i = x-1; i > -1 && (y + (x-i)) < 8 && (pieceArray[i][y + (x-i)].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i--) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(x-i)].getColor().equals("white")) {
                                nextPieceWhite = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(x-i));
                            } else if (pieceArray[i][y+(x-i)].getColor().equals("black")) {
                                nextPieceBlack = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(x-i));
                            }
                            
                            

                        }
                        
                    }
                    
                   
                    
                } else if (color.equals("white")) {
                    if (x < 7) {
                        
                        
                        //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                        boolean nextPieceWhite = false;
                        boolean nextPieceBlack = false;
                        for (int i = x+1; i < 8 && (y + (i-x)) < 8 && (pieceArray[i][y + (i-x)].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i++) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            } else if (pieceArray[i][y+(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1);
                        nextPieceWhite = false;
                        nextPieceBlack = false;
                        for (int i = x+1; i < 8 && (y - (i-x)) >= 0 && (pieceArray[i][y - (i-x)].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i++) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y-(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y-(i-x));
                            } else if (pieceArray[i][y-(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y-(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1);
                    }
                    if (x > 0) {
                        
                        
                        //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                        boolean nextPieceWhite = false;
                        boolean nextPieceBlack = false;
                        for (int i = x-1; i > -1 && (y + (i-x)) > -1 && (pieceArray[i][y + (i-x)].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i--) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(i-x)].getColor().equals("black")) {
                                nextPieceBlack = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            } else if (pieceArray[i][y+(i-x)].getColor().equals("white")) {
                                nextPieceWhite = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(i-x));
                            }
                            
                            

                        }
                        //legalMoveList.add(-1); 
                        nextPieceWhite = false;
                        nextPieceBlack = false;
                        for (int i = x-1; i > -1 && (y + (x-i)) < 8 && (pieceArray[i][y + (x-i)].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i--) {
                            //System.out.println(nextPieceWhite);
                            if (pieceArray[i][y+(x-i)].getColor().equals("black")) {
                                nextPieceBlack = true;
                                //System.out.println("test");
                                legalMoveList.add(i);
                                legalMoveList.add(y+(x-i));
                            } else if (pieceArray[i][y+(x-i)].getColor().equals("white")) {
                                nextPieceWhite = true;
                            } else {
                                legalMoveList.add(i);
                                legalMoveList.add(y+(x-i));
                            }
                            
                            

                        }
                        
                    }
                }
                
                break;
            case ROOK:
                if (color.equals("black")) {
                    boolean nextPieceWhite = false;
                    boolean nextPieceBlack = false;
                    for (int i = x+1; i < 8  && (pieceArray[i][y].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i++) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[i][y].getColor().equals("white")) {
                            nextPieceWhite = true;
                            //System.out.println("test");
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        } else if (pieceArray[i][y].getColor().equals("black")) {
                            nextPieceBlack = true;
                        } else {
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1); 
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = x-1; i > -1  && (pieceArray[i][y].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i--) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[i][y].getColor().equals("white")) {
                            nextPieceWhite = true;
                            //System.out.println("test");
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        } else if (pieceArray[i][y].getColor().equals("black")) {
                            nextPieceBlack = true;
                        } else {
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1);
                    //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = y+1; i < 8 && (pieceArray[x][i].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i++) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[x][i].getColor().equals("white")) {
                            nextPieceWhite = true;
                            //System.out.println("test");
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        } else if (pieceArray[x][i].getColor().equals("black")) {
                            nextPieceBlack = true;
                        } else {
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1); 
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = y-1; i > -1 && (pieceArray[x][i].getType() == Type.NONE || !nextPieceWhite) && !nextPieceWhite && !nextPieceBlack; i--) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[x][i].getColor().equals("white")) {
                            nextPieceWhite = true;
                            //System.out.println("test");
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        } else if (pieceArray[x][i].getColor().equals("black")) {
                            nextPieceBlack = true;
                        } else {
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        }
                        
                        

                    }
                } else if (color.equals("white")) {
                    boolean nextPieceWhite = false;
                    boolean nextPieceBlack = false;
                    for (int i = x+1; i < 8  && (pieceArray[i][y].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i++) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[i][y].getColor().equals("black")) {
                            nextPieceBlack = true;
                            //System.out.println("test");
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        } else if (pieceArray[i][y].getColor().equals("white")) {
                            nextPieceWhite = true;
                        } else {
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1); 
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = x-1; i > -1  && (pieceArray[i][y].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i--) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[i][y].getColor().equals("black")) {
                            nextPieceBlack = true;
                            //System.out.println("test");
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        } else if (pieceArray[i][y].getColor().equals("white")) {
                            nextPieceWhite = true;
                        } else {
                            legalMoveList.add(i);
                            legalMoveList.add(y);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1);
                    //System.out.println(x + ", " + (x < 8) + ", " + (y < 8) + ", " + pieceArray[x][y]);
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = y+1; i < 8 && (pieceArray[x][i].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i++) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[x][i].getColor().equals("black")) {
                            nextPieceBlack = true;
                            //System.out.println("test");
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        } else if (pieceArray[x][i].getColor().equals("white")) {
                            nextPieceWhite = true;
                        } else {
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        }
                        
                        

                    }
                    //legalMoveList.add(-1); 
                    nextPieceWhite = false;
                    nextPieceBlack = false;
                    for (int i = y-1; i > -1 && (pieceArray[x][i].getType() == Type.NONE || !nextPieceBlack) && !nextPieceWhite && !nextPieceBlack; i--) {
                        //System.out.println(nextPieceWhite);
                        if (pieceArray[x][i].getColor().equals("black")) {
                            nextPieceBlack = true;
                            //System.out.println("test");
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        } else if (pieceArray[x][i].getColor().equals("white")) {
                            nextPieceWhite = true;
                        } else {
                            legalMoveList.add(x);
                            legalMoveList.add(i);
                        }
                        
                        

                    }
                }

                break;

            case KNIGHT:
                if (color.equals("black")) {
                    if (x < 7) {
                        if (y > 1) {
                            if (!pieceArray[x+1][y-2].getColor().equals("black")){
                                legalMoveList.add(x+1);
                                legalMoveList.add(y-2);
                            }
                        } 
                        if (y < 6) {
                            if (!pieceArray[x+1][y+2].getColor().equals("black")){
                                legalMoveList.add(x+1);
                                legalMoveList.add(y+2);
                            }
                        }
                    }
                    if (x > 0) {
                        if (y > 1) {
                            if (!pieceArray[x-1][y-2].getColor().equals("black")){
                                legalMoveList.add(x-1);
                                legalMoveList.add(y-2);
                            }
                        } 
                        if (y < 6) {
                            if (!pieceArray[x-1][y+2].getColor().equals("black")){
                                legalMoveList.add(x-1);
                                legalMoveList.add(y+2);
                            }
                        }
                    }
                    if (x < 6) {
                        if (y > 0) {
                            if (!pieceArray[x+2][y-1].getColor().equals("black")){
                                legalMoveList.add(x+2);
                                legalMoveList.add(y-1);
                            }
                        } 
                        if (y < 7) {
                            if (!pieceArray[x+2][y+1].getColor().equals("black")){
                                legalMoveList.add(x+2);
                                legalMoveList.add(y+1);
                            }
                        }
                    }
                    if (x > 1) {
                        if (y > 0) {
                            if (!pieceArray[x-2][y-1].getColor().equals("black")){
                                legalMoveList.add(x-2);
                                legalMoveList.add(y-1);
                            }
                        } 
                        if (y < 7) {
                            if (!pieceArray[x-2][y+1].getColor().equals("black")){
                                legalMoveList.add(x-2);
                                legalMoveList.add(y+1);
                            }
                        }
                    }
                    
                } else if (color.equals("white")) {
                    if (x < 7) {
                        if (y > 1) {
                            if (!pieceArray[x+1][y-2].getColor().equals("white")){
                                legalMoveList.add(x+1);
                                legalMoveList.add(y-2);
                            }
                        } 
                        if (y < 6) {
                            if (!pieceArray[x+1][y+2].getColor().equals("white")){
                                legalMoveList.add(x+1);
                                legalMoveList.add(y+2);
                            }
                        }
                    }
                    if (x > 0) {
                        if (y > 1) {
                            if (!pieceArray[x-1][y-2].getColor().equals("white")){
                                legalMoveList.add(x-1);
                                legalMoveList.add(y-2);
                            }
                        } 
                        if (y < 6) {
                            if (!pieceArray[x-1][y+2].getColor().equals("white")){
                                legalMoveList.add(x-1);
                                legalMoveList.add(y+2);
                            }
                        }
                    }
                    if (x < 6) {
                        if (y > 0) {
                            if (!pieceArray[x+2][y-1].getColor().equals("white")){
                                legalMoveList.add(x+2);
                                legalMoveList.add(y-1);
                            }
                        } 
                        if (y < 7) {
                            if (!pieceArray[x+2][y+1].getColor().equals("white")){
                                legalMoveList.add(x+2);
                                legalMoveList.add(y+1);
                            }
                        }
                    }
                    if (x > 1) {
                        if (y > 0) {
                            if (!pieceArray[x-2][y-1].getColor().equals("white")){
                                legalMoveList.add(x-2);
                                legalMoveList.add(y-1);
                            }
                        } 
                        if (y < 7) {
                            if (!pieceArray[x-2][y+1].getColor().equals("white")){
                                legalMoveList.add(x-2);
                                legalMoveList.add(y+1);
                            }
                        }
                    }
                    
                }
                                
                break;

            case QUEEN:
                legalMoveList.addAll(checkLegalMoves(Type.BISHOP, color, x, y));
                legalMoveList.addAll(checkLegalMoves(Type.ROOK, color, x, y));
                break;

            case KING:
                
                legalMoveList.addAll(checkKingMoves(color,x,y));

                //System.out.println(legalMoveList);
                               


                
                break;
        }
        Button currentButton = buttonArray[x][y];
        currentButton.setStyle("-fx-background-color: #e3e37b; -fx-background-radius: 0; -fx-border-width: 0; ");
        /*
        int counter = 0;
        while (counter < legalMoveList.size()) {
            if (legalMoveList.get(counter) == -1) {
                counter++;
            } else {
                Button testButton = buttonArray[legalMoveList.get(counter)][legalMoveList.get(counter+1)];
                testButton.setStyle("-fx-background-color: #e3e37b; -fx-background-radius: 0; -fx-border-width: 0; ");
                counter += 2;
            }
            
            
        }
        */
        return legalMoveList;
    }

    private ArrayList<Integer> checkKingMoves(String color, int x, int y) {
        ArrayList<Integer> kingMoves = new ArrayList<Integer>();
        if (color.equals("black")){
            
            
            if (x > 0) {
                if (!pieceArray[x-1][y].getColor().equals("black")){
                        kingMoves.add(x-1);
                        kingMoves.add(y);
                }
                if (y > 0) {
                    if (!pieceArray[x-1][y-1].getColor().equals("black")){
                        kingMoves.add(x-1);
                        kingMoves.add(y-1);
                    }
                }
                if (y < 7) {
                    if (!pieceArray[x-1][y+1].getColor().equals("black")){
                        kingMoves.add(x-1);
                        kingMoves.add(y+1);
                    }
                }  
            }
            if (x < 7) {
                if (!pieceArray[x+1][y].getColor().equals("black")){
                        kingMoves.add(x+1);
                        kingMoves.add(y);
                }
                if (y > 0) {
                    if (!pieceArray[x+1][y-1].getColor().equals("black")){
                        kingMoves.add(x+1);
                        kingMoves.add(y-1);
                    }
                }
                if (y < 7) {
                    if (!pieceArray[x+1][y+1].getColor().equals("black")){
                        kingMoves.add(x+1);
                        kingMoves.add(y+1);
                    }
                }  
            }
            if (y > 0) {
                if (!pieceArray[x+1][y+1].getColor().equals("black")){
                        kingMoves.add(x);
                        kingMoves.add(y-1);
                } 
            }
            if (y < 7) {
                if (!pieceArray[x][y+1].getColor().equals("black")){
                    kingMoves.add(x);
                    kingMoves.add(y+1);
                }
            } 
             
            
        } else if (color.equals("white")){
            
            if (x > 0) {
                if (!pieceArray[x-1][y].getColor().equals("white")){
                        kingMoves.add(x-1);
                        kingMoves.add(y);
                }
                if (y > 0) {
                    if (!pieceArray[x-1][y-1].getColor().equals("white")){
                        kingMoves.add(x-1);
                        kingMoves.add(y-1);
                    }
                }
                if (y < 7) {
                    if (!pieceArray[x-1][y+1].getColor().equals("white")){
                        kingMoves.add(x-1);
                        kingMoves.add(y+1);
                    }
                }  
            }
            if (x < 7) {
                if (!pieceArray[x+1][y].getColor().equals("white")){
                        kingMoves.add(x+1);
                        kingMoves.add(y);
                }
                if (y > 0) {
                    if (!pieceArray[x+1][y-1].getColor().equals("white")){
                        kingMoves.add(x+1);
                        kingMoves.add(y-1);
                    }
                }
                if (y < 7) {
                    if (!pieceArray[x+1][y+1].getColor().equals("white")){
                        kingMoves.add(x+1);
                        kingMoves.add(y+1);
                    }
                }  
            }
            if (y > 0) {
                if (!pieceArray[x][y+1].getColor().equals("white")){
                        kingMoves.add(x);
                        kingMoves.add(y-1);
                } 
            }
            if (y < 7) {
                if (!pieceArray[x][y+1].getColor().equals("white")){
                    kingMoves.add(x);
                    kingMoves.add(y+1);
                }
            }

            
        }
        return kingMoves;
    } 

    /* 
    private ArrayList<Integer> attackedSquares(String color) {
        String oppositeColor;
        if (color.equals("white")) {
            oppositeColor = "black";
        } else if (color.equals("black")){
            oppositeColor = "white";
        } else {
            oppositeColor = color;
        }
        ArrayList<Integer> squares = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                if (pieceArray[i][j].getColor().equals(oppositeColor)) {
                    //System.out.println(i + "," + j + "," + pieceArray[i][j].getType() + "," + pieceArray[i][j].getColor());
                    
                     
                    if (pieceArray[i][j].getType() == Type.KING) {
                        squares.addAll(checkKingMoves(color, i, j));
                    } else if (pieceArray[i][j].getType() == Type.PAWN) {
                        squares.addAll(checkPawnAttackSquares(Type.PAWN, oppositeColor, i, j));
                    } else {
                        squares.addAll(checkLegalMoves(pieceArray[i][j].getType(), pieceArray[i][j].getColor(), i, j));
                    }
                    
                }
            }
        }
        
        return squares;
    }*/

    
     
    private void disableAll() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonArray[i][j].setDisable(true);
            }
        }
    }
    private void enableAll() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonArray[i][j].setDisable(false);
            }
        }
    }
    /*
    private void enableOnlyWhite() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceArray[i][j].getType() == Type.NONE || pieceArray[i][j].getColor().equals("black")) {
                    buttonArray[i][j].setDisable(true);
                } else {
                    buttonArray[i][j].setDisable(false);
                }
            }
        }
    }
    private void enableOnlyBlack() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceArray[i][j].getType() == Type.NONE || pieceArray[i][j].getColor().equals("white")) {
                    buttonArray[i][j].setDisable(true);
                } else {
                    buttonArray[i][j].setDisable(false);
                }
            }
        }
    }
    */
    

    private void gameOver() {
        String winnerString;
        if (whiteTurn) {
            whiteWins = true;
            winnerString = "White Has Won the Game!";
        } else {
            whiteWins = false;
            winnerString = "Black Has Won the Game!";
        }
        
        System.out.println("White won: " + whiteWins);
        this.disableAll();
        Alert alert = new Alert(AlertType.INFORMATION,winnerString);
        alert.setTitle("Game Over");
        
        alert.setGraphic(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("test");
            restartHelper();
           
        }
    }

    @Override
    public void start(Stage stage) throws Exception{
        
        scene = new Scene(tilePane, 612, 612);
        
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Chess");
        stage.getIcons().add(blackPawn);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}