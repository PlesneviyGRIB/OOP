import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private final String url = "jdbc:h2:/home/egor/IdeaProjects/bd/goods";
    private final String user = "MASTER";
    private final String password = "8220AAaa";
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    DataBase() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public void addGoodsToDB(Goods item) throws SQLException {
        try {
            switch (item.getTitle()) {
                case PC -> {
                    PC pc = (PC) item;
                    statement.executeUpdate("INSERT INTO PC VALUES ('" + pc.getSeriesNumber() + "', " + pc.getPrice() + ", '" + pc.getManufacturer() + "', " + pc.getCount() + ", '" + pc.getType() + "')");
                }
                case HDD -> {
                    HDD hdd = (HDD) item;
                    statement.executeUpdate("INSERT INTO HDD VALUES ('" + hdd.getSeriesNumber() + "', " + hdd.getPrice() + ", '" + hdd.getManufacturer() + "', " + hdd.getCount() + ", '" + hdd.getCapacity() + "')");
                }
                case LAPTOP -> {
                    Laptop laptop = (Laptop) item;
                    statement.executeUpdate("INSERT INTO LAPTOP VALUES ('" + laptop.getSeriesNumber() + "', " + laptop.getPrice() + ", '" + laptop.getManufacturer() + "', " + laptop.getCount() + ", '" + laptop.getSize() + "')");
                }
                case MONITOR -> {
                    Monitor monitor = (Monitor) item;
                    statement.executeUpdate("INSERT INTO MONITOR VALUES ('" + monitor.getSeriesNumber() + "', " + monitor.getPrice() + ", '" + monitor.getManufacturer() + "', " + monitor.getCount() + ", '" + monitor.getSize() + "')");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public Goods getByID(String id) {
        try {
            rs = statement.executeQuery("SELECT * FROM PC WHERE SERIESNUMBER = '" + id + "'");
            if (rs.next())
                return new PC(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), defineType(rs.getString(5)));
            rs = statement.executeQuery("SELECT * FROM HDD WHERE SERIESNUMBER = '" + id + "'");
            if (rs.next())
                return new HDD(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            rs = statement.executeQuery("SELECT * FROM LAPTOP WHERE SERIESNUMBER = '" + id + "'");
            if (rs.next())
                return new Laptop(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            rs = statement.executeQuery("SELECT * FROM MONITOR WHERE SERIESNUMBER = '" + id + "'");
            if (rs.next())
                return new Monitor(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            throw new NoSuchItemExceptiom();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchItemExceptiom e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void GoodsRefactor(String id, Goods item) {
        try {
            Goods oldItem = getByID(id);
            switch (oldItem.getTitle()) {
                case PC -> statement.execute("DELETE FROM PC WHERE SERIESNUMBER = '" + id + "'");
                case HDD -> statement.execute("DELETE FROM HDD WHERE SERIESNUMBER = '" + id + "'");
                case LAPTOP -> statement.execute("DELETE FROM LAPTOP WHERE SERIESNUMBER = '" + id + "'");
                case MONITOR -> statement.execute("DELETE FROM MONITOR WHERE SERIESNUMBER = '" + id + "'");
            }
            addGoodsToDB(item);
        }
        catch (SQLException e) { e.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public List<Goods> getGoodsByType(Title title) {
        LinkedList<Goods> goods = new LinkedList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM " + title.toString());
            while (rs.next()) {
                switch (title) {
                    case PC -> goods.add(new PC(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), defineType(rs.getString(5))));
                    case HDD -> goods.add(new HDD(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
                    case LAPTOP -> goods.add(new Laptop(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
                    case MONITOR -> goods.add(new Monitor(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return goods;
    }

    public void finalize() {
        try {
            connection.close();
            statement.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Type defineType(String s) {
        switch (s) {
            case "DESKTOP":
                return Type.DESKTOP;
            case "NETTOP":
                return Type.NETTOP;
            case "MONOBLOCK":
                return Type.MONOBLOCK;
            default:
                return null;
        }
    }
}