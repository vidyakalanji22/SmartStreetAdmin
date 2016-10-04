package com.msd.utils;

/**
 * pojo class for sensor information to map with firebase
 */
public class SensorInfo {
    private String sensorMake;
    private String sensorModel;
    private String sensorNumber;
    private String treeName;
    private Object installedDate;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Object installedDate) {
        this.installedDate = installedDate;
    }

    public String getSensorMake() {
        return sensorMake;
    }

    public String getSensorModel() {
        return sensorModel;
    }

    public String getSensorNumber() {
        return sensorNumber;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setSensorMake(String sensorMake) {
        this.sensorMake = sensorMake;
    }

    public void setSensorModel(String sensorModel) {
        this.sensorModel = sensorModel;
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumber = sensorNumber;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

}
