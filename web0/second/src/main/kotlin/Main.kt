fun main(args: Array<String>) {
    val tree = BinaryTree<Int>()

    tree.insert(1)
    tree.insert(2)
    tree.insert(3)
    tree.insert(4)
    tree.insert(5)
    tree.insert(6)
    tree.insert(7)

    println("Прямой обход:")
    var nodes = tree.directTraversal()
    for (node in nodes) {
        print("${node.value} ")
    }

    println("\nПоиск:")
    println(tree.search(1))
    println(tree.search(9))

    println("\nУдаление:")
    println(tree.delete(2))
    println(tree.delete(3))

}