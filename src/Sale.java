public class Sale {
    private String monthYear;
    private String seller;
    private Integer items;
    private Double total;

    public Sale(String monthYear, String seller, Integer items, Double total) {
        this.monthYear = monthYear;
        this.seller = seller;
        this.items = items;
        this.total = total;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public String getSeller() {
        return seller;
    }

    public Integer getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public Double getAveragePrice() {
        return total / items;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f, pm = %.2f", monthYear, seller, items, total, getAveragePrice());
    }
}
