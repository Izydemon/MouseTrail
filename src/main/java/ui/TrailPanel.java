package ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author legol
 */
public class TrailPanel extends JPanel {
    
    boolean draw = false;
    
    int drawSize = 20;
    
    int distanceBetweenTrail = 10;
    private int timer = 0;
    
    private final Color[] backgroundColors;
    private final Color[] foregroundColors;
    
    private class Point{
        int x;
        int y;
        
        public void SetPoint(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int GetPointX(){
            return x;
        }
        
        public int GetPointY(){
            return y;
        }
    }
    
    Point[] trail = { new Point(), new Point(), new Point(), new Point(), new Point() };
    int trailIndex = 0;
    
    public TrailPanel(){
        super();
        
        backgroundColors = new Color[3];
        backgroundColors[0] = Color.WHITE;
        backgroundColors[1] = Color.BLACK;
        backgroundColors[2] = Color.GRAY;
        
        foregroundColors = new Color[5];
        foregroundColors[0] = Color.BLACK;
        foregroundColors[1] = Color.RED;
        foregroundColors[2] = Color.BLUE;
        foregroundColors[3] = Color.GREEN;
        foregroundColors[4] = Color.YELLOW;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(draw){
            for (Point point : trail) {
                g.fillOval(point.GetPointX() - (drawSize / 2), point.GetPointY() - (drawSize / 2), drawSize, drawSize);
            }
        }
    }
    
    public void DrawCircle(int x, int y){
        if(timer == 0){
            trail[trailIndex].SetPoint(x, y);
            trailIndex = (trailIndex + 1) % 5;
            if(trailIndex == 0)
                draw = true;
            timer = distanceBetweenTrail;
            this.repaint();
        } else{
            timer--;
        }
    }
    
    public void SetDistance(int value){
        distanceBetweenTrail = value;
    }
    
    public void SetSize(int value){
        drawSize = value;
    }
    
    public void setBackground(int opcion){
        this.setBackground(backgroundColors[opcion]);
    }
    
    public void setForeground(int opcion){
        this.setForeground(foregroundColors[opcion]);
    }
}
