package org.springboot.restapi.controllers;


import org.springboot.restapi.models.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    private List<Comment> comments = new ArrayList<>();

    @GetMapping("/comments")
    public String showCommentPage(Model model) {
        model.addAttribute("comments", comments);
        return "comment";
    }

    @PostMapping("/comments")
    public String addComment(@RequestParam("author") String author,
                             @RequestParam("content") String content,
                             Model model) {
        Comment comment = new Comment();
//        comment.setAuthor(author);
        comment.setContent(content);

        comments.add(comment);
        model.addAttribute("comments", comments);

        return "comment";
    }
}
