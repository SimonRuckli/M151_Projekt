package M151.model;

import javax.persistence.*;

@Entity
public class Buys {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buys_sequence")
    @SequenceGenerator(allocationSize = 1, name = "buys_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long purchaseID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    protected Buys() {}

    public Buys(Article article, User user) {
        this.article = article;
        this.user = user;
    }

    public long getPurchaseID() {
        return purchaseID;
    }

    public Article getArticle() {
        return article;
    }

    public User getUser() {
        return user;
    }
}
