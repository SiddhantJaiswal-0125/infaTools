package org.example.Modals;

public class InitiatorTaskResponse {
    private String jobId;
    private String status;
    private String orgId;
    private String userId;
    private String selectedOrg;
    private String meterId;
    private String startDate;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSelectedOrg() {
        return selectedOrg;
    }

    public void setSelectedOrg(String selectedOrg) {
        this.selectedOrg = selectedOrg;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private String endDate;
    private String callbackUrl;
    private String createTime;
    private String updateTime;


}
//{
//        "jobId": "a3NifWzK28ycF3fLBqXkgs",
//        "status": "CREATED",
//        "orgId": "80bTjaasFejbEkQynNFnyT",
//        "userId": "6cs7gTpxCCakR1GCeCQ3an",
//        "selectedOrgId": "80bTjaasFejbEkQynNFnyT",
//        "meterId": "a2nB20h1o0lc7k3P9xtWS8",
//        "startDate": "2023-11-01T00:00:00Z",
//        "endDate": "2023-12-01T23:59:59Z",
//        "callbackUrl": "https://MyExportJobStatus.com",
//        "createTime": "2023-12-09T14:49:08Z",
//        "updateTime": "2023-12-09T14:49:08Z"
//        }