package com.srkapi.auth.api.service;

import com.srkapi.auth.api.dto.SellerDto;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.service.GenericService;
import reactor.core.publisher.Mono;

public interface SellerService extends GenericService<User,SellerDto>{
	Mono<SellerDto> registerOtherSeller(User seller);
}
