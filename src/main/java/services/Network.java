package services;

import models.institutions.Bank;

import java.util.List;

/**
 * Created by: tituskc
 * Created On  Sat, Sep 02, 2017 at 10:26 AM.
 */
public interface Network
{
    void initializeAdjacencyMatrix();

    void initiateNetwork(double A, double theta, double gamma);

    List<Integer> getExposureVector(List<Integer> expVec, int pos);

    List<Bank> getBanks();

    void simulateShock(int pos, double shock, boolean isInitial);

    int getFailures();

    List<List<Integer>> getPrunedAdjacencyMatrix();

    void writeNetworkData();

    void writeMetaData(double initialShock);

    double getMarketLoss();

    double getEntropy();
}
