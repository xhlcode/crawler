package cn.xhlcode;

import java.io.IOException;
import java.io.Serializable;

public class People implements Serializable {
    private static final long serialVersionUID = -2092465505451534820L;


    private String name;

    private  Salary salary;



    public People(String name, Salary salary) {
        this.name = name;
        this.salary = salary;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(salary.getBasePay());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        salary = new Salary(new Double(0),in.readDouble());
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
