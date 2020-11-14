
package edu.greenblitz.bigRodika.utils;

public class DumbDH implements IDirectionHandler {
    
    @Override
    public boolean handle(double angle, double linVel) {
        return angle <= 90 && linVel <= 0;
    }
}
