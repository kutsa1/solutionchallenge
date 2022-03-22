package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IHomeworkService;
import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;
import com.example.solutionchallenge.repo.abstracts.IHomeworkDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkManager implements IHomeworkService {
    private final IHomeworkDao iHomeworkDao;
    private final IStudentService iStudentService;


    @Override
    public IResult addHomeworkToUser(int homeworkId, int studentId) {
        var result = BusinessRule.run(isHomeworkExistById(homeworkId), isHomeworkExistById(homeworkId));
        if (result != null)
            return result;
        Homework homework = this.iHomeworkDao.findById(homeworkId);
        Student student = this.iStudentService.getById(studentId).getData();
        student.getHomeworks().add(homework);
        iStudentService.update(student);
        return new SuccessResult(Messages.homeworkAddedToUser);

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

    public DataResult<List<Homework>> getAllByStudents(String username) {
        return new SuccesDataResult<>(iHomeworkDao.getAllByStudents_Username(username));
    }

    private IResult isHomeworkExistById(int homeworkId) {
        var result = iHomeworkDao.existsById(homeworkId);
        if (!result)
            return new ErrorResult(Messages.homeworkNotFound);
        return new SuccessResult();
    }


}
