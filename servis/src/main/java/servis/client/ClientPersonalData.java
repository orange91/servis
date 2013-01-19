package servis.client;

import hiber.HibernateUtil;
import servis.client.ClientDetails;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


public class ClientPersonalData {

	public static void main(String args[]){
		ClientMethods cpd = new ClientMethods();
		
		//Adding clients
		cpd.addClient("Michael","Popov", "Gdańsk 80-690", 744854855 );

/*	﻿CREATE TABLE klient
(
    	id_client            	serial,
    	name            	varchar(32)   	not null,
   		surname         	varchar(32)   	not null,
    	address            	varchar(100)  	not null,
    	nr_phone	     		int           	not null,
    	comments            	varchar(120),
	CONSTRAINT		klient_id_klienta_pk PRIMARY KEY(id_klienta)
);

 */
		// dodane 
		cpd.listClients();
		
		// 
		cpd.getClientDetails(1, "Michael");
		
		//
		cpd.countClient();

		//
//		cpd.updateClient("Begining Hibernate",1000);

		//dostępni 
		cpd.listClients();

		
//		cpd.deleteClient();

		
		cpd.listClients();
	}
}

class ClientMethods{
	//dodawanie
	public void addClient(String clientName, String clientSurName, String clientAddress, int clientPhoneNumber){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ClientDetails cpd = new ClientDetails();
		cpd.setClientName(clientName);
		cpd.setClientSurName(clientSurName);  // dodać w ClientDetails
		cpd.setClientAddress(clientAddress);
		cpd.setClientPhoneNumber(clientPhoneNumber);
		session.save(cpd);
		session.getTransaction().commit();
	}

	//kasowanie
	public void deleteClient(int idClient){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List list = session.createQuery("from client where id_client='"+idClient+"'").list();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			ClientDetails cpd = (ClientDetails)itr.next();
			System.out.println("delete : "+cpd);
			session.delete(cpd);
		}
		session.getTransaction().commit();
	}

	//Update
	public void updateClient(int idClient, String clientComments){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(ClientDetails.class);
		cri = cri.add(Restrictions.eq("idClient", idClient));
		List list = cri.list();
		ClientDetails cpd = (ClientDetails)list.iterator().next();
		cpd.setClientComments(clientComments);
		session.update(cpd);
		session.getTransaction().commit();
	}
// zrobione 
	//Using HQL - Hibernate Query Language
	public void getClientDetails(String idClient, String clientComments){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from client where id_client=:cn");
		q.setString("cn", clientComments);
		List list = q.list();
		System.out.println("Getting Book Details using HQL. \n"+list);

		//The above query can also be achieved with Criteria & Restrictions API.
		Criteria cri = session.createCriteria(ClientDetails.class);
		cri = cri.add(Restrictions.eq("bookName", bookName));
		list = cri.list();
		System.out.println("Getting Book Details using Criteria API. \n"+list);

		session.getTransaction().commit();
	}

	//Aggregate function.
	public void countClient(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List list = session.createQuery("select count(*) from BookDetails").list();
		System.out.println("Aggregate function count \n"+list);
		session.getTransaction().commit();
	}

	//Native SQL Query
	public void listClients(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List list = session.createSQLQuery("select * from BookStore").addEntity(ClientDetails.class).list();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			ClientDetails cpd = (ClientDetails)itr.next();
			System.out.println(cpd);
		}
		session.getTransaction().commit();

	}
}