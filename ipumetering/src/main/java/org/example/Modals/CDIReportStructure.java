package org.example.Modals;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class CDIReportStructure {
    @CsvBindByName(column = "Task ID")
    private  String TaskID;

    @CsvBindByName(column = "Task Name")
    private  String TaskName;

    @CsvBindByName(column = "Task Object")
    private  String TaskObject;

    @CsvBindByName(column = "Task Type")
    private  String TaskType;

    @CsvBindByName(column = "Task Run ID")
    private  String TaskRunID;
    @CsvBindByName(column = "Project Name")
    private  String ProjectName;

    @CsvBindByName(column = "Folder Name")
    private  String FolderName;
    @CsvBindByName(column = "Org ID")
    private  String OrgID;
    @CsvBindByName(column = "Environment ID")
    private  String EnvironmentID;

    @CsvBindByName(column = "Environment")
    private  String Environment;

    @CsvBindByName(column = "Cores Used")
    private  String CoresUsed;


    @CsvBindByName(column = "Start Time")
    private  String StartTime;

    @CsvBindByName(column = "End Time")
    private  String EndTime;

    @CsvBindByName(column = "Status")
    private  String Status;

    @CsvBindByName(column = "Metered Value")
    private  String MeteredValue;

    @CsvBindByName(column = "Audit Time")
    private  String AuditTime;

    @CsvBindByName(column = "OBM Task Time(s)")
    private  String OBMTaskTime;


    @CsvBindByName(column = "Execution Time (In Hours)")

    private String ExecutionTime;
    public String getExecutionTime() {
        return ExecutionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.ExecutionTime = executionTime;
    }


    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    @Override
    public String toString() {
        return "CDIReportStructure{" +
                "TaskID='" + TaskID + '\'' +
                ", TaskName='" + TaskName + '\'' +
                ", TaskObject='" + TaskObject + '\'' +
                ", TaskType='" + TaskType + '\'' +
                ", TaskRunID='" + TaskRunID + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", FolderName='" + FolderName + '\'' +
                ", OrgID='" + OrgID + '\'' +
                ", EnvironmentID='" + EnvironmentID + '\'' +
                ", Environment='" + Environment + '\'' +
                ", CoresUsed='" + CoresUsed + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", Status='" + Status + '\'' +
                ", MeteredValue='" + MeteredValue + '\'' +
                ", AuditTime='" + AuditTime + '\'' +
                ", OBMTaskTime='" + OBMTaskTime + '\'' +
                ", executionTime='" + ExecutionTime + '\'' +
                '}';
    }

    public String getTaskObject() {
        return TaskObject;
    }

    public void setTaskObject(String taskObject) {
        TaskObject = taskObject;
    }

    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    public String getTaskRunID() {
        return TaskRunID;
    }

    public void setTaskRunID(String taskRunID) {
        TaskRunID = taskRunID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    public String getEnvironmentID() {
        return EnvironmentID;
    }

    public void setEnvironmentID(String environmentID) {
        EnvironmentID = environmentID;
    }

    public String getEnvironment() {
        return Environment;
    }

    public void setEnvironment(String environment) {
        Environment = environment;
    }

    public String getCoresUsed() {
        return CoresUsed;
    }

    public void setCoresUsed(String coresUsed) {
        CoresUsed = coresUsed;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMeteredValue() {
        return MeteredValue;
    }

    public void setMeteredValue(String meteredValue) {
        MeteredValue = meteredValue;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getOBMTaskTime() {
        return OBMTaskTime;
    }

    public void setOBMTaskTime(String OBMTaskTime) {
        this.OBMTaskTime = OBMTaskTime;
    }
}
