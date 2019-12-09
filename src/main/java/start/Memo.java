package start;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Transient
    private String ps;

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

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Access(AccessType.PROPERTY)
    public String getPostscript() {
        System.out.println("getPostscript");
        return ps + "!!";
    }

    public void setPostscript(String postscript) {
        // 호출 안됨
        System.out.println("setPostscript");
    }

    @Access(AccessType.PROPERTY)
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @Access(AccessType.PROPERTY)
    public void setTime(String localDateTime) {
        // 호출 안됨
        System.out.println("setTime");
    }
}
