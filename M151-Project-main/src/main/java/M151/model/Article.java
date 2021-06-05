package M151.model;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_sequence")
    @SequenceGenerator(allocationSize = 1, name = "article_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long articleID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    protected Article() {
    }

    public Article(String articleName, String articlePrice) {
        this.name = articleName;
        this.price = articlePrice;
    }

    public long getArticleID() {
        return articleID;
    }

    public void setArticleID(long articleID) {
        this.articleID = articleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}