import java.io.*;
import java.time.LocalDate;

class DataIO {
    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder b = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char ch = in.readChar();
            i++;
            if (ch == 0)
                more = false;
            else
                b.append(ch);
        }
        in.skipBytes(2 * (size - i));
        return b.toString();
    }

    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length())
                ch = s.charAt(i);
            out.writeChar(ch);
        }
    }
}

public class RandomAccessTest {
    public static void main(String[] args) {

    }

    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
    }

    class Employee implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private double salary;
        private LocalDate hireDay;

        public static final int NAME_SIZE = 40;
        public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

        public Employee() {
        }

        public Employee(String n, double s, int year, int month, int day) {
            name = n;
            salary = s;
            hireDay = LocalDate.of(year, month, day);
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public LocalDate getHireDay() {
            return hireDay;
        }

        /**
         * Raises the salary of this employee.
         * 
         * @byPercent the percentage of the raise
         */
        public void raiseSalary(double byPercent) {
            double raise = salary * byPercent / 100;
            salary += raise;
        }

        public String toString() {
            return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
        }
    }
}
