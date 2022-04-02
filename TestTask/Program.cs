using TestTask;

public class Program
{
    private static readonly String path = "/home/egor/RiderProjects/Solution1/TestTask/TestTask/data.json";
    
    public static void Main(string[] args)
    {
        double temperature = Convert.ToInt32(Console.ReadLine());

        TimeSpan timeSpan = EngineStand.TestEngineOverheating(new InternalEngine(DataForSimulation.GetDataForSimulation(path), temperature));

        if (timeSpan != TimeSpan.MaxValue) Console.WriteLine("{0:00}:{1:00}:{2:00}.{3:00}", timeSpan.Hours, timeSpan.Minutes, timeSpan.Seconds, timeSpan.Milliseconds / 10);
        else Console.WriteLine("engine will not overheat");
    }
}