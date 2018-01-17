package com.quebic.auth.api.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.quebic.auth.api.dao.RoleDao;
import com.quebic.auth.api.model.Role;
import com.quebic.common.dao.impl.GenericDaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.quebic.auth.api.model.Role.ROLE_SELLER;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}


	@Override
	public List<Role> findByCode(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(id));
		return Optional.ofNullable(this.mongoOperations.find(query,Role.class))
				.orElse(Collections.emptyList());
	}
}
