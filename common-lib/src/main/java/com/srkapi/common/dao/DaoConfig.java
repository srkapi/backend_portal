package com.srkapi.common.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

	@Bean
	public SequenceDao sequenceDao() {
        return new SequenceDaoImpl();
    }
	
}
