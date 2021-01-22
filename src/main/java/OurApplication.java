
import desserts.*;
import ecommerce.LaptopEntity;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import robot.RobotEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OurApplication {

    public static void main(String[] args) {
        try {
            hibernateSession = HibernateUtils
            		.buildSessionFactory()
                    .openSession();
            hibernateSession.beginTransaction();

            /* Insert some robots
            for (int i = 0; i <= 10; i++) {
                RobotEntity robot = new RobotEntity();
                robot.setName("awesome robot number:" + i);
                robot.setSwitchedOn((i % 2 == 0));
                robot.setEvil((i % 2 == 1));
                robot.setWeight(i * 100L);
                robot.getParts().put("motor", "electric");
                robot.getParts().put("power", "nuclear");
                robot.getParts().put("weapon", "hammer");
                hibernateSession.save(robot);
            }
             */

            /* Insert some laptops */
            for (int i = 0; i <= 10; i++) {
                LaptopEntity laptop = new LaptopEntity();
                laptop.setName("SomeBook" + i);
                laptop.setPrice(10F * i);
                hibernateSession.save(laptop);
            }

            /* Get all robots
            CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
            CriteriaQuery<RobotEntity> criteria = builder.createQuery(RobotEntity.class);
            criteria.from(RobotEntity.class);
            List<RobotEntity> robots = hibernateSession.createQuery(criteria).getResultList();

            robots.forEach((r) ->  {
                System.out.println(
                    "r: " + r.getId() +
                            " name:" + r.getName() +
                            " weight:" + r.getWeight() +
                            " on:" + r.getSwitchedOn() +
                            " evil:" + r.getEvil()
                );
                System.out.println("motor:" + r.getParts().get("motor"));
                System.out.println("power:" + r.getParts().get("power"));
                System.out.println("weapon:" + r.getParts().get("weapon"));
                System.out.println("====================");
            });
            */


            hibernateSession.getTransaction().commit();
        } catch(Exception sqlException) {
            if (null != hibernateSession.getTransaction()) {
                hibernateSession.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }

    static Session hibernateSession;

    static int counter = 0;

}
