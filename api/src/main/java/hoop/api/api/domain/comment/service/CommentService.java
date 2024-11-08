package hoop.api.api.domain.comment.service;

import hoop.api.api.domain.comment.DTO.CommentRequestDTO;
import hoop.api.api.domain.comment.DTO.CommentResponseDTO;
import hoop.api.api.domain.comment.entity.Comment;
import hoop.api.api.domain.comment.mapper.CommentMapper;
import hoop.api.api.domain.comment.repository.CommentRepository;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.post.service.PostService;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.handler.exceptions.ConflitException;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public CommentResponseDTO attachComentToPost(Long userId, Long postId, CommentRequestDTO requestDTO) {
        User user = userService.existsUserById(userId);
        Post post = postService.existsPost(postId);

        Comment comment = CommentMapper.toEntity(requestDTO);
        comment.setUser(user);
        comment.setPost(post);


        commentRepository.save(comment);

        return CommentMapper.toDTO(comment);
    }


    public CommentResponseDTO updateComment(Long userId,Long commentId, CommentRequestDTO requestDTO) {

        User user = userService.existsUserById(userId);
        Comment comment = existComment(commentId);

        if(!comment.getUser().getId().equals(userId)){
            throw new ConflitException("You cant update this comment");
        }

        comment.setContent(requestDTO.content());

        commentRepository.save(comment);

        return CommentMapper.toDTO(comment);

    }


    public void deleteComment(Long userId,Long commentId) {
        User user = userService.existsUserById(userId);

        Comment comment = existComment(commentId);

        if(!comment.getUser().getId().equals(userId)){
            throw new ConflitException("You cant delete this comment");
        }

        commentRepository.delete(comment);

    }


    public Comment existComment(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(()-> new ObjectNotFoundException("Comment not found"));
    }
}
