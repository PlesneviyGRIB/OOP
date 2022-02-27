import java.math.BigDecimal;

public class HDD extends Goods{
    private static final Title title = Title.HDD;
    private int capacity;

    public HDD(String seriesNumber, BigDecimal price, String manufacturer, int count, int capacity) throws WrongPriceException, WrongCountException, WrongCapacityException {
        super(seriesNumber, price, manufacturer, count);
        checkCapacity(capacity);
        this.capacity = capacity;
    }

    public int getCapacity(){ return capacity; }

    public void setCapacity(int capacity) throws WrongCapacityException {
        checkCapacity(capacity);
        this.capacity = capacity;
    }

    @Override
    public Title getTitle(){ return title; }

    @Override
    public String toString(){
        return new StringBuilder().append("HDD: ").append(super.getSeriesNumber()).
                append(" capacity-").append(capacity).append("gb ").
                append(super.getPrice()).append(" \"").append(getManufacturer()).
                append("\" ").append(super.getCount()).toString();
    }

    private void checkCapacity(int capacity) throws  WrongCapacityException{
        if(capacity <= 0) throw new WrongCapacityException();
    }
}