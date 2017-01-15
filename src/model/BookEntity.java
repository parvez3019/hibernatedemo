package model;

import javax.persistence.*;

/**
 * Created by aparvez on 1/15/17.
 */
@Entity
@Table(name = "book", schema = "", catalog = "")
public class BookEntity {
  private Integer id;
  private String title;

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BookEntity that = (BookEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BookEntity{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }
}
