package connectFour;

import javax.swing.*;
import java.awt.*;
import java.net.PasswordAuthentication;
import java.nio.file.Path;

public class Display {
    static int SQUARE_LENGTH = 72;
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


        frame.setLayout(null);//missing

        Buttons(); //Handless the creation of buttons

        Painting();

        frame.setVisible(true);


    }

    public void Painting(){
        JPanel pnPanel = new JPanel();
        pnPanel.setLayout(null);
        pnPanel.setBounds(0,60,600,600);
        pnPanel.setBackground(Color.YELLOW);

        board = new Paint(game);
        board.setBounds(30, 30, 450, 500);//missing
        pnPanel.add(board);

        frame.add(pnPanel);
        pnPanel.setVisible(true);
}

public void paintingUpdate(){
        if(!game.isWinner())
            board.repaint();
        else{
            game.restart();
            board.repaint();
        }
}

    private void Buttons(){
        JPanel pnButons = new JPanel();
        pnButons.setLayout(new FlowLayout());
        pnButons.setBounds(-100, 10, 700,50);
        pnButons.setBackground(Color.CYAN);
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
