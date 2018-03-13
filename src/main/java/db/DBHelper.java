package db;

import models.File;
import models.Folder;
import models.Owner;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria cr) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = cr.list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUniqueResult(Criteria cr) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) cr.uniqueResult();
            transaction.commit();

        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        results = getList(cr);
        return results;
    }

    public static <T> T find(Class classType, int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        result = getUniqueResult(cr);
        return result;
    }

    public static List<File> getFilesByFolder(Folder folder) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<File> result = null;
        Criteria cr = session.createCriteria(File.class);
        cr.add(Restrictions.eq("folder", folder));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        result = getList(cr);
        return result;
    }

    public static List<Folder> getFolderByOwner(Owner owner) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Folder> result = null;
        Criteria cr = session.createCriteria(Folder.class);
        cr.add(Restrictions.eq("owner", owner));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        result = getList(cr);
        return result;
    }
}
