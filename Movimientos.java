import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;

public class Movimientos extends JFrame implements Runnable {
    private Image buffer;
    private Image fondo;
    private static Thread hilo;
    private int x = 0, y = 320;
    private Boolean cambio = false;

    public Movimientos() {
        setTitle("figuras con movimiento");
        setSize(800, 533);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLocationRelativeTo(null);
        hilo = new Thread(this);
        hilo.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
        }
        actualizar(g);
    }

    private void actualizar(Graphics g1) {
        g1.setClip(0, 0, getWidth(), getHeight());
        buffer = createImage(getWidth(), getHeight());
        Graphics g = buffer.getGraphics();
        g.setClip(0, 0, getWidth(), getHeight());
        g.drawImage(fondo, 0, 0, this);

        ImageIcon img = new ImageIcon("paisaje.jpg");
        if (img.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
            g.drawImage(img.getImage(), 0, 0, 800, 533, this);
        } else {
            System.out.println("Error al cargar la imagen paisaje.jpg");
        }

        switch (x) {
            case 120, 160, 200, 360, 400:
                y = 250;
                cambio = false;
                break;
            case 440, 480:
                y = 200;
                cambio = false;
                break;
            case 520, 560, 600, 640:
                y = 130;
                cambio = false;
                break;
            default:
                y = 320;
                cambio = true;
                break;
        }
        System.out.println("x : " + x);
        if (cambio) {
            ImageIcon imgMario1 = new ImageIcon("mario01.png");
            if (imgMario1.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
                g.drawImage(imgMario1.getImage(), x, y, 70, 70, this);
            } else {
                System.out.println("Error al cargar la imagen mario01.png");
            }
        } else {
            ImageIcon imgMario2 = new ImageIcon("mario02.png");
            if (imgMario2.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
                g.drawImage(imgMario2.getImage(), x, y, 70, 70, this);
            } else {
                System.out.println("Error al cargar la imagen mario02.png");
            }
        }
        if (x <= 760) {
            x += 40;
        } else {
            x = 0;
        }
        g1.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                Thread.sleep(250);
            } catch (InterruptedException ex) {
            }
        }
    }
}