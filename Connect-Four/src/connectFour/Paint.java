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
        g.setColor(new Color(52,52,74));
        g.fillRect(0,0,600,600);

        int x = 0;
        int y = 0;
        int w = 60;
        int h = 60;

        g.setColor(new Color(52,52,74));

            for (int j = 0; j < 6 ; j++) {
                for (int i = 0; i < 7 ; i++) {
                    switch (game.location(j,i)){
                        case 1:
                            g.setColor(new Color(224,82,99));
                            break;
                        case 2:
                            g.setColor(new Color(249,220,92));
                            break;
                        case 0 :
                            g.setColor(new Color(243,232,238));
                            break;
                    }
                    g.fillOval(x,y,61,61);

                    x+=63;
                }
                x = 0;
                y+=63;
            }



    }
}
