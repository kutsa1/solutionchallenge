package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IHomeworkService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.repo.abstracts.IHomeworkDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkManager implements IHomeworkService {
    private final IHomeworkDao iHomeworkDao;
    IUserService iUserService;


    @Override
    public IResult addHomeworkToUser(int homeworkId, int userId) {
      //  var result = BusinessRule.run(isHomeworkExistById(homeworkId),isHomeworkExistById(homeworkId));

//        if (result!=null)
  //          return result;
    //    var homework = iHomeworkDao.getById(homeworkId);
      //  var user = iUserService.getById(userId);
        //homework.setUser(user.getData());
        //iHomeworkDao.save(homework);
      //  return new SuccessResult(Messages.homeworkAddedToUser);
        return null;
    }

    @Override
    public DataResult<List<Homework>> getAll() {
        return new SuccesDataResult(iHomeworkDao.findAll(), Messages.homeworkListed);
    }

    @Override
    public IResult add(Homework homework) {
        iHomeworkDao.save(homework);
        return new SuccessResult(Messages.homeworkSave);
    }

    @Override
    public IResult update(Homework homework) {
        iHomeworkDao.save(homework);
        return new SuccessResult(Messages.homeworkUpdate);
    }

    @Override
    public IResult delete(Homework homework) {
        iHomeworkDao.delete(homework);
        return new SuccessResult(Messages.homeworkDelete);
    }

    @Override
    public DataResult<Homework> getById(Integer id) {
        var result = iHomeworkDao.existsById(id);
        if (result)
            return new SuccesDataResult<>(iHomeworkDao.getById(id));
        return new ErrorDataResult<>(null);
    }

    private IResult isHomeworkExistById(int homeworkId){
        var result = iHomeworkDao.existsById(homeworkId);
        if (!result)
            return new ErrorResult(Messages.homeworkNotFound);
        return new SuccessResult();
    }


}
