package connectFour;

import javax.swing.*;
import java.awt.*;
import java.net.PasswordAuthentication;
import java.nio.file.Path;

public class Display {
    Game game;
    int numCol;
    JFrame frame;
    Paint board;

    public Display(int width, int height, String title, Game game){
        this.game = game;
        //BASIC SETINGS
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);//missing


        //This is just to make the top the same color
        JPanel empty = new JPanel();
        empty.setBackground(new Color(52,52,74));
        empty.setBounds(0,0,900,20);
        frame.add(empty);

        Buttons(); //Handles the creation of buttons

        Painting();//Handles the board creation and "painting"
        frame.setBackground(new Color(52,52,74));
        frame.setVisible(true);


    }

    //Calls the class Paint that draws the board
    public void Painting(){
        JPanel pnPanel = new JPanel();
        pnPanel.setLayout(null);
        pnPanel.setBounds(0,60,600,600);
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
            game.restart();
            board.repaint();
        }
}

//Handles the buttons
    private void Buttons(){
        JPanel pnButons = new JPanel();
        pnButons.setLayout(new FlowLayout());
        pnButons.setBounds(-100, 20, 700,50);
        pnButons.setBackground(new Color(52,52,74));
        frame.add(pnButons);

        JButton colZero = new JButton("Zero");
        colZero.addActionListener(this::ColZeroActionPerformed);
        pnButons.add(colZero);

        JButton colOne = new JButton("One");
        colOne.addActionListener(this::ColOneActionPerformed);
        pnButons.add(colOne);

        JButton colTwo = new JButton("Two");
        colTwo.addActionListener(this::ColTwoActionPerformed);
        pnButons.add(colTwo);

        JButton colThree = new JButton("Three");
        colThree.addActionListener(this::ColThreeActionPerformed);
        pnButons.add(colThree);

        JButton colFour = new JButton("Four");
        colFour.addActionListener(this::ColFourActionPerformed);
        pnButons.add(colFour);

        JButton colFive = new JButton("Five");
        colFive.addActionListener(this::ColFiveActionPerformed);
        pnButons.add(colFive);

        JButton colSix = new JButton("Six");
        colSix.addActionListener(this::ColSixActionPerformed);
        pnButons.add(colSix);
    }


    //BUTTONS ACTION
    //Each button has its own action that send the col number
    /*Maybe try to make a single action if possible?*/
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



}
