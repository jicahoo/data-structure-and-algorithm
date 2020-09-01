package com.jichao.ds;

import java.util.*;

public class Trie {

    static class Node {
        Map<Character, Node> chars = new HashMap<>();
        Boolean isWord = false;
    }

    public static void testCase(Node dict, String prefix) {
        System.out.println("==================");
        List<String> recommendWords = getWords(dict, prefix);
        for (String recommendWord : recommendWords) {
            System.out.println(recommendWord);
        }
    }
    public static void main(String[] args) {
        Node dict = loadDict();
        testCase(dict, "cat");
        testCase(dict, "any");
    }

    private static Node root = new Node();
    private static Node loadDict() {
        /*
                         c  h  b
                        /   |  |
                       a    e  e
                      /     |  t
                     t      l
                    /       |
                   e        o
                  /
                 g
                /
               o
              /
             r
            /
           y
           above drawing not good, since editor not good at column edit, try use rows to indicate the tree
        a - n - y.- w - h - e - r -e.
                  - o - n - e.
                  - t - h - i - n -g.
        b - e - t - t - e - r
        c - a - t.- e - g - o - r - y.
        g - o - o - d.- n - e - s -s.
              - d.
        h - e - l - l - o
        s - o - m - e.- t - h - i - n - g.
        t - e - s - t
            r - i - e.
                e - e.
                e - a - t.
        w - h - e - r - e.- i - n.
              - o.- m.
                  - s - e.
        */
        List<String> words = Arrays.asList(
                "cat",
                "category",
                "hello",
                "better",
                "good",
                "test",
                "where",
                "god",
                "goodness",
                "any",
                "anywhere",
                "some",
                "something",
                "tree",
                "trie",
                "treat",
                "who",
                "whom",
                "whose",
                "anyone",
                "anything"
        );

        for (String word : words) {
            putWord(root, word);
        }
        return root;
    }

    private static void putWord(Node root, String word) {
        int len = word.length();

        Node p = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            boolean contains = p.chars.containsKey(c);
            if (contains) {
                p = p.chars.get(c);
            } else {
                Node newNode = new Node();
                p.chars.put(c, newNode);
                p = newNode;
            }
        }
        p.isWord = true;
    }

    private static List<String> getWords(Node root, String inputStrPrefix) {

        Node curNode = root;
        int len = inputStrPrefix.length();
        int i = 0;
        for (i = 0; i < len; i++) {
            Character curChar = inputStrPrefix.charAt(i);
            if (!curNode.chars.containsKey(curChar)) {
               break;
            } else {
                curNode = curNode.chars.get(curChar);
            }
        }

        if (len > i) {
            //not found
            return Collections.emptyList();
        } else {
            int depth = 0;
            List<String> wordRecorder = new LinkedList<>();
            getWordsInternal(curNode, 0,  inputStrPrefix, wordRecorder);
            return wordRecorder;
        }

    }
    /*
      How to determine the limitation?  Don't try bigger than 1 firstly, it will become complex for current you.
      First step, try 0, then try 1 this is the easy way. More advanced way use VIM+Lines+Table to run the recursive call
      manually.
      Trie Tree: root
        a - n - y.- w - h - e - r -e.
                  - o - n - e.
                  - t - h - i - n -g.
        b - e - t - t - e - r
        c - a - t.- e - g - o - r - y.
        g - o - o - d.- n - e - s -s.
              - d.
        h - e - l - l - o
        s - o - m - e.- t - h - i - n - g.
        t - e - s - t
            r - i - e.
                e - e.
                e - a - t.
        w - h - e - r - e.- i - n.
              - o.- m.
                  - s - e.

      Input: any

      Run:
      getWords(root, "any") -
        > for(){...}
        - curNode:{isWord:true, {"w":_, "o":_, "t":_}
        > getWordsInternal(curNode, 0, "any", wordRecorder:[])
            > wordsRecorder.add(word)
            - wordRecorder:["any"]
            > getWordsInternal(curNode: {isWord:false, {"h":...}}, 1, "anyw", wordRecorder)
                > getWordsInternal(curNode: {isWord:false, {"e":_}}, 2, "anywh", wordRecorder)
                    > getWordsInternal(curNode: {isWord:false, {"r":_}}, 3, "anywhe", wordRecorder)
                        > getWordsInternal(curNode: {isWord:false, {"e":_}}, 4, "anywher", wordRecorder)
                        > depth > 3: return
            > getWordsInternal(curNode: {isWord:false, {"n":...}}, 1, "anyo", wordRecorder)
                > getWordsInternal(curNode: {isWord:false, {"e":...}}, 2, "anyon", wordRecorder)
                    > getWordsInternal(curNode: {isWord:true, {}}, 3, "anyone", wordRecorder)
                    > curNode.isWord = true: wordRecorders.add("anyone")
                        - wordRecorder:["any", "anyone"]
            > getWordsInternal(curNode: {isWord:false, {"n":...}}, 1, "anyt", wordRecorder)
                > similar with case anywhere.
                ...

     */
    private static void getWordsInternal(Node curNode, int depth, String word, List<String> wordsRecorder){
        if (depth > 3) {
            return;
        } else {
            Map<Character, Node> entries = curNode.chars;
            if (curNode.isWord)  {
                wordsRecorder.add(word);
            }
            for (Map.Entry<Character, Node> characterNodeEntry : entries.entrySet()) {
                getWordsInternal(characterNodeEntry.getValue(), depth + 1, word+characterNodeEntry.getKey(), wordsRecorder);
            }
        }
    }
}
