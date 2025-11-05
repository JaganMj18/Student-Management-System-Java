public class Student implements java.io.Serializable 
{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;
    private String branch;

    public Student(int id, String name, int age, String branch) 
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
    }

    public int getId() 
    { 
      return id; 
    }
    public String getName() 
    { 
      return name; 
    }
    public int getAge() 
    { 
      return age; 
    }
    public String getBranch() 
    { 
      return branch; 
    }

    public void setName(String name) 
    { 
      this.name = name; 
    }
    public void setAge(int age) 
    { 
      this.age = age; 
    }
    public void setBranch(String branch) 
    { 
      this.branch = branch; 
    }

    @Override
    public String toString() 
    {
        return String.format("ID: %d | Name: %s | Age: %d | Branch: %s", id, name, age, branch);
    }
}



