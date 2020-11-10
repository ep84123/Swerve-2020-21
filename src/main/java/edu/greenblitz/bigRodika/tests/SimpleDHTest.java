package edu.greenblitz.bigRodika.tests;

import edu.greenblitz.bigRodika.utils.SimpleDriverFriendlyDH;

public class SimpleDHTest {

    void test(){
        SimpleDriverFriendlyDH ddh = new SimpleDriverFriendlyDH();

        //Vel 0
        assert ddh.handle(10,0, new double[]{100,1.5});
        assert ddh.handle(90,0, new double[]{100,1.5});
        assert !ddh.handle(100,0, new double[]{100,1.5});

        //Ang 170
        assert !ddh.handle(170,0, new double[]{100, 1.5});
        assert !ddh.handle(170,3, new double[]{100, 1.5});
        assert !ddh.handle(170,2, new double[]{100, 1.5});

        //Ang 0
        assert !ddh.handle(0,0, new double[]{100, 1.5});
        assert !ddh.handle(0,3, new double[]{100, 1.5});
        assert !ddh.handle(0,2, new double[]{100, 1.5});

        //Ang 0.1
        assert !ddh.handle(0.1,0, new double[]{100, 1.5});
        assert !ddh.handle(0.1,3, new double[]{100, 1.5});
        assert !ddh.handle(0.1,2, new double[]{100, 1.5});

        //Ang 90
        assert !ddh.handle(0.1,1, new double[]{100, 1.5});
        assert !ddh.handle(0.1,2, new double[]{100, 1.5});
        assert !ddh.handle(0.1,3    , new double[]{100, 1.5});


    }
}
