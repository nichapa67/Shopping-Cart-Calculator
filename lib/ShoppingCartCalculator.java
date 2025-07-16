import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * -->เป็นการคำนวณสินค้าแต่ละชิ้นในตะกร้าว่าจะต้องจ่ายเท่าไหร่ โดยมีกฎส่วนลด2ข้อ คือ
     *    ซื้อ1แถม1 มีรหัสการซื้อคือBOGO และซื้อ>=6ชิ้นจะลด10% มีรหัสการซื้อคือBULK
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * -->ส่งค่ากลับ0.0
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * -->ส่งค่ากลับ0.0และบอกว่าราคาหรือจำนวนสินค้าไม่ถูกต้อง
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * -->คำนวณราคาสินค้าโดยจ่ายเงินสำหรับจำนวนชิ้นที่เป็นเลขคู่ เช่นซื้อ2จ่ายแค่1=>2/2=1 
     *    และจ่ายเงินสำหรับชิ้นที่เหลือในกรณีที่เป็นเลขคี่ เช่นซื้อ3=> 3/2=1 + 3%2=1 =1+1=2
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * -->เมื่อซื้อสินค้าตั้งแต่ 6 ชิ้นขึ้นไป คำนวณราคาสินค้าโดยจ่ายเงิน 90%(0.9) ของราคารวมทั้งหมด
     * -@param items ประกอบด้วย(sku,name,price,quantity)
     * sku คือ string ของโค้ดสินค้าที่ใช้ตรวจจำแนกการคำนวณราคาสินค้าตามกฎส่วนลด
     * name คือ ชื่อของสินค้า
     * price คือ ราคาของสินค้า
     * quantity คือ จำนวนสินค้าที่ซื้อ
     * @return ราคารวมทั้งหมดหลังคำนวณกฎส่วนลด
     */

    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        //ตรวจสอบกรณีที่ตะกร้าสินค้าเป็น null หรือ Empty
        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        double total = 0.0; //เก็บราคารวมทั้งหมด

        //วนลูปผ่านสินค้าแต่ละชิ้นในตะกร้า
        for (CartItem item : items) {

            //ราคาและจำนวนไม่ติดลบ
            double itemPrice = Math.max(0, item.price());
            int itemQuantity = Math.max(0, item.quantity());

            double itemSubtotal = itemPrice * itemQuantity; //คำนวณราคารวมของสินค้าแต่ละชิ้น

            //ใช้ส่วนลดซื้อ1แถม1 BOGO
            if ("BOGO".equals(item.sku())) {
                int paidQuantity = (itemQuantity / 2) + (itemQuantity % 2); //จำนวนชิ้นที่ต้องจ่ายเงิน (ซื้อ 1 แถม 1)
                itemSubtotal = itemPrice * paidQuantity;
            }

            //ใช้ส่วนลดซื้อ>=6ชิ้นจะลด10% BULK
            else if ("BULK".equals(item.sku()) && itemQuantity >= 6) {
                itemSubtotal *= 0.90;//จ่าย 90%
            }

            total += itemSubtotal; //ราคารวมสินค้าทั้งหมดหลังหักส่วนลดแล้ว
        }

        return total; //ราคารวมสินค้าทั้งหมด
    }
}
