package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        if (size() == 0) {
            return null;
        }
        if (size()==1) {
            Patient element = patients.get(0);
            patients.remove(0);
            return element;
        }
        Patient min = patients.get(size() - 1);
        for (int i = patients.indexOf(min); i > 0; i--) {
            Patient prev = patients.get(i - 1);
            if (min.getPriority().compareTo(prev.getPriority()) > 0) {
                min = prev;
            }
        }
        patients.remove(min);
        return min;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
