namespace TestTask;

public interface Engine
{
    public void TurnOn();

    public void TurnOff();

    public bool IsOverheated();

    public bool TemperatureIncreasing();
}