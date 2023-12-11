//package org.example.Modals;
//
//
//
//import com.opencsv.bean.CsvBindByName;
//import com.opencsv.bean.CsvBindByPosition;
//
//public class CSVobject_writer {
//    @CsvBindByPosition(position = 0)
//    @CsvBindByName(column = "Task ID")
//    private  String TaskID;
//
//    @CsvBindByName(column = "Task Name")
////    @CsvBindByPosition(position = 2)
//    private  String TaskName;
//
//    @CsvBindByName(column = "Task Object")
//    @CsvBindByPosition(position = 3)
//    private  String TaskObject;
//
//    @Override
//    public String toString() {
//        return "CSVobject_writer{" +
//                "TaskID='" + TaskID + '\'' +
//                ", TaskName='" + TaskName + '\'' +
//                ", TaskObject='" + TaskObject + '\'' +
//                ", TaskType='" + TaskType + '\'' +
//                ", TaskRunID='" + TaskRunID + '\'' +
//                ", ProjectName='" + ProjectName + '\'' +
//                ", FolderName='" + FolderName + '\'' +
//                ", OrgID='" + OrgID + '\'' +
//                ", EnvironmentID='" + EnvironmentID + '\'' +
//                ", Environment='" + Environment + '\'' +
//                ", CoresUsed='" + CoresUsed + '\'' +
//                ", StartTime='" + StartTime + '\'' +
//                ", EndTime='" + EndTime + '\'' +
//                ", Status='" + Status + '\'' +
//                ", MeteredValue='" + MeteredValue + '\'' +
//                ", AuditTime='" + AuditTime + '\'' +
//                ", OBMTaskTime='" + OBMTaskTime + '\'' +
//                ", ExecutionTime='" + ExecutionTime + '\'' +
//                '}';
//    }
//
//    @CsvBindByPosition(position = 4)
//    private  String TaskType;
//
//    @CsvBindByPosition(position = 5)
//    private  String TaskRunID;
//    @CsvBindByPosition(position = 6)
//    private  String ProjectName;
//
//    @CsvBindByPosition(position = 7)
//    private  String FolderName;
//    @CsvBindByPosition(position = 8)
//    private  String OrgID;
//    @CsvBindByPosition(position = 9)
//    private  String EnvironmentID;
//
//    @CsvBindByPosition(position = 10)
//    private  String Environment;
//
//    @CsvBindByPosition(position = 11)
//    private  String CoresUsed;
//
//
//    @CsvBindByPosition(position = 12)
//    private  String StartTime;
//
//    @CsvBindByPosition(position = 13)
//    private  String EndTime;
//
//    @CsvBindByPosition(position = 14)
//    private  String Status;
//
//    @CsvBindByPosition(position = 15)
//    private  String MeteredValue;
//
//    @CsvBindByPosition(position = 16)
//    private  String AuditTime;
//
//    @CsvBindByPosition(position = 17)
//    private  String OBMTaskTime;
//
//
//    @CsvBindByPosition(position = 18)
//    private String ExecutionTime;
//    public String getExecutionTime() {
//        return ExecutionTime;
//    }
//
//    public void setExecutionTime(String executionTime) {
//        this.ExecutionTime = executionTime;
//    }
//
//
//    public String getTaskID() {
//        return TaskID;
//    }
//
//    public void setTaskID(String taskID) {
//        TaskID = taskID;
//    }
//
//    public String getTaskName() {
//        return TaskName;
//    }
//
//    public void setTaskName(String taskName) {
//        TaskName = taskName;
//    }
//
//    public String getTaskObject() {
//        return TaskObject;
//    }
//
//    public void setTaskObject(String taskObject) {
//        TaskObject = taskObject;
//    }
//
//    public String getTaskType() {
//        return TaskType;
//    }
//
//    public void setTaskType(String taskType) {
//        TaskType = taskType;
//    }
//
//    public String getTaskRunID() {
//        return TaskRunID;
//    }
//
//    public void setTaskRunID(String taskRunID) {
//        TaskRunID = taskRunID;
//    }
//
//    public String getProjectName() {
//        return ProjectName;
//    }
//
//    public void setProjectName(String projectName) {
//        ProjectName = projectName;
//    }
//
//    public String getFolderName() {
//        return FolderName;
//    }
//
//    public void setFolderName(String folderName) {
//        FolderName = folderName;
//    }
//
//    public String getOrgID() {
//        return OrgID;
//    }
//
//    public void setOrgID(String orgID) {
//        OrgID = orgID;
//    }
//
//    public String getEnvironmentID() {
//        return EnvironmentID;
//    }
//
//    public void setEnvironmentID(String environmentID) {
//        EnvironmentID = environmentID;
//    }
//
//    public String getEnvironment() {
//        return Environment;
//    }
//
//    public void setEnvironment(String environment) {
//        Environment = environment;
//    }
//
//    public String getCoresUsed() {
//        return CoresUsed;
//    }
//
//    public void setCoresUsed(String coresUsed) {
//        CoresUsed = coresUsed;
//    }
//
//    public String getStartTime() {
//        return StartTime;
//    }
//
//    public void setStartTime(String startTime) {
//        StartTime = startTime;
//    }
//
//    public String getEndTime() {
//        return EndTime;
//    }
//
//    public void setEndTime(String endTime) {
//        EndTime = endTime;
//    }
//
//    public String getStatus() {
//        return Status;
//    }
//
//    public void setStatus(String status) {
//        Status = status;
//    }
//
//    public String getMeteredValue() {
//        return MeteredValue;
//    }
//
//    public void setMeteredValue(String meteredValue) {
//        MeteredValue = meteredValue;
//    }
//
//    public String getAuditTime() {
//        return AuditTime;
//    }
//
//    public void setAuditTime(String auditTime) {
//        AuditTime = auditTime;
//    }
//
//    public String getOBMTaskTime() {
//        return OBMTaskTime;
//    }
//
//    public void setOBMTaskTime(String OBMTaskTime) {
//        this.OBMTaskTime = OBMTaskTime;
//    }
//}
