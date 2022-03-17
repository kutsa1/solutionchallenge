package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IRoleService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.entities.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController extends ControllerBase<Role, IRoleService> {

    private final IRoleService iRoleService;

    public RoleController(IRoleService iRoleService) {
        super(iRoleService);
        this.iRoleService = iRoleService;
    }


}
