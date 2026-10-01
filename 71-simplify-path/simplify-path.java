class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String dir : components) {
            if (dir.equals("") || dir.equals(".")) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}