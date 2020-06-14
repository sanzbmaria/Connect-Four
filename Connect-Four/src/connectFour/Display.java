package connectFour;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Display {
    Game game;
    int numCol;
    JFrame frame;
    Paint board;

    public Display(int width, int height, String title, Game game){
        this.game = game;
        //BASIC SETTINGS
        frame = new JFrame("Connect Four");
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

        //JPanel for JLabel for the showing of turns
        JPanel Blue = new JPanel();
        Blue.setLayout(new FlowLayout());
        Blue.setBounds(-245, 550, 1000, 40);
        Blue.setBackground(new Color(105,162,176));
        frame.add(Blue);

        /*JLabel JTurnLabel = new JLabel();
        JTurnLabel.setBounds(-245, 550, 228, 25);
        TurnLabel.add(JTurnLabel);*/

        Buttons(); //Handles the creation of buttons

        Painting();//Handles the board creation and "painting"

        playBackgroundMusic();

        frame.setBackground(new Color(52,52,74));
        frame.setVisible(true);

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
            game.restart();
            board.repaint();
        }
}

//to play some background music when running
public void playBackgroundMusic(){

        try{
           File audioFile = new File("C:\\Users\\Angel Fitri Sari\\IdeaProjects\\Connect-Four\\Connect-Four/sound2.wav");
           Clip audioClip = AudioSystem.getClip();
           audioClip.open(AudioSystem.getAudioInputStream(audioFile));
           audioClip.start();
        }
        catch(Exception e){
           System.err.println(e.getMessage());
        }
    }

//Handles the buttons
    private void Buttons() {
        JPanel pnButtons = new JPanel();
        pnButtons.setLayout(new FlowLayout());
        pnButtons.setBounds(-100, 20, 700, 50);
        pnButtons.setBackground(new Color(52, 52, 74));
        frame.add(pnButtons);

        JPanel ngButton = new JPanel();
        ngButton.setLayout(new FlowLayout());
        ngButton.setBounds(-245, 590, 1000, 60);
        ngButton.setBackground(new Color(52, 52, 74));
        frame.add(ngButton);

        JButton colZero = new JButton(" \uD83E\uDC83 ");
        colZero.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colZero.addActionListener(this::ColZeroActionPerformed);
        pnButtons.add(colZero);

        JButton colOne = new JButton(" \uD83E\uDC83 ");
        colOne.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colOne.addActionListener(this::ColOneActionPerformed);
        pnButtons.add(colOne);

        JButton colTwo = new JButton(" \uD83E\uDC83 ");
        colTwo.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colTwo.addActionListener(this::ColTwoActionPerformed);
        pnButtons.add(colTwo);

        JButton colThree = new JButton(" \uD83E\uDC83 ");
        colThree.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colThree.addActionListener(this::ColThreeActionPerformed);
        pnButtons.add(colThree);

        JButton colFour = new JButton(" \uD83E\uDC83 ");
        colFour.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colFour.addActionListener(this::ColFourActionPerformed);
        pnButtons.add(colFour);

        JButton colFive = new JButton(" \uD83E\uDC83 ");
        colFive.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colFive.addActionListener(this::ColFiveActionPerformed);
        pnButtons.add(colFive);

        JButton colSix = new JButton(" \uD83E\uDC83 ");
        colSix.setFont(new Font("Arial ExtraBlack", Font.BOLD, 16));
        colSix.addActionListener(this::ColSixActionPerformed);
        pnButtons.add(colSix);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        newGameButton.addActionListener(this::newGameButtonActionPerformed);
        ngButton.add(newGameButton);

    }

    //BUTTONS ACTION
    //Each button has its own action that send the col number
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

    //show the winner popup message
    public void showWinner(){
        JFrame frameShowWinner = new JFrame();
        if(game.isWinner()){
            JOptionPane.showMessageDialog(frameShowWinner, "You won! A new game will start!", "End Game", JOptionPane.INFORMATION_MESSAGE);
            game.restart();
            paintingUpdate();
        }
    }
    
    //show an announcement if it is a draw
    public void showDraw(){
        JFrame frameShowDraw = new JFrame();
        JOptionPane.showMessageDialog(frameShowDraw, "It is a Draw! A new game will start!", "Draw!", JOptionPane.INFORMATION_MESSAGE);
        game.restart();
        paintingUpdate();
    }

    //show an announcement if a column is full
    public void showColumnFull(){
        JFrame frameShowColumnFull = new JFrame();
        JOptionPane.showMessageDialog(frameShowColumnFull, "Column full, try again!", "Column Full!", JOptionPane.INFORMATION_MESSAGE);
    }

}
