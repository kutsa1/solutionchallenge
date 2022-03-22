package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;
import com.example.solutionchallenge.entities.dtos.StudentDetailDto;
import com.example.solutionchallenge.repo.abstracts.IStudentDao;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements IStudentService {
    private final IStudentDao iStudentDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public DataResult<List<Student>> getAll() {
        return new SuccesDataResult<>(iStudentDao.findAll());
    }

    @Override
    public IResult add(Student student) {
        var result = BusinessRule.run(
                ifExistByUsername(student.getUsername()),
                ifExistByEmail(student.getEmail())
        );
        if (result != null)
            return result;

        student.setPassword(passwordEncoder.encode(student.getPassword()));
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

    @Override
    public DataResult<StudentDetailDto> getStudentDetailDto(String username) {
        return new SuccesDataResult<>(iStudentDao.getStudentDetailDto(username), Messages.studentListed);
    }



    private IResult ifExistByUsername(String username) {
        if (iStudentDao.existsByUsername(username))
            return new ErrorResult(Messages.usernameAlreadyInUse);

        return new SuccessResult();
    }

    private IResult ifExistByEmail(String email) {
        if (iStudentDao.existsByEmail(email))
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();
    }
}
