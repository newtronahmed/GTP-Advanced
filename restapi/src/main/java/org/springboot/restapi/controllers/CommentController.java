package org.springboot.restapi.controllers;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("/api/encode")
    public ResponseEntity<String> encodeOutput(@RequestBody String comment) {
        // Encode the comment to prevent XSS
        String safeComment = StringEscapeUtils.escapeHtml4(comment);

        return ResponseEntity.ok(safeComment);
    }
}
