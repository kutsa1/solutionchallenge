package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.core.utilities.results.SuccesDataResult;
import com.example.solutionchallenge.core.utilities.results.SuccessResult;
import com.example.solutionchallenge.entities.concretes.Student;
import com.example.solutionchallenge.repo.abstracts.IStudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements IStudentService {
    private final IStudentDao iStudentDao;


    @Override
    public DataResult<List<Student>> getAll() {
        return new SuccesDataResult<>(iStudentDao.findAll());
    }

    @Override
    public IResult add(Student student) {
        iStudentDao.save(student);
        return new SuccessResult(Messages.studentAdd);
    }

    @Override
    public IResult update(Student student) {
        iStudentDao.save(student);
        return new SuccessResult(Messages.studentUpdate);
    }

    @Override
    public IResult delete(Student student) {
        iStudentDao.delete(student);
        return new SuccessResult(Messages.studentDelete);
    }

    @Override
    public DataResult<Student> getById(Integer id) {

        return new SuccesDataResult<>(iStudentDao.getById(id), Messages.studentListed);
    }

    @Override
    public IResult existById(int studentId) {
        return null;
    }
}
