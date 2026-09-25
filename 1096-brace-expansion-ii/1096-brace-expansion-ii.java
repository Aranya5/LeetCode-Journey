import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> resultSet = parse(expression);
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private Set<String> parse(String expr) {
        Set<String> unionSet = new HashSet<>();
        Set<String> concatSet = new HashSet<>();
        concatSet.add(""); // Multiplicative identity

        int i = 0;
        int n = expr.length();

        while (i < n) {
            char ch = expr.charAt(i);

            if (ch == '{') {
                // Find matching closing brace
                int j = i, braceCount = 0;
                while (j < n) {
                    if (expr.charAt(j) == '{') braceCount++;
                    else if (expr.charAt(j) == '}') braceCount--;
                    if (braceCount == 0) break;
                    j++;
                }

                // Recursively parse the inner expression
                Set<String> inner = parse(expr.substring(i + 1, j));
                concatSet = cartesianProduct(concatSet, inner);
                i = j + 1;
            } else if (Character.isLowerCase(ch)) {
                // Read continuous letters as a single token or character-by-character
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLowerCase(expr.charAt(i))) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                Set<String> wordSet = Collections.singleton(sb.toString());
                concatSet = cartesianProduct(concatSet, wordSet);
            } else if (ch == ',') {
                // End of current concatenation group -> merge into unionSet
                unionSet.addAll(concatSet);
                concatSet = new HashSet<>();
                concatSet.add("");
                i++;
            }
        }

        unionSet.addAll(concatSet);
        return unionSet;
    }

    private Set<String> cartesianProduct(Set<String> set1, Set<String> set2) {
        Set<String> product = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                product.add(s1 + s2);
            }
        }
        return product;
    }
}