package com.epam.rd.autocode.bstprettyprint;

public class PrettyPrintableTree implements PrintableTree {

    private static class Node {
        private final int data;
        private Node left = null;
        private Node right = null;

        public Node(int data) {
            this.data = data;
        }

        public int getSize() {
            return Integer.toString(data).length();
        }

    }

    private Node root = null;

    @Override
    public void add(int i) {
        root = add(root, i);
    }

    private Node add(Node node, int value) {
        if (node == null) {
            return new Node(value);
        } else if (value < node.data) {
            node.left = add(node.left, value);
        } else {
            node.right = add(node.right, value);
        }
        return node;
    }

    public String prettyPrint() {
        return prettyString() + "\n";
    }

    public String prettyString() {
        StringBuilder treeSB = new StringBuilder();
        prettyString(root, new StringBuilder("\n"), treeSB);
        return treeSB.substring(1);
    }

    private void prettyString(Node node, StringBuilder lineSB, StringBuilder treeSB)
    {
        if (node == null) return;

        int dataSize = node.getSize();
        int depth = lineSB.length();
        int i = "\n │".indexOf(lineSB.charAt(depth - 1));
        int j = (node.left != null ? 2 : 0) + (node.right != null ? 1 : 0);

        lineSB.append(" ".repeat(dataSize + 1));
        prettyString(node.left, lineSB, treeSB);
        lineSB.setLength(depth - 1);

        treeSB.append(lineSB);
        treeSB.append("\n┌└".charAt(i));
        treeSB.append(node.data);
        if (node.left != null || node.right != null) {
            treeSB.append(" ┐┘┤".charAt(j));
        }


        lineSB.append("\n│ ".charAt(i));
        lineSB.append(" ".repeat(dataSize));
        lineSB.append(" │ │ ".charAt(j));
        prettyString(node.right, lineSB, treeSB);
    }
}
