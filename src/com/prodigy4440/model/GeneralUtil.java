/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodigy4440.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author prodigy4440
 */
public class GeneralUtil {

    public static byte[] imageToByteArray(BufferedImage image) {
        byte[] imageInByte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                baos.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return imageInByte;
    }

    public static BufferedImage imageFromByteArra(byte[] imageInByte) {

        InputStream inputStream = new ByteArrayInputStream(imageInByte);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return bufferedImage;
    }

    public static Account getLastInsertedIndex() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Account integer = null;
        try {
            Account entry = (Account) session.createQuery("from Account").uniqueResult();
//            int tid = (Integer) session.createQuery("select max(entryId) from Account").uniqueResult();
//            entry = (Account) session.get(Account.class, tid);
            session.getTransaction().commit();
            if (entry == null) {
                return null;
            } else {
                integer = entry;
            }
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return integer;
    }

    public static String generateNewAccountNumber() {
        if (getLastInsertedIndex() == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM");
            String format = simpleDateFormat.format(Calendar.getInstance().getTime());
            return "VATAG" + format.toUpperCase() + "-" + 1;
        }

     return null;
    }

    public static void main(String args[]) {
        System.out.println(getLastInsertedIndex());
    }

}
