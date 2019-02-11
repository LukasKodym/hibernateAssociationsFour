package pl.lukas.bibernateAssociationsFour;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.bibernateAssociationsFour.entity.*;

public class ManyToManySaveApp {

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

        session.beginTransaction();

        Training training = new Training("sales training");
        Employee employeeOne = new Employee("Johny","Depp", 16000);
        Employee employeeTwo = new Employee("Brad","Pit", 16000);

        training.addEmployee(employeeOne);
        training.addEmployee(employeeTwo);

        session.persist(training); // utrwalanie w bazie danych obiektu szkolenia

        session.getTransaction().commit();

        factory.close();

    }


}
