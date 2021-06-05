package M151.dto;

import M151.model.Article;
import M151.model.Buys;

public class BuyWithArticleAndUser {
    private final Buys buys;
    private final long articleId;
    private final long userId;

    public BuyWithArticleAndUser(final Buys buys, final long articleId, final long userId) {
        this.buys = buys;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Buys getBuys() {
        return buys;
    }

    public long getArticleId() {
        return articleId;
    }

    public long getUserId() {
        return userId;
    }
}