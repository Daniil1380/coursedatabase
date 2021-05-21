package com.daniil1380.coursedatabase.entity;

import io.swagger.client.model.TopShare;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "top10shares", schema = "public")
public class TopSharesEntity {
    private String share;
    private String stock;
    private String currency;
    private Integer allBS;
    private Integer buy;
    private Double buyCost;
    private Integer sell;
    private Integer sellCost;


    @Id
    @Basic
    @Column(name = "share")
    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    @Basic
    @Column(name = "stock")
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "allbs")
    public Integer getAllBS() {
        return allBS;
    }

    public void setAllBS(Integer allbs) {
        this.allBS = allbs;
    }

    @Basic
    @Column(name = "buy")
    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    @Basic
    @Column(name = "buycost")
    public Double getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(Double buycost) {
        this.buyCost = buycost;
    }

    @Basic
    @Column(name = "sell")
    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    @Basic
    @Column(name = "sellcost")
    public Integer getSellCost() {
        return sellCost;
    }

    public void setSellCost(Integer sellcount) {
        this.sellCost = sellcount;
    }


    public TopShare toTopShare(){
        TopShare topShare = new TopShare();
        topShare.setShare(share);
        topShare.setAllBS(allBS);
        topShare.setBuy(buy);
        topShare.setBuyCost(BigDecimal.valueOf(buyCost));
        topShare.setCurrency(currency);
        topShare.setSell(sell);
        topShare.setSellCost(BigDecimal.valueOf(sellCost));
        topShare.setStock(stock);
        return topShare;
    }
}
