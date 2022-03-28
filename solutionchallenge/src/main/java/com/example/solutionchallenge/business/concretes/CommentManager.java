package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.*;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Comment;
import com.example.solutionchallenge.entities.dtos.CommentDetailDto;
import com.example.solutionchallenge.repo.abstracts.ICommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentManager implements ICommentService {
    private final ICommentDao iCommentDao;
    private final IUserService iUserService;
    private final IHomeworkService iHomeworkService;
    private final ISolutionService iSolutionService;
    private final IStudentService iStudentService;

    @Override
    public IResult addCommentToSolution(int commentId, int solutionId) {
        var result = BusinessRule.run(isCommentExistById(commentId), isSoluitonExistById(solutionId));

        if (result != null)
            return result;
        var comment = iCommentDao.getById(commentId);
        var solution = iSolutionService.getById(solutionId);

        comment.setSolution(solution.getData());
        iCommentDao.save(comment);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<CommentDetailDto>> getAllCommentDtoByHomeworkId(int homeworkId) {
        return new SuccesDataResult<>(iCommentDao.getAllCommentDtoByHomeworkId(homeworkId));
    }


//    @Override
//    public IResult addCommentToHomework(int commentId, int homeworkId) {
//        var result = BusinessRule.run(isCommentExistById(commentId), isHomeworkExistById(homeworkId));
//
//        if (result != null)
//            return result;
//        var comment = iCommentDao.getById(commentId);
//        var homework = iHomeworkService.getById(homeworkId);
//
//        comment.setHomework(homework.getData());
//
//        iCommentDao.save(comment);
//        return new SuccessResult();
//    }

    @Override
    public IResult addCommentToUser(int commentId, int userId) {
        var result = BusinessRule.run(isCommentExistById(commentId), isExistUserById(userId));

        if (result != null)
            return result;
        var comment = iCommentDao.getById(commentId);
        var student = iStudentService.getById(userId);
        comment.setStudent(student.getData());
        iCommentDao.save(comment);

        return new SuccessResult(Messages.commentAddedToUser);
    }


    @Override
    public DataResult<List<Comment>> getAll() {
        return new SuccesDataResult(iCommentDao.findAll(), Messages.commentListed);

    }

    @Override
    public IResult add(Comment comment) {
        comment.setDate(LocalDateTime.now());
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentSave);
    }

    @Override
    public IResult update(Comment comment) {
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentUpdate);

    }

    @Override
    public IResult delete(Comment comment) {
        iCommentDao.delete(comment);
        return new SuccessResult(Messages.commentDelete);
    }

    @Override
    public DataResult<Comment> getById(Integer id) {
        return new SuccesDataResult(iCommentDao.getById(id), Messages.commentListed);
    }

    private IResult isCommentExistById(int commentId) {
        var result = iCommentDao.existsById(commentId);
        if (!result)
            return new ErrorResult(Messages.commentNotFound);

        return new SuccessResult();
    }

    private IResult isExistUserById(int userId) {

        var result = iUserService.existsById(userId);
        if (!result.isSuccess())
            return new ErrorResult(Messages.userNotFound);
        return new SuccessResult();
    }

    private IResult isHomeworkExistById(int homeworkId) {
        var result = iHomeworkService.getById(homeworkId);
        if (result.getData() == null)
            return new ErrorResult(Messages.homeworkNotFound);
        return new SuccessResult();

    }

    private IResult isSoluitonExistById(int soluitonId) {
        var result = iSolutionService.getById(soluitonId);
        if (result.getData() == null)
            return new ErrorResult(Messages.solutionNotFound);
        return new SuccessResult();
    }
}
