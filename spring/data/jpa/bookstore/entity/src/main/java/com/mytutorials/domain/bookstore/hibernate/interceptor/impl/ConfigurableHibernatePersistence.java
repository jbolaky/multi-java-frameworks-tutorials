package com.mytutorials.domain.bookstore.hibernate.interceptor.impl;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.MergeEventListener;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

@SuppressWarnings("deprecation")
public class ConfigurableHibernatePersistence extends HibernatePersistence {
	private Interceptor interceptor;
	private LoadEventListener loadEventListener;
	private SaveOrUpdateEventListener saveOrUpdateEventListener;
	private MergeEventListener mergeEventListener;
	private PostLoadEventListener postLoadEventListener;
	private PreInsertEventListener preInsertEventListener;
	private PreUpdateEventListener preUpdateEventListener;

	public Interceptor getInterceptor() {
		return this.interceptor;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	public LoadEventListener getLoadEventListener() {
		return this.loadEventListener;
	}

	public void setLoadEventListener(LoadEventListener loadEventLister) {
		this.loadEventListener = loadEventLister;
	}

	public SaveOrUpdateEventListener getSaveOrUpdateEventListener() {
		return this.saveOrUpdateEventListener;
	}

	public void setSaveOrUpdateEventListener(
			SaveOrUpdateEventListener saveOrUpdateEventListener) {
		this.saveOrUpdateEventListener = saveOrUpdateEventListener;
	}

	public MergeEventListener getMergeEventListener() {
		return this.mergeEventListener;
	}

	public void setMergeEventListener(MergeEventListener mergeEventListener) {
		this.mergeEventListener = mergeEventListener;
	}

	public void setPostLoadEventListener(
			PostLoadEventListener postLoadEventListener) {
		this.postLoadEventListener = postLoadEventListener;
	}

	public PostLoadEventListener getPostLoadEventListener() {
		return this.postLoadEventListener;
	}

	public void setPreInsertEventListener(
			PreInsertEventListener preInsertEventListener) {
		this.preInsertEventListener = preInsertEventListener;
	}

	public PreInsertEventListener getPreInsertEventListener() {
		return this.preInsertEventListener;
	}

	public void setPreUpdateEventListener(
			PreUpdateEventListener preUpdateEventListener) {
		this.preUpdateEventListener = preUpdateEventListener;
	}

	public PreUpdateEventListener getPreUpdateEventListener() {
		return this.preUpdateEventListener;
	}

	public EntityManagerFactory createContainerEntityManagerFactory(
			PersistenceUnitInfo info, @SuppressWarnings("rawtypes") Map map) {
		Ejb3Configuration cfg = new Ejb3Configuration();
		Ejb3Configuration configured = cfg.configure(info, map);
		postprocessConfiguration(info, map, configured);
		return ((configured != null) ? configured.buildEntityManagerFactory()
				: null);
	}

	protected void postprocessConfiguration(PersistenceUnitInfo info,
			@SuppressWarnings("rawtypes") Map map, Ejb3Configuration configured) {
		
		if (this.interceptor != null) {
			if ((configured.getInterceptor() == null)
					|| (EmptyInterceptor.class.equals(configured
							.getInterceptor().getClass()))) {
				configured.setInterceptor(this.interceptor);
			} else
				throw new IllegalStateException(
						"Hibernate interceptor already set in persistence.xml ("
								+ configured.getInterceptor() + ")");

		}
	}
}