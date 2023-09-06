package Graph;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        new GRAPH();
        List<List<Integer>> alist = GRAPH.alist;
        System.out.println(alist);

        Deque<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        int n = GRAPH.n;
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(!vis.contains(i)) {
               q.add(i);

               while(!q.isEmpty()){
                   int size = q.size();
                   while(size-- > 0){
                       int parent = q.poll();
                       if(vis.contains(parent)) continue;
                       vis.add(parent);
                       answer.add(parent);
                       for(int child : alist.get(parent)) {
                           if(vis.contains(parent)) continue;
                           q.add(child);
                       }

                   }
               }
            }
        }

        System.out.println(answer);

    }

}
