import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); //50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));  //15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณกฎส่วนลดที่1 (ซื้อ1แถม1)
        ArrayList<CartItem> promotion1 = new ArrayList<>();
        promotion1.add(new CartItem("BOGO", "Bread", 25.0, 4)); //(ซื้อแบบเลขคู่)จ่าย2ชิ้น=50
        double total4 = ShoppingCartCalculator.calculateTotalPrice(promotion1);
        if (total4 == 50.0) {
            System.out.println("PASSED: Promotion1 total is correct (50.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Promotion1 total expected 50.0 but got " + total4);
            failedCount++;
        }
        promotion1.clear();

        promotion1.add(new CartItem("BOGO", "Milk", 15.0, 3)); //(ซื้อแบบเลขคี่)จ่าย2ชิ้น=30
        total4 = ShoppingCartCalculator.calculateTotalPrice(promotion1);
        if (total4 == 30.0) {
            System.out.println("PASSED: Promotion1 total is correct (30.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Promotion1 total expected 30.0 but got " + total4);
            failedCount++;
        }
        promotion1.clear();

        promotion1.add(new CartItem("BOGO", "Meat", 40.0, 1));//???
        total4 = ShoppingCartCalculator.calculateTotalPrice(promotion1);
        if (total4 == 40.0) {
            System.out.println("PASSED: Promotion1 total is correct (40.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Promotion1 total expected 40.0 but got " + total4);
            failedCount++;
        }
        
        // Test 5: คำนวณกฎส่วนลดที่2 (ส่วนลดเมื่อซื้อเยอะ) ซื้อตั้งแต่ 6 ชิ้นขึ้นไป จะได้รับส่วนลด 10% จากราคารวมของสินค้านั้น
        ArrayList<CartItem> promotion2 = new ArrayList<>();
        promotion2.add(new CartItem("BULK", "Bread", 25.0, 6)); //จาก150*90%=135
        double total5 = ShoppingCartCalculator.calculateTotalPrice(promotion2);
        if (total5 == 135.0) {
            System.out.println("PASSED: Promotion2 total is correct (135.0)");
            passedCount++;
        } else {    
            System.out.println("FAILED: Promotion2 total expected 135.0 but got " + total5);
            failedCount++;
        }
        promotion2.clear();

        promotion2.add(new CartItem("BULK", "Milk", 15.0, 8)); //จาก120*90%=108
        total5 = ShoppingCartCalculator.calculateTotalPrice(promotion2);
        if (total5 == 108.0) {
            System.out.println("PASSED: Promotion2 total is correct (108.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Promotion2 total expected 108.0 but got " + total5);
            failedCount++;
        }

        // Test 6: คำนวณผสมทั้งสินค้าปกติและสินค้าที่มีส่วนลด
        ArrayList<CartItem> mixCart = new ArrayList<>();
        mixCart.add(new CartItem("BULK", "Bread", 25.0, 6)); //จาก150*90%=135
        mixCart.add(new CartItem("NORMAL", "Meat", 40.0, 1)); //40
        mixCart.add(new CartItem("BOGO", "Milk", 15.0, 3)); //(ซื้อแบบเลขคี่)จ่าย2ชิ้น=30
        double total6 = ShoppingCartCalculator.calculateTotalPrice(mixCart);
        if (total6 == 205.0) {
            System.out.println("PASSED: Mix cart total is correct (205.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Mix cart total expected 205.0 but got " + total6);
            failedCount++;
        }

        // Test 7: มี price หรือ quantity ติดลบ
        ArrayList<CartItem> negativeCart = new ArrayList<>();
        negativeCart.add(new CartItem("NORMAL", "Bread", -25.0, 2));
        negativeCart.add(new CartItem("NORMAL", "Milk", 15.0, -1));
        double total7 = ShoppingCartCalculator.calculateTotalPrice(negativeCart);
        if (total7 == 0.0) {
            System.out.println("PASSED: Negative price or quantity should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative price or quantity expected 0.0 but got " + total7);
            failedCount++;
        }

         // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}