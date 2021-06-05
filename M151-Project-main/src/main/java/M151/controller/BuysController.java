package M151.controller;

import M151.dto.BuyWithArticleAndUser;
import M151.model.Article;
import M151.model.Buys;
import M151.service.ArticleService;
import M151.service.BuysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/buys")
@PreAuthorize("hasAuthority('Customer') or hasAuthority('Admin')")
public class BuysController {
    private final BuysService buysService;

    @Autowired
    public BuysController(final BuysService buysService) {
        this.buysService = buysService;
    }

    @GetMapping("/")
    public List<Buys> getAll() {
        return buysService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Buys> get(@PathVariable final long id) {
        return buysService.get(id);
    }

    @PostMapping("/")
    public Buys add(@RequestBody final BuyWithArticleAndUser buyWithArticleAndUser) {
        return buysService.add(buyWithArticleAndUser);
    }
}
