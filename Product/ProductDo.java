package com.cbx.tby.entity.product;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.cbx.tby.entity.ISharable;
import com.cbx.tby.entity.common.Category;
import com.cbx.tby.entity.common.Color;
import com.cbx.tby.entity.embedded.copy_info.CopyInfoDO;
import com.cbx.tby.entity.embedded.share.ShareDO;
import com.cbx.tby.entity.file.FileObject;

@Document(collection = "product")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@indexProduct}", type = "product")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProductDO implements ISharable {

    @Id
    private String id;

    private String itemNo;

    @TextIndexed
    private String description;

    @TextIndexed
    private String itemName;

    private Double priceFrom;

    private Double priceTo;

    private String currency;

    private Double moq;

    @TextIndexed
    private String moqUnit;

    @TextIndexed
    private List<Category> categories;

    @TextIndexed
    private String owner;

    private String creator;

    @TextIndexed
    private String countryOfOrigin;

    private String size;

    private List<FileObject> images;

    private List<FileObject> attachment;

    @Indexed
    private Date created;

    private List<Color> colors;

    private List<String> sellingPoints;

    private String material;

    private List<ShareDO> shares;

    private CopyInfoDO copyInfo;

    /**
     *  This field is a reference.
     *  For buyer-catalog product use: Buyer catalog product reference to supplier product.
     *    (Reference to a different collection)
     *      { "supplier" : *supplier_product_id* }
     *
     *  For publish use: Private product reference to public product.
     *    (Reference in the same collection)
     *      { "publish" : *public_product_id* }
     */
    private Map<String, String> relations = new HashMap<>();

    private String creatorRole;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // for elasticsearch set embedded document id
    public void set_id(String _id) {
        this.id = _id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMoq() {
        return moq;
    }

    public void setMoq(Double moq) {
        this.moq = moq;
    }

    public String getMoqUnit() {
        return moqUnit;
    }

    public void setMoqUnit(String moqUnit) {
        this.moqUnit = moqUnit;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<FileObject> getImages() {
        return images;
    }

    public void setImages(List<FileObject> images) {
        this.images = images;
    }

    public List<FileObject> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<FileObject> attachs) {
        this.attachment = attachs;
    }

    public void setCreated(Date date) {
        this.created = date;
    }

    public Date getCreated() {
        return created;
    }

    public Map<String, String> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, String> relations) {
        this.relations = relations;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public List<String> getSellingPoints() {
        return sellingPoints;
    }

    public void setSellingPoints(List<String> sellingPoints) {
        this.sellingPoints = sellingPoints;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Iterator<ShareDO> getShares() {
        return (null == shares) ? null : shares.iterator();
    }

    public void setShares(List<ShareDO> shares) {
        this.shares = shares;
    }

    public CopyInfoDO getCopyInfo() {
        return copyInfo;
    }

    public void setCopyInfo(CopyInfoDO copyInfo) {
        this.copyInfo = copyInfo;
    }

    public boolean isCopyProduct() {
        return null != copyInfo && StringUtils.isNotEmpty(copyInfo.getOwnerId());
    }

    @Override
    public boolean notifiable() {
        return true;
    }
}
