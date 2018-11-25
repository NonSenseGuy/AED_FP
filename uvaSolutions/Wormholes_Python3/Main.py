
def bellmanford(graph, numV, numE):
    INF = 1000000
    dist = []
    for i in range(numV):
        dist.append(INF)
    dist[0] = 0
    for i in range(1,numV):
        for j in range(numE):
            u = graph[j][0]
            v = graph[j][1]
            w = graph[j][2]
            if(dist[u] != INF and dist[u] + w < dist[v]):
                dist[v] = dist[u] + w
    for j in range(numE):
        u = graph[j][0]
        v = graph[j][1]
        w = graph[j][2]
        if(dist[u] != INF and dist[u] + w < dist[v]):
            return False
    return True

def main():
    testCases = int(input())
    for t in range(testCases):
        line = [int(x) for x in input().split(" ")]
        graph = [[0]*line[0] for i in range(3)]
        for i in range(line[1]):
            l = [int(x) for x in input().split(" ")]
            graph[i][0] = l[0]
            graph[i][1] = l[1]
            graph[i][2] = l[2]
        if(bellmanford(graph,line[0], line[1])):
            print("not possible")
        else:
            print("possible")


if __name__ == "__main__":
    main()
