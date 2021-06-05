package M151.service;

import M151.model.Article;
import M151.repo.ArticleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleService {
    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(final ArticleRepo articleRepo) { this.articleRepo = articleRepo;}

    @Transactional(readOnly = true)
    public List<Article> getAll() {
        final Iterable<Article> articles = articleRepo.findAll();
        return StreamSupport
                .stream(articles.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Article add(final Article article) {
        return articleRepo.save(article);
    }

    @Transactional(readOnly = true)
    public Optional<Article> get(final long id) {
        return Optional.ofNullable(articleRepo.findById(id));
    }
}
