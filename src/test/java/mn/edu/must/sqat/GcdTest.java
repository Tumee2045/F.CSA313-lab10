package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * gcd() — all-DU-pairs. Мөрийн дугаар нь DataFlowTargets.java-ийн мөр:
 * 13 def x,y · 15 while (y != 0) p-use y · 16 tmp = x % y · 17 x = y · 18 y = tmp · 20 return x.
 * Нийт 13 DU хос (x: 4, y: 8, tmp: 1) — README-ийн хүснэгтийг үз.
 */
class GcdTest {

    @Test
    @DisplayName("G1 gcd(7,0)=7, 0 iterations: x(13->20), y(13->15F)")
    void g1_gcd7_0_is7() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2 gcd(12,8)=4, 2 iterations: x(13->16) x(17->16) x(17->20) "
            + "y(13->15T) y(13->16) y(13->17) y(18->15T) y(18->15F) y(18->16) y(18->17) tmp(16->18)")
    void g2_gcd12_8_is4() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("G3 gcd(0,5)=5, 1 iteration: x(13->16) x(17->20) y(13->15T) y(13->16) y(13->17) "
            + "y(18->15F) tmp(16->18) - no loop-back pairs")
    void g3_gcd0_5_is5() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
