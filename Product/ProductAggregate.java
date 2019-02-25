package com.cbx.tby.entity.product;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cbx.tby.component.query.entity.aggregate.ICompanyAggregate;
import com.cbx.tby.component.query.entity.aggregate.IFavoriteAggregate;
import com.cbx.tby.component.query.entity.aggregate.IPublicationAggregate;
import com.cbx.tby.component.query.entity.aggregate.IRatingAggregate;
import com.cbx.tby.component.query.entity.aggregate.ISearchFilterAggregate;
import com.cbx.tby.constants.GeneralConstants;
import com.cbx.tby.entity.ISharable;
import com.cbx.tby.entity.common.Category;
import com.cbx.tby.entity.common.Color;
import com.cbx.tby.entity.file.FileObject;
import com.cbx.tby.entity.company.CompanyDO;
import com.cbx.tby.entity.embedded.copy_info.CopyInfoDO;
import com.cbx.tby.entity.embedded.share.ShareDO;
import com.cbx.tby.entity.rating.RatingDO;
import com.cbx.tby.utils.Favorite;

public class ProductAggregate
        implements IFavoriteAggregate, IPublicationAggregate, ICompanyAggregate,
        ISearchFilterAggregate, IRatingAggregate, ISharable {

    private String id;

    private String itemNo;

    private String description;

    private String itemName;

    private Double priceFrom;

    private Double priceTo;

    private String currency;

    private Double moq;

    private String moqUnit;

    private List<Category> categories;

    private String owner;

    private String creator;

    private String countryOfOrigin;

    private String size;

    private List<FileObject> images;

    private List<FileObject> attachment;

    private Date created;

    private List<Color> colors;

    private List<String> sellingPoints;

    private String material;

    private List<Favorite> favoriteInfo;

    private String companyName;

    private Boolean verifiedCompany;

    private String publicationId;

    private String publicationType;

    private Map<String, String> relations = new HashMap<>();

    private String creatorRole;

    private List<ShareDO> shares;

    private String shareType;

    private CompanyDO company;

    private List<RatingDO> ratingInfo;

    private CopyInfoDO copyInfo;

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

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    @Override
    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public Double getMoq() {
        return moq;
    }

    public void setMoq(Double moq) {
        this.moq = moq;
    }

    @Override
    public String getMoqUnit() {
        return moqUnit;
    }

    public void setMoqUnit(String moqUnit) {
        this.moqUnit = moqUnit;
    }

    @Override
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

    @Override
    public List<Favorite> getFavoriteInfo() {
        return favoriteInfo;
    }

    @Override
    public void setFavoriteInfo(List<Favorite> favoriteInfo) {
        this.favoriteInfo = favoriteInfo;
    }

    @Override
    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public String getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(String publicationType) {
        this.publicationType = publicationType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String ownerName) {
        this.companyName = ownerName;
    }

    public Boolean isVerifiedCompany() {
        return verifiedCompany;
    }

    public void setVerifiedCompany(Boolean verifiedCompany) {
        this.verifiedCompany = verifiedCompany;
    }

    @Override
    public String getCompanyId() {
        return owner;
    }

    @Override
    public Iterator<ShareDO> getShares() {
        return (null == shares) ? null : shares.iterator();
    }

    @Override
    public void setShares(List<ShareDO> shares) {
        this.shares = shares;
    }

    public String getShareType() {
        return this.shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    @Override
    public CompanyDO getCompany() {
        return company;
    }

    @Override
    public void setCompany(CompanyDO company) {
        this.company = company;
    }

    public List<RatingDO> getRatingInfo() {
        return ratingInfo;
    }

    @Override
    public void setRatingInfo(List<RatingDO> ratingInfo) {
        this.ratingInfo = ratingInfo;
    }

    public CopyInfoDO getCopyInfo() {
        return copyInfo;
    }

    public void setCopyInfo(CopyInfoDO copyInfo) {
        this.copyInfo = copyInfo;
    }

    @Override
    public boolean notifiable() {
        return false;
    }

    public String getStuffType() {
        return GeneralConstants.FAVORITE_STUFF_TYPE_PRODUCT;
    }

}
