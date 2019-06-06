package coll.EnrollmentManager;

import java.util.*;

public class EnrollmentManager {

	private static HashMap<String, Set<String>> enrollments = new HashMap<String, Set<String>>();

	/**
	 * Enrolls a student into a unit.
	 *
	 * @param unit
	 * @param student
	 */


	public static void enroll(String unit, String student) {

		if(enrollments.containsKey(unit)) {
			Set<String> studentTemp = new HashSet<String>();
			studentTemp.add(new String(student));
			enrollments.get(unit).addAll(studentTemp);

		} else {
			Set<String> studentTemp1 = new HashSet<String>();
			studentTemp1.add(new String(student));
			enrollments.put(unit, studentTemp1);

		}

		//enrollments.put(unit, studentTemp);
		//studentTemp.add(new String(student));
	}

	/**
	 * Gets the HashMap containing the current enrollments.
	 *
	 * @return
	 */
	public static HashMap<String, Set<String>> getEnrollments() {
		return enrollments;
	}

	/**
	 * Removes all enrollments form the HashMap.
	 */
	public static void wipeEnrollments() {
		enrollments.clear();
	}

	/**
	 * Withdraws a student from a unit.
	 *
	 * @param unit
	 * @param student
	 */
	public static void withdrawEnrollment(String unit, String student) {
		enrollments.get(unit).remove(student);
	}

	/**
	 * Withdraws a student from all units they are enrolled in.
	 *
	 * @param student
	 */
	public static void withdrawStudent(String student) {

		//enrollments.values().removeIf(val -> student.equals(student));
		//enrollments.values().remove(student);
		//enrollments.values().removeAll(Collections.singletonList(student));

		for (String unit : enrollments.keySet()) {
            withdrawEnrollment(unit, student);
        }


	}

	/**
	 * Gets a list of all students of a particular discipline. E.g. If discipline is
	 * "ABC" then return a collection of all students enrolled in units that start
	 * with "ABC", so ABC301, ABC299, ABC741 etc. This method is non-trivial so it
	 * would help to first implement the helper method matchesDiscipline (below).
	 *
	 * @param discipline
	 * @return
	 */
	public static Set<String> getStudents(String discipline) {
        Set<String> allStudentsIn = new HashSet<>();

        for (String unit : enrollments.keySet()) {
            if (matchesDiscipline(unit, discipline)) {
            	allStudentsIn.addAll(enrollments.get(unit));
            }
        }

        return allStudentsIn;
	}

	public static boolean matchesDiscipline(String unit, String discipline) {

        String unitTemp = unit.substring(0, 3);
        String disciplineTemp = discipline.substring(0, 3);

        return unitTemp.equalsIgnoreCase(disciplineTemp);
    	}
	}
