import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws Exception {

        DataBase dataBase = new DataBase();
        dataBase.addGoodsToDB( new PC("32ASSWwG#asd", BigDecimal.valueOf(630.90),"HyperPC", 6, Type.DESKTOP));
        dataBase.addGoodsToDB( new Laptop("kamdwG#asd", BigDecimal.valueOf(130.90),"Huawei", 1, 14));
        dataBase.addGoodsToDB( new HDD("11wqLlLlcWwG#asd", BigDecimal.valueOf(59.90),"Toshiba", 3, 256));
        dataBase.addGoodsToDB( new Monitor("fdswG#asd", BigDecimal.valueOf(210.90),"ЛОСЬ", 1, 49));

//        dataBase.GoodsRefactor("32ASSWwG#asd", new PC("as", BigDecimal.valueOf(12),"ya", 30, Type.MONOBLOCK));

        System.out.println(dataBase.getGoodsByType(Title.PC));
        System.out.println(dataBase.getGoodsByType(Title.HDD));
        System.out.println(dataBase.getGoodsByType(Title.LAPTOP));
        System.out.println(dataBase.getGoodsByType(Title.MONITOR));
    }
}