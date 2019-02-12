package pl.lukas.bibernateAssociationsFour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.bibernateAssociationsFour.entity.*;

import java.util.List;

public class ManyToManyHqlApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Department.class);
        conf.addAnnotatedClass(Employee.class);
        conf.addAnnotatedClass(Training.class);
        conf.addAnnotatedClass(Property.class);

        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        // zmienne
        int minEmployeeNumber = 6; // index
        String course = "javascript";
        int trainingNumber = 1;
        int maxSalary = 12000;

        // zapytania hql piszemy przed rozpoczęciem sesji

        String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber";
        String getEmployee = "select e from Employee e join e.trainings t where t.name=:course";
        String getEmployeeTwo = "select e from Employee e where size(e.trainings) =:trainingNumber and e.salary =:maxSalary";

        session.beginTransaction();

        // obiekt zapytania tworzymy za pomocą obiektu Query po rozpoczęciu transakcji

        Query query = session.createQuery(getEmployeeTwo);


// part3
        // przypisanie zmiennej  do parametru w zapytaniu hql
        query.setParameter("trainingNumber", trainingNumber);
        query.setParameter("maxSalary", maxSalary);
        List<Employee> resultList = query.getResultList();
        for (Employee employee : resultList) {
            System.out.println(employee);
        }

/*
// part2
        // przypisanie zmiennej  do parametru w zapytaniu hql
        query.setParameter("course", course);
        List<Employee> resultList = query.getResultList();
        for (Employee employee : resultList) {
            System.out.println(employee);
        }
*/

/*
 // part1
        // przypisanie zmiennej  do parametru w zapytaniu hql
        query.setParameter("minEmployeeNumber", minEmployeeNumber);
        List<Training> resultList = query.getResultList();
        for (Training training : resultList){
            System.out.println(training);
        }
*/
        session.getTransaction().commit();
        factory.close();
    }
}
