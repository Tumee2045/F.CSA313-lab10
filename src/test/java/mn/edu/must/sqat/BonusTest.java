package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // ---------------------------------------------------------------
    // Шат 2 — ALL-DU-PAIRS (b1..b7 нийлээд 33/33 биелэх хос)
    // ---------------------------------------------------------------

    @Test
    @DisplayName("B3 null attendance -> IAE: attendance(31->32T)")
    void b3_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("B4 [1] thr=-1 -> IAE: threshold(31->32T)")
    void b4_negThr_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("B5 [0,0] thr=0 -> 5: present(35->50T), score(51->56), best(37->53F)")
    void b5_allAbsent_thr0_is5() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {0, 0}, 0));
    }

    @Test
    @DisplayName("B6 [] thr=1 -> 0: present(35->50F), attendance(31->38F) with 0 iterations")
    void b6_empty_thr1_is0() {
        assertEquals(0, DataFlowTargets.bonus(new int[0], 1));
    }

    @Test
    @DisplayName("B7 [1,1,1,1,1] thr=10 -> 3: score(49->54) - +3 without the threshold 5")
    void b7_streak5_thr10_is3() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 10));
    }

    // ---------------------------------------------------------------
    // Хэсэг C — M2 ("best = streak" -> "best = present")-ийг унагах тест.
    // b1..b7-д best шинэчлэгдэх мөч бүрд present == streak байсан (тасалсан өдөр
    // рекорд цувааны ДАРАА л байсан) тул M2 амьд үлдсэн. Энд тасалсан өдөр рекордоос
    // ӨМНӨ байна: бодит best = 4 (< 5), мутант best = present = 5 -> +3 болно.
    // ---------------------------------------------------------------

    @Test
    @DisplayName("B8 [1,0,1,1,1,1] thr=10 -> 0: best(43->53F) with present != streak - kills M2")
    void b8_absentBeforeRecord_thr10_is0() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1, 0, 1, 1, 1, 1}, 10));
    }
}
