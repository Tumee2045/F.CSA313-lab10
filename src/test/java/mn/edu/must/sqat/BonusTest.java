package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus() — DU тестүүд. Мөрийн дугаар нь DataFlowTargets.java-ийн мөр (31–56).
 * Шат 1: ALL-DEFS — 13 def бүр дор хаяж нэг use-д def-clear замаар хүрнэ.
 * score-ийн 49 ба 51 дахь def нэг гүйцэтгэлд хоёулаа use-д хүрч чадахгүй
 * (50-ийн T/F салаа харилцан үгүйсгэнэ) тул хамгийн цөөндөө 2 тест.
 */
class BonusTest {

    // ---------------------------------------------------------------
    // Шат 1 — ALL-DEFS (2 тест)
    // ---------------------------------------------------------------

    @Test
    @DisplayName("B1 [1,1,1,1,1,0,1] thr=6 -> 8: defs present35/40, streak36/41/46, best37/43, a38, score51/54")
    void b1_mixed_thr6_is8() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 6));
    }

    @Test
    @DisplayName("B2 [1] thr=5 -> 0: def score49 reaches use 56 (present 1 < 5)")
    void b2_single_thr5_is0() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1}, 5));
    }
}
