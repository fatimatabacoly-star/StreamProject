package org.blitmatthew.service;

import org.blitmatthew.entity.Student;
import org.blitmatthew.data.DataRetriever;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentOps {
    private List<Student> students = DataRetriever.getStudents();

    public Integer getStudentCount() {
        return students.size();
    }

// 1) Find all students with GPA above 3.5
// iterate over the students
// add a conditional check to compare student gpa to 3.5
// return new list of students who met the condition

    public List<Student> getStudentsWithGpaAbove35() {
        return students.stream()
                .filter(student -> student.getGpa() > 3.5)
                .toList();
    }

// 2) List students under 20 years old
// iterate over the students
//  add a condition check to compare students age to under 20 years old
// return new list of students age under 20 years old

    public List<Student> getStudentsUnder20YearsOld() {
        return students.stream()
                .filter(student -> student.getAge() < 20)
                .toList();
    }

// 3) Identify all international students
// iterate over the students
// add a condition check to identify international students
// return new list of international students

    public List<Student> listInternationalStudents() {
        return students.stream()
                .filter(student -> student.isInternational())
                .toList();
    }

// 4) Find students in computer science major
// iterate over the students
// add a conditional check to identify computer science student major
// return new list of computer science major students

    public List<Student> listComputerScienceStudents() {
        return students.stream()
                .filter(student ->
                        student.getMajor() != null &&
                                student.getMajor().equalsIgnoreCase("Computer Science"))
                .toList();

    }

// 5) List students from specific universities
// iterate over the students
// add a condition check to match the specific university
// return new list of students from that university

    public List<Student> listStudentsFromUniversity(String university) {
        return students.stream()
                .filter(student -> student.getUniversity() != null)
                .filter(student -> student.getUniversity().equalsIgnoreCase(university))
                .toList();

    }

// 6) Identify students with scholarship
// iterate over students
// add a conditional check to identify students with scholarship
// return new list of students with scholarship

    public List<Student> listScholarshipStudents() {
        return students.stream()
                .filter(student -> student.isScholarshipRecipient())
                .toList();

    }

// 7) Filter students by graduation year
// iterate over students
// add a conditional check to identify students by graduation year
// return new list of students by graduation year

    public List<Student> listStudentsByGraduationYear(int year) {
        return students.stream()
                .filter(student -> student.getGraduationYear() == year)
                .toList();

    }

// 8) Find students with exactly 60 credit hours
// iterate over students
// add a conditional check to identify students with exactly 60 credit hours
// return new list of students with exactly 60 credit hours

    public List<Student> listStudentsWithExactly60CreditHours() {
        return students.stream()
                .filter(student -> student.getCreditHours() == 60)
                .toList();

    }

// 9) Students with GPA between 3.0 and 3.5 (inclusive)
// iterate over students
// add a conditional check to identify students with GPA between 3.0 and 3.5
// return new list of students with GPA between 3.0 and 3.5

    public List<Student> listStudentsGpaBetween30And35() {
        return students.stream()
                .filter(student -> student.getGpa() >= 3.0 && student.getGpa() <= 3.5)
                .toList();

    }
// 10) International students in STEM majors (basic STEM keyword list)
// iterate over students
// add a conditional check to identify students in STEM majors
// return new list of students in STEM majors

    public List<Student> listInternationalStudentsInStemMajors() {
        return students.stream()
                .filter(student -> student.isInternational())
                .filter(student -> student.getMajor() != null)
                .filter(student -> student.getMajor().equalsIgnoreCase("Computer Science")
                        || student.getMajor().equalsIgnoreCase("Software Engineering")
                        || student.getMajor().equalsIgnoreCase("Information Technology")
                        || student.getMajor().equalsIgnoreCase("Cybersecurity")
                        || student.getMajor().equalsIgnoreCase("Mathematics")
                        || student.getMajor().equalsIgnoreCase("Engineering")
                        || student.getMajor().equalsIgnoreCase("Data Science")
                        || student.getMajor().equalsIgnoreCase("Statistics")
                        || student.getMajor().equalsIgnoreCase("Physics")
                        || student.getMajor().equalsIgnoreCase("Chemistry")
                        || student.getMajor().equalsIgnoreCase("Biology")
                )
                .toList();
    }

// 11) Scholarship recipients with high credit hours (>= minCredits)
// iterate over students
// add a conditional check to identify scholarship recipients with high credit hours
// return a new list of scholarship recipients with high credit hours

    //students.stream()
// iterates over all students

    // Student::isScholarshipRecipient
//keeps only students with scholarship (true)

    //student.getCreditHours() >= minCredits
//keeps students meeting the credit-hour requirement

    //.toList()
//returns a new list (does not modify original list)

    public List<Student> listScholarshipStudentsWithHighCreditHours(int minCredits) {
        return students.stream()
                .filter(Student::isScholarshipRecipient)
                .filter(student -> student.getCreditHours() >= minCredits)
                .toList();
    }

//12) Students in top 10% of their class by GPA
// iterate over students
// add a conditional check to identify students in top 10% of their class by GPA
// return new list of students in top 10% of their class by GPA

    public List<Student> listTop10PercentStudentsByGpa() {
        int total = students.size();
        int topCount = (int) Math.ceil(total * 0.10);

        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(topCount)
                .toList();
    }

// 13) Students with phone numbers from specific area codes
// iterate over students
// add a conditional check to identify students with phone numbers from specific area codes
// return a new list of students with phone numbers from the specified area code

    public List<Student> listStudentsByPhoneAreaCode(String areaCode) {
        return students.stream()
                .filter(student -> student.getPhoneNumber() != null)
                .filter(student -> student.getPhoneNumber().startsWith(areaCode))
                .toList();
    }

// 14) Find students born in specific months (1-12)
// iterate over students
// add a conditional check to identify students born in specific months (1-12)
// return new list of students born in specific months (1-12)

    public List<Student> listStudentsBornInMonth(int month1to12) {
        if (month1to12 < 1 || month1to12 > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd

        return students.stream()
                .filter(s -> s.getDateOfBirth() != null && !s.getDateOfBirth().isBlank())
                .filter(s -> {
                    try {
                        LocalDate dob = LocalDate.parse(s.getDateOfBirth(), fmt);
                        return dob.getMonthValue() == month1to12;
                    } catch (DateTimeParseException e) {
                        return false;
                    }
                })
                .toList();
    }

// 15) Identify students near graduation (within 2 years)
// iterate over students
// add a conditional check to identify students near graduation (within 2 years)
// return new list of students near graduation (within 2 years)

    public List<Student> listStudentsNearGraduation(int currentYear) {
        return students.stream()
                .filter(student -> student.getGraduationYear() >= currentYear)
                .filter(student -> student.getGraduationYear() <= currentYear + 2)
                .toList();
    }

// 16) Calculate total number of students per major
// iterate over students
// add a conditional check to identify total number of students per major
// return new list of total number of students per major

    public Map<String, Long> countStudentsPerMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        s -> (s.getMajor() == null || s.getMajor().isBlank())
                                ? "Unknown"
                                : s.getMajor(),
                        Collectors.counting()
                ));
    }

// 17) Find average GPA by major
// iterate over students
// add a conditional check to identify average GPA by major
// return new list of average GPA by major

    public Map<String, Double> averageGpaByMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.getMajor() == null ? "Unknown" : student.getMajor(),
                        Collectors.averagingDouble(Student::getGpa)
                ));
    }

// 18) Determine percentage of international students
// iterate over students
// add a conditional check to identify percentage of international students
// return a new list of percentage of international students

    public double calculateInternationalStudentPercentage() {
        long total = students.size();
        if (total == 0) return 0.0;

        long internationalCount = students.stream()
                .filter(Student::isInternational)
                .count();

        return (internationalCount * 100.0) / total;
    }

// 19) Find the oldest student (using sorted)
// iterate over students
// add a conditional check to identify the oldest student
// return a new list of oldest student

    public Student getOldestStudent() {
        return students.stream()
                .max(Comparator.comparingInt(Student::getAge))
                .orElse(null);
    }

// 20) Find total credit hours across all students
// iterate over students
// add a conditional check to identify total credit hours across all students
// return a new list of total credit hours across all students

    public int totalCreditHours() {
        return students.stream()
                .mapToInt(Student::getCreditHours)
                .sum();
    }

// 21) Compute median GPA
// iterate over students
// add a conditional check to compute median GPA
// return new list of median GPA

    public double computeMedianGpa() {
        int n = students.size();
        if (n == 0) return 0.0;

        return students.stream()
                .mapToDouble(Student::getGpa)
                .sorted()
                .skip((n - 1) / 2)
                .limit(2 - (n % 2))
                .average()
                .orElse(0.0);
    }

// 22) Find the youngest and oldest students
// iterate over students
// add a conditional check to identify the youngest and oldest students
// return a new list of youngest and oldest students

    public List<Student> findYoungestAndOldestStudents() {
        Student youngest = students.stream()
                .min(Comparator.comparingInt(Student::getAge))
                .orElse(null);

        Student oldest = students.stream()
                .max(Comparator.comparingInt(Student::getAge))
                .orElse(null);

        return List.of(youngest, oldest);
    }

// 23) Calculate scholarship distribution percentage
// iterate over students
// add a conditional check to identify scholarship distribution percentage
// return a new list of scholarship distribution percentage

    public double calculateScholarshipDistributionPercentage() {
        long total = students.size();
        if (total == 0) return 0.0;

        long scholarshipCount = students.stream()
                .filter(Student::isScholarshipRecipient)
                .count();

        return (scholarshipCount * 100.0) / total;

    }

// 24) Determine gender ratio in different majors
// iterate over students
// add a conditional check to identify gender ratio in different majors
// return a new list of gender ratio in different majors

    public Map<String, Long> genderRatioForMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor() != null && s.getMajor().equalsIgnoreCase(major))
                .filter(s -> s.getGender() != null && !s.getGender().isBlank())
                .collect(Collectors.groupingBy(
                        Student::getGender,
                        Collectors.counting()
                ));
    }

// 25) Find universities with the highest average GPA
// iterate over students
// add a conditional check to identify universities with the highest average GPA
// return a new list of universities with the highest average GPA

    public List<String> findUniversitiesWithHighestAverageGpa() {

        // Step 1: calculate average GPA per university
        Map<String, Double> averages = students.stream()
                .filter(student -> student.getUniversity() != null)
                .collect(Collectors.groupingBy(
                        Student::getUniversity,
                        Collectors.averagingDouble(Student::getGpa)
                ));

        // Step 2: find the highest average GPA
        double highest = averages.values().stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);

        // Step 3: return universities with that highest GPA
        return averages.entrySet().stream()
                .filter(entry -> entry.getValue() == highest)
                .map(Map.Entry::getKey)
                .toList();
    }

// 26) Identify students matching multiple criteria:

// Iterate over students
// Add conditional checks to identify:
//    - GPA greater than 3.7
//    - International students
//    - Students in STEM majors
//    - Scholarship recipients
// Return a new list of students matching all criteria

    public List<Student> listTopInternationalStemScholarshipStudents() {

        return students.stream()
                // GPA > 3.7
                .filter(student -> student.getGpa() > 3.7)

                // International students
                .filter(Student::isInternational)

                // Scholarship recipients
                .filter(Student::isScholarshipRecipient)

                // STEM majors
                .filter(student -> student.getMajor() != null)
                .filter(student ->
                        student.getMajor().equalsIgnoreCase("Computer Science")
                                || student.getMajor().equalsIgnoreCase("Software Engineering")
                                || student.getMajor().equalsIgnoreCase("Information Technology")
                                || student.getMajor().equalsIgnoreCase("Cybersecurity")
                                || student.getMajor().equalsIgnoreCase("Mathematics")
                                || student.getMajor().equalsIgnoreCase("Engineering")
                                || student.getMajor().equalsIgnoreCase("Data Science")
                                || student.getMajor().equalsIgnoreCase("Statistics")
                                || student.getMajor().equalsIgnoreCase("Physics")
                                || student.getMajor().equalsIgnoreCase("Chemistry")
                                || student.getMajor().equalsIgnoreCase("Biology")
                )
                // Return new list of students matching all criteria
                .toList();
    }

// 27) Create a comprehensive student profile ranking system
// iterate over students
// add a conditional check to create comprehensive student profile ranking system
// return a new list of student profile ranking system

    public List<Student> rankStudentsByProfileScore() {
        return students.stream()
                .sorted(Comparator
                        .comparingDouble(Student::getGpa).reversed()
                        .thenComparingInt(Student::getCreditHours).reversed()
                        .thenComparing(Student::isScholarshipRecipient).reversed()
                        .thenComparing(Student::isInternational).reversed()
                )
                .toList();
    }

// 28) Develop a predictive model for scholarship likelihood
// iterate over students
// add a conditional check to develop a predictive model for scholarship likelihood
// return a new list of a predictive model for scholarship likelihood

    public List<Student> predictHighScholarshipLikelihood() {
        return students.stream()
                .filter(s -> s.getGpa() >= 3.7)
                .filter(Student::isInternational)
                .filter(Student::isScholarshipRecipient)
                .toList();
    }

// 29) Analyze correlation between age and academic performance
// iterate over students
// group students by age
// calculate average GPA per age
// return age vs average GPA

    public Map<Integer, Double> analyzeAgeVsGpa() {
        return students.stream()
                .filter(s -> s.getAge() > 0)
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        Collectors.averagingDouble(Student::getGpa)
                ));
    }

// 30) Group students by complex multidimensional criteria
// iterate over students
// add conditional checks to handle missing values
// build a composite key using multiple student attributes
// group students by the composite key and count students
// return grouped student counts

    public Map<String, Long> groupStudentsByMultiDimensionalCriteria() {
        return students.stream()
                .filter(s -> s.getMajor() != null && !s.getMajor().isBlank())
                .filter(s -> s.getUniversity() != null && !s.getUniversity().isBlank())
                .collect(Collectors.groupingBy(
                        s -> s.getMajor() + " | " +
                                s.getUniversity() + " | " +
                                s.getGraduationYear() + " | " +
                                (s.isInternational() ? "International" : "Local"),
                        Collectors.counting()
                ));

    }

}


































