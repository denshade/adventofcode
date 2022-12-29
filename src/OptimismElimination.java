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
        Solution clone();
    }

    public Solution calculate(Solution initial)
    {
        long bestActual = 0;
        Solution bestSolution = null;
        var toProcessQueue = new PriorityQueue<Solution>();
        toProcessQueue.add(initial);
        while (toProcessQueue.size() > 0) {
            var currentSolution = toProcessQueue.poll();
            Solution bestGreedyWalk = currentSolution.getBestGreedyWalk();
            var bestCandidate = bestGreedyWalk.getActual();
            if (bestCandidate > bestActual) {
                System.out.println(bestGreedyWalk.getActual());
                bestActual = bestCandidate;
                bestSolution = bestGreedyWalk.clone();
                System.out.println("Cutting for "+bestActual);
                toProcessQueue = filterPriorityOnActual(toProcessQueue, bestActual);
            }
            long finalBestActual = bestActual;
            var notTerribleSolutions = currentSolution.getOneStepFurther().stream().filter(s -> s.getOptimistic() >= finalBestActual).collect(Collectors.toList());
            toProcessQueue.addAll(notTerribleSolutions);
        }
        return bestSolution;
    }

    private PriorityQueue<Solution> filterPriorityOnActual(PriorityQueue<Solution> toProcessQueue, long bestActual) {
        System.out.println("Removing:" + toProcessQueue.stream().filter(s -> s.getOptimistic() < bestActual).map(Object::toString).collect(Collectors.toList()));
        return toProcessQueue.stream().filter(s -> s.getOptimistic() >= bestActual).collect(Collectors.toCollection(PriorityQueue::new));
    }
}
