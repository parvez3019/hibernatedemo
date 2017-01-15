import model.BookEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

/**
 * Created by aparvez on 1/15/17.
 */
public class Main {
  private static final SessionFactory ourSessionFactory;

  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();

      ourSessionFactory = configuration.buildSessionFactory();
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static Session getSession() throws HibernateException {
    return ourSessionFactory.openSession();
  }

  public static void main(final String[] args) throws Exception {

    final Session session = getSession();
    Metamodel metamodel = session.getSessionFactory().getMetamodel();
    Transaction tx= session.beginTransaction();

    //Save one student
    addBook(session);

//    updateBook(session);

//    deleteBook(session);

    printBooks(session);
  }

  private static void printBooks(Session session) {
    Metamodel metamodel;
    try {
      System.out.println("querying all the managed entities...");
      metamodel = session.getSessionFactory().getMetamodel();
      for (EntityType<?> entityType : metamodel.getEntities()) {
        final String entityName = entityType.getName();
        final Query query = session.createQuery("from " + entityName);
        System.out.println("executing: " + query.getQueryString());
        for (Object o : query.list()) {
          System.out.println("  " + o.toString());
        }
      }
    } finally {
      session.close();
    }
  }

  private static void deleteBook(Session session) {
    BookEntity bookEntity1 = session.get(BookEntity.class,65);
    session.delete(bookEntity1);
  }

  private static void updateBook(Session session) {
    BookEntity bookEntity1 = session.get(BookEntity.class,65);
    bookEntity1.setTitle("titianix");
    session.update(bookEntity1);
  }

  private static void addBook(Session session) {
    BookEntity bookEntity = new BookEntity();
    bookEntity.setId(65);
    bookEntity.setTitle("hello day");
    session.save(bookEntity);
  }
}