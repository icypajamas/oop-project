import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Composed by Student
class Transcript implements Serializable {
    private List<ResultEntry> results;

    @Override
    public String toString() {
        return "Transcript{" +
                "results=" + results +
                '}';
    }

    public Transcript(List<ResultEntry> r) {
        results = r;
    }

    public List<ResultEntry> getResults() {
        return results;
    }

    public void setResults(List<ResultEntry> results) {
        this.results = results;
    }

    public Transcript() {
        results = new ArrayList<>();
    }

    public void addResultEntry(ResultEntry result) {
        results.add(result);
    }

    public double getGPA() {
        double points = 0;
        double totalHours = 0;
        for (ResultEntry result : results) {
            double creditHours = result.getCourse().getCreditHours();
            double marks = result.getMarksObtained();
            points += (creditHours * marksToGradePoints(marks));
            totalHours += creditHours;
        }
        if (totalHours != 0) {
            return (points / totalHours);

        } else {
            return -1;
        }
    }

    public double marksToGradePoints(double marks) {
        if (marks >= 90) {
            return 4.0;
        } else if (marks >= 80) {
            return 3.7;
        } else if (marks >= 70) {
            return 3.0;
        } else if (marks >= 60) {
            return 2.0;
        } else if (marks >= 50) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public double getTotalMarks() {
        double totalMarks = 0;
        for (ResultEntry result : results) {
            totalMarks += result.getMarksObtained();
        }
        return totalMarks;
    }

    public String toReportString() {
        if (results.isEmpty()) {
            return "No results available.";
        }
        StringBuilder text = new StringBuilder();
        text.append("------ TRANSCRIPT ------\n\n");

        for (ResultEntry result : results) {
            text.append(result.courseString()).append("\n");
        }

        text.append("\n------------------------\n");
        text.append(String.format("GPA: %.2f%n", getGPA()));
        text.append(String.format("Total Marks: %.2f%n", getTotalMarks()));

        return text.toString();
    }
}

// Composed by Transcript
class ResultEntry implements Serializable {
    private double marksObtained;
    private Course course;

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ResultEntry(double marksObtained, Course course) {
        setMarksObtained(marksObtained);
        this.course = course;
    }

    public ResultEntry() {
        marksObtained = 0;
        course = new Course();
    }

    public String courseString() {
        return course.getCourseCode() + " - " +
                course.getTitle() +
                " (" + course.getCreditHours() + " CH): " +
                marksObtained + "/100";
    }

    @Override
    public String toString() {
        return "ResultEntry{" +
                "marksObtained=" + marksObtained +
                ", course=" + course +
                '}';
    }
}