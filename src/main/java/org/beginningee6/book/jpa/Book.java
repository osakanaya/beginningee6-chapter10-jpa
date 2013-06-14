package org.beginningee6.book.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 書籍情報を表すエンティティクラス。
 */
@Entity
@NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final int DESCRIPTION_LENGTH = 2000;
	
	/**
	 * 主キー
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 書籍タイトル
	 */
	private String title;
	/**
	 * 書籍の価格
	 */
	private Float price;
	/**
	 * 書籍の詳細
	 */
	@Column(length = DESCRIPTION_LENGTH)
	private String description;
	/**
	 * 書籍のISBN番号
	 */
	private String isbn;
	/**
	 * 書籍のページ数
	 */
	private Integer nbOfPage;
	/**
	 * イラストを含むかどうかを示すフラグ
	 */
	private Boolean illustrations;

	/**
	 * デフォルトコンストラクタ
	 */
	public Book() {}

	/**
	 * 書籍の各属性情報を引数に取るコンストラクタ
	 * 
	 * @param title 書籍タイトル
	 * @param price 書籍の価格
	 * @param description 書籍の詳細
	 * @param isbn 書籍のISBN番号
	 * @param nbOfPage 書籍のページ数
	 * @param illustrations イラストを含む場合はtrue
	 */
	public Book(String title, Float price, String description, String isbn,
			Integer nbOfPage, Boolean illustrations) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.isbn = isbn;
		this.nbOfPage = nbOfPage;
		this.illustrations = illustrations;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNbOfPage() {
		return nbOfPage;
	}

	public void setNbOfPage(Integer nbOfPage) {
		this.nbOfPage = nbOfPage;
	}

	public Boolean getIllustrations() {
		return illustrations;
	}

	public void setIllustrations(Boolean illustrations) {
		this.illustrations = illustrations;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price
				+ ", description=" + description + ", isbn=" + isbn
				+ ", nbOfPage=" + nbOfPage + ", illustrations=" + illustrations
				+ "]";
	}	
	
}
