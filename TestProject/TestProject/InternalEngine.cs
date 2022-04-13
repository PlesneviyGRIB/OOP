using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

namespace TestProject
{
    public class InternalEngine : Engine
    {
        private DataForSimulation dataForSimulation;
        private double currentTemperature;
        private double initialTemperature;
        private double CurrentCrankshaftVelocity;
        private bool state;

        public InternalEngine(DataForSimulation dataForSimulation, double temperature)
        {
            this.dataForSimulation = dataForSimulation;
            currentTemperature = temperature;
            initialTemperature = temperature;
        }
        public void TurnOn()
        {
            CurrentCrankshaftVelocity = 0;
            state = true;

            while (state)
            {
                Thread.Sleep(10);
                CurrentCrankshaftVelocity += CurrentAcceleration();
                currentTemperature += HeatingRate() + RefrigerationRate();
            }
        }

        public void TurnOff() { state = false; }

        public bool IsOverheated() { return currentTemperature >= dataForSimulation.overheating; }

        public bool TemperatureIncreasing() { return Math.Round(HeatingRate(),2).CompareTo(Math.Round(Math.Abs(RefrigerationRate()),2)).Equals(1); }

        private int CurrentTorque(double currentVelocity)
        {
            List<int> velocities = new List<int>(dataForSimulation.dependencyMV.Keys);

            for (int i = 0; i < velocities.Count - 1; i++)
                if (currentVelocity >= velocities[i] && currentVelocity < velocities[i + 1])
                    return dataForSimulation.dependencyMV.GetValueOrDefault(velocities[i]);

            return dataForSimulation.dependencyMV.Last().Value;
        }

        private double CurrentAcceleration() { return CurrentTorque(CurrentCrankshaftVelocity) / dataForSimulation.inertiaCoeff * 6.28; }

        private double HeatingRate() { return CurrentTorque(CurrentCrankshaftVelocity) * dataForSimulation.coeffM + Math.Pow(CurrentCrankshaftVelocity, 2) * dataForSimulation.coeffV; }

        private double RefrigerationRate() { return dataForSimulation.refrigeration * (initialTemperature - currentTemperature); }
    }
}