package edu.greenblitz.bigRodika.exceptions;

public class MotorPowerOutOfRangeException extends Exception{
    public MotorPowerOutOfRangeException(){
        super("Tried to set drive motor to power out of [-1; 1]");
    }
}
