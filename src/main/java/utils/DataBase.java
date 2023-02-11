package utils;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.util.List;

public class DataBase {
    @PersistenceContext(unitName = "postgres")
    private EntityManager em;



    public List<Result> getResultsFromDB() {
        Query query = em.createQuery("select r from Result r");
        return query.getResultList();
    }



    public void addResultToDB(Result result) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
//        userTransaction.begin();
//        em.persist(result);
//        System.out.println(result);
//        userTransaction.commit();
        //System.out.println(result);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        entityManager.persist(result);

        transaction.commit();
        entityManager.close();
        factory.close();
    }
}
