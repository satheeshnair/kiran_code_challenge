package com.codechallenge.snowx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SnowX {

    private static final String TEST_DATA = "TEST_DATA";
    private static final String TORPEDO = "HPtorpedo";
    private static final String SHIP = "HPship";

    private static Source testDataSource;
    private static Source hpShipSource;
    private static Source hpTorpedoSource;

    private static List<Target> targetList;


    public static void main(final String[] args) {

        //test data path
        final String filePathforTestData = args.length > 0 ? args[0] : "src/main/resources/TestData.snw";

        //sample data path
        final String filePathforHPTorpedo = args.length > 1 ? args[1] : "src/main/resources/HPTorpedo.snw";
        final String filePathforHPShip = args.length > 2 ? args[2] : "src/main/resources/HPship.snw";

        char[][] inputData = getInputFileData(filePathforTestData);
        testDataSource = new Source(inputData, TEST_DATA, inputData.length * inputData[0].length);

        char[][] hpTorpedo = getInputFileData(filePathforHPTorpedo);
        hpTorpedoSource  = new Source(getInputFileData(filePathforHPTorpedo), SHIP,hpTorpedo.length * hpTorpedo[0].length);

        char[][] hpShip = getInputFileData(filePathforHPShip);
        hpShipSource = new Source(getInputFileData(filePathforHPShip), TORPEDO,hpShip.length * hpShip[0].length);

        targetList = findMatchingPatterns();


        System.out.println("target list size" + targetList.size());

        for (Target t: targetList) {
            if(t.getType() == TORPEDO) {
                System.out.println("target list " + t + " in the path specified.");
            }
        }
    }


    /*
    *  Function to read the data from the file input
    * */

    public static char[][] getInputFileData(String filename) {

        List<char[]> inputData = new ArrayList<char[]>();

        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while( (line = reader.readLine()) != null){
                inputData.add(line.toCharArray());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Please check for the presence of " + filename + " in the path specified.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Unable to read the file." + filename);
            e.printStackTrace();
        }



        int rows = inputData.size();
        int columns = rows > 0 ? inputData.get(0).length : 0;

        char[][] formattedData = new char[inputData.size()][columns];

        for (int i = 0; i < rows; i++) {
            formattedData[i] = inputData.get(i);
        }
        return formattedData;
    }


    /*
     *  find matching patterns for Torpedo and Ship
     *
     * */
    private static List<Target> findMatchingPatterns() {
        List<Target> targetList = new ArrayList<Target>();

        //check hptorpedo
        checkPattern(testDataSource.getData(), hpTorpedoSource.getData(), targetList, TORPEDO);

        //check hpship
        checkPattern(testDataSource.getData(), hpShipSource.getData(), targetList, SHIP);

        return targetList;
    }

    /*
     *  split the data and call find confidence method to get the confidence value
     *
     * */
    private static void checkPattern(char[][] mainData, char[][] checkData, List<Target> targetData, String type) {
        for (int i = 0; i <= mainData[0].length - checkData[0].length; i++) {
            for (int j = 0; j <= mainData.length - checkData.length; j++) {
                double confidenceLevel  = findConfidence(mainData, checkData, i, j);
                if (confidenceLevel > 0.6) {
                    targetData.add(new Target(type, i, j, confidenceLevel));
                }
            }
        }
    }


    /*
     * main logic to compare the data.
     *
     * */
    private static double findConfidence(char[][] mainData, char[][] checkData, int xCoordinate, int yCoordinate) {
        int similarityCount = 0;
        for (int i = 0; i < checkData[0].length; i++) {
            for (int j = 0; j < checkData.length; j++) {
                //check the pattern is matching
                if (checkData[j][i] == mainData[yCoordinate + j][xCoordinate + i]) {
                    similarityCount++;
                }
            }
        }
        return (double)similarityCount/(checkData.length * checkData[0].length);
    }

    public static List<Target> getTargetList() {
        return targetList;
    }
}
