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
    
    int drawX = -10;
    int drawY = -10;
    
    int drawSize = 20;
    
    int distanceBetweenTrail = 10;
    private int timer = 0;
    
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
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        
        if(draw){
            for (Point point : trail) {
                g.fillOval(point.GetPointX() - (drawSize / 2), point.GetPointY() - (drawSize / 2), drawSize, drawSize);
            }
        }
    }
    
    public void DrawCircle(int x, int y){
        if(timer == 0){
            draw = true;
            trail[trailIndex].SetPoint(x, y);
            trailIndex = (trailIndex + 1) % 5;
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
}
