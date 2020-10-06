import org.junit.Test;
import org.junit.Before;

public class PuckhaberEfficiencyTests {
    private SinglyLinkedList<String> list;

    public static final int TIMEOUT = 1000;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Disclaimer:
     * This is a program that sort of helps give a visualization of how long
     * it takes to run a method. It uses the elapsed time of the computer
     * between when it starts and finishes running the method. This is called
     * "wall clock time" and isn't a great estimate of how many Operations
     * the method is doing.
     *
     * To mitigate noise I have it run 50 "quality samples"
     * and it prints the average values. As a result, this test takes
     * a long time to execute, especially on slower computers.
     *
     * If the graph is more-or-less constant (you can ignore the first column),
     * then you have O(1). Congratulations!
     * If the graph is more-or-less an upward slope,
     * then you have O(n). Make sure this is what the method should be doing!
     *
     * But the only way to know for sure is to read your method, maybe
     * debug it a little by putting a counter or print out lines to keep
     * track of how many operations it is doing with different sizes.
     * Good luck!
     */
    @Test(timeout = TIMEOUT)
    public void addToFrontEfficiencyTest() {
        long start, end;
        String testData = "hamberder";

        int numSamples = 20;
        int numQualitySamples = 50;
        int maxSize = 1000;
        int numTestMethodRuns = 100;
        int increment = maxSize / numSamples;
        long[][] timeValues = new long[numSamples][numQualitySamples];

        boolean doTheFirstTestAgain = true;

        for (int qs = 0; qs < numQualitySamples; qs++) {

            for (int test = 0; test < numSamples; test++) {
                list = new SinglyLinkedList<String>();

                // prepopulate linked list to size n
                for (int i = 0; i < increment * test; i++) {
                    list.addToFront(testData);
                    // list.addAtIndex(list.size() / 2, testData);
                }

                /**
                 * Efficiency testing part
                 */
                start = System.nanoTime();
                for (int i = 0; i < numTestMethodRuns; i++) {
                    /**
                     * Put the method you want to test here:
                     */
                    // list.addAtIndex((increment * test) / 2, testData);
                    // list.addAtIndex(list.size() / 2, testData);
                    list.addAtIndex(0, testData);
                    /**
                     * Avoid doing printing/calculations other than the method
                     * you are trying to test inside ^this loop^
                     */
                }
                end = System.nanoTime();
                timeValues[test][qs] = end - start;

                if (doTheFirstTestAgain) {
                    test = -1;
                    doTheFirstTestAgain = false;
                }
            }
        }

        /**
         * Averaging part
         */

        long[] avgTimeValues = new long[numSamples];

        for (int test = 0; test < timeValues.length; test++) {
            long sampleSum = 0;
            for (int qs = 0; qs < numQualitySamples; qs++) {
                sampleSum += timeValues[test][qs];
            }
            avgTimeValues[test] = sampleSum / numQualitySamples;
        }


        /**
         * Normalizing part
         */
        // get max value
        long maxValue = 0;
        for (int i = 0; i < timeValues.length; i++) {
            if (avgTimeValues[i] > maxValue) {
                maxValue = avgTimeValues[i];
            }
            // System.out.println(avgTimeValues[i]);
        }
        System.out.println("max: " + maxValue + "ns");

        /**
         * Labels
         */

        // labels
        String yLabelMessage = "O(1)?";
        int yLabelSize = yLabelMessage.length();

        String xLabelMessage = "n";
        int xLabelSize = xLabelMessage.length();

        String titleMessage = "O(n) graph";

        /**
         * Graph Parameters
         */
        int originX = yLabelSize + 2;
        int originY = xLabelSize + 2;

        int windowHeight = 10;
        int windowWidth = numSamples + originX;
        int yMax = 7;
        int xMax = numSamples;


        /**
         * Plotting part
         */

        // generate O plot
        boolean[][] plot = new boolean[xMax][yMax];

        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                //debug
                /*System.out.printf("(%d / %d)*(%d)",
                        maxValue, xMax, windowHeight - y);*/
                if (avgTimeValues[x] > (maxValue / yMax)*(yMax - y)) {
                    plot[x][y] = true;
                    // System.out.print(avgTimeValues[x] % 9);
                } else {
                    // System.out.print(" ");
                    plot[x][y] = false;
                }
            }
            // System.out.print("\n");
        }


        /**
         * Drawing part
         */
        // print graph

        // print label
        System.out.println(titleMessage);

        int yLower = (windowHeight - originY) - yMax;
        if (yLower < 0) {
            yLower = 0;
            System.out.println("^^^^^^^^^(top is cut off)");
        }

        int yUpper = yLower + yMax;
        // System.out.printf("yLower: %d yUpper: %d \n", yLower, yUpper);

        for (int y = 0; y < windowHeight; y++) {
            for (int x = 0; x < windowWidth; x++) {

                // printing plot
                boolean printed = false;
                if (y >= yLower && y < yUpper) {
                    if (x >= originX && x < originX + xMax) {
                        // print value
                        //System.out.printf("[%d][%d]", x - originX,
                        //   yLower + y);
                        if (plot[x - originX][y - yLower]) {
                            System.out.print((x - originX) % 10);
                        } else {
                            System.out.print(" ");
                        }
                        printed = true;
                    }
                }
                // printing labels and axes
                if (!printed) {
                    if (x == originX - 1) {
                        System.out.print("|");
                    } else if (y == yUpper) {
                        System.out.print("-");
                    } else if (y == (yUpper + yLower) / 2) {
                        if (x == 0) {
                            System.out.print(yLabelMessage);
                        } else if (x < 0 + yLabelSize) {
                            // message padding
                        } else {
                            System.out.print(" ");
                        }
                    } else if (y == (yUpper + windowHeight) / 2) {
                        int messageStart = (originX + windowWidth) / 2;
                        if (x == messageStart) {
                            System.out.print(xLabelMessage);
                        } else if (x < (originX + windowWidth) / 2
                                + xLabelSize && x > messageStart) {
                            // message padding
                        } else {
                            System.out.print(" ");
                        }
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
