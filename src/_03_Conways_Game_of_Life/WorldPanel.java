package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private int cellsPerRow;
    private int cellSize;

    private Timer timer;

    //1. Create a 2D array of Cells. Do not initialize it.

    private Cell[][] cells;

    public WorldPanel(int w, int h, int cpr) {
        setPreferredSize(new Dimension(w, h));
        addMouseListener(this);
        timer = new Timer(500, this);
        this.cellsPerRow = cpr;

        //2. Calculate the cell size.
        cellSize = w / cellsPerRow;
        //3a. Initialize the cell array to the appropriate size.
        cells = new Cell[cellsPerRow][cellsPerRow];
        //3b. Iterate through the array and initialize each cell.
        //   Don't forget to consider the cell's dimensions when
        //   passing in the location.
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell(i*cellSize, j*cellSize, cellSize);
            }
        }
    }

    public void randomizeCells() {
        //4. Iterate through each cell and randomly set each
        //   cell's isAlive member to true of false
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].isAlive = randomLife();
            }
        }
        repaint();
    }

    private boolean randomLife() {
        //int rand = 1+new Random().nextInt(1);
        boolean life = false;
        Random rand = new Random();
        int r = rand.nextInt(100);
        if (r % 2 == 0) {
            life = true;
        }
        return life;
    }

    public void clearCells() {
        //5. Iterate through the cells and set them all to dead.
        for (int i = 0; i < cellsPerRow; i++) {
            for (int j = 0; j < cellsPerRow; j++) {
                cells[i][j].isAlive = false;
            }
        }
        repaint();
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    public void setAnimationDelay(int sp) {
        timer.setDelay(sp);
    }

    @Override
    public void paintComponent(Graphics g) {
        //6. Iterate through the cells and draw them all
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].draw(g);
            }

        // draws grid
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    //advances world one step
    public void step() {
        //7. iterate through cells and fill in the livingNeighbors array
        // . using the getLivingNeighbors method.
        int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
        for (int i = 0; i < cellsPerRow; i++) {
            for (int j = 0; j < cellsPerRow; j++) {
                livingNeighbors[i][j] = getLivingNeighbors(i, j);
            }
        }
        for (int i = 0; i < cellsPerRow; i++) {
            for (int j = 0; j < cellsPerRow; j++) {
                //8. check if each cell should live or die
                cells[i][j].liveOrDie(livingNeighbors[i][j]);
            }
        }

        repaint();
    }

    //9. Complete the method.
    //   It returns an int of 8 or less based on how many
    //   living neighbors there are of the
    //   cell identified by x and y
    public int getLivingNeighbors(int x, int y) {
        int numLiving = 0;

        if (x != 0) {
            if (cells[x - 1][y].isAlive) {
                numLiving++;
            }
        }
        if (x != cellsPerRow - 1) {
            if (cells[x + 1][y].isAlive) {
                numLiving++;
            }
        }
        if (y != 0) {
            if (cells[x][y - 1].isAlive) {
                numLiving++;
            }
        }
        if (y != cellsPerRow - 1) {
            if (cells[x][y + 1].isAlive) {
                numLiving++;
            }
        }
        if (x != 0 && y != 0) {
            if (cells[x - 1][y - 1].isAlive) {
                numLiving++;
            }
        }
        if (x != 0 && y != cellsPerRow - 1) {
            if (cells[x - 1][y + 1].isAlive) {
                numLiving++;
            }
        }
        if (x != cellsPerRow - 1 && y != cellsPerRow - 1) {
            if (cells[x + 1][y + 1].isAlive) {
                numLiving++;
            }
        }
        if (x != cellsPerRow - 1 && y != 0) {
            if (cells[x + 1][y - 1].isAlive) {
                numLiving++;
            }
        }
        return numLiving;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //10. Use e.getX() and e.getY() to determine
        //    which cell is clicked. Then toggle
        //    the isAlive variable for that cell.

        for (int i = 0; i < cellsPerRow; i++) {
            for (int j = 0; j < cellsPerRow; j++) {
                Cell cell = cells[i][j];
                if (e.getX() > cell.getX() && e.getX() < (cell.getX() + cellSize)
                && e.getY() > cell.getY() && e.getY() < (cell.getY() + cellSize)) {
                    cell.isAlive = !cell.isAlive;
                }
            }
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }
}