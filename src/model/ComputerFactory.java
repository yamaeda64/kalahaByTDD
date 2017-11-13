package model;


public class ComputerFactory
{
    
    public MediumComputer getMediumComputer()
    {
        return new MediumComputer();
    }
}
