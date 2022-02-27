import java.math.BigDecimal;

enum Title{ PC, LAPTOP, HDD, MONITOR }

public abstract class Goods {
    private String seriesNumber;
    private BigDecimal price;
    private String manufacturer;
    private int count;

    Goods(String seriesNumber, BigDecimal price, String manufacturer, int count) throws WrongPriceException, WrongCountException{
        this.seriesNumber = seriesNumber;
        priceChecker(price);
        this.price = price;
        this.manufacturer = manufacturer;
        countChecker(count);
        this.count = count;
    }

    public String getSeriesNumber(){ return seriesNumber; }

    public BigDecimal getPrice() { return price; }

    public String getManufacturer() { return manufacturer; }

    public int getCount() { return count; }

    public abstract Title getTitle();

    public void setPrice(BigDecimal price) throws WrongPriceException{
        priceChecker(price);
        this.price = price;
    }

    public void setSeriesNumber(String seriesNumber){ this.seriesNumber = seriesNumber; }

    public void setManufacturer(String manufacturer){ this.manufacturer = manufacturer; }

    public void setCount(int count) throws WrongCountException{
        countChecker(count);
        this.count = count;
    }

    private void priceChecker(BigDecimal price) throws WrongPriceException{
        if(price.compareTo(BigDecimal.ZERO) < 0) throw new WrongPriceException();
    }

    private void countChecker(int count) throws WrongCountException{
        if(count < 0) throw new WrongCountException();
    }
}