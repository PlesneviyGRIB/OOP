import java.math.BigDecimal;

enum Type{ DESKTOP, NETTOP, MONOBLOCK; }

public class PC extends Goods {
    private static final Title title = Title.PC;
    private Type type;

    public PC(String seriesNumber, BigDecimal price, String manufacturer, int count, Type type) throws WrongPriceException, WrongCountException {
        super(seriesNumber, price, manufacturer, count);
        this.type = type;
    }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    @Override
    public Title getTitle(){ return title; }

    @Override
    public String toString(){
        return new StringBuilder().append("PC: ").append(super.getSeriesNumber()).
                                   append(" type-").append(type.toString()).append(" ").
                                   append(super.getPrice()).append(" \"").append(getManufacturer()).
                                   append("\" ").append(super.getCount()).toString();
    }
}