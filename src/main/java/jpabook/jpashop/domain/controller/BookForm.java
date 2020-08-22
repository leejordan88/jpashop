package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public BookForm createBookFrom(Item item) {
        Book book = (Book) item;
        this.setId(book.getId());
        this.setName(item.getName());
        this.setPrice(book.getPrice());
        this.setStockQuantity(book.getStockQuantity());
        this.setAuthor(book.getAuthor());
        this.setIsbn(book.getIsbn());
        return this;
    }


}
