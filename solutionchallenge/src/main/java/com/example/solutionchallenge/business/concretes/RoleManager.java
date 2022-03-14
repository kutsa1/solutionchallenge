package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IRoleService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.Role;
import com.example.solutionchallenge.repo.abstracts.IRoleDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleManager implements IRoleService {

    private final IRoleDao iRoleDao;

    @Override
    public DataResult<Role> findByName(String roleName) {
        var result = iRoleDao.findByName(roleName);
        if (result==null)
            return new ErrorDataResult<>(Messages.roleNotFound);
        return new SuccesDataResult<>(result);
    }

    @Override
    public IResult existByRoleId(int id) {
        return null;
    }

    @Override
    public DataResult<List<Role>> getAll() {
        return new SuccesDataResult<>(iRoleDao.findAll());
    }

    @Override
    public IResult add(Role role) {
        var result = BusinessRule.run(ifExistByRoleName(role.getName()));
        if (result != null)
            return result;
        iRoleDao.save(role);
        return new SuccessResult(Messages.roleAdded);
    }

    @Override
    public IResult update(Role role) {
        iRoleDao.save(role);
        return new SuccessResult(Messages.roleUpdated);
    }

    @Override
    public IResult delete(Role role) {
        iRoleDao.delete(role);
        return new SuccessResult(Messages.roleDeleted);
    }

    @Override
    public DataResult<Role> getById(Integer id) {
        return new SuccesDataResult<>(iRoleDao.getById(id),Messages.roleListed);
    }
    private IResult ifExistByRoleName(String roleName) {
        if (iRoleDao.existsByName(roleName))
            return new ErrorResult(Messages.roleNameAlreadyExist);
        return new SuccessResult();
    }
}
