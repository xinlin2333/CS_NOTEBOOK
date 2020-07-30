class Solution {
    HashMap<String,HashMap<String,Double>> map ;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        map = new HashMap<>();
        int idx =0;
        for(List<String> list: equations){
            map.computeIfAbsent(list.get(0),k->new HashMap<>()).put(list.get(1),values[idx]);
            map.computeIfAbsent(list.get(1),k->new HashMap<>()).put(list.get(0),1/values[idx]);
            idx++;
        }
        double[] res = new double[queries.size()];
        idx = 0;
        for(List<String> q:queries){
            res[idx] = dfs(q.get(0),q.get(1),1,new HashMap<String>());
            idx++;
        }
        return res;

    }
    public double[] dfs(String s,String e ,double values,HashMap<String> visit){
        if(!map.containsKey(s)){
            return -1;
        }
        if(visit.contains(s)){
            return -1;
        }
        HashMap<String,Double> next = map.get(s);
        visit.add(s);
        for(String k: next.keySet()){
            double res = dfs(k,e,next.get(k)*values,visit);
            if(res !=-1){
                return res;
            }
        }
        visit.remove(s);
        return -1;
    }
}