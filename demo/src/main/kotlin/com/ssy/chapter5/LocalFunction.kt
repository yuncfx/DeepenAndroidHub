package com.ssy.chapter5

class Vertex {
    val neighbors = arrayListOf<Vertex>()
}
class Graph {
    val vertices = arrayListOf<Vertex>()
}

/*
    local function 可以访问外部函数的局部变量
 */
fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(graph.vertices[0])
}