package br.com.fuctura;

import br.com.fuctura.entity.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Aplicação {

	public static void main(String[] args) {
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("FUCTURA-PU");
	
	EntityManager em = emf.createEntityManager();
	
	Vendedor v = new Vendedor();
	//v.setCodigo(1);
	v.setNome("Joao");
	
	Vendedor v2 = new Vendedor();
	//v.setCodigo(2);
	v.setNome("Maria");
	
	Vendedor v3 = new Vendedor();
	//v.setCodigo(3);
	v.setNome("Jose");
	
	
	em.getTransaction().begin();
	em.persist(v);
	em.persist(v2);
	em.persist(v3);
	em.getTransaction().commit();
	
	em.getTransaction().begin();
	v.setNome("Maria");
	em.persist(v);
	em.getTransaction().commit();
	
	em.getTransaction().begin();
	em.remove(v);
	em.getTransaction().commit();
	
	Vendedor ql = em.find(Vendedor.class, 1);
	
	System.out.println("Nome: " + ql.getNome());
	
	Vendedor v4 = new Vendedor();
	v4.setNome("Teste");
	
	em.getTransaction().begin();
	em.persist(v4);
	v4.setNome("Lucas");
	em.merge(v4);
	em.remove(v4);
	em.getTransaction().commit();
	
	
	}

}
