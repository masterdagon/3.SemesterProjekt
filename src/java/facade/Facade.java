/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Muggi
 */
public class Facade {

    private EntityManagerFactory emf;

    public Facade() {
        emf = Persistence.createEntityManagerFactory("3_SemesterProjektPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public Person getPersonFromPhone(int phoneNumber) throws EntityNotFoundException {//Finnish
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            Phone phone = em.find(Phone.class, phoneNumber);
//            if (phone == null) {
//                throw new EntityNotFoundException("The PhoneNumber: " + phoneNumber + " does not exist");
//            }
//            Person p = phone.getPerson();
//            return p;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }



}
