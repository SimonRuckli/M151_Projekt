package M151.controller;

import M151.model.Article;
import M151.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/articles")
@PreAuthorize("hasAuthority('Customer') or hasAuthority('Admin')")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public List<Article> getAll() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> get(@PathVariable final long id) {
        return articleService.get(id);
    }

    @PostMapping("/")
    public Article add(@RequestBody final Article article) {
        return articleService.add(article);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Article update(@PathVariable final long id, @RequestBody final Article article) {
        return articleService.update(id, article);
    }
}