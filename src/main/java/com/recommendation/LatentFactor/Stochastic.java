package com.recommendation.LatentFactor;

import Jama.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Component
public class Stochastic {

    private static Logger logger = LoggerFactory.getLogger(Stochastic.class);

    private int factor;
    private int iteration;

    private int userNumber;
    private int itemNumber;

    private double alpha;
    private double lambda;

    private double[][] user;
    private double[][] item;

    private String trainFilePath;
    private String testFilePath;

    private Matrix userMatrix;
    private Matrix itemMatrix;


    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void setTrainFilePath(String trainFilePath) {
        this.trainFilePath = trainFilePath;
    }

    public void setTestFilePath(String testFilePath) {
        this.testFilePath = testFilePath;
    }

    public void setUserNumberAndItemNumber() throws IOException {

        if(("").equals(trainFilePath)) {
            logger.error("train file not specified ");
            return;
        }

        if(("".equals(testFilePath))){
            logger.error("test file not specified ");
        }

        int maxUserId = 0;
        int maxitemId = 0;

        File trainFile = new File(trainFilePath);
        File testFile = new File(testFilePath);

        FileInputStream fis = new FileInputStream(trainFile);
        Scanner scanner = new Scanner(fis);
        while (scanner.hasNext()){
            int userId = scanner.nextInt();
            int itemId = scanner.nextInt();
            scanner.nextDouble();
            maxUserId = maxUserId>userId?maxUserId:userId;
            maxitemId = maxitemId>itemId?maxitemId:itemId;
        }
        fis = new FileInputStream(testFile);
        scanner = new Scanner(fis);
        while (scanner.hasNext()){
            int userId = scanner.nextInt();
            int itemId = scanner.nextInt();
            scanner.nextDouble();
            maxUserId = maxUserId>userId?maxUserId:userId;
            maxitemId = maxitemId>itemId?maxitemId:itemId;
        }
        userNumber = maxUserId;
        itemNumber = maxitemId;
        if(fis!=null)
        fis.close();

    }

    public static void main(String[] args) {
        Stochastic test = new Stochastic();
        try {
            test.setTrainFilePath("/Users/victor/Desktop/2/data/train.txt");
            test.setTestFilePath("/Users/victor/Desktop/2/data/test.txt");
            test.setUserNumberAndItemNumber();
            System.out.println(test.userNumber);
            System.out.println(test.itemNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
