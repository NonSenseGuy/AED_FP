from queue import Queue

def bfs(graph,src, to):
    visited = [False for __ in range(len(graph))]
    dist = [0 for __ in range(len(graph))]
    q = Queue(maxsize=0)
    q.put(src)
    visited[src] = True
    dist[src] = 0
    while not q.empty():
        u = q.get()
        for e in graph[u]:
            if visited[e] is False:
                visited[e] = True
                dist[e] = dist[u] + 1
                q.put(e)
    return dist[to]-1

def main():
    testCases = int(input())
    for t in range(testCases):
        input()
        numV = int(input())
        graph = []
        for i in range(numV):
            line = [int(x) for x in input().split(" ") if int(x) is not i]
            graph.append(line)
        fromTo = [int(y) for y in input().split(" ")]
        print("{} {} {}".format(fromTo[0],fromTo[1],bfs(graph,fromTo[0],fromTo[1])))
        if t is not testCases-1:
            print()

if __name__ == "__main__":
    main()
