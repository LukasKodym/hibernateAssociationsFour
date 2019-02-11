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

        int minEmployeeNumber = 6; // index
        // zapytania hql piszemy przed rozpoczęciem sesji

        String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber";

        session.beginTransaction();

        // obiekt zapytania tworzymy za pomocą obiektu Query po rozpoczęciu transakcji

        Query query = session.createQuery(getTraining);
        // przypisanie zmiennej index do parametru w zapytaniu hql
        query.setParameter("minEmployeeNumber", minEmployeeNumber);

        List<Training> resultList = query.getResultList();

        for (Training training : resultList){
            System.out.println(training);
        }



        session.getTransaction().commit();

        factory.close();

    }
}
