
import java.util.*;

public class Main 
{
    private static Scanner sc = new Scanner(System.in);
    private static StudentService service = new StudentService();

    public static void main(String[] args) 
    {
        System.out.println("===== Student Management System (Core Java) =====");
        boolean running = true;
        while (running) 
        {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) 
            {
                case 1 -> addStudent();
                case 2 -> displayAll();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> { System.out.println("Exiting... Goodbye!"); running = false; }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() 
    {
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    private static void addStudent() 
    {
        int id = readInt("Enter ID: ");
        if (service.findById(id) != null) 
        { 
          System.out.println("Student with this ID already exists."); 
          return; 
        }
        String name = readString("Enter Name: ");
        int age = readInt("Enter Age: ");
        String branch = readString("Enter Branch: ");
        Student s = new Student(id, name, age, branch);
        service.addStudent(s);
        System.out.println("Student added successfully.");
    }

    private static void displayAll() 
    {
        List<Student> all = service.getAll();
        if (all.isEmpty()) 
        { 
          System.out.println("No students found."); 
          return; 
        }
        System.out.println("--- Student List ---");
        for (Student s : all) System.out.println(s);
    }

    private static void searchStudent() 
    {
        System.out.println("Search by: 1) ID  2) Name");
        int opt = readInt("Choose: ");
        if (opt == 1) 
        {
            int id = readInt("Enter ID: ");
            Student s = service.findById(id);
            System.out.println(s == null ? "Not found." : s);
        }
        else if (opt == 2) 
        {
            String name = readString("Enter Name: ");
            List<Student> res = service.findByName(name);
            if (res.isEmpty())
              System.out.println("Not found."); 
            else 
              res.forEach(System.out::println);
        } 
        else 
          System.out.println("Invalid option.");
    }

    private static void updateStudent() 
    {
        int id = readInt("Enter ID to update: ");
        Student s = service.findById(id);
        if (s == null) 
        { 
          System.out.println("Student not found."); 
          return; 
        }
        String name = readString("Enter new Name (current: " + s.getName() + "): ");
        int age = readInt("Enter new Age (current: " + s.getAge() + "): ");
        String branch = readString("Enter new Branch (current: " + s.getBranch() + "): ");
        boolean ok = service.updateStudent(id, name, age, branch);
        System.out.println(ok ? "Updated successfully." : "Update failed.");
    }

    private static void deleteStudent() 
    {
        int id = readInt("Enter ID to delete: ");
        boolean ok = service.deleteStudent(id);
        System.out.println(ok ? "Deleted successfully." : "Student not found.");
    }

    private static int readInt(String prompt) 
    {
        while (true) 
        {
            try 
            {
                System.out.print(prompt);
                String line = sc.nextLine().trim();
                return Integer.parseInt(line);
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static String readString(String prompt) 
    {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
