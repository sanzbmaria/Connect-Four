package connectFour;

import javax.swing.*;
import java.awt.*;

public class Paint extends JPanel {

    Game game;

    private static int SQUARE_LENGTH;
    private static int PANEL_WIDTH;
    private static int PANEL_HEIGHT;

    Paint(Game game){
        this.game = game;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;
        int y = 0;
        int w = 60;
        int h = 60;

            for (int j = 0; j < 6 ; j++) {
                for (int i = 0; i < 7 ; i++) {
                    switch (game.location(j,i)){
                        case 1:
                            g.setColor(Color.RED);
                            break;
                        case 2:
                            g.setColor(Color.YELLOW);
                            break;
                        case 0 :
                            g.setColor(Color.WHITE);
                            break;
                    }
                    g.fillOval(x,y,62,62);

                    x+=65;
                }
                x = 0;
                y+=65;
            }



    }
}
