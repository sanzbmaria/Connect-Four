package connectFour;

import javax.swing.*;
import java.awt.*;
 /*import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.PasswordAuthentication;
import java.nio.file.Path;*/

public class Display {
    Game game;
    int numCol;
    JFrame frame;
    Paint board;
    JTextField JTextField1;

    /*
    1. We need a NEW GAME button //DONE
    2. We need a popup message to announce the winner //DONE
    3. Popup message to announce draw // DONE
    4. Popup message to announce if column is full //DONE
    5. Shows the current player
    6. Make the top buttons into arrows

    Extra: Choose the colors
     */

    public Display(int width, int height, String title, Game game){
        this.game = game;
        //BASIC SETINGS
        frame = new JFrame("Connect Four");
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.addMouseListener((MouseListener) this);
        //frame.addMouseMotionListener((MouseMotionListener) this);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);//missing


        //This is just to make the top the same color
        JPanel empty = new JPanel();
        empty.setBackground(new Color(52,52,74));
        empty.setBounds(0,0,900,20);
        frame.add(empty);

        //JPanel for JTextField for the showing of turns
        JPanel TextField = new JPanel();
        TextField.setLayout(new FlowLayout());
        TextField.setBounds(-245, 550, 1000, 40);
        TextField.setBackground(new Color(105,162,176));
        frame.add(TextField);

        //showTurns();
        JTextField1 = new JTextField();
        JTextField1.setBounds(-245, 550, 228, 25);
        TextField.add(JTextField1);

        if(game.getTurn() == 1){
            JTextField1.setText("Red's Turn");
        }
        else if (game.getTurn() == 2){
            JTextField1.setText("Yellow's Turn");
        }
        else {
            JTextField1.setText("Turn");
        }

        Buttons(); //Handles the creation of buttons

        Painting();//Handles the board creation and "painting"
        frame.setBackground(new Color(52,52,74));
        frame.setVisible(true);

        /* experimenting functions*/
        //to show turns
        //showTurns();
        //addTurnName(gr);
        showWinner();

    }

    //Calls the class Paint that draws the board
    public void Painting(){
        JPanel pnPanel = new JPanel();
        pnPanel.setLayout(null);
        pnPanel.setBounds(0,60,600,490);
        pnPanel.setBackground(new Color(52,52,74));

        //This is the important part because it call the Paint method (the one that "draws the board")
        board = new Paint(game);
        board.setBounds(30, 30, 450, 500);//missing
        pnPanel.add(board);

        frame.add(pnPanel);
        pnPanel.setVisible(true);
}

//Will "repaint" the board after every move
public void paintingUpdate(){
        //Check if there is winner if not then just repaint, if there is restart the game and repaint (it is a safety check)
        if(!game.isWinner())
            board.repaint();
        else{
            showWinner();
            showDraw();
            game.restart();
            board.repaint();
        }
}

//Handles the buttons
    private void Buttons() {
        JPanel pnButons = new JPanel();
        pnButons.setLayout(new FlowLayout());
        pnButons.setBounds(-100, 20, 700, 50);
        pnButons.setBackground(new Color(52, 52, 74));
        frame.add(pnButons);

        JPanel ngButton = new JPanel();
        ngButton.setLayout(new FlowLayout());
        ngButton.setBounds(-245, 590, 1000, 60);
        ngButton.setBackground(new Color(52, 52, 74));
        frame.add(ngButton);

        JButton colZero = new JButton("Zero");
        colZero.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colZero.addActionListener(this::ColZeroActionPerformed);
        pnButons.add(colZero);

        JButton colOne = new JButton("One");
        colOne.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colOne.addActionListener(this::ColOneActionPerformed);
        pnButons.add(colOne);

        JButton colTwo = new JButton("Two");
        colTwo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colTwo.addActionListener(this::ColTwoActionPerformed);
        pnButons.add(colTwo);

        JButton colThree = new JButton("Three");
        colThree.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colThree.addActionListener(this::ColThreeActionPerformed);
        pnButons.add(colThree);

        JButton colFour = new JButton("Four");
        colFour.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colFour.addActionListener(this::ColFourActionPerformed);
        pnButons.add(colFour);

        JButton colFive = new JButton("Five");
        colFive.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colFive.addActionListener(this::ColFiveActionPerformed);
        pnButons.add(colFive);

        JButton colSix = new JButton("Six");
        colSix.setFont(new Font("Century Gothic", Font.BOLD, 12));
        colSix.addActionListener(this::ColSixActionPerformed);
        pnButons.add(colSix);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        newGameButton.addActionListener(this::newGameButtonActionPerformed);
        ngButton.add(newGameButton);

    }

    //BUTTONS ACTION
    //Each button has its own action that send the col number
    /*Maybe try to make a single action if possible?*/
    private void newGameButtonActionPerformed(java.awt.event.ActionEvent event) {
        game.restart();
        paintingUpdate();
    }

    private void ColZeroActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 0;
        game.runNT(0);
    }

    private void ColOneActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 1;
        game.runNT(1);
    }
    private void ColTwoActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 2;
        game.runNT(2);

    }

    private void ColThreeActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 3;
        game.runNT(3);
    }

    private void ColFourActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 4;
        game.runNT(4);
    }

    private void ColFiveActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 5;
        game.runNT(5);
    }

    private void ColSixActionPerformed(java.awt.event.ActionEvent event) {
        this.numCol = 6;
        game.runNT(6);
    }

    //show current players turn
    /* public void addTurnName(Graphics showTurn) {
        Graphics2D gr = (Graphics2D) showTurn;
        gr.setColor(new Color(255,255,255));
        gr.setBackground(new Color(0,0,0));
        if(game.turn == 1){
            gr.drawString("Red's Turn", -245,550);
        }
        else if (game.turn == 2){
            gr.drawString("Yellow's Turn", -245,550);
        } //I will explore again to see if this might work
    }*/

    //Algorithm for Winner message popup window
    /*
        if (isWinner == true) { //or something like if(isWinner())
            JOptionPane.showDialog.message(null, "____ wins");
        }
     */

    /* do the algorithm for Draw message popup window*/

    //attempt to show the winner popup message
    public void showWinner(){
        JFrame frameShowWinner = new JFrame();
        if(game.isWinner()){
            JOptionPane.showMessageDialog(frameShowWinner, "You won! A new game will start!", "End Game", JOptionPane.INFORMATION_MESSAGE);
            game.restart();
            paintingUpdate();
        }
    }  //figure out why this is not working, most probably it is the if statement.
    
    //attempt to show an announcement if it is a draw
    public void showDraw(){
        JFrame frameShowDraw = new JFrame();
        if(game.noWinner()) {
            JOptionPane.showMessageDialog(frameShowDraw, "It is Draw! A new game will start!", "End Game", JOptionPane.INFORMATION_MESSAGE);
            game.restart();
            paintingUpdate();
        }
    }

    //attempt to show an announcement if a column is full
    public void showColumnFull(){
        JFrame frameShowColumnFull = new JFrame();
        JOptionPane.showMessageDialog(frameShowColumnFull, "Column full, try again!", "Column Full!", JOptionPane.INFORMATION_MESSAGE);
    }

}
