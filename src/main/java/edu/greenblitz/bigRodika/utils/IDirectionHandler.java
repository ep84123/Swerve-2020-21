package edu.greenblitz.bigRodika.utils;

import com.fasterxml.jackson.databind.util.ClassUtil;

/**
 * @author Orel
 */

public interface IDirectionHandler {

    boolean handle(double angle, double linVel);

}
