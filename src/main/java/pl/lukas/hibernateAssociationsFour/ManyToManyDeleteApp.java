package pl.lukas.bibernateAssociationsFour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.bibernateAssociationsFour.entity.*;

public class ManyToManyDeleteApp {

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

        int idEmployee = 119; // odczytane z bazy danych
        int idTraining = 3; // odczytane z bazy danych

        session.beginTransaction();

/*
//part1
        Employee employee = session.get(Employee.class, idEmployee); // pobieranie obiektu Employee
        session.delete(employee); // usuwanie
//end part1-------------------------
*/

//part2
        Training training = session.get(Training.class, idTraining); // pobierniae obiektu Training
        session.delete(training);
//end part2-------------------------

        session.getTransaction().commit();

        factory.close();
    }
}
