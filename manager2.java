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

    public void indicateRespiratoryStatus(Patient patient, int respirationRate, double oxygenSaturation) {
        
        if (patient.getWeight() < MIN_WEIGHT || patient.getWeight() > MAX_WEIGHT) {
            System.out.println("Suspicious weight!");
            LOGS.add("Invalid weight for " + patient.getPatientName());
        }

        if (oxygenSaturation < O2_THRESHOLD) {
            last_stat = "LOW O2";
            LOGS.add("Low O2 detected for " + patient.getPatientName());
        }
        
        if (respirationRate > RR_THRESHOLD) {
            last_stat = "FAST BREATHING";
            LOGS.add("Fast breathing detected for " + patient.getPatientName());
        }
        
        if (oxygenSaturation >= O2_THRESHOLD && respirationRate <= RR_THRESHOLD) {
            last_stat = "OK";
        }

        LOGS.add("Processed " + patient.getPatientName());
    }


    public String classifyBreathingFrequency(Patient patient, int respirationRate, boolean shouldPrintResult) {

        String classification = CLASSIFICATION_INVALID;

        if (patient.getWeight() <= 0 || patient.getWeight() > 1000) {
            LOGS.add("Bad weight: " + patient.getWeight());
            return classification;
        }

        if (respirationRate > 20) {
            classification = CLASSIFICATION_FAST;
        } else if (respirationRate > 12) {
            classification = CLASSIFICATION_NORMAL;
        } else if (respirationRate < 6) {
            classification = CLASSIFICATION_SUSPICIOUS;
        } else {
            classification = CLASSIFICATION_SLOW;
        }

        if (shouldPrintResult) {
            System.out.println("Class result=" + classification + " for " + patient.getPatientName());
        }

        return classification;
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