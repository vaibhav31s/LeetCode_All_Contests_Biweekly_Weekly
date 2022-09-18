package Weekly.Contest311;

import java.util.Arrays;
import java.util.HashMap;

public class D {
    public static void main(String[] args) {
            String[]  words = {"abc","ab","bc","b"};
        Arrays.sort(words);
        HashMap<String, Integer> map = new HashMap<>();
        for(String s : words){
            for(int i = 0; i < s.length();i++){
                String pre = s.substring(0,i+1);
                map.put(pre, map.getOrDefault(pre,0)+1);
            }
        }
        int[] answer = new int[words.length];
        int k =0;
        for(String s : words){
            int count = 0 ;
            for(int i = 0; i < s.length();i++){
                String pre = s.substring(0,i+1);
                count += map.getOrDefault(pre,0);
            }
            answer[k] =  count;
        }
        System.out.println(Arrays.toString(answer));
    }
}
class Solution {
    public int[] sumPrefixScores(String[] words) {
        int i = 0;
        int[] answer = new int[words.length];
        Trie t = new Trie();
        for(String s : words){
            t.insert(s);
        }
        for(String s : words){
            answer[i++] = t.search(s);

        }
        return answer;
    }
}

class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.nodes[c - 'a'] == null){
                temp.nodes[c - 'a'] = new Node();

            }
            temp.count++;
            temp = temp.nodes[c - 'a'];
        }
        temp.count++;

        temp.flag = true;
    }

    public int search(String word) {
        Node temp = root;
        int a = 0;
        for(char c : word.toCharArray()){
            if(temp.nodes[c - 'a'] != null){
                temp = temp.nodes[c - 'a'];
                a+= temp.count;
            }else{
                return a;
            }
        }
        return a;
    }

    public boolean startsWith(String prefix) {
        Node temp = root;
        for(char c : prefix.toCharArray()){
            if(temp.nodes[c - 'a'] != null){
                temp = temp.nodes[c - 'a'];
            }else{
                return false;
            }
        }
        return true;
    }
}

class Node{
    Node[] nodes = new Node[26];
    boolean flag = false;
    int count = 0;
}
