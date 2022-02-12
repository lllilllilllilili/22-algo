const costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]];
const n = 4;
const solution = (n, costs) => {
    //결과값 = 간선들의 가중치 합
    let totalBridgeCost = 0;

    //# MST 구현하기

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

    for(let eachCost of costsByIncrease){

        const node1 = eachCost[0];
        const node2 = eachCost[1];
        const cost = eachCost[2];

        //2) 간선들 연결(단, 사이클이 형성되는 간선 제외)
        union(node1, node2, cost);
    }

    function union (x, y, cost) {

        const xRoot = findRoot(x);
        const yRoot = findRoot(y);

        //연결 시 사이클 형성되는 간선 제외 조건 추가
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
