package org.beginningee6.book.jpa;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;

/**
 * CD情報を表すエンティティクラス。
 */
@Entity
@NamedQuery(name = "findAllCDs", query = "SELECT c FROM CD c")
public class CD implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DESCRIPTION_LENGTH = 2000;

	/**
	 * 主キー
	 */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * CDのタイトル
     */
    private String title;
    /**
     * CDの価格
     */
    private Float price;
    /**
     * CDの詳細
     */
    @Column(length = DESCRIPTION_LENGTH)
    private String description;
    /**
     * CDカバーイメージのバイナリデータ
     */
    @Lob
    private byte[] cover;
    /**
     * CDの販売会社名
     */
    private String musicCompany;
    /**
     * CDの枚数
     */
    private Integer numberOfCDs;
    /**
     * CDの総演奏時間
     */
    private Float totalDuration;
    /**
     * アーティストの性別
     */
    private String gender;

    /**
     * デフォルトコンストラクタ
     */
    public CD() {}

    /**
     * CDの各属性情報を引数に取るコンストラクタ
     * 
     * @param title CDのタイトル
     * @param price CDの価格
     * @param description CDの詳細情報
     * @param cover CDカバーイメージのバイナリデータ
     * @param musicCompany CDの販売会社
     * @param numberOfCDs CDの枚数
     * @param totalDuration CDの総演奏時間
     * @param gender アーティストの性別
     */
	public CD(String title, Float price, String description, byte[] cover,
			String musicCompany, Integer numberOfCDs, Float totalDuration,
			String gender) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.cover = Arrays.copyOf(cover, cover.length);
		this.musicCompany = musicCompany;
		this.numberOfCDs = numberOfCDs;
		this.totalDuration = totalDuration;
		this.gender = gender;
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

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = Arrays.copyOf(cover, cover.length);
	}

	public String getMusicCompany() {
		return musicCompany;
	}

	public void setMusicCompany(String musicCompany) {
		this.musicCompany = musicCompany;
	}

	public Integer getNumberOfCDs() {
		return numberOfCDs;
	}

	public void setNumberOfCDs(Integer numberOfCDs) {
		this.numberOfCDs = numberOfCDs;
	}

	public Float getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Float totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CD [id=" + id + ", title=" + title + ", price=" + price
				+ ", description=" + description + ", cover="
				+ Arrays.toString(cover) + ", musicCompany=" + musicCompany
				+ ", numberOfCDs=" + numberOfCDs + ", totalDuration="
				+ totalDuration + ", gender=" + gender + "]";
	}
}
