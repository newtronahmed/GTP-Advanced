package org.springboot.restapi.controllers;

import org.springboot.restapi.dto.CommentDTO;
import org.springboot.restapi.models.Comment;
import org.springboot.restapi.models.User;
import org.springboot.restapi.services.AuthService;
import org.springboot.restapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final AuthService authService;

    @Autowired
    public CommentController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        User currentUser = authService.getCurrentUser();
        CommentDTO savedComment = commentService.createComment(commentDTO, currentUser);
        return ResponseEntity.ok(savedComment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') and @authService.isCommentOwner(#id, authentication.name)")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        User currentUser = authService.getCurrentUser();
        CommentDTO updatedComment = commentService.updateComment(id, comment, currentUser);
        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CommentDTO>> getAllComments() {

        List<CommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
}
