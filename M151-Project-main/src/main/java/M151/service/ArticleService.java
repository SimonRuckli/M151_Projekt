package M151.service;

import M151.model.Article;
import M151.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CacheConfig(cacheNames = {"article"})
@Service
public class ArticleService {
    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(final ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "0")
    public List<Article> getAll() {
        final Iterable<Article> articles = articleRepo.findAll();
        return StreamSupport
                .stream(articles.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(key = "#result.articleId")
    @CacheEvict(key = "0")
    public Article add(final Article article) {
        return articleRepo.save(article);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<Article> get(final long id) {
        return articleRepo.findById(id);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public Article update(final long id, final Article article) {
        final Optional<Article> optionalArticle = articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            Article foundArticle = optionalArticle.get();
            foundArticle.setName(article.getName());
            foundArticle.setPrice(article.getPrice());
            return articleRepo.save(foundArticle);
        }
        return null;
    }
}
