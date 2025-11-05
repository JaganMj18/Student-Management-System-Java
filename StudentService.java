import java.util.*;
import java.io.*;

public class StudentService 
{
    private List<Student> students = new ArrayList<>();
    private final String DATA_FILE = "students.dat";

    public StudentService() 
    {
        loadFromFile();
    }

    public void addStudent(Student s) 
    {
        students.add(s);
        saveToFile();
    }

    public List<Student> getAll() 
    {
        return new ArrayList<>(students);
    }

    public Student findById(int id) 
    {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }

    public List<Student> findByName(String name) 
    {
        List<Student> res = new ArrayList<>();
        for (Student s : students) if (s.getName().equalsIgnoreCase(name)) res.add(s);
        return res;
    }

    public boolean updateStudent(int id, String name, int age, String branch) 
    {
        Student s = findById(id);
        if (s == null) return false;
        s.setName(name);
        s.setAge(age);
        s.setBranch(branch);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(int id) 
    {
        Student s = findById(id);
        if (s == null) return false;
        students.remove(s);
        saveToFile();
        return true;
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() 
    {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                students = (List<Student>) obj;
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not load data file. Starting with empty list.");
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
