package edu.greenblitz.bigRodika.tests;

import edu.greenblitz.bigRodika.utils.SimpleDriverFriendlyDH;

public class SimpleDHTest {

    void test(){
        SimpleDriverFriendlyDH ddh = new SimpleDriverFriendlyDH();

        assert ddh.handle(10,0, new double[]{100,1.5});
        assert ddh.handle(90,0, new double[]{100,1.5});
        assert !ddh.handle(100,0, new double[]{100,1.5});
    }
}
