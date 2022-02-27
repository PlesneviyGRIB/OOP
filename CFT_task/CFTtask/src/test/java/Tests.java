import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class Tests {
 @Test
 public void GoodsClassTest() {
  try {
   new PC("-1", BigDecimal.valueOf(-1), "ChinaPC", 1, Type.MONOBLOCK);
  } catch (Exception e) { Assertions.assertEquals(new WrongPriceException().getClass(), e.getClass()); }

  try {
   new PC("-1", BigDecimal.valueOf(10), "ChinaPC", -10, Type.MONOBLOCK);
  }
  catch (Exception e) { Assertions.assertEquals(new WrongCountException().getClass(), e.getClass()); }

  try{
   PC pc = new PC("12sadsa@ds", BigDecimal.valueOf(10), "ChinaPC", 100, Type.DESKTOP);
   Assertions.assertEquals(100, pc.getCount());
   Assertions.assertEquals("12sadsa@ds", pc.getSeriesNumber());
   Assertions.assertEquals(BigDecimal.valueOf(10), pc.getPrice());
   Assertions.assertEquals("ChinaPC", pc.getManufacturer());

   pc.setSeriesNumber("123");
   Assertions.assertEquals("123", pc.getSeriesNumber());

   pc.setCount(120);
   Assertions.assertEquals(120, pc.getCount());

   pc.setPrice(BigDecimal.valueOf(1200.99));
   Assertions.assertEquals(BigDecimal.valueOf(1200.99), pc.getPrice());

   pc.setManufacturer("ya");
   Assertions.assertEquals("ya", pc.getManufacturer());
  } catch (Exception e) {}

 }

 @Test
 public void PCClassTest(){
  try {
   PC pc = new PC("-1", BigDecimal.valueOf(10), "ChinaPC", 10, Type.DESKTOP);

   Assertions.assertEquals("PC: -1 type-DESKTOP 10 \"ChinaPC\" 10", pc.toString());
   Assertions.assertEquals(Type.DESKTOP, pc.getType());

   pc.setType(Type.MONOBLOCK);
   Assertions.assertEquals(Type.MONOBLOCK, pc.getType());
  } catch (Exception e) {}

 }

 @Test
 public void LaptopClassTest(){
  try {
   Laptop laptop = new Laptop("-1", BigDecimal.valueOf(10), "ChinaPC", 10, 14);

   Assertions.assertEquals("Laptop: -1 size-14 10 \"ChinaPC\" 10", laptop.toString());
   Assertions.assertEquals(14, laptop.getSize());

   laptop.setSize(17);
   Assertions.assertEquals(17, laptop.getSize());

   new Laptop("-1", BigDecimal.valueOf(10), "ChinaPC", 10, 140);
  }
  catch (Exception e) { Assertions.assertEquals(new WrongLaptopSizeException().getClass(), e.getClass()); }
 }

 @Test
 public void HDDClassTest(){
  try {
   HDD hdd = new HDD("-1", BigDecimal.valueOf(10), "ChinaPC", 10, 256);

   Assertions.assertEquals("HDD: -1 capacity-256gb 10 \"ChinaPC\" 10", hdd.toString());
   Assertions.assertEquals(256, hdd.getCapacity());

   hdd.setCapacity(1024);
   Assertions.assertEquals(1024,hdd.getCapacity());

   new HDD("-1", BigDecimal.valueOf(10), "ChinaPC", 10, -9);
  }
  catch (Exception e) { Assertions.assertEquals(new WrongCapacityException().getClass(), e.getClass()); }
 }

 @Test
 public void MoitorClassTest(){
  try {
   Monitor monitor = new Monitor("-1", BigDecimal.valueOf(10), "ChinaPC", 10, 49);

   Assertions.assertEquals("Monitor: -1 size-49 10 \"ChinaPC\" 10", monitor.toString());
   Assertions.assertEquals(49, monitor.getSize());

   monitor.setSize(102);
   Assertions.assertEquals(102,monitor.getSize());

   new Monitor("-1", BigDecimal.valueOf(10), "ChinaPC", 10, -9);
  }
  catch (Exception e) { Assertions.assertEquals(new WrongMonitorDiagonalException().getClass(), e.getClass()); }
 }
}