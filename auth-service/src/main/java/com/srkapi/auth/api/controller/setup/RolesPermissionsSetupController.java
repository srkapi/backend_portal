package com.srkapi.auth.api.controller.setup;

import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.service.PermissionService;
import com.srkapi.auth.api.service.RoleService;
import com.srkapi.common.constants.PermissionConstant;
import com.srkapi.common.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Only for development
 */
@RestController
@RequestMapping("/roles-permissions-setup")
public class RolesPermissionsSetupController extends ControllerBase{

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/init", method=RequestMethod.POST)
	public ResponseEntity<String> init(){
		
		//Permission
		Permission permission_USER = new Permission();
		permission_USER.setId(PermissionConstant.USER);
		permission_USER.setCode(PermissionConstant.USER);
		permission_USER.setStatus(1);
		
		Permission permission_ADMIN = new Permission();
		permission_ADMIN.setId(PermissionConstant.USER_ADMIN);
		permission_ADMIN.setCode(PermissionConstant.USER_ADMIN);
		permission_ADMIN.setStatus(1);
		

		
		permissionService.add(permission_USER);
		permissionService.add(permission_ADMIN);
		//Permission
		
		//Role ROLE_ADMIN
		Role role_ADMIN = new Role();
		role_ADMIN.setId(Role.ROLE_ADMIN);
		role_ADMIN.setCode(Role.ROLE_ADMIN);
		role_ADMIN.setStatus(1);
		role_ADMIN.getPermissions().add(permission_USER);
		role_ADMIN.getPermissions().add(permission_ADMIN);
		
		//Role ROLE_SELLER_ADMIN
		Role role_SELLER_ADMIN = new Role();
		role_SELLER_ADMIN.setId(Role.ROLE_SELLER_ADMIN);
		role_SELLER_ADMIN.setCode(Role.ROLE_SELLER_ADMIN);
		role_SELLER_ADMIN.setStatus(1);
		role_SELLER_ADMIN.getPermissions().add(permission_USER);

		//Role ROLE_SELLER
		Role role_SELLER = new Role();
		role_SELLER.setId(Role.ROLE_SELLER);
		role_SELLER.setCode(Role.ROLE_SELLER);
		role_SELLER.setStatus(1);
		role_SELLER.getPermissions().add(permission_USER);

		//Role ROLE_BUYER
		Role role_BUYER = new Role();
		role_BUYER.setId(Role.ROLE_BUYER);
		role_BUYER.setCode(Role.ROLE_BUYER);
		role_BUYER.setStatus(1);
		role_BUYER.getPermissions().add(permission_USER);

		roleService.add(role_ADMIN);
		roleService.add(role_SELLER_ADMIN);
		roleService.add(role_SELLER);
		roleService.add(role_BUYER);
		
		return makeResponse("roles-permissions : init");
		
	}
	
	
}
