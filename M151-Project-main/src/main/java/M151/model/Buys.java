package M151.model;

import javax.persistence.*;

@Entity
public class Buys {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buys_sequence")
    @SequenceGenerator(allocationSize = 1, name = "buys_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long purchaseID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private M151.model.Article article;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
