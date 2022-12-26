import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class OptimismElimination
{
    interface Solution extends Comparable<Solution> {
        long getActual();
        long getOptimistic();
        Solution getBestGreedyWalk();
        List<Solution> getOneStepFurther();
    }

    public Solution calculate(Solution initial)
    {
        long bestActual = 0;
        Solution bestSolution = null;
        var toProcessQueue = new PriorityQueue<Solution>();
        toProcessQueue.add(initial);
        while (toProcessQueue.size() > 0) {
            var currentSolution = toProcessQueue.poll();
            var bestCandidate = currentSolution.getBestGreedyWalk().getActual();
            if (bestCandidate > bestActual) {
                bestActual = bestCandidate;
                bestSolution = currentSolution.getBestGreedyWalk();
                toProcessQueue = filterPriorityOnActual(toProcessQueue, bestActual);
            }
            long finalBestActual = bestActual;
            var notTerribleSolutions = currentSolution.getOneStepFurther().stream().filter(s -> s.getOptimistic() >= finalBestActual).collect(Collectors.toList());
            toProcessQueue.addAll(notTerribleSolutions);
        }
        return bestSolution;
    }

    private PriorityQueue<Solution> filterPriorityOnActual(PriorityQueue<Solution> toProcessQueue, long bestActual) {
        return toProcessQueue.stream().filter(s -> s.getOptimistic() >= bestActual).collect(Collectors.toCollection(PriorityQueue::new));
    }
}
