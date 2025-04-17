package com.Apooinments.Appoinments.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appoinments")
public class Appoinments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "appoinment_Id", nullable = false)
    private Integer appoinmentId;

//    private String PhysicianName;

    @Column(name = "patient_id", nullable = false)
    private  Integer patientID;

    @Column(name = "doctorname", nullable = false)
    private  String docname;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "appoinment_Date", nullable = false)
    private Date appoinment_Date;


    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "appoinment_Time", nullable = false)
    private Time appoinment_Time;

    private String reason;


    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public Integer getAppoinmentId() {
        return appoinmentId;
    }

    public void setAppoinmentId(Integer appoinmentId) {
        this.appoinmentId = appoinmentId;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public Date getAppoinment_Date() {
        return appoinment_Date;
    }

    public void setAppoinment_Date(Date appoinment_Date) {
        this.appoinment_Date = appoinment_Date;
    }

    public Time getAppoinment_Time() {
        return appoinment_Time;
    }

    public void setAppoinment_Time(Time appoinment_Time) {
        this.appoinment_Time = appoinment_Time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
