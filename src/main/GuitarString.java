package main;

import engine.Signal;

public class GuitarString extends Signal<Boolean> {

    private int count = 0;

    private RingBuffer buffer; // ring buffer
    // YOUR OTHER INSTANCE VARIABLES HERE

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {

        super(false);
        buffer = new RingBuffer((int) (44100 / frequency));
        filter(x -> x == true).onEvent(() -> {
            
            buffer.enqueue(100);
            this.tic();
        });
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {

        super(false);

        buffer = new RingBuffer(init.length);
        for (int i = 0; i < init.length; i++) {
            buffer.enqueue(init[i]);
        }

        filter(x -> x).onEvent(() -> {

            tic();
        });
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {

        for (int i = buffer.size(); i > 0; i--) {
            buffer.dequeue();
        }
        for (int i = 0; i < buffer.length(); i++) {
            buffer.enqueue(Math.random() - .5);
        }
    }

    // advance the simulation one time step
    public void tic() {

        double a1 = buffer.dequeue();
        double a2 = buffer.peek();
        System.out.println(a2);
        StdAudio.play(a2);
        buffer.enqueue((a1 + a2) * .994);
        count++;
    }

    // return the current sample
    public double sample() {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() {
        return count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = {.2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3};
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}
