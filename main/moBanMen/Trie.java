package moBanMen;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;

        public TrieNode(){
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        char[] chars = word.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if(cur.children[index] == null) {
                cur.children[index] = new TrieNode();;
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean searchInNode(String word, int start, TrieNode root) {
        if(start == word.length()) {
            return root.isEnd;
        }
        TrieNode cur = root;
        if(word.charAt(start) == '.') {
            for(TrieNode s : cur.children) {
                if(s != null && searchInNode(word, start + 1, s)) {
                    return true;
                }
            }
        }else{
            int index = word.charAt(start) - 'a';
            if(cur.children[index] == null) {
                return false;
            }else{
                return searchInNode(word, start+1, cur.children[index]);
            }
        }
        return false;
    }

    public boolean search(String word) {
        return searchInNode(word,0, root);
    }

}
