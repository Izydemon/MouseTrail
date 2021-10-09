package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
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
    
    private enum Figure{
        Circle,
        Rect,
        Trian
    }
    
    private Figure figure = Figure.Circle;
    
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
                switch(figure){
                    case Circle:
                        g.fillOval(point.GetPointX() - (drawSize / 2), point.GetPointY() - (drawSize / 2), drawSize, drawSize);
                        break;
                    case Rect:
                        g.fillRect(point.GetPointX() - (drawSize / 2), point.GetPointY() - (drawSize / 2), drawSize, drawSize);
                        break;
                    case Trian:
                        g.fillPolygon(GetTriangle(point.GetPointX(), point.GetPointY()));
                        break;
                }
            }
        }
    }
    
    private Polygon GetTriangle(int x, int y){        
        Polygon triangle = new Polygon(new int[] {x - (drawSize / 2), x + (drawSize / 2), x},
                new int[] {y + (drawSize / 2), y + (drawSize / 2), y - (drawSize / 2)},
                3);
        
        return triangle;
    }
    
    public void Draw(int x, int y){
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
    
    public void setFigure(int opcion){
        switch(opcion){
            case 0:
                figure = Figure.Circle;
                break;
            case 1:
                figure = Figure.Rect;
                break;
            case 2:
                figure = Figure.Trian;
                break;
        }
    }
}
