package org.springboot.restapi.services;

import jakarta.validation.Valid;
import org.owasp.encoder.Encode;
import org.springboot.restapi.dto.CommentDTO;
import org.springboot.restapi.models.Comment;
import org.springboot.restapi.models.User;
import org.springboot.restapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO createComment(CommentDTO commentDTO, User user) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);

        return mapToDTO(savedComment);
    }

    public CommentDTO updateComment(Long id, @Valid Comment commentDTO, User user) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!existingComment.getUser().equals(user)) {
            throw new RuntimeException("You can only edit your own comments");
        }

        existingComment.setContent(commentDTO.getContent());
        Comment updatedComment = commentRepository.save(existingComment);

        return mapToDTO(updatedComment);
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(Encode.forHtml(comment.getContent())); // Output encoding
        dto.setUser(comment.getUser());
        return dto;
    }
}
