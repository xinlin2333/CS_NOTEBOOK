package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-04 10:57
 */
 class PrefixTree {

    private TriNode root;
    /** Initialize your data structure here. */
    public void TrieTree() {
        root = new TriNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TriNode node = root;
        for(int i=0;i<word.length();i++){
            char currentChar = word.charAt(i);
            if(!node.containsKey(currentChar)){
                node.put(currentChar,new TriNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    private TriNode searchPrefix(String word){
        TriNode node = root;
        for(int i=0;i<word.length();i++){
            char currPre = word.charAt(i);
            if(node.containsKey(currPre)){
                node = node.get(currPre);
            }else{
                return null;
            }
        }
        return node;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TriNode node = searchPrefix(word);
        return node !=null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TriNode node = searchPrefix(prefix);
        return node!=null;
    }
}
class TriNode{
    private boolean isEnd;
    private final int R =26;
    private TriNode[] links;
    public TriNode(){
        links = new TriNode[R];
    }
    public boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    public TriNode get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch,TriNode node){
        links[ch-'a'] = node;
    }
    public void setEnd(){
        isEnd = true;
    }
    public boolean isEnd(){
        return isEnd;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
