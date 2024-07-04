package fr.diginamic.hello.entytes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class DepartementDao {

    @PersistenceContext
    private EntityManager em;

    public List<Departement> findAll() {
        return em.createQuery("SELECT d FROM Departement d", Departement.class)
                 .getResultList();
    }

    public Departement findById(int idDepartement) {
        return em.find(Departement.class, idDepartement);
    }

    public Departement findByNom(String nom) {
        List<Departement> departements = em.createQuery("from Departement where nom = :nom", Departement.class)
                .setParameter("nom", nom)
                .getResultList();
        return departements.isEmpty() ? null : departements.get(0);
    }
    public Departement save(Departement departement) {
        em.persist(departement);
        return departement;
    }

    public Departement update(Departement departement) {
        return em.merge(departement);
    }

    public void delete(Departement departement) {
        em.remove(departement);
    }
}