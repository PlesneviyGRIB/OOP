using System;

namespace TestProject
{
    public class Program
    {
        private static readonly String path = "C:\\Users\\unsto\\Documents\\GitHub\\OOP\\TestProject\\TestProject\\data.json";

        public static void Main(string[] args)
        {
            Console.Write("enter temperature: ");

            double temperature = Convert.ToDouble(Console.ReadLine());

            TimeSpan timeSpan = EngineStand.TestEngineOverheating(new InternalEngine(DataForSimulation.GetDataForSimulation(path), temperature));

            if (timeSpan != TimeSpan.MaxValue) Console.WriteLine("{0:00}:{1:00}:{2:00}.{3:00}", timeSpan.Hours, timeSpan.Minutes, timeSpan.Seconds, timeSpan.Milliseconds / 10);
            else Console.WriteLine("engine will not overheat");
        }
    }
}