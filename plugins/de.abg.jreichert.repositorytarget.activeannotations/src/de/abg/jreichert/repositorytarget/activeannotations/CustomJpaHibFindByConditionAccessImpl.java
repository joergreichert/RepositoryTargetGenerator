package de.abg.jreichert.repositorytarget.activeannotations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.sculptor.framework.accessimpl.jpahibernate.JpaHibFindByConditionAccessImpl;

public class CustomJpaHibFindByConditionAccessImpl<T> extends JpaHibFindByConditionAccessImpl<T> {
	private final Session session;

	public CustomJpaHibFindByConditionAccessImpl(Class<T> clazz, Session session) {
		super(clazz);
		this.session = session;
	}

	@Override
	protected Criteria createCriteria() {
		return session.createCriteria(getPersistentClass());
	}
}
