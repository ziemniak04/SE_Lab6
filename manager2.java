// The RespiratoryStatusChecker class implements the following requirements:
// 1/ The system should classify the patient's respiratory status based on oxygen saturation (O2) and respiration rate (RR) readings as low O2 (O2 < 88), fast breathing (RR > 15), or normal. 
// 2/ The system should separately classify the patient's breathing frequency based on RR reading as normal (12-20 breaths per minute), suspicious (< 6 br/min), slow (6-12 br/min), or fast (> 20 br/min).
// 3/ The system should output both results for further processing.


import java.io.*;
import java.io.IOException;
import java.util.*;

public class RespiratoryStatusChecker {
    private static List<String> LOGS = new ArrayList<>();

    private static final double MIN_WEIGHT = 50;
    private static final double MAX_WEIGHT = 120;
    private static final double O2_THRESHOLD = 88;
    private static final int RR_THRESHOLD = 15;
    
    private static final String CLASSIFICATION_FAST = "FAST";
    private static final String CLASSIFICATION_NORMAL = "NORMAL";
    private static final String CLASSIFICATION_SUSPICIOUS = "SUSPICIOUS";
    private static final String CLASSIFICATION_SLOW = "SLOW";
    private static final String CLASSIFICATION_INVALID = "INVALID";

    private static String last_stat = null;

    public void indicateRespiratoryStatus(Patient pt, int rr, double o2) {
        
        if (pt.getWeight() < MIN_WEIGHT || pt.getWeight() > MAX_WEIGHT) {
            System.out.println("Suspicious weight!");
            LOGS.add("Invalid weight for " + pt.getPatientName());
        }

        if (o2 < O2_THRESHOLD) {
            last_stat = "LOW O2";
            LOGS.add("Low O2 detected for " + pt.getPatientName());
        }
        
        if (rr > RR_THRESHOLD) {
            last_stat = "FAST BREATHING";
            LOGS.add("Fast breathing detected for " + pt.getPatientName());
        }
        
        if (o2 >= O2_THRESHOLD && rr <= RR_THRESHOLD) {
            last_stat = "OK";
        }

        LOGS.add("Processed " + pt.getPatientName());
    }


    public String method2(Patient p, int rr, boolean print) {

        String cls = CLASSIFICATION_INVALID;

        if (p.getWeight() <= 0 || p.getWeight() > 1000) {
            LOGS.add("Bad weight: " + p.getWeight());
            return cls;
        }

        if (rr > 20) {
            cls = CLASSIFICATION_FAST;
        } else if (rr > 12) {
            cls = CLASSIFICATION_NORMAL;
        } else if (rr < 6) {
            cls = CLASSIFICATION_SUSPICIOUS;
        } else {
            cls = CLASSIFICATION_SLOW;
        }

        if (print) {
            System.out.println("Class result=" + cls + " for " + p.getPatientName());
        }

        return cls;
    }

}


class Patient {
    private String patientName;
    private double weight;
    private String condition;

    public Patient(String name, double weight, String condition) {
        this.patientName = name;
        this.weight = weight;
        this.condition = condition;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getWeight() {
        return weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}