const costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]];
const n = 4;

const solution = (n, costs) => {

    if(n === 1) return 0;
    if(n === 2) return costs[0][2];

    //간선들의 가중치 합
    let totalBridgeCost = 0;

    //# MST 구현하기

    //# MST 구현 방법
    //1. Kruskal 알고리즘 사용
    //  1) 간선들의 가중치를 오름차순으로 정렬
    //  2) 사이클을 형성하는 간선을 제외하고 간선을 연결(합친다.)

    //1) 간선들의 가중치 오름차순으로 정렬
    const costsByIncrease = costs.sort((a,b) => a[2] - b[2]);

    //2) 사이클을 형성하는 간선을 제외하기 한다.
    //사이클 여부 알아보는 법 Union-find 알고리즘
    //2-1. 자기 자신이 root인 node(섬)를 만든다.
    const parent = [];
    for(let i = 0; i < n; i ++){
        parent[i] = i;
    }

    for(let eachInfo of costsByIncrease){

        const node1 = eachInfo[0];
        const node2 = eachInfo[1];
        const cost = eachInfo[2];

        //2) 간선들 연결(단, 사이클이 형성되는 간선 제외)
        union(node1, node2, cost);
    }

    function union (node1, node2, cost) {

        const node1Root = findRoot(node1);
        const node2Root = findRoot(node2);

        //연결 시 사이클 형성되는 간선 제외 조건 추가
        //연결하려는 node(섬)의 rootNode가 다른 경우에만 다리를 건설한다.(같으면 이미 연결되어 있다는 의미)
        if(node1Root !== node2Root){
            (parent[node2] = node1);
            totalBridgeCost += cost;
        }
    }

    //rootNode를 찾는 함수
    //parent가 자기 자신인 경우가 rootNode
    function findRoot (x) {
        return parent[x] === x ? x : findRoot(parent[x]);
    }

    return totalBridgeCost;
}
