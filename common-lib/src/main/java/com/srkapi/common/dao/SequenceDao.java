package com.srkapi.common.dao;

import com.srkapi.common.exception.DataAccessException;

public interface SequenceDao  extends GenericDao<Sequence>{

	int getNextSequenceId(String key) throws DataAccessException;
}
