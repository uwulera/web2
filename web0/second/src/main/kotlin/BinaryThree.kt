class Node<T : Comparable<T>>(var value: T) {
    var left: Node<T>? = null
    var right: Node<T>? = null
}

class BinaryTree<T : Comparable<T>> {
    var root: Node<T>? = null

    fun insert(value: T) {
        val node = Node(value)
        if (root == null) {
            root = node
        } else {
            var current = root
            var parent: Node<T>? = null
            while (true) {
                parent = current
                if (value < current!!.value) {
                    current = current.left
                    if (current == null) {
                        parent?.left = node
                        return
                    }
                } else {
                    current = current.right
                    if (current == null) {
                        parent?.right = node
                        return
                    }
                }
            }
        }
    }

    fun search(value: T): Boolean {
        var current = root
        while (current != null) {
            if (current.value == value) {
                return true
            } else if (value < current.value) {
                current = current.left
            } else {
                current = current.right
            }
        }
        return false
    }

    fun delete(value: T): Boolean {
        var current = root
        var parent: Node<T>? = null
        var isLeftChild = true
        while (current != null && current.value != value) {
            parent = current
            if (value < current.value) {
                current = current.left
                isLeftChild = true
            } else {
                current = current.right
                isLeftChild = false
            }
        }
        if (current == null) {
            return false
        }
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null
            } else if (isLeftChild) {
                parent!!.left = null
            } else {
                parent!!.right = null
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right
            } else if (isLeftChild) {
                parent!!.left = current.right
            } else {
                parent!!.right = current.right
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left
            } else if (isLeftChild) {
                parent!!.left = current.left
            } else {
                parent!!.right = current.left
            }
        } else {
            val successor = getSuccessor(current)
            if (current == root) {
                root = successor
            } else if (isLeftChild) {
                parent!!.left = successor
            } else {
                parent!!.right = successor
            }
            successor.left = current.left
        }
        return true
    }

    private fun getSuccessor(node: Node<T>): Node<T> {
        var current = node.right
        var successorParent: Node<T>? = node
        var successor = node
        while (current != null) {
            successorParent = successor
            successor = current
            current = current.left
        }
        if (successor != node.right) {
            successorParent!!.left = successor.right
            successor.right = node.right
        }
        return successor
    }

    fun directTraversal(): List<Node<T>> {
        val result = mutableListOf<Node<T>>()
        directTraversal(root, result)
        return result
    }

    private fun directTraversal(node: Node<T>?, result: MutableList<Node<T>>) {
        if (node != null) {
            result.add(node)
            directTraversal(node.left, result)
            directTraversal(node.right, result)
        }
    }
}