package com.klu.Hibernate_exp2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Product.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // ---------- CREATE ----------
        Product p1 = new Product("Laptop", "Dell i5", 55000, 10);
        Product p2 = new Product("Mobile", "Samsung", 20000, 25);
        Product p3 = new Product("Headphones", "Sony", 3000, 15);

        session.persist(p1);
        session.persist(p2);
        session.persist(p3);

        System.out.println("Products inserted successfully");

        // ---------- READ ----------
        Product product = session.byId(Product.class).load(1);

        if (product != null) {
            System.out.println("Product Name : " + product.getName());

            // ---------- UPDATE ----------
            product.setPrice(52000);
            product.setQuantity(8);

            System.out.println("Product updated successfully");
        }

        // ---------- DELETE ----------
        Product deleteProduct = session.byId(Product.class).load(3);

        if (deleteProduct != null) {
            session.remove(deleteProduct);
            System.out.println("Product deleted successfully");
        }

        tx.commit();

        session.close();
        factory.close();
    }
}
