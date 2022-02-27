import java.math.BigDecimal;

public class Monitor extends Goods{
    private static final Title title = Title.MONITOR;
    private int size;

    public Monitor(String seriesNumber, BigDecimal price, String manufacturer, int count, int size) throws WrongPriceException, WrongCountException, WrongMonitorDiagonalException {
        super(seriesNumber, price, manufacturer, count);
        checkSize(size);
        this.size = size;
    }

    public int getSize() { return size; }

    public void setSize(int size) throws WrongMonitorDiagonalException{
        checkSize(size);
        this.size = size;
    }

    @Override
    public Title getTitle(){ return title; }

    @Override
    public String toString(){
        return new StringBuilder().append("Monitor: ").append(super.getSeriesNumber()).
                append(" size-").append(size).append(" ").
                append(super.getPrice()).append(" \"").append(getManufacturer()).
                append("\" ").append(super.getCount()).toString();
    }

    private void checkSize(int size) throws WrongMonitorDiagonalException{
        if(size <= 0) throw new WrongMonitorDiagonalException();
    }
}
