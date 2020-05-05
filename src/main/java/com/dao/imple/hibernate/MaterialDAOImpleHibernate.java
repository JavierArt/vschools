package com.dao.imple.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.MaterialDAO;
import com.dao.imple.hibernate.mapping.Course;
import com.dao.imple.hibernate.mapping.Material;

public class MaterialDAOImpleHibernate implements MaterialDAO {

	private boolean containsID(final Collection<Material> materials, final int ID){
	    return materials.stream().anyMatch(new Predicate<Material>() {
			public boolean test(Material o) {
				return o.getMaterialId() == ID;
			}
		});
	}
	
	private int showMaterials()
	{
		Session session = HibernateSessionFactory.getSession();
		Scanner scan = new  Scanner(System.in);
		String hql = "FROM Material m";
		Query query = session.createQuery(hql);
		int id=0;
		
		List<Material> materials = query.list();
		if(!materials.isEmpty()) {
			for(Material m: materials)
			{
				System.out.println(m.getMaterialId() + " " + m.getDetails());
			}
			System.out.println("choose one");
			id = scan.nextInt();
		}
		if(id<=0 && !containsID(materials, id))
		{
			return -1;
		}
		return id;
	}

	public void addmaterial() {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter details of material");
		String details = scan.nextLine();
		System.out.println("enter cost of everything");
		double cost = scan.nextDouble();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
	        
	        Material mat = new Material();
	        mat.setDetails(details);
	        mat.setCost(cost);
			
	        //Save the student in database
	        session.save(mat);
	 
	        //Commit the transaction
	        tx.commit();
		}
		catch(Exception ex)
		{
			if(tx!=null)tx.rollback();
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	 public Material assignMaterial() {
		Session session = HibernateSessionFactory.getSession();
		int materialID = showMaterials();
		Material mat = (Material) session.get(Material.class, materialID);
		if(materialID == -1 || mat==null)
		{
			return null;
		}
		return mat;		
	}
}
