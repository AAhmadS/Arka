package graphic;

public class GameLoop implements Runnable{
    private int fps;
    private final Runnable runnable;
    private final Thread thread;
    private volatile boolean running=false;

    public GameLoop(int fps,Runnable Runnable) {
        this.runnable = Runnable;
        this.thread = new Thread(this);
        this.fps=fps;
    }

    public void update() {
        if (runnable != null)
            runnable.run();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        int ns_per_update = 1000000000 / fps;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) * 1.0 / ns_per_update;
            lastTime = now;
            if (delta < 1) { sleep((long) (ns_per_update * (1 - delta)));
            }
            while (running && delta >= 1) {
                update();
                delta--;
            }
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time/1000000);
        } catch (InterruptedException e) {
            running = false;
            // we are Interrupted
        }
    }

    public void stop() {
        running = false;
    }

    public void start() {
        thread.start();
        running = true;
    }
}
