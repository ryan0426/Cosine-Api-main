package cn.globalyouth.cosineapi.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_resume")
public class TbResume {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`user`")
    private Integer user;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 履历库字段项
     */
    @Column(name = "record_values")
    private String recordValues;

    /**
     * pdf地址
     */
    @Column(name = "pdf_url")
    private String pdfUrl;

    /**
     * jpg地址
     */
    @Column(name = "jpg_url")
    private String jpgUrl;

    /**
     * 是否默认 1默认，0非默认
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * 评分
     */
    @Column(name = "grade")
    private Short grade;

    /**
     * 完成度
     */
    @Column(name = "`percent`")
    private Short percent;

    /**
     * 语言 1中文，2英文
     */
    @Column(name = "`language`")
    private Short language;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date addTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user
     */
    public Integer getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取履历库字段项
     *
     * @return record_values - 履历库字段项
     */
    public String getRecordValues() {
        return recordValues;
    }

    /**
     * 设置履历库字段项
     *
     * @param recordValues 履历库字段项
     */
    public void setRecordValues(String recordValues) {
        this.recordValues = recordValues;
    }

    /**
     * 获取pdf地址
     *
     * @return pdf_url - pdf地址
     */
    public String getPdfUrl() {
        return pdfUrl;
    }

    /**
     * 设置pdf地址
     *
     * @param pdfUrl pdf地址
     */
    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    /**
     * 获取jpg地址
     *
     * @return jpg_url - jpg地址
     */
    public String getJpgUrl() {
        return jpgUrl;
    }

    /**
     * 设置jpg地址
     *
     * @param jpgUrl jpg地址
     */
    public void setJpgUrl(String jpgUrl) {
        this.jpgUrl = jpgUrl;
    }


    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取评分
     *
     * @return grade - 评分
     */
    public Short getGrade() {
        return grade;
    }

    /**
     * 设置评分
     *
     * @param grade 评分
     */
    public void setGrade(Short grade) {
        this.grade = grade;
    }

    /**
     * 获取完成度
     *
     * @return percent - 完成度
     */
    public Short getPercent() {
        return percent;
    }

    /**
     * 设置完成度
     *
     * @param percent 完成度
     */
    public void setPercent(Short percent) {
        this.percent = percent;
    }

    /**
     * 获取语言 1中文，2英文
     *
     * @return language - 语言 1中文，2英文
     */
    public Short getLanguage() {
        return language;
    }

    /**
     * 设置语言 1中文，2英文
     *
     * @param language 语言 1中文，2英文
     */
    public void setLanguage(Short language) {
        this.language = language;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}