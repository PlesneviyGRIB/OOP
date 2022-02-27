import java.math.BigDecimal;

public class Laptop extends Goods {
    private static final Title title = Title.LAPTOP;
    private int size;

    public Laptop(String seriesNumber, BigDecimal price, String manufacturer, int count, int size) throws WrongPriceException, WrongCountException, WrongLaptopSizeException {
        super(seriesNumber, price, manufacturer, count);
        checkSize(size);
        this.size = size;
    }

    public int getSize(){ return size; }

    public void setSize(int size) throws WrongLaptopSizeException{
        checkSize(size);
        this.size = size;
    }

    @Override
    public Title getTitle(){ return title; }

    @Override
    public String toString(){
        return new StringBuilder().append("Laptop: ").append(super.getSeriesNumber()).
                append(" size-").append(size).append(" ").
                append(super.getPrice()).append(" \"").append(getManufacturer()).
                append("\" ").append(super.getCount()).toString();
    }

    private void checkSize(int size) throws WrongLaptopSizeException{
        switch (size){
            case 13, 14, 15, 17: break;
            default: throw new WrongLaptopSizeException();
        }
    }
}