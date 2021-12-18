package com.sipsewanaInstitue.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Program implements SuperEntity {
    @Id
    private String pid;
    private String program;
    private String duration;
    private double fee;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<com.sipsewanaInstitue.entity.RegistrationDetail> registrationDetailList;

    public Program() {
    }

    public Program(String pid, String program, String duration, double fee) {
        this.setPid(pid);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public Program(String pid, String program, String duration, double fee, List<RegistrationDetail> registrationDetailList) {
        this.setPid(pid);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
        this.setRegistrationDetailList(registrationDetailList);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<RegistrationDetail> getRegistrationDetailList() {
        return registrationDetailList;
    }

    public void setRegistrationDetailList(List<RegistrationDetail> registrationDetailList) {
        this.registrationDetailList = registrationDetailList;
    }
}
