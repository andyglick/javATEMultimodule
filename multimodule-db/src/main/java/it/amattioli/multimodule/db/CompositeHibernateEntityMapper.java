package it.amattioli.multimodule.db;

import it.amattioli.multimodule.db.HibernateEntityMapper;

public class CompositeHibernateEntityMapper implements HibernateEntityMapper {
	private HibernateEntityMapper[] mappers;
	
	public CompositeHibernateEntityMapper(HibernateEntityMapper... mappers) {
		this.mappers = mappers;
	}
	
	@Override
	public void map() {
		for (HibernateEntityMapper curr: mappers) {
			curr.map();
		}
	}

}
