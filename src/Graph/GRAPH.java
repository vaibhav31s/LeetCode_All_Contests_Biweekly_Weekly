package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GRAPH {
    static int[][] uni = new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
    static int n = 7;
    // 0 ->
    static List<List<Integer>> alist = Stream.generate(ArrayList<Integer>::new)
                .limit(n)
                .collect(Collectors.toList());
    public GRAPH() {


        for(int[] node : uni) {
            int first = node[0];
            int second = node[1];
            alist.get(first).add(second);
            alist.get(second).add(first);

        }


    }

}
