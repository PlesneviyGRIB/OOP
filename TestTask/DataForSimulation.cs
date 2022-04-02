using System.Text.Json;

namespace TestTask;

public class DataForSimulation
{
    public double inertiaCoeff{ get; }
    public SortedDictionary<int, int> dependencyMV { get; }
    public double overheating { get; }
    public double coeffM { get; }
    public double coeffV { get; }
    public double refrigeration { get; }
    
    public DataForSimulation(double inertiaCoeff, SortedDictionary<int, int> dependencyMV, double overheating, double coeffM, double coeffV, double refrigeration)
    {
        this.inertiaCoeff = inertiaCoeff;
        this.dependencyMV = dependencyMV;
        this.overheating = overheating;
        this.coeffM = coeffM;
        this.coeffV = coeffV;
        this.refrigeration = refrigeration;
    }

    public static DataForSimulation GetDataForSimulation(String path)
    {
        return JsonSerializer.Deserialize<DataForSimulation>(new StreamReader(path).ReadLine() ?? throw new FileNotFoundException()) ?? throw new JsonException();
    }
}