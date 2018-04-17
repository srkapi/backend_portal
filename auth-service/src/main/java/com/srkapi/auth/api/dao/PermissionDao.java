package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Permission;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionDao extends GenericRepositoryMongoImpl<Permission> {

}
