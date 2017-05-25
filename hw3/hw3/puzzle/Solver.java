package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by mwang on 5/24/17.
 */
public class Solver {

    public class searchNode {
        public WorldState state;
        public int moveNums;
        public searchNode pre;
        public int estimateMoves;
        public int totalPriority;

        public searchNode(WorldState state, int moveNums, searchNode pre) {
            this.state = state;
            this.moveNums = moveNums;
            this.pre = pre;
            this.estimateMoves = state.estimatedDistanceToGoal();
            this.totalPriority = this.moveNums + this.estimateMoves;
        }
    }

    public searchNode initialNode;
    public int moves;
    public Queue<WorldState> solution = new Queue<>();
    public Solver(WorldState initial) {
        this.initialNode = new searchNode(initial, 0, null);
        this.moves = 0;

        Comparator<searchNode> cmp = new Comparator<searchNode>() {
            @Override
            public int compare(searchNode o1, searchNode o2) {
                return o1.totalPriority - o2.totalPriority;
            }
        };

        MinPQ<searchNode> infringe = new MinPQ<>(cmp);
        infringe.insert(initialNode);
        Set<WorldState> searched = new HashSet<>();

        while (true) {
            searchNode s = infringe.delMin();
            searched.add(s.state);

            solution.enqueue(s.state);

            if (s.state.isGoal()) break;
            moves += 1;
            infringe = new MinPQ<>(cmp);
            for (WorldState eachNeig: s.state.neighbors()) {
                if (eachNeig != s.pre && (! searched.contains(eachNeig))) {
                    searchNode neighs = new searchNode(eachNeig, moves, s);
                    infringe.insert(neighs);
                }

            }
        }


    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
