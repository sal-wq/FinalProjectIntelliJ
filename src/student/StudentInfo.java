package student;

public class StudentInfo {
    int ID;
    String Name;
    int Age;
    String Email;
    String Grade;
    Float Score;

    public StudentInfo(int ID, String name, int age, String email, String grade, Float score) {
        this.ID = ID;
        Name = name;
        Age = age;
        Email = email;
        Grade = grade;
        Score = score;
    }
    public StudentInfo(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public Float getScore() {
        return Score;
    }

    public void setScore(Float score) {
        Score = score;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Email='" + Email + '\'' +
                ", Grade='" + Grade + '\'' +
                ", Score=" + Score +
                '}';
    }
}
