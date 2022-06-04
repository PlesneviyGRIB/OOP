using System;
using System.Diagnostics;
using System.Threading;

namespace TestProject
{
    public class EngineStand
    {
        private static int timeRatio = 100;

        public static TimeSpan TestEngineOverheating(Engine engine)
        {
            Stopwatch stopwatch = new Stopwatch();
            
            try
            {
                if (engine.IsOverheated()) return TimeSpan.Zero;

                new Thread(engine.TurnOn).Start();
                stopwatch.Start();

                while (!engine.IsOverheated() && engine.TemperatureIncreasing()) Thread.Sleep(10);

                stopwatch.Stop();
            }
            finally { engine.TurnOff(); }

            return engine.TemperatureIncreasing() ? stopwatch.Elapsed * timeRatio : TimeSpan.MaxValue;
        }
    }
}