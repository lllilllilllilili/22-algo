const costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]];
const n = 4;
const solution = (n, costs) => {

    //# MST 구현하기

    //Minimun Spanning Tree(최소 신장 트리)
    //최소 신장 트리란? = Spanning Tree에서 간선들의 가중치(무게, 비용 등) 합이 최소인 트리

    //Spanning Tree란? = 최소의 간선으로 연결된 그래프(점과, 간선으로 구성된 구조)
    //최소 신장 트리란 = 최소의 간선으로 연결한 그래프이다.(단, 간선들의 가중치의 합이 최소인 경우) = 간선들의 가중치 합이 최소이면서 최소의 간선으로 연결된 그래프

    //# MST 구현 방법
    //1. Kruskal 알고리즘 사용
    //  1) 간선들의 가중치를 오름차순으로 정렬
    //  2) 사이클을 형성하는 간선을 제외하고 간선을 연결(합친다.)

    //1) 간선들의 가중치 오름차순으로 정렬
    const costsByIncrease = costs.sort((a,b) => a[2] - b[2]);

    const root = [];

    for(let i = 0; i < n; i ++){
        root[i] = i;
    }

    //2) 간선들 연결(단, 사이클이 형성되는 간선 제외)
    //사이클 형성 여부를 알아보는 방법
    //unionFind 알고리즘 사용

    let totalBridgeCost = 0; //결과값 = 간선들의 가중치 합
    for(let eachCost of costsByIncrease){

        const node1 = eachCost[0];
        const node2 = eachCost[1];
        const cost = eachCost[2];

        //node 연결
        union(node1, node2, cost);
    }

    function union (x, y, cost) {

        //연결 시 사이클 형성되는 간선 제외 조건 추가
        //Q1. 왜 사이클이 형성되는 간선을 제외해야 하는가?
        //- 사이클이 형성된다는 것은 노드에 도달하는 방법이 2가지 이상이 된다는 의미(즉, 최소 간선이 될 수 없다.)

        //Q. unionFind로 어떻게 사이클이 형성되지 않는지를 파악할 수 있을까?
        //-> 여러개의 노드를 선택해서 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘
        //같은 그래프에 속하지 않으면 union 한다. root가 다르다 = 같은 그래프가 아니다.
        const xRoot = findRoot(x);
        const yRoot = findRoot(y);

        //rootNode가 다른 경우
        if(xRoot !== yRoot){
            (root[y] = xRoot);
            totalBridgeCost += cost;
        }
    }

    //rootNode를 찾는 함수
    //parent가 자기 자신인 경우가 rootNode
    function findRoot (x) {
        return root[x] === x ? x : root[x] = findRoot(root[x]);
    }

    return totalBridgeCost;
}
