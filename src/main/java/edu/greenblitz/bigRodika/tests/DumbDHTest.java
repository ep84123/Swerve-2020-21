package edu.greenblitz.bigRodika.tests;

import edu.greenblitz.bigRodika.utils.DumbDH;
import org.jetbrains.annotations.TestOnly;

public class DumbDHTest {

    void test(){
        DumbDH ddh = new DumbDH();

        //Vel 0
        assert ddh.handle(10,0);
        assert ddh.handle(90,0);
        assert !ddh.handle(100, 0);
    }
}
