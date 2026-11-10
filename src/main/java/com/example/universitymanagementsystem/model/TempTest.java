package com.example.universitymanagementsystem.model;

// TEMPORARY: delete this file before final delivery (Step 14).
public class TempTest {
    public static void main(String[] args) {
        UniversityManager m = new UniversityManager();

        m.addStudent(new UndergraduateStudent("Lee", "Ann", "555-0101", "U1", "1 Main St"));
        m.addStudent(new MSStudent("Cruz", "Ben", "555-0102", "M1", "2 Oak Ave"));
        m.addStudent(new PhDStudent("Shah", "Cal", "555-0103", "P1", "3 Pine Rd"));

        System.out.println("--- Register courses and grades ---");
        System.out.println(m.registerCourse("U1", "CS101"));   // expect null
        System.out.println(m.registerCourse("U1", "CS201"));   // expect null
        System.out.println(m.setGrade("U1", "CS101", 4.0));    // expect null
        System.out.println(m.setGrade("U1", "CS201", 3.0));    // expect null

        System.out.println("--- Project and dissertation ---");
        System.out.println(m.registerProject("M1", "Network Study"));      // expect null
        System.out.println(m.registerProject("P1", "Cache Design"));       // expect null
        System.out.println(m.registerDissertation("P1", "Compiler Theory")); // expect null

        System.out.println("--- Info for each student ---");
        System.out.println(m.findStudent("U1").getInfo());
        System.out.println(m.findStudent("M1").getInfo());
        System.out.println(m.findStudent("P1").getInfo());

        System.out.println("--- Errors (compare each to the contract text) ---");
        System.out.println(m.addStudent(new Student("Dup", "Dan", "555-0199", "U1", "9 Elm St"))); // expect false
        System.out.println(m.registerCourse("U1", "CS101"));         // already registered
        System.out.println(m.registerCourse("ZZZ", "CS101"));        // no student
        System.out.println(m.registerCourse("U1", "XX999"));         // no course
        System.out.println(m.setGrade("U1", "MA101", 3.5));          // not registered
        System.out.println(m.registerProject("U1", "Nope"));         // only MS and PhD
        System.out.println(m.registerDissertation("M1", "Nope"));    // only PhD
    }
}