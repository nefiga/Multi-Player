package Game;

import entity.Player;
import graphics.SpriteSheet;
import net.GameClient;
import net.GameServer;
import net.Packet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.Map;

public class GameLoop extends Canvas implements Runnable {

    private JFrame frame;
    private Thread gameThread;
    private Screen screen;

    private static GameClient client;
    private static GameServer server;

    private Map<String, Player> players = new HashMap<String, Player>();

    private Player player;

    public static final int WIDTH = 350;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;

    private boolean serverRunning;
    private boolean running;
    private Dimension size;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public GameLoop() {
        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setSize(size);
        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT);
    }

    public void init() {
        int[] playerSprite = SpriteSheet.spriteSheet.getImage(0, 0, 16, 16);
        player = new Player("Nefiga", playerSprite, 50, 50, 16, 16);
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - startTime) / ns;
            startTime = currentTime;

            if (delta > 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("UPS " + updates + "  FPS " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        screen.clear(1454567);

        player.render(screen);

        int[] screenPixels = screen.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screenPixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void start() {
        client = new GameClient(this, "localhost");
        client.start();

        init();

        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        running = true;
    }

    public void startServer() {
        server = new GameServer(this);
        server.start();
        serverRunning = true;
    }

    public void stop() {
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        running = false;
    }

    public void addPlayer(Player player) {
        players.put(player.getUserName(), player);
    }

    public void removePlayer(String userName) {
        players.remove(userName);
    }

    public void renderPlayers(Screen screen) {
        String[] users = (String[]) (players.keySet().toArray());
        for (String u : users) {
            players.get(u).render(screen);
        }
    }

    public static void sendData(Packet packet) {
        client.sendData(packet.getData());
    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        if (JOptionPane.showConfirmDialog(game, "Start Sever?") == 0) {
            game.startServer();
        }
        game.start();
    }
}
