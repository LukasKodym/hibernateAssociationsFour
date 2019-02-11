package pl.lukas.bibernateAssociationsFour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.bibernateAssociationsFour.entity.*;

import java.util.List;

public class ManyToManyGetApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Department.class);
        conf.addAnnotatedClass(Employee.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Training.class);

        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int id = 3; // id z tabeli employee_training w bazie danych

        session.beginTransaction();

        String getTraining = "from Training"; // zapytanie hql zwracające wszystkie obiekty klasy Training

        Query query = session.createQuery(getTraining); // obiekt zapytania

        List<Training> resultList = query.getResultList(); // lista obiektów training które zwróci zapytanie

        for (Training training : resultList) { // wyświetlanie
            System.out.println("\n" + training);
            for (Employee employee : training.getEmployees()) {
                System.out.println("- " + employee);
            }
        }

/*//part2
        Training training = new Training("java training");

        Employee employeeOne = session.get(Employee.class, 118);
        Employee employeeTwo = session.get(Employee.class, 119);

        training.addEmployee(employeeOne);
        training.addEmployee(employeeTwo);

        session.persist(training);
*/

/*//part 1
        Training training = session.get(Training.class, id); // pobierniae obiektu training metodą get, przypisujemy do zmiennej

        System.out.println(training); // wyświetlanie

        for (Employee employee : training.getEmployees()) { // wyświetlanie o powiązanych z trainingiem obiektach klasey Employee
            System.out.println("- " + employee);
        }
*/

        session.getTransaction().commit();

        factory.close();

    }
}
