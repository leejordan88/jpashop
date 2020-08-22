package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;

    public Book createBook(BookForm form) {
        if (StringUtils.hasText(form.getName())) {
            this.setName(form.getName());
        }
        if (form.getPrice() != getPrice()) {
            this.setPrice(form.getPrice());
        }
        if (form.getStockQuantity() != getStockQuantity()) {
            this.setStockQuantity(form.getStockQuantity());
        }
        if (StringUtils.hasText(form.getAuthor())) {
            this.setAuthor(form.getAuthor());
        }
        if (StringUtils.hasText(form.getIsbn())) {
            this.setIsbn(form.getIsbn());
        }
        return this;
    }

    public void change(Book param) {
        if (StringUtils.hasText(param.getName())) {
            this.setName(param.getName());
        }
        if (param.getPrice() != getPrice()) {
            this.setPrice(param.getPrice());
        }
        if (param.getStockQuantity() != getStockQuantity()) {
            this.setStockQuantity(param.getStockQuantity());
        }
        if (StringUtils.hasText(param.getAuthor())) {
            this.setAuthor(param.getAuthor());
        }
        if (StringUtils.hasText(param.getIsbn())) {
            this.setIsbn(param.getIsbn());
        }
    }

}
