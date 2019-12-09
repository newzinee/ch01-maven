package start;

import javax.persistence.*;

@Entity
@TableGenerator(
        name = "MEMO_SEQ_GENERATOR"
        , table = "MY_SEQUENCES"
        , pkColumnValue = "MEMO_SEQ"
        , allocationSize = 1
)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMO_SEQ_GENERATOR")
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
