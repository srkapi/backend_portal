package com.srkapi.auth.api.service;

import com.srkapi.auth.api.dto.SellerDto;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.service.GenericService;

import rx.Single;

public interface SellerService extends GenericService<User,SellerDto>{
	Single<SellerDto> registerOtherSeller(User seller);
}
