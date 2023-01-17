package assn05;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
//        SimpleEmergencyRoom er = new SimpleEmergencyRoom();
//        er.addPatient("Chris", 5);
//        er.addPatient("Dain", 4);
//        er.addPatient("Jay", 2);
//        er.addPatient("Maggie", 2);
//        er.addPatient("Daniel", 7);
//        System.out.println("Priority patient: " + er.dequeue().getValue());


       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MinBinHeapER betterEr = new MinBinHeapER();
        betterEr.enqueue("David", 5);
        betterEr.enqueue("Jeff", 3);
        betterEr.enqueue("Christina", 6);
        betterEr.enqueue("John", 2);
        betterEr.enqueue("Hailey", 4);
        betterEr.enqueue("Mark", 5);
        betterEr.enqueue("Chris", 5);
        betterEr.enqueue("Ben", 2);
        System.out.println("Priority patient: " + betterEr.dequeue());


//        /*
//        Part 3
//         */
//        MinBinHeapER transfer = new MinBinHeapER(makePatients())
        int[] a = new int[10];
        a[0] = 3;
        a[1] = 10;
        a[2] = 7;
        a[3] = 4;
        a[4] = 11;
        a[5] = 17;

        Prioritized<String, Integer>[] initialEntries = new Prioritized[6];
        for(int i=0; i<6; i++) {
            Patient<String, Integer> patient = new Patient<>("string", a[i]);
            initialEntries[i] = patient;
        }
        MinBinHeapER<String, Integer> myHeapEr = new MinBinHeapER<>(makePatients());
        Prioritized[] arr = myHeapEr.getAsArray();
        for(int i = 0; i < myHeapEr.size(); i++) {
            System.out.println("Patient: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }

    }

    public static void fillER(MinBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[100000];
        for(int i = 0; i < 100000; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);
        long before = System.nanoTime();
        while(simplePQ.size() > 0) {
            simplePQ.dequeue();
        }
        long after = System.nanoTime();
        results[0] = after - before;
        results[1] = (after - before) / 100000;


        MinBinHeapER binHeap = new MinBinHeapER();
        fillER(binHeap);
        long before2 = System.nanoTime();
        while(binHeap.size() > 0) {
            binHeap.dequeue();
        }
        long after2 = System.nanoTime();
        results[2] = after2-before2;
        results[3] = (after2-before2) / 100000;

        return results;
    }



}



