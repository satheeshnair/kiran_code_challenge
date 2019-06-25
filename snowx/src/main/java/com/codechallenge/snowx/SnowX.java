package com.codechallenge.snowx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SnowX {

    private static char[][] testData;
    private static char[][] hpShip;
    private static char[][] hpTorpedo;

    private static Source testDataSource;
    private static Source hpShipSource;
    private static Source hpTorpedoSource;

    private static final String TORPEDO = "TORPEDO";
    private static final String SHIP = "SHIP";


    public static void main(final String[] args) {

        //sample data
        final String filePathforHPTorpedo = "src/main/resources/HPTorpedo.snw";
        final String filePathforHPShip = "src/main/resources/HPship.snw";

        //test data
        final String filePathforTestData = "src/main/resources/TestData.snw";

        //hpShip Data
        hpShip = getInputFileData(filePathforHPShip);
        hpTorpedo = getInputFileData(filePathforHPTorpedo);
        testData = getInputFileData(filePathforTestData);

        testDataSource = new Source(testData,"MainData", testData.length * testData[0].length);
        hpShipSource = new Source(hpShip, SHIP,hpShip.length * hpShip[0].length);
        hpTorpedoSource = new Source(hpTorpedo, TORPEDO,hpTorpedo.length * hpTorpedo[0].length);

        List<Target> targetList = findMatchingPatterns(testData, hpTorpedo, hpShip);

        for (Target t: targetList) {
            if(t.getType() == SHIP) {
                System.out.println("target list " + t + " in the path specified.");
                break;
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
    private static List<Target> findMatchingPatterns(char[][] mainData, char[][] hpTorpedoData, char[][] hpShipData ) {
        List<Target> targetList = new ArrayList<Target>();
        //check torpedo
        checkPattern(mainData, hpTorpedoData, targetList, TORPEDO);
        checkPattern(mainData, hpShipData, targetList, SHIP);

        return targetList;
    }

    /*
     *  split the data and call find confidence method to get the confidence
     *
     * */
    private static void checkPattern(char[][] mainData, char[][] checkData, List<Target> targetData, String type) {
        for (int i = 0; i < mainData[0].length - checkData[0].length; i++) {
            for (int j = 0; j < mainData.length - checkData.length; j++) {
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
}
