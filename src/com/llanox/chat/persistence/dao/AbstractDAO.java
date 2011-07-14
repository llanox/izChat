/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.llanox.chat.persistence.dao;

import com.llanox.chat.persistence.conf.HibernateUtil;
import com.llanox.chat.persistence.entities.Chat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 * 
 * @author llanox
 */
public class AbstractDAO {

	protected Transaction tx = null;
	protected Session session = null;
	private Logger logger = Logger.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();

	protected Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	protected void save(Object object) {
		try {

			session = getSession();
			logger.info("Session " + session);

			tx = session.beginTransaction();
			logger.info("Tx " + tx);

			Long id = (Long) session.save(object);

			logger.info("ID saved object " + id);

			tx.commit();

		} catch (Exception e) {

			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}

	}

	protected void saveOrUpdate(Object object) {
		try {

			session = getSession();
			logger.info("Session " + session);

			tx = session.beginTransaction();
			logger.info("Tx " + tx);

			session.saveOrUpdate(object);

			tx.commit();

		} catch (Exception e) {

			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}

	}

	protected Object find(Class clazz, Serializable id) {

		try {
			session = getSession();
			tx = session.beginTransaction();

			return session.load(clazz, id);

		} catch (Exception e) {
			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}
		return null;

	}

	protected Object findByProperty(Class clazz, String property, Object value) {

		Criteria criteria = null;

		try {
			session = getSession();
			tx = session.beginTransaction();
			criteria = session.createCriteria(clazz);
			criteria.add(Expression.eq(property, value));
			Object object = criteria.uniqueResult();
			return object;

		} catch (Exception e) {
			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}
		return null;

	}
	
	protected List findAllByProperty(Class clazz, String property, Object value) {

		Criteria criteria = null;

		try {
			session = getSession();
			tx = session.beginTransaction();
			criteria = session.createCriteria(clazz);
			criteria.add(Expression.eq(property, value));
			List result = criteria.list();
			return result;

		} catch (Exception e) {
			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}
		return null;

	}
	

	public List findByDate(Class clazz, String startProperty, Date startTime, Date endTime, int firstRecord,	int lastRecord,String[] propertiesToFetch) {

		Criteria criteria = null;

		try {
			session = getSession();
			tx = session.beginTransaction();
			criteria = session.createCriteria(clazz);

			if (startTime != null) {
				criteria.add(Expression.ge(startProperty, startTime));
			}

			if (endTime != null) {
				criteria.add(Expression.le(startProperty, endTime));

			}
			
			
              
			for(int i =0; i < propertiesToFetch.length; i++){
				criteria.setFetchMode(propertiesToFetch[i], FetchMode.JOIN);
			}
              
			
			criteria.addOrder(Order.asc(startProperty));
			List result = criteria.list();
			
			

			// consulta total de resultados en la consulta
			// retorno[1] = ((Integer)
			// criteria[1].setProjection(Projections.rowCount()).uniqueResult()).intValue();

			return result;

		} catch (Exception e) {
			closeAfterException(e);

		} finally

		{
			if (session != null) {
				session.close();
			}
		}
		return null;

	}

	protected void closeAfterException(Exception e) {
		logger.info("Exception ", e);
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}
	}
}
