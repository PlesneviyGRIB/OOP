using System.Diagnostics;

namespace TestTask;

public class EngineStand
{
    private static int timeRatio = 1000;

    public static TimeSpan TestEngineOverheating(Engine engine)
    {
        Stopwatch stopwatch = new Stopwatch();

        try
        {
            new Thread(engine.TurnOn).Start();
            stopwatch.Start();

            while (!engine.IsOverheated() && engine.TemperatureIncreasing()) Thread.Sleep(1);

            stopwatch.Stop();
        }
        finally { engine.TurnOff(); }

        return engine.TemperatureIncreasing()? stopwatch.Elapsed * timeRatio : TimeSpan.MaxValue;
    }
}