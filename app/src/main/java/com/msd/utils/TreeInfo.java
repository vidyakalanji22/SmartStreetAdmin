package com.msd.utils;

/**
 * Pojo class for tree information to map with firebase
 */

public class TreeInfo {

    private String sensorAttached;
    private String treeName;
    private String treeNumber;
    private Object installedDate;

    public Object getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Object installedDate) {
        this.installedDate = installedDate;
    }

        public String getSensorAttached() {
        return sensorAttached;
    }

    public String getTreeName() {
        return treeName;
    }

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setSensorAttached(String sensorAttached) {
        this.sensorAttached = sensorAttached;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }


}
